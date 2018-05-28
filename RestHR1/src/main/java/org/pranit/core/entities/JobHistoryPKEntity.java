package org.pranit.core.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

/**
 * The primary key class for the JOB_HISTORY database table.
 * 
 */
@Embeddable
public class JobHistoryPKEntity implements Persistable<Serializable> {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="EMPLOYEE_ID", insertable=false, updatable=false)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private java.util.Date startDate;

	public JobHistoryPKEntity() {
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


	public java.util.Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JobHistoryPKEntity)) {
			return false;
		}
		JobHistoryPKEntity castOther = (JobHistoryPKEntity)other;
		return 
			(this.id == castOther.id)
			&& this.startDate.equals(castOther.startDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.id ^ (this.id >>> 32)));
		hash = hash * prime + this.startDate.hashCode();
		
		return hash;
	}
}