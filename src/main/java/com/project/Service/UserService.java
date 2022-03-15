package com.project.Service;

import java.util.List;

import com.project.DTO.User;

public interface UserService {

	int getUserCount();
	int removeUser();
	int insertUser(User user) throws Exception;
	int updateUser();
	List<User> getUserList();
	User getUser(User user) throws Exception;
	
}