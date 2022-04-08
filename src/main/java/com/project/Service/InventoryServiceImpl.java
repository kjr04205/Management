package com.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DAO.InventoryDAO;
import com.project.DTO.IGroup;
import com.project.DTO.Location;

@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private InventoryDAO inventoryDao;
	
	@Override
	public List<Location> getLocationList() throws Exception {
		return inventoryDao.selectLocationAll();
	}
	
	@Override
	public List<IGroup> getGroupList() throws Exception {
		return inventoryDao.selectGroupAll();
	}
}
