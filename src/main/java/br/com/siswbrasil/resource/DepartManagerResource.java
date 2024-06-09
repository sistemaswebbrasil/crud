package br.com.siswbrasil.resource;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.siswbrasil.entity.DeptManager;
import br.com.siswbrasil.entity.DeptManagerPk;
import br.com.siswbrasil.repository.DepartManagerRepository;
import br.com.siswbrasil.service.DepartManagerService;
import br.com.siswbrasil.util.PaginatedResponse;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Tag(name = "DepartManagerResource")
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/depart-manager")
public class DepartManagerResource {

	@Inject
	private DepartManagerRepository repository;

	@Inject
	private DepartManagerService service;

	@Operation(summary = "findAll", description = "Lista todos os itens (Limitando inicialmente em 20 registros)")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DeptManager> findAll(
			@QueryParam("page") @DefaultValue("0") int pageIndex,
			@QueryParam("size") @DefaultValue("20") int pageSize,
			@QueryParam("sort") String... sortList
			) {
		
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
		
		return repository.findAll(sort).page(Page.of(pageIndex, pageSize)).stream().toList();
	}

	@Operation(summary = "findById", description = "Lista registro pelo id")
	@GET
	@Path("/{empNo}/{deptNo}")

	public DeptManager findById(@PathParam("empNo") Long empNo, @PathParam("deptNo") String deptNo) {
		return service.findById(new DeptManagerPk(empNo, deptNo));
	}

	@Operation(summary = "findAll", description = "Lista todos os itens (Limitando inicialmente em 20 registros)")
	@GET
	@Path("/page")
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedResponse<DeptManager> findAllPageable(
			@QueryParam("page") @DefaultValue("0") Integer pageIndex,
			@QueryParam("size") @DefaultValue("20") Integer pageSize,
			@QueryParam("sort") String... sortList) {

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

		var query = repository
				.findAll(sort)
				.page(Page.of(pageIndex, pageSize));

		var response = new PaginatedResponse<DeptManager>();
		response.setTotal(query.count());
		response.setContent(query.stream().toList());
		response.setPages(query.pageCount());
		response.setPageSize(pageSize);

		return response;
	}
}
