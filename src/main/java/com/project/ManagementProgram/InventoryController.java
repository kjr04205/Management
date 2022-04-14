package com.project.ManagementProgram;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.DTO.Employee;
import com.project.DTO.IGroup;
import com.project.DTO.Inventory;
import com.project.DTO.Location;
import com.project.DTO.PageHandler2;
import com.project.DTO.SearchCondition;
import com.project.DTO.Team;
import com.project.Service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class InventoryController {
	
	@Autowired
	InventoryService inventoryservice;
	
	public List<Inventory> inventoryList() throws Exception{
		List<Inventory> list = inventoryservice.getList();
		return list;
	}
	
	@RequestMapping("/inventory")
	public String inventory(Model m, SearchCondition sc) throws Exception {
		try {
			int totalCnt = inventoryservice.getCount(sc);
			PageHandler2 pageHandler = new PageHandler2(totalCnt, sc);
			
			List<Inventory> list = inventoryservice.getPage(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
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
	public String inventoryRegisterSave(Inventory inventory, RedirectAttributes rattr){
		try {
			inventoryservice.insertInventory(inventory);
			rattr.addFlashAttribute("msg", "ADD_OK");
			
		}catch(Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "ADD_ERR");
		}
		return "redirect:/inventoryRegister";
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
	
	@GetMapping("/inventory/update")
	public String updateInventory(int ino, Model m, RedirectAttributes rattr) {
		try {
			Inventory inventory = inventoryservice.getInventory(ino);
			List<Location> lList = inventoryservice.getLocationList();			
			List<IGroup> IGroup = inventoryservice.getGroupList();
			
			m.addAttribute("location", lList);
			m.addAttribute("group", IGroup);

			m.addAttribute("inventory", inventory);
			m.addAttribute("type","Modify");	
			return "inventoryRegister";
		}catch(Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "MOD_ERR");
			return "redirect:/inventory";
		}
		
	}
		
	@PostMapping("/inventory/update")
	public String updateInventory(Inventory inventory, RedirectAttributes rattr) {
		try {
			int res = inventoryservice.updateInventory(inventory);
			
			if(res!=1) {
				throw new Exception("update error: "+inventory.getIno());
			}
			
			rattr.addFlashAttribute("msg","MOD_OK");
			return "redirect:/inventory";
			
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "MOD_ERR");
			return "redirect:/inventory/update?ino="+inventory.getIno();
		}
		
	}
	
	@RequestMapping("/inventory/delete")
	public String deleteInventory(int ino, RedirectAttributes rattr) {
		try {
			int res = inventoryservice.deleteInventory(ino);
			
			if(res!=1) {
				throw new Exception("delete error: "+ino);
			}
			
			rattr.addFlashAttribute("msg","REMOVE_OK");
			
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "REMOVE_ERR");
		}
		return "redirect:/inventory";
		
	}
}
