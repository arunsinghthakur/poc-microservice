package com.safexpress.propeli.redis.manager;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public abstract class AbstractCacheManager<T> implements CacheManager<T> {

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	public void putMap(Object redisKey, Object key, T data) {
		getHashOperation().put(redisKey, key, data);
	}

	@Override
	public T getMapAsSingleEntry(Object redisKey, Object key) {
		return getHashOperation().get(redisKey, key);
	}

	@Override
	public Map<Object, T> getMapAsAll(Object redisKey) {
		return getHashOperation().entries(redisKey);
	}

	@Override
	public void putValue(Object key, T value) {
		getValueOperations().set(key, value);
	}

	@Override
	public void putValueWithExpireTime(Object key, T value, long timeout, TimeUnit unit) {
		getValueOperations().set(key, value, timeout, unit);
	}

	@Override
	public T getValue(Object key) {
		return getValueOperations().get(key);
	}

	@Override
	public List<T> getMultiValues(Set<Object> keys) {
		return getValueOperations().multiGet(keys);
	}

	@Override
	public void setExpire(Object key, long timeout, TimeUnit unit) {
		redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public Set<Object> getKeysByRegex(String keyRegex) {
		return redisTemplate.keys(keyRegex);
	}

	@Override
	public void deleteKeys(Set<Object> keys) {
		redisTemplate.delete(keys);
	}

	private HashOperations<Object, Object, T> getHashOperation() {
		return redisTemplate.opsForHash();
	}

	private ValueOperations<Object, T> getValueOperations() {
		return (ValueOperations<Object, T>) redisTemplate.opsForValue();
	}

}
