package br.com.siswbrasil.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "salaries")
@IdClass(SalaryPk.class)
public class Salary extends PanacheEntityBase {

	@EqualsAndHashCode.Include
	@Id
	@Size(min = 4, max = 4)
	@Column(name = "emp_no")
	private Long empNo;

	@Id
	@Temporal(TemporalType.DATE)
	@Column(name = "from_date")
	private Date fromDate;
	
	@NotNull
	@Column(name = "salary")
	private Long salary;

	@Temporal(TemporalType.DATE)
	@Column(name = "to_date")
	private Date toDate;

	@JsonIgnore
	@JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
	@ManyToOne
	private Employee employee;

}
