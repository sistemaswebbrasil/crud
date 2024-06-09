package br.com.siswbrasil.resource;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

import br.com.siswbrasil.repository.BaseRepository;
import br.com.siswbrasil.service.BaseService;
import io.quarkus.panache.common.Page;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

public abstract class BaseResourceImpl<T, ID> implements BaseResource<T, ID> {

	protected BaseRepository<T, ID> repository;

	protected BaseService<T, ID> service;

	@Operation(summary = "findAll", description = "Lista todos os itens (Limitando inicialmente em 20 registros)")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<T> findAll(  
			@QueryParam("page") @DefaultValue("0") int pageIndex,
			@QueryParam("size") @DefaultValue("20") int pageSize) {
		return repository.findAll().page(Page.of(pageIndex, pageSize)).stream().toList();
	}

	@Operation(summary = "findById", description = "Lista registro pelo id")
	@GET
	@Path("/{id}")
	@Override
	public T findById(@PathParam("id") ID id) {
		return service.findById(id);
	}

}

