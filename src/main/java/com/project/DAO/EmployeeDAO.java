package com.project.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.DTO.Employee;

@Repository
public class EmployeeDAO {
	
	@Autowired
	SqlSession session;
	String namespace = "com.project.DAO.EmployeeMapper.";
	
	public List<Employee> selectEmployee() throws Exception{
		return session.selectList(namespace+"selectEmployee");
	}
}
