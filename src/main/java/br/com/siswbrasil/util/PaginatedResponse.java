package br.com.siswbrasil.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponse<T> {

	private Long total;
	private Integer pageSize;
	private Integer pages;
	private List<T> content;
}
