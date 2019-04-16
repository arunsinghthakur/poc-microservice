package com.safexpress.propeli.redis.manager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface CacheManager<T> {

	void setExpire(Object key, long timeout, TimeUnit unit);

	T getValue(Object key);

	void putValueWithExpireTime(Object key, T value, long timeout, TimeUnit unit);

	void putValue(Object key, T value);

	Map<Object, T> getMapAsAll(Object redisKey);

	T getMapAsSingleEntry(Object redisKey, Object key);

	void putMap(Object redisKey, Object key, T data);
}
