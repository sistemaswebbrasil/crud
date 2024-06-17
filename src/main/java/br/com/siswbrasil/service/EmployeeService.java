package br.com.siswbrasil.service;

import br.com.siswbrasil.entity.Employee;
import br.com.siswbrasil.repository.EmployeeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EmployeeService implements BaseService<Employee, Long> {

	@Inject
	private EmployeeRepository repository;

	@Override
	public Employee findById(Long id) {
		return repository.findByIdOptional(id).orElseThrow(() -> new NotFoundException());
	}

	@Override
	@Transactional
	public Employee create(Employee form) {
		repository.persist(form);
		return form;
	}

	@Override
	public Employee update(Long id, Employee form) {
		var entity = findById(id);
		repository.persist(form);
		return entity;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		var entity = findById(id);
		repository.delete(entity);
	}

}
