package br.com.siswbrasil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "departments")
public class Department {
	
	@EqualsAndHashCode.Include
	@Id
	@Size(min = 4,max = 4)
	@Column(name = "dept_no")
	private String deptNo;

	@Size(max = 40)
	@NotBlank
	@Column(name = "dept_name")
	protected String deptName;
}
