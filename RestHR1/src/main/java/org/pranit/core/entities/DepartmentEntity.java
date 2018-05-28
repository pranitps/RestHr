package org.pranit.core.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

import java.util.List;


/**
 * The persistent class for the DEPARTMENTS database table.
 * 
 */
@Entity
@Table(name="DEPARTMENTS")
@NamedQuery(name="DepartmentEntity.findAll", query="SELECT d FROM DepartmentEntity d")
public class DepartmentEntity implements Persistable<Serializable> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DEPARTMENT_ID")
	private Long id;

	@Column(name="DEPARTMENT_NAME")
	private String departmentName;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="MANAGER_ID")
	private EmployeeEntity employee;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="LOCATION_ID")
	private LocationEntity location;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="department")
	private List<EmployeeEntity> employees;

	//bi-directional many-to-one association to JobHistory
	@OneToMany(mappedBy="department")
	private List<JobHistoryEntity> jobHistories;

	public DepartmentEntity() {
	}

	@Override
    public boolean isNew() {
        Serializable id = getId();
        return id == null || StringUtils.isBlank(String.valueOf(id));
    }

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public EmployeeEntity getEmployee() {
		return this.employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public LocationEntity getLocation() {
		return this.location;
	}

	public void setLocation(LocationEntity location) {
		this.location = location;
	}

	public List<EmployeeEntity> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public EmployeeEntity addEmployee(EmployeeEntity employee) {
		getEmployees().add(employee);
		employee.setDepartment(this);

		return employee;
	}

	public EmployeeEntity removeEmployee(EmployeeEntity employee) {
		getEmployees().remove(employee);
		employee.setDepartment(null);

		return employee;
	}

	public List<JobHistoryEntity> getJobHistories() {
		return this.jobHistories;
	}

	public void setJobHistories(List<JobHistoryEntity> jobHistories) {
		this.jobHistories = jobHistories;
	}

	public JobHistoryEntity addJobHistory(JobHistoryEntity jobHistory) {
		getJobHistories().add(jobHistory);
		jobHistory.setDepartment(this);

		return jobHistory;
	}

	public JobHistoryEntity removeJobHistory(JobHistoryEntity jobHistory) {
		getJobHistories().remove(jobHistory);
		jobHistory.setDepartment(null);

		return jobHistory;
	}

}