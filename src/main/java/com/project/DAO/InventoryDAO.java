package com.project.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.DTO.IGroup;
import com.project.DTO.Location;
import com.project.DTO.Team;

@Repository
public class InventoryDAO {
	
	@Autowired
	SqlSession session;
	String namespace = "com.project.DAO.InventoryMapper.";
	
	public List<Location> selectLocationAll() throws Exception{
		return session.selectList(namespace + "selectLocationAll");
	}
	
	public List<IGroup> selectGroupAll() throws Exception{
		return session.selectList(namespace + "selectGroupAll");
	}
	
	public int locationInsert(Location location) throws Exception{
		return session.insert(namespace + "locationInsert", location);
	}

	public int locationInsert(IGroup igroup) {
		return session.insert(namespace + "IGroupInsert", igroup);
	}
	
}
