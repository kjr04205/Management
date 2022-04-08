package com.project.ManagementProgram;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.DTO.IGroup;
import com.project.DTO.Location;
import com.project.Service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class InventoryController {
	
	@Autowired
	InventoryService inventoryservice;
	
	@RequestMapping("/inventory")
	public String inventory() {
		return "inventory";
	}
	
	@RequestMapping("/inventoryRegister")
	public String inventoryRegister(Model m) throws Exception {
		
		List<Location> lList = inventoryservice.getLocationList();
		
		List<IGroup> IGroup = inventoryservice.getGroupList();
		
		m.addAttribute("location", lList);
		m.addAttribute("group", IGroup);
		return "inventoryRegister";
	}
	
	@RequestMapping("/inventoryLocation")
	public String inventoryLocation() {
		return "inventoryLocation";
	}
	
}
