package com.safexpress.propeli.redis.manager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractCacheManager<T> implements CacheManager<T> {

	private RedisTemplate<String, T> redisTemplate;
	private HashOperations<String, Object, T> hashOperation;
	private ValueOperations<String, T> valueOperations;

	@Autowired
	public AbstractCacheManager(RedisTemplate<String, T> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperation = redisTemplate.opsForHash();
		this.valueOperations = redisTemplate.opsForValue();
	}

	@Override
	public void putMap(String redisKey, Object key, T data) {
		hashOperation.put(redisKey, key, data);
	}

	@Override
	public T getMapAsSingleEntry(String redisKey, Object key) {
		return hashOperation.get(redisKey, key);
	}

	@Override
	public Map<Object, T> getMapAsAll(String redisKey) {
		return hashOperation.entries(redisKey);
	}

	@Override
	public void putValue(String key, T value) {
		valueOperations.set(key, value);
	}

	@Override
	public void putValueWithExpireTime(String key, T value, long timeout, TimeUnit unit) {
		valueOperations.set(key, value, timeout, unit);
	}

	@Override
	public T getValue(String key) {
		return valueOperations.get(key);
	}

	@Override
	public void setExpire(String key, long timeout, TimeUnit unit) {
		redisTemplate.expire(key, timeout, unit);
	}

}
