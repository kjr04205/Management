package com.project.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.DTO.Employee;
import com.project.DTO.IGroup;
import com.project.DTO.Inventory;
import com.project.DTO.Location;
import com.project.DTO.SearchCondition;
import com.project.DTO.Team;

@Repository
public class InventoryDAO {
	
	@Autowired
	SqlSession session;
	String namespace = "com.project.DAO.InventoryMapper.";
	
	public List<Inventory> selectInventory() throws Exception{
		System.out.println("DAO");
		return session.selectList(namespace + "selectInventory");
	}
	
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
	
	public List<Inventory> selectPage(SearchCondition sc) throws Exception{
		return session.selectList(namespace+"selectInventoryPage", sc);
	}
	
	public int count(SearchCondition sc) throws Exception {
        return session.selectOne(namespace+"count", sc);
    } 
	
	public int insertInventory(Inventory inventory) throws Exception{
		return session.insert(namespace + "insertInventory", inventory); 
	}
	
	public Inventory selectInventory(int ino) throws Exception{
		return session.selectOne(namespace + "selectInventoryOne", ino);
	}
	
	public int updateInventory(Inventory inventory) throws Exception{
		return session.update(namespace + "updateInventory", inventory);
	}
	
	public int deleteInventory(int ino) throws Exception{
		return session.delete(namespace + "deleteInventory", ino);
	}
}
