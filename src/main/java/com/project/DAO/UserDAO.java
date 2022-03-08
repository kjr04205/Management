package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.DTO.User;

interface UserDAOInterface {
	void deleteAll();
	void deleteUser();
	boolean insertUser(User user);
	void updateUser();
	User selectUser(String id);
	List<User> selectAll(int startRow, int ppl);
	
}

@Repository
public class UserDAO implements UserDAOInterface{

	@Autowired
	DataSource ds;
	
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
	public boolean insertUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try 
		{
			conn = ds.getConnection();
			String sql = "insert into user(id,pwd,name,email,birth) values(?,?,?,?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user.getId());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getBirth());
			int res = pstmt.executeUpdate();
			if(res==1) return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt,conn);
		}
		return false;
		
	}

	@Override
	public void updateUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User selectUser(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			conn = ds.getConnection();
			String sql = "select * from user where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setName(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setBirth(rs.getString(6));
				return user;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			close(rs,pstmt,conn);
		}
		
		return null;
	}

	@Override
	public List<User> selectAll(int startRow, int ppl) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try 
		{
			conn = ds.getConnection();
			String sql = "select * from user where row between ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, ppl);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setName(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setBirth(rs.getString(6));
				list.add(user);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			close(rs,pstmt,conn);
		}
		
		return list;
	}
	
	private void close(AutoCloseable... acs) {
		for(AutoCloseable ac : acs) {
			try {
				if(ac!=null) ac.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}