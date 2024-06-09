package br.com.siswbrasil.resource;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

public interface BaseResource<T, ID> {

	@Operation(summary = "findAll", description = "Lista todos os itens")
	List<T> findAll(int pageIndex, int pageSize);

	@Operation(summary = "findById", description = "Lista registro pelo id")
	T findById(@Parameter(name = "id", description = "ID do recurso", required = true) ID id);

}
