package com.project.DAO;

import java.util.List;
import java.util.Map;

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
	
	public List<Employee> selectPage(Map map) throws Exception{
		return session.selectList(namespace+"selectEmployeePage", map);
	}
	
	public int count() throws Exception {
        return session.selectOne(namespace+"count");
    } 
}
