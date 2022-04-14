package com.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DAO.InventoryDAO;
import com.project.DTO.Employee;
import com.project.DTO.IGroup;
import com.project.DTO.Inventory;
import com.project.DTO.Location;
import com.project.DTO.SearchCondition;
import com.project.DTO.Team;

@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private InventoryDAO inventoryDao;
	
	@Override
	public List<Inventory> getList() throws Exception{
		System.out.println("service");
		return inventoryDao.selectInventory();
	}
	
	@Override
	public List<Location> getLocationList() throws Exception {
		return inventoryDao.selectLocationAll();
	}
	
	@Override
	public List<IGroup> getGroupList() throws Exception {
		return inventoryDao.selectGroupAll();
	}
	
	@Override 
	public int locationInsert(Location location) throws Exception{
		return inventoryDao.locationInsert(location);
	}
	
	@Override 
	public int igroupInsert(IGroup igroup) throws Exception{
		return inventoryDao.locationInsert(igroup);
	}
	
	@Override
	public List<Inventory> getPage(SearchCondition sc) throws Exception{
		return inventoryDao.selectPage(sc);
	}
	
	@Override
	public int getCount(SearchCondition sc) throws Exception{
		return inventoryDao.count(sc);
	}
	
	@Override
	public int insertInventory(Inventory inventory) throws Exception{
		return inventoryDao.insertInventory(inventory);
	}

	@Override
	public Inventory getInventory(int ino) throws Exception {
		return inventoryDao.selectInventory(ino);
	}

	@Override
	public int updateInventory(Inventory inventory) throws Exception {
		return inventoryDao.updateInventory(inventory);
	}

	@Override
	public int deleteInventory(int ino) throws Exception {
		return inventoryDao.deleteInventory(ino);
	}
}
