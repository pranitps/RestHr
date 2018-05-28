package org.pranit.core.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

import java.util.List;


/**
 * The persistent class for the LOCATIONS database table.
 * 
 */
@Entity
@Table(name="LOCATIONS")
@NamedQuery(name="LocationEntity.findAll", query="SELECT l FROM LocationEntity l")
public class LocationEntity implements Persistable<Serializable> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOCATION_ID")
	private Long id;

	private String city;

	@Column(name="POSTAL_CODE")
	private String postalCode;

	@Column(name="STATE_PROVINCE")
	private String stateProvince;

	@Column(name="STREET_ADDRESS")
	private String streetAddress;

	//bi-directional many-to-one association to Department
	@OneToMany(mappedBy="location")
	private List<DepartmentEntity> departments;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private CountryEntity country;

	public LocationEntity() {
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



	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStateProvince() {
		return this.stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public List<DepartmentEntity> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<DepartmentEntity> departments) {
		this.departments = departments;
	}

	public DepartmentEntity addDepartment(DepartmentEntity department) {
		getDepartments().add(department);
		department.setLocation(this);

		return department;
	}

	public DepartmentEntity removeDepartment(DepartmentEntity department) {
		getDepartments().remove(department);
		department.setLocation(null);

		return department;
	}

	public CountryEntity getCountry() {
		return this.country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

}