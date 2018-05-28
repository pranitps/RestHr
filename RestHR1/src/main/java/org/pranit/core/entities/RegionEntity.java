package org.pranit.core.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

import java.util.List;


/**
 * The persistent class for the REGIONS database table.
 * 
 */
@Entity
@Table(name="REGIONS")
@NamedQuery(name="RegionEntity.findAll", query="SELECT r FROM RegionEntity r")
public class RegionEntity implements Persistable<Serializable> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REGION_ID")
	private Long id;

	@Column(name="REGION_NAME")
	private String regionName;

	//bi-directional many-to-one association to Country
	@OneToMany(mappedBy="region")
	private List<CountryEntity> countries;

	public RegionEntity() {
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



	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<CountryEntity> getCountries() {
		return this.countries;
	}

	public void setCountries(List<CountryEntity> countries) {
		this.countries = countries;
	}

	public CountryEntity addCountry(CountryEntity country) {
		getCountries().add(country);
		country.setRegion(this);

		return country;
	}

	public CountryEntity removeCountry(CountryEntity country) {
		getCountries().remove(country);
		country.setRegion(null);

		return country;
	}

}