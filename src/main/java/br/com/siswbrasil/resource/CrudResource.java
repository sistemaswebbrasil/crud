package br.com.siswbrasil.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import jakarta.validation.Valid;

public interface CrudResource<T, ID> extends BaseResource<T, ID> {

	@Operation(summary = "create", description = "Cria um novo registro")
	T create(@Valid T form);

	@Operation(summary = "update", description = "Atualiza o registro")
	T update(@Parameter(name = "id", description = "ID do recurso", required = true) ID id, @Valid T form);

	@Operation(summary = "deleteById", description = "Remove o registro pelo id")
	void deleteById(@Parameter(name = "id", description = "ID do recurso", required = true) ID id);

}
