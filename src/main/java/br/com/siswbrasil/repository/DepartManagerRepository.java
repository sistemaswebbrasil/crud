package br.com.siswbrasil.repository;

import br.com.siswbrasil.entity.DeptManager;
import br.com.siswbrasil.entity.DeptManagerPk;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DepartManagerRepository implements PanacheRepository<DeptManager> {

	public DeptManager findById(DeptManagerPk id) {
		PanacheQuery<DeptManager> query = this.find("empNo = ?1 and deptNo = ?2 ", id.getEmpNo(), id.getDeptNo());
		var entity = query.firstResult();
		if (entity == null) {
			throw new NotFoundException();
		}
		return entity;
	}

}
