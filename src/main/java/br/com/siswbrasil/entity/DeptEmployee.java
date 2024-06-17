package br.com.siswbrasil.entity;

import java.util.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "dept_emp")
@IdClass(DeptManagerPk.class)
public class DeptEmployee extends PanacheEntityBase {

	@Id
	@Column(name = "emp_no")
	protected Long empNo;

	@Id
	@Column(name = "dept_no")
	protected String deptNo;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;

//	@JoinColumn(name = "dept_no")
//	@ManyToOne
//	private Department department;

}
