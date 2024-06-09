package br.com.siswbrasil.service;

import br.com.siswbrasil.entity.Department;
import br.com.siswbrasil.repository.DepartmentRepository;
import io.vertx.ext.web.handler.HttpException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DepartmentService implements BaseService<Department, String> {

	@Inject
	private DepartmentRepository repository;

	@Override
	public Department findById(String id) {
		return repository.findByIdOptional(id).orElseThrow(() -> new NotFoundException());
	}

	@Override
	@Transactional
	public Department create(Department form) {

		System.out.println(repository.checkIsUnique(form));
		if(repository.checkIsUnique(form) > 0) {
			throw new BadRequestException("Registro já existe");
		}
		
		repository.persist(form);
		return form;

	}

	@Override
	public Department update(String id, Department form) {
		var entity = findById(id);
		entity.setDeptName(form.getDeptName());
		repository.persist(form);
		return entity;
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		var entity = findById(id);
		repository.delete(entity);
	}

}
