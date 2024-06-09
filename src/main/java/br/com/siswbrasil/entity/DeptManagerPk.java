package br.com.siswbrasil.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptManagerPk {

	@Column(name = "emp_no")
	protected Long empNo;

	@Column(name = "dept_no")
	protected String deptNo;

}
