package br.com.siswbrasil.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@FilterDef(name = "firstName", parameters = @ParamDef(name = "firstName", type = String.class))
@Filter(name = "firstName", condition = "first_name LIKE :firstName")

@FilterDef(name = "filterLastName", parameters = @ParamDef(name = "lastName", type = String.class))
@Filter(name = "filterLastName", condition = "last_name LIKE :lastName")

@FilterDef(name = "search", parameters = {@ParamDef(name = "firstName", type = String.class),@ParamDef(name = "lastName", type = String.class)})
@Filter(name = "search", condition = "last_name LIKE :lastName or first_name LIKE :firstName")

@FilterDef(name = "deptNo", parameters = {@ParamDef(name = "deptNo", type = String.class),@ParamDef(name = "deptNo", type = String.class)})
@Filter(name = "deptNo", condition = "emp_no in ( SELECT emp_no FROM dept_emp WHERE emp_no = emp_no AND dept_no = :deptNo)")

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_no")
	private Long empNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name = "hire_date")
	private Date hireDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Title> titles = new ArrayList<>();

	@ManyToMany(mappedBy = "employees")
	private List<Department> department = new ArrayList<>();

	@ManyToMany(mappedBy = "managers")
	private List<Department> managers = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Salary> salaries = new ArrayList<>();

}
