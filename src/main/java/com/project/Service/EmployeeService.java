package com.project.Service;

import java.util.List;
import java.util.Map;

import com.project.DTO.Employee;

public interface EmployeeService {

	List<Employee> getList() throws Exception;

	List<Employee> getPage(Map map) throws Exception;

	int insertEmployee(Employee employee) throws Exception;
	
	int getCount() throws Exception;

}