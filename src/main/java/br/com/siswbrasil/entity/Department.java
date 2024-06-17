package br.com.siswbrasil.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	@Size(min = 4, max = 4)
	@Column(name = "dept_no")
	private String deptNo;

	@Size(max = 40)
	@NotBlank
	@Column(name = "dept_name")
	protected String deptName;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "dept_manager", joinColumns = @JoinColumn(name = "dept_no"), inverseJoinColumns = @JoinColumn(name = "emp_no"))
	private List<Employee> managers = new ArrayList<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "dept_emp", joinColumns = @JoinColumn(name = "dept_no"), inverseJoinColumns = @JoinColumn(name = "emp_no"))
	private List<Employee> employees = new ArrayList<>();
	
}
