package org.pranit.core.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the COUNTRIES database table.
 * 
 */
@Entity
@Table(name="COUNTRIES")
@NamedQuery(name="CountryEntity.findAll", query="SELECT c FROM CountryEntity c")
public class CountryEntity implements Persistable<Serializable> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COUNTRY_ID", columnDefinition="CHAR", length=2)
	private String id;

	@Column(name="COUNTRY_NAME")
	private String countryName;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="REGION_ID")
	@JsonIgnore
	private RegionEntity region;

	//bi-directional many-to-one association to Location
	@OneToMany(mappedBy="country")
	@JsonIgnore
	private List<LocationEntity> locations;

	public CountryEntity() {
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

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public RegionEntity getRegion() {
		return this.region;
	}

	public void setRegion(RegionEntity region) {
		this.region = region;
	}

	public List<LocationEntity> getLocations() {
		return this.locations;
	}

	public void setLocations(List<LocationEntity> locations) {
		this.locations = locations;
	}

	/*public LocationEntity addLocation(LocationEntity location) {
		getLocations().add(location);
		location.setCountry(this);

		return location;
	}

	public LocationEntity removeLocation(LocationEntity location) {
		getLocations().remove(location);
		location.setCountry(null);

		return location;
	}*/

}