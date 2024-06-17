package br.com.siswbrasil.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryPk {

	@Column(name = "emp_no")
	protected Long empNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "from_date")
	protected Date fromDate;

}
 