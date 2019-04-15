package com.safexpress.propeli.messaging.consumer;

import java.io.Serializable;

public abstract class AbstractMessageConsumer<T extends Serializable> {

	public abstract void consume(T message);
}
