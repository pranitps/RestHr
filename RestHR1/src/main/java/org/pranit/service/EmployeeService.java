package org.pranit.service;

import java.util.List;

import org.pranit.core.dao.BaseDao;
import org.pranit.core.entities.EmployeeEntity;
import org.pranit.core.service.BaseService;
import org.pranit.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService extends BaseService<EmployeeEntity, Long> {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	protected BaseDao<EmployeeEntity, Long> getEntityDao() {
		return employeeDao;
	}
	
	public List<EmployeeEntity> getEmployee(){
		return employeeDao.findAll();
	
	}
}
