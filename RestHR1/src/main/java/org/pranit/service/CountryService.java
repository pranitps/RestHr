package org.pranit.service;

import java.util.List;

import org.pranit.core.dao.BaseDao;
import org.pranit.core.entities.CountryEntity;
import org.pranit.core.entities.EmployeeEntity;
import org.pranit.core.service.BaseService;
import org.pranit.dao.CountryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CountryService extends BaseService<CountryEntity, String> {

	@Autowired
	private CountryDao countryDao;
	
	@Override
	protected BaseDao<CountryEntity, String> getEntityDao() {
		return countryDao;
	}
	
	public List<CountryEntity> getCountries(){
		return countryDao.findAll();
	
	}
}
