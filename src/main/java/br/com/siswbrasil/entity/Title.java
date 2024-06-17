package br.com.siswbrasil.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "titles")
@IdClass(TitlePk.class)
public class Title extends PanacheEntityBase {

	@EqualsAndHashCode.Include
	@Id
	@Size(min = 4, max = 4)
	@Column(name = "emp_no")
	private Long empNo;

	@Id
	@Size(max = 40)
	@NotBlank
	@Column(name = "title")
	private String title;

	@Id
	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;

	@JsonIgnore
	@JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
	@ManyToOne
	private Employee employee;

}
