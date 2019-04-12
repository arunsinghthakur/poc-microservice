package com.safexpress.propeli.messaging.configuration;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractSerializer<T> implements Serializer<T> {

	@Override
	public byte[] serialize(String topic, T data) {
		byte[] bytes = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			bytes = objectMapper.writeValueAsString(data).getBytes();
		} catch (Exception e) {
			e.printStackTrace();
			// Logger statement here...
		}
		return bytes;
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public void close() {
	}

}
