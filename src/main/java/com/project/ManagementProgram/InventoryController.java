package com.project.ManagementProgram;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InventoryController {
	
	@RequestMapping("/inventory")
	public String inventory() {
		return "inventory";
	}
	
	@RequestMapping("/inventoryRegister")
	public String inventoryRegister() {
		return "inventoryRegister";
	}
}
