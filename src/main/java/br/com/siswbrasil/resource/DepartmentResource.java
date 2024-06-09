package br.com.siswbrasil.resource;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.siswbrasil.entity.Department;
import br.com.siswbrasil.repository.DepartmentRepository;
import br.com.siswbrasil.service.DepartmentService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Tag(name = "Department", description = "Recursos da tabela employees.departments")
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/departments")
public class DepartmentResource extends CrudResourceImpl<Department, String> {

	@Inject
	private DepartmentRepository repository;

	@Inject
	private DepartmentService service;

	@PostConstruct
	public void init() {
		super.repository = repository;
		super.service = service;
	}

}
