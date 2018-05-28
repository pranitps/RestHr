package org.pranit.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the EMPLOYEES database table.
 * 
 */
@Getter
@Setter
@XmlRootElement
public class EmployeeDTO{
	
	private Long id;
	
	private BigDecimal commissionPct;

	private String email;
	
	private String firstName;
	
	private Date hireDate;
	
	private String lastName;
	
	private String phoneNumber;

	private BigDecimal salary;

	/*public EmployeeDTO(EmployeeEntity employeeEntity) {
		try {
			PropertyUtils.copyProperties(this, employeeEntity);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}