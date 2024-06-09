package br.com.siswbrasil.service;

import br.com.siswbrasil.entity.DeptManager;
import br.com.siswbrasil.entity.DeptManagerPk;
import br.com.siswbrasil.repository.DepartManagerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DepartManagerService {

	@Inject
	private DepartManagerRepository repository;

	public DeptManager findById(DeptManagerPk id) {
		return repository.findById(id);

	}

}
