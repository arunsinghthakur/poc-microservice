package com.safexpress.propeli.cache.manager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import com.safexpress.propeli.redis.manager.AbstractCacheManager;
import com.safexpress.propeli.security.exception.CacheManagerPrincipalIdNotAssignedException;
import com.safexpress.propeli.security.exception.PrincipalIdNullException;
import com.safexpress.propeli.security.exception.PrincipalInstanceException;

@Service
public class CredentialCacheManagerImpl extends AbstractCacheManager<Object> implements CredentialCacheManager {

	private final ConcurrentMap<String, Cache<Object, Object>> cachesMap = new ConcurrentHashMap<>();

	@Override
	public Cache<Object, Object> getCache(String name) throws CacheException {
		Cache<Object, Object> cache = (Cache<Object, Object>) cachesMap.get(name);

		if (cache == null) {
			cachesMap.put(name, new RedisCache(this, name));
		}
		return cache;
	}

	public class RedisCache implements Cache<Object, Object> {

		private String keyPrefix = "shiro:cache:";
		private String principalIdFieldName = "username";
		private final long expires = 30;
		private TimeUnit timeUnitInDays = TimeUnit.DAYS;

		private AbstractCacheManager<Object> cacheManager;

		public RedisCache(AbstractCacheManager<Object> cacheManager, String name) {
			this.cacheManager = cacheManager;
			this.keyPrefix = this.keyPrefix + name + ":";
		}

		@Override
		public void clear() throws CacheException {
			cacheManager.deleteKeys(cacheManager.getKeysByRegex(keyPrefix));
		}

		@Override
		public Object get(Object key) throws CacheException {
			return getValue(getRedisCacheKey(key));
		}

		@Override
		public Set<Object> keys() {
			return cacheManager.getKeysByRegex(keyPrefix);
		}

		@Override
		public Object put(Object key, Object value) throws CacheException {
			putValueWithExpireTime(getRedisCacheKey(key), value, expires, timeUnitInDays);
			return value;
		}

		@Override
		public Object remove(Object key) throws CacheException {
			Object redisKey = getRedisCacheKey(key);
			Object value = getValue(redisKey);
			Set<Object> keys = new HashSet<>();
			keys.add(redisKey);
			cacheManager.deleteKeys(keys);
			return value;
		}

		@Override
		public int size() {
			return cacheManager.getKeysByRegex(keyPrefix).size();
		}

		@Override
		public Collection<Object> values() {
			return getMultiValues(cacheManager.getKeysByRegex(keyPrefix));
		}

		private Object getRedisCacheKey(Object key) {
			if (key == null) {
				return null;
			}
			return (Object) (this.keyPrefix + getStringRedisKey(key));
		}

		private String getStringRedisKey(Object key) {
			String redisKey;
			if (key instanceof PrincipalCollection) {
				redisKey = getRedisKeyFromPrincipalIdField((PrincipalCollection) key);
			} else {
				redisKey = key.toString();
			}
			return redisKey;
		}

		private String getRedisKeyFromPrincipalIdField(PrincipalCollection key) {
			Object principalObject = (Object) key.getPrimaryPrincipal();
			if (principalObject instanceof String) {
				return principalObject.toString();
			}
			Method pincipalIdGetter = getPrincipalIdGetter(principalObject);
			return getIdObj(principalObject, pincipalIdGetter);
		}

		private String getIdObj(Object principalObject, Method pincipalIdGetter) {
			String redisKey;
			try {
				Object idObj = (Object) pincipalIdGetter.invoke(principalObject);
				if (idObj == null) {
					throw new PrincipalIdNullException(principalObject.getClass(), this.principalIdFieldName);
				}
				redisKey = idObj.toString();
			} catch (IllegalAccessException e) {
				throw new PrincipalInstanceException(principalObject.getClass(), this.principalIdFieldName, e);
			} catch (InvocationTargetException e) {
				throw new PrincipalInstanceException(principalObject.getClass(), this.principalIdFieldName, e);
			}
			return redisKey;
		}

		private Method getPrincipalIdGetter(Object principalObject) {
			Method pincipalIdGetter = null;
			String principalIdMethodName = this.getPrincipalIdMethodName();
			try {
				pincipalIdGetter = principalObject.getClass().getMethod(principalIdMethodName);
			} catch (NoSuchMethodException e) {
				throw new PrincipalInstanceException(principalObject.getClass(), this.principalIdFieldName);
			}
			return pincipalIdGetter;
		}

		private String getPrincipalIdMethodName() {
			if (this.principalIdFieldName == null || "".equals(this.principalIdFieldName)) {
				throw new CacheManagerPrincipalIdNotAssignedException();
			}
			return "get" + this.principalIdFieldName.substring(0, 1).toUpperCase()
					+ this.principalIdFieldName.substring(1);
		}
	}
}
