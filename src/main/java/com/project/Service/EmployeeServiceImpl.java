package com.project.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DAO.EmployeeDAO;
import com.project.DTO.Employee;
import com.project.DTO.Position;
import com.project.DTO.Team;
import com.project.DTO.User;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeDAO employeeDao;
	
	@Override
	public int insertEmployee(Employee employee) throws Exception{
		return employeeDao.insertEmployee(employee);
	}
	
	@Override
	public List<Employee> getList() throws Exception{
		return employeeDao.selectEmployee();
	}
	
	@Override
	public List<Employee> getPage(Map map) throws Exception{
		return employeeDao.selectPage(map);
	}
	
	@Override
	public int getCount() throws Exception{
		return employeeDao.count();
	}

	@Override
	public List<Team> getTeamList() throws Exception {
		return employeeDao.selectTeamAll();
	}

	@Override
	public int teamInsert(Team team) throws Exception{
		return employeeDao.teamInsert(team);
	}
	
	@Override
	public List<Position> getPositionList() throws Exception {
		return employeeDao.selectPositionAll();
	}

	@Override
	public int getTeamCount() throws Exception {
		return employeeDao.teamCount();
	}

	@Override
	public List<Team> getTeamList(Map map) throws Exception {
		return employeeDao.selectTeam(map);
	}
	
	@Override
	public int removeEmployee(Integer EmployeeEno) throws Exception{
		return employeeDao.removeEmployee(EmployeeEno);
	}
	
	
}
