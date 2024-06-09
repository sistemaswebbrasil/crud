package br.com.siswbrasil.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface BaseRepository<T,ID> extends PanacheRepository<T> {


}
