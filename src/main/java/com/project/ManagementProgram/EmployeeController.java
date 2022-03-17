package com.project.ManagementProgram;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.DTO.Employee;
import com.project.Service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeservice; 
	
	@RequestMapping("/employee")
	public String employee(Model m) throws Exception {
		
		List<Employee> list = employeeservice.getList();
		m.addAttribute("list", list);
		
		return "employee";
	}
}
