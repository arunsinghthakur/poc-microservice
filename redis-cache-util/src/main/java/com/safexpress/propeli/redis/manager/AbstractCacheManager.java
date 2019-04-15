package com.safexpress.propeli.redis.manager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class AbstractCacheManager<T> implements CacheManager<T> {

	@Autowired
	@Lazy
	private RedisTemplate<String, T> redisTemplate;

	@Override
	public void putMap(String redisKey, Object key, T data) {
		getHashOperation().put(redisKey, key, data);
	}

	@Override
	public T getMapAsSingleEntry(String redisKey, Object key) {
		return getHashOperation().get(redisKey, key);
	}

	@Override
	public Map<Object, T> getMapAsAll(String redisKey) {
		return getHashOperation().entries(redisKey);
	}

	@Override
	public void putValue(String key, T value) {
		getValueOperations().set(key, value);
	}

	@Override
	public void putValueWithExpireTime(String key, T value, long timeout, TimeUnit unit) {
		getValueOperations().set(key, value, timeout, unit);
	}

	@Override
	public T getValue(String key) {
		return getValueOperations().get(key);
	}

	@Override
	public void setExpire(String key, long timeout, TimeUnit unit) {
		redisTemplate.expire(key, timeout, unit);
	}

	private HashOperations<String, Object, T> getHashOperation() {
		return redisTemplate.opsForHash();
	}

	private ValueOperations<String, T> getValueOperations() {
		return redisTemplate.opsForValue();
	}

}
