package br.com.siswbrasil.filter;

import jakarta.validation.constraints.Size;
import jakarta.ws.rs.QueryParam;
import lombok.Data;

@Data
public class EmployeeFilter {

	@QueryParam(value = "firstName")
	private String firstName;

	@QueryParam(value = "lastName")
	private String lastName;
	
	@QueryParam(value = "search")
	private String search;
	
	@QueryParam(value = "deptNo")
	@Size(min = 4, max = 4)
	private String deptNo;

}
