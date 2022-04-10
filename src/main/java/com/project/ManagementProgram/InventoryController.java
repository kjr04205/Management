package com.project.ManagementProgram;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.DTO.IGroup;
import com.project.DTO.Location;
import com.project.DTO.Team;
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
	
	@PostMapping("/inventoryRegister/save")
	public String inventoryRegisterSave(Location location, RedirectAttributes rattr){
		try {
			System.out.println("location = " + location);
			inventoryservice.locationInsert(location);
			rattr.addFlashAttribute("msg", "ADD_OK");
			
		}catch(Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "ADD_ERR");
		}
		return "redirect:/inventoryLocation";
	}
	
	@RequestMapping("/inventoryLocation")
	public String inventoryLocation() {
		return "inventoryLocation";
	}
	
	@PostMapping("/inventoryLocation/save")
	public String inventoryLocationSave(Location location, RedirectAttributes rattr){
		try {
			System.out.println("location = " + location);
			inventoryservice.locationInsert(location);
			rattr.addFlashAttribute("msg", "ADD_OK");
			
		}catch(Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "ADD_ERR");
		}
		return "redirect:/inventoryLocation";
	}
	
	@RequestMapping("/inventoryGroup")
	public String inventoryGroup() {
		return "inventoryGroup";
	}
	
	@PostMapping("/inventoryGroup/save")
	public String inventoryGroupSave(IGroup igroup, RedirectAttributes rattr){
		try {
			inventoryservice.igroupInsert(igroup);
			rattr.addFlashAttribute("msg", "ADD_OK");
			
		}catch(Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "ADD_ERR");
		}
		return "redirect:/inventoryGroup";
	}
	
}
