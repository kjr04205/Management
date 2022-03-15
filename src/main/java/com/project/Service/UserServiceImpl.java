package com.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DAO.UserDAO;
import com.project.DTO.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO userDao;

	@Override
	public int getUserCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeUser() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertUser(User user) throws Exception{
		return userDao.insertUser(user);
	}

	@Override
	public int updateUser() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(User user) throws Exception{
		return userDao.selectUser(user);
	}

}
