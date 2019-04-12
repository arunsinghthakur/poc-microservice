package com.safexpress.propeli.common.dao;

import java.util.List;

public interface AbstractRepository<T, Serializable> {

	T get();

	void save(T entity);

	T update(T entity);

	void delete();

	List<T> getAll();
}
