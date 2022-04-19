package com.project.Service;

import java.util.List;

import com.project.DTO.Goods;
import com.project.DTO.IGroup;
import com.project.DTO.Inventory;
import com.project.DTO.Location;
import com.project.DTO.Position;
import com.project.DTO.SearchCondition;

public interface InventoryService {

	List<Location> getLocationList() throws Exception;

	List<IGroup> getGroupList() throws Exception;

	int locationInsert(Location location) throws Exception;

	int igroupInsert(IGroup igroup) throws Exception;
	
	List<Inventory> getPage(SearchCondition sc) throws Exception;
	
	int getCount(SearchCondition sc) throws Exception;
	
	int insertInventory(Inventory inventory) throws Exception;
	
	List<Inventory> getList() throws Exception;
	
	Inventory getInventory(int ino) throws Exception;
	
	int updateInventory(Inventory inventory) throws Exception;
	
	int deleteInventory(int ino) throws Exception;
	
	List<Location> getLocationList(SearchCondition sc) throws Exception;
	
	int getLocationCount(SearchCondition sc) throws Exception;
	
	List<IGroup> getGroupList(SearchCondition sc) throws Exception;
		
	int getGroupCount(SearchCondition sc) throws Exception;
	
	public int insertInventoryGoods(Goods goods) throws Exception;
	
	public List<Goods> getGoodsList() throws Exception;
	
	int updateInventoryCount(int ino, int amount) throws Exception;
}