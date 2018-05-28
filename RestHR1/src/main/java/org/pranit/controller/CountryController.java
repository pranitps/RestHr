package org.pranit.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.pranit.core.entities.CountryEntity;
import org.pranit.core.entities.EmployeeEntity;
import org.pranit.dto.EmployeeDTO;
import org.pranit.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@RequestMapping(path="/all", produces={"application/json"})
	public List<CountryEntity> getCountries(){
		System.out.println(countryService);
		return countryService.getCountries();
	}
	
	private List<EmployeeDTO> convertToDto(List<EmployeeEntity> employeeEntitys) {
		Type listType = new TypeToken<List<String>>() {}.getType();
		List<EmployeeDTO> employeeDTOs = modelMapper.map(employeeEntitys, listType);
		//EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
		
	    return employeeDTOs;
	}
}
