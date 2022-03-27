package com.project.Service;

import java.util.List;
import java.util.Map;

import com.project.DTO.Employee;
import com.project.DTO.Position;
import com.project.DTO.Team;

public interface EmployeeService {

	List<Employee> getList() throws Exception;

	List<Employee> getPage(Map map) throws Exception;

	int insertEmployee(Employee employee) throws Exception;
	
	int getCount() throws Exception;
	
	List<Team> getTeamList() throws Exception;
	
	List<Position> getPositionList() throws Exception;

}