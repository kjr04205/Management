package com.project.DAO;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.DTO.User;

interface UserDAOInterface {
	void deleteAll();
	void deleteUser();
	int insertUser(User user) throws Exception;
	void updateUser();
	User selectUser(User user) throws Exception;
	List<User> selectAll(int startRow, int ppl);
	
}

@Repository
public class UserDAO implements UserDAOInterface{
	
	@Autowired
	SqlSession session;
	
	String namespace = "com.project.DAO.UserMapper.";
	
	public UserDAO() {}
	
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertUser(User user) throws Exception{
		return session.insert(namespace + "insertUser", user);
	}

	@Override
	public void updateUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User selectUser(User user) throws Exception{
		return session.selectOne(namespace + "selectUser", user);
	}

	@Override
	public List<User> selectAll(int startRow, int ppl) {
		
		return null;
	}
	
	
}