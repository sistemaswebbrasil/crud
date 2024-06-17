package br.com.siswbrasil.repository;

import org.apache.commons.lang3.StringUtils;

import br.com.siswbrasil.entity.Employee;
import br.com.siswbrasil.filter.EmployeeFilter;
import br.com.siswbrasil.util.PaginatedResponse;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort.Direction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository implements BaseRepository<Employee, Long> {

	public PaginatedResponse<Employee> findAllWithFilterAndPage(EmployeeFilter filter, Integer pageIndex,
			Integer pageSize, String[] sortList) {

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

		var query = this.findAll(sort).page(Page.of(pageIndex, pageSize));
		

		if (StringUtils.isNotBlank(filter.getFirstName())) {
			 query.filter("firstName", Parameters.with("firstName", filter.getFirstName().concat("%")));
		}
		
		if (StringUtils.isNotBlank(filter.getLastName())) {
			query.filter("filterLastName", Parameters.with("lastName", filter.getLastName().concat("%").toUpperCase()));
		}

//		"empNo = ?1 and deptNo = ?2 "
		
//		if (filter.getCdUnidade() != null && !filter.getCdUnidade().isEmpty()) {
//			queryPessoas.filter("cdUnidade", Parameters.with("cdUnidade", filter.getCdUnidade()));
//		}
//
//		if (!StringUtil.isNullOrEmpty(filter.getNrCpf())) {
//			queryPessoas.filter("nrCpf", Parameters.with("nrCpf", filter.getNrCpf()));
//		}	

		var response = new PaginatedResponse<Employee>();
		response.setTotal(query.count());
		response.setContent(query.stream().toList());
		response.setPages(query.pageCount());
		response.setPageSize(pageSize);

		return response;
	}

}
