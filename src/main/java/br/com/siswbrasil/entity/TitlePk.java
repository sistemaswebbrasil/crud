package br.com.siswbrasil.entity;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitlePk {

	@Column(name = "emp_no")
	protected Long empNo;

	@Column(name = "title")
	protected String title;

	@Column(name = "from_date")
	protected Date fromDate;

}
