package br.com.siswbrasil.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface BaseRepository<T, ID> extends PanacheRepositoryBase<T, ID> {

}
