package com.safexpress.propeli.redis.manager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface CacheManager<T> {

	void setExpire(String key, long timeout, TimeUnit unit);

	T getValue(String key);

	void putValueWithExpireTime(String key, T value, long timeout, TimeUnit unit);

	void putValue(String key, T value);

	Map<Object, T> getMapAsAll(String redisKey);

	T getMapAsSingleEntry(String redisKey, Object key);

	void putMap(String redisKey, Object key, T data);
}
