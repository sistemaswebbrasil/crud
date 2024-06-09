package br.com.siswbrasil.service;

public interface BaseService<T, ID> {

	T findById(ID id);

	T create(T form);

	T update(ID id, T form);

	void deleteById(ID id);

}