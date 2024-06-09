package br.com.siswbrasil.repository;

import br.com.siswbrasil.entity.Department;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class DepartmentRepository implements BaseRepository<Department, String> {

	@Inject
	EntityManager em;

	public Long checkIsUnique(Department form) {
		String sql = """
				SELECT
				    COUNT(*)
				FROM
				    employees.departments
				WHERE
					(
				    dept_no = :deptNo
				        OR dept_name =:deptName
					)
					""";

		return (Long) em
				.createNativeQuery(sql, Long.class)
				.setParameter("deptNo", form.getDeptNo())
				.setParameter("deptName", form.getDeptName()).getSingleResult();

	}

}
