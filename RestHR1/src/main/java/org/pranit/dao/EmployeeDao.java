package org.pranit.dao;

import org.pranit.core.dao.BaseDao;
import org.pranit.core.entities.EmployeeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends BaseDao<EmployeeEntity, Long>{

}
