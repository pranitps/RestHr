package org.pranit.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.pranit.core.entities.EmployeeEntity;
import org.pranit.dto.EmployeeDTO;
import org.pranit.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@RequestMapping(path="/all", produces={"application/json"})
	public List<EmployeeEntity> getEmployees(){
		System.out.println(employeeService);
		return employeeService.getEmployee();
	}
	
	private List<EmployeeDTO> convertToDto(List<EmployeeEntity> employeeEntitys) {
		Type listType = new TypeToken<List<String>>() {}.getType();
		List<EmployeeDTO> employeeDTOs = modelMapper.map(employeeEntitys, listType);
		//EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
		
	    return employeeDTOs;
	}
}
