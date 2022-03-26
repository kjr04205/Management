package com.project.ManagementProgram;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.DTO.Employee;
import com.project.DTO.PageHandler;
import com.project.Service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeservice; 
	
	@RequestMapping("/employee")
	public String employee(Model m, Integer page, Integer pageSize) throws Exception {
		
		if(page==null) {
			page = 1;
		}
		if(pageSize == null) {
			pageSize = 10;
		}
		
		try {
			int totalCnt = employeeservice.getCount();
			PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
			
			Map map = new HashMap();
			map.put("offset", (page-1)*pageSize);
			map.put("pageSize", pageSize);
			
			List<Employee> list = employeeservice.getPage(map);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "employee";
	}
	
	@RequestMapping("/employeeRegister")
	public String employeeRegister() throws Exception{
		return "employeeRegister";
	}
	
	@RequestMapping("/employeeRegister/save")
	public String save() {
		return "employee";
	}
}
