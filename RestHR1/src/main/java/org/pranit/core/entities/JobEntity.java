package org.pranit.core.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the JOBS database table.
 * 
 */
@Entity
@Table(name="JOBS")
@NamedQuery(name="JobEntity.findAll", query="SELECT j FROM JobEntity j")
public class JobEntity implements Persistable<Serializable> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOB_ID")
	private String id;

	@Column(name="JOB_TITLE")
	private String jobTitle;

	@Column(name="MAX_SALARY")
	private BigDecimal maxSalary;

	@Column(name="MIN_SALARY")
	private BigDecimal minSalary;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="job")
	private List<EmployeeEntity> employees;

	//bi-directional many-to-one association to JobHistory
	@OneToMany(mappedBy="job")
	private List<JobHistoryEntity> jobHistories;

	public JobEntity() {
	}

	@Override
    public boolean isNew() {
        Serializable id = getId();
        return id == null || StringUtils.isBlank(String.valueOf(id));
    }

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public BigDecimal getMaxSalary() {
		return this.maxSalary;
	}

	public void setMaxSalary(BigDecimal maxSalary) {
		this.maxSalary = maxSalary;
	}

	public BigDecimal getMinSalary() {
		return this.minSalary;
	}

	public void setMinSalary(BigDecimal minSalary) {
		this.minSalary = minSalary;
	}

	public List<EmployeeEntity> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public EmployeeEntity addEmployee(EmployeeEntity employee) {
		getEmployees().add(employee);
		employee.setJob(this);

		return employee;
	}

	public EmployeeEntity removeEmployee(EmployeeEntity employee) {
		getEmployees().remove(employee);
		employee.setJob(null);

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
		jobHistory.setJob(this);

		return jobHistory;
	}

	public JobHistoryEntity removeJobHistory(JobHistoryEntity jobHistory) {
		getJobHistories().remove(jobHistory);
		jobHistory.setJob(null);

		return jobHistory;
	}

}