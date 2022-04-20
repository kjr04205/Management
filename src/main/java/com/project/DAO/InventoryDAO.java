package com.project.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.DTO.Employee;
import com.project.DTO.Goods;
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
	
	public int insertInventoryGoods(Goods goods) throws Exception{
		return session.insert(namespace + "insertGoods", goods); 
	}
	
	public Inventory selectInventory(int ino) throws Exception{
		return session.selectOne(namespace + "selectInventoryOne", ino);
	}
	
	public int updateInventory(Inventory inventory) throws Exception{
		return session.update(namespace + "updateInventory", inventory);
	}
	
	public int deleteInventory(int ino) throws Exception{
//		return session.delete(namespace + "deleteInventory", ino);
		return session.update(namespace + "deleteInventory", ino);
	}
	
	public List<Location> selectLocation(SearchCondition sc) throws Exception{
		return session.selectList(namespace + "selectLocation", sc);
	}
	
	public int locationCount(SearchCondition sc) throws Exception{
		return session.selectOne(namespace + "locationCount", sc);
	}
	
	public List<IGroup> selectGroup(SearchCondition sc) throws Exception{
		return session.selectList(namespace + "selectGroup", sc);
	}
	
	public int groupCount(SearchCondition sc) throws Exception{
		return session.selectOne(namespace + "groupCount", sc);
	}
	
	public List<Goods> selectGoods() throws Exception{
		return session.selectList(namespace + "selectGoods");
	}
	
	public int updateInventoryCount(int ino, int amount) throws Exception{
		Map map = new HashMap();
		map.put("ino", ino);
		map.put("amount",amount);
		return session.update(namespace + "updateInventoryCount", map);
	}
	
	public int removeGoods(int gno) throws Exception{
		return session.delete(namespace + "removeGoods", gno);
	}
	
	public int updateInventoryGoodsCount(Goods goods) throws Exception{
		return session.update(namespace + "goodsCountupdate", goods);
	}
}
