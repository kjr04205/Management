package com.project.Service;

import java.util.List;

import com.project.DTO.IGroup;
import com.project.DTO.Inventory;
import com.project.DTO.Location;
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
}