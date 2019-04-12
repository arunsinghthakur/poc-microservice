package com.safexpress.propeli.messaging.configuration;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractDeserializer<T> implements Deserializer<T> {

	private Class<T> clazz;

	public AbstractDeserializer(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public T deserialize(String topic, byte[] bytes) {
		ObjectMapper mapper = new ObjectMapper();
		T data = null;
		try {
			data = mapper.readValue(bytes, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			// Logger here....
		}
		return data;
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public void close() {
	}
}
