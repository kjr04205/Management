package com.project.ManagementProgram;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.DTO.Employee;
import com.project.DTO.Goods;
import com.project.DTO.IGroup;
import com.project.DTO.Inventory;
import com.project.DTO.Location;
import com.project.DTO.PageHandler2;
import com.project.DTO.SearchCondition;
import com.project.DTO.Team;
import com.project.Service.EmployeeService;
import com.project.Service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class InventoryController {
	
	@Autowired
	DataSource ds;
	
	@Autowired
	InventoryService inventoryservice;
	
	@Autowired
	EmployeeService employeeservice;
	
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
	
	@GetMapping("/inventoryLocation")
	public String inventoryLocation(SearchCondition sc, Model m) {
		try {
			int totalCnt = inventoryservice.getLocationCount(sc);
			PageHandler2 pageHandler = new PageHandler2(totalCnt, sc);
			List<Location> locationList = inventoryservice.getLocationList(sc);
			m.addAttribute("locationList", locationList);
			m.addAttribute("ph", pageHandler);
		}catch(Exception e) {
			e.printStackTrace();
		}
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
	
	@GetMapping("/inventoryGroup")
	public String inventoryGroup(SearchCondition sc, Model m) {
		try {
			int totalCnt = inventoryservice.getGroupCount(sc);
			PageHandler2 pageHandler = new PageHandler2(totalCnt, sc);
			List<IGroup> groupList = inventoryservice.getGroupList(sc);
			m.addAttribute("groupList", groupList);
			m.addAttribute("ph", pageHandler);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
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
	
	@RequestMapping("/inventoryGoods")
	public String inventoryGoods(Model m) throws Exception {
		
		//List<IGroup> IGroup = inventoryservice.getGroupList();
		
		return "inventoryGoods";
	}
	
	@RequestMapping("/inventoryGoodsLend")
	public String inventoryGoodsLend(Model m) throws Exception {
		List<Inventory> invList = inventoryservice.getList();
		m.addAttribute("inventory", invList);
		
		List<Goods> goodsList = inventoryservice.getGoodsList();
		m.addAttribute("goodsList", goodsList);
		
		return "inventoryGoodsLend";
	}
	
	@PostMapping("/inventoryGoodsLend/save")
//	@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
	public String inventoryGoodsLendSave(Goods goods, RedirectAttributes rattr){
		PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
		DefaultTransactionDefinition txd = new DefaultTransactionDefinition();
		txd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = tm.getTransaction(txd);
		
		try {
			int result = inventoryservice.updateInventoryCount(Integer.parseInt(goods.getGname()), goods.getGcount() * (-1));
			if(result<1) // 수량이 0보다 적어지거나 오류가 있을 경우
				throw new Exception("CNT_ERR");
			
			inventoryservice.insertInventoryGoods(goods);
			rattr.addFlashAttribute("msg", "ADD_OK");
			tm.commit(status);
		}catch(Exception e) {
			e.printStackTrace();
			if(e.getMessage().equals("CNT_ERR")) {
				rattr.addFlashAttribute("msg", "ADD_CNT_ERR");
			}else {
				rattr.addFlashAttribute("msg", "ADD_ERR");
			}
			
			tm.rollback(status);
		}
		return "redirect:/inventoryGoodsLend";
	}
	
	@RequestMapping("/inventoryGoodsLend/remove")
	public String goodsRemove(Goods goods, RedirectAttributes rattr){
		try {
			int res = inventoryservice.removeGoods(goods.getGno());
			int gcount = inventoryservice.updateInventoryGoodsCount(goods);
			
			rattr.addFlashAttribute("msg", "DEL_OK");
		}catch(Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "DEL_ERR");
		}
		return "redirect:/inventoryGoodsLend";
		
	}

	@ResponseBody
	@RequestMapping("/inventoryGoods/employee")
	public List<Employee> getEmployeeList(Model m) throws Exception{
		List<Employee> empList = employeeservice.getEmployee();
		
		m.addAttribute("employee", empList);
		return empList;
		
	}
}
