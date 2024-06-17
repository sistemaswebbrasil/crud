package br.com.siswbrasil.resource;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.openapi.annotations.Operation;

import br.com.siswbrasil.repository.BaseRepository;
import br.com.siswbrasil.service.BaseService;
import br.com.siswbrasil.util.PaginatedResponse;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

public abstract class CrudResourceImpl<T, ID> implements CrudResource<T, ID> {

	protected BaseRepository<T, ID> repository;

	protected BaseService<T, ID> service;

	@Operation(summary = "findAll", description = "Lista todos os itens (Limitando inicialmente em 20 registros)")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<T> findAll(@QueryParam("page") @DefaultValue("0") int pageIndex,
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

	@Operation(summary = "create", description = "Cria um novo registro")
	@POST
	@Override
	public T create(@Valid T form) {
		return service.create(form);
	}

	@Operation(summary = "update", description = "Atualiza o registro")
	@PUT
	@Path("/{id}")
	@Override
	public T update(@PathParam("id") ID id, @Valid T form) {
		return service.update(id, form);
	}

	@Operation(summary = "deleteById", description = "Remove pelo id")
	@DELETE
	@Path("/{id}")
	@Override
	public void deleteById(@PathParam("id") ID id) {
		service.deleteById(id);
	}

	@Operation(summary = "findAllPageable", description = "Lista todos os itens com daods de paginação e ordenação")
	@GET
	@Path("/page")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public PaginatedResponse<T> findAllPageable(@QueryParam("page") @DefaultValue("0") Integer pageIndex,
			@QueryParam("size") @DefaultValue("20") Integer pageSize, @QueryParam("sort") String... sortList) {

		Sort sort = Sort.by();
		for (String item : sortList) {
			if (StringUtils.containsAnyIgnoreCase(item, ",asc")) {
				item = StringUtils.replaceIgnoreCase(item, ",asc", "");
				sort.and(item, Direction.Ascending);
			} else if (StringUtils.containsAnyIgnoreCase(item, ",desc")) {
				item = StringUtils.replaceIgnoreCase(item, ",desc", "");
				sort.and(item, Direction.Descending);
			} else {
				sort.and(item, Direction.Ascending);
			}
		}

		var query = repository.findAll(sort).page(Page.of(pageIndex, pageSize));

		var response = new PaginatedResponse<T>();
		response.setTotal(query.count());
		response.setContent(query.stream().toList());
		response.setPages(query.pageCount());
		response.setPageSize(pageSize);

		return response;
	}

}
