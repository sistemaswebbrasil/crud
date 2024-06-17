package br.com.siswbrasil.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.siswbrasil.entity.Employee;
import br.com.siswbrasil.filter.EmployeeFilter;
import br.com.siswbrasil.repository.EmployeeRepository;
import br.com.siswbrasil.service.EmployeeService;
import br.com.siswbrasil.util.PaginatedResponse;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Tag(name = "EmployeeResource", description = "Recursos da tabela employees.employees")
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/employees")
public class EmployeeResource extends CrudResourceImpl<Employee, Long> {

	@Inject
	private EmployeeRepository repository;

	@Inject
	private EmployeeService service;

	@PostConstruct
	public void init() {
		super.repository = repository;
		super.service = service;
	}

	@Operation(summary = "findAllPageable", description = "Lista todos os itens com daods de paginação e ordenação")
	@GET
	@Path("/filter")
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedResponse<Employee> findAllWithFilterAndPage(
			@BeanParam EmployeeFilter filter ,@QueryParam("page") @DefaultValue("0") Integer pageIndex,
			@QueryParam("size") @DefaultValue("20") Integer pageSize, @QueryParam("sort") String... sortList) {		
		return repository.findAllWithFilterAndPage(filter,pageIndex,pageSize,sortList);
	}

}
