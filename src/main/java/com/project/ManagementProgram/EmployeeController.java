package com.project.ManagementProgram;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.DTO.Employee;
import com.project.DTO.PageHandler;
import com.project.DTO.Position;
import com.project.DTO.Team;
import com.project.DTO.User;
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
	
	@RequestMapping("/employee/remove")
	public String employeeRemove(HttpServletRequest request, Integer EmployeeEno, RedirectAttributes rattr) throws Exception{
		EmployeeEno = Integer.parseInt(request.getParameter("eno")); 
		System.out.println("EmployeeEno = " +EmployeeEno);
		employeeservice.removeEmployee(EmployeeEno);
		rattr.addFlashAttribute("msg", "REMOVE_OK");
		return "redirect:/employee";
	}
	
	@RequestMapping("/employeeRegister")
	public String employeeRegister(Model m) throws Exception{
		List<Team> tList = employeeservice.getTeamList();
		List<Position> pList = employeeservice.getPositionList();
		m.addAttribute("team", tList);
		m.addAttribute("position", pList);
		return "employeeRegister";
	}
	
	@PostMapping("/employeeRegister/save")
	public String save(Employee employee, Model m, RedirectAttributes rattr){
		// DB에 insert
		try {
			employeeservice.insertEmployee(employee);
			rattr.addFlashAttribute("msg", "ADD_OK");
			
		}catch(Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "ADD_ERR");
		}
		return "redirect:/employeeRegister";
	}
	
	@GetMapping("/teamManagement")
	public String teamManagement(Integer page, Integer pageSize, Model m) {
		if(page == null) {
			page = 1;
		}
		if(pageSize == null) {
			pageSize = 10;
		}
		try {
			int totalCnt = employeeservice.getTeamCount();
			PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
			Map map = new HashMap();
			map.put("offset", (page-1) * pageSize);
			map.put("pageSize", pageSize);
			
			List<Team> teamList = employeeservice.getTeamList(map);
			m.addAttribute("teamList", teamList);
			m.addAttribute("ph", pageHandler);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "teamManagement";
	}
	
	@PostMapping("/teamManagement/save")
	public String teamManagementSave(Team team, Model m, RedirectAttributes rattr){
		try {
			System.out.println("team = " + team);
			employeeservice.teamInsert(team);
			rattr.addFlashAttribute("msg", "ADD_OK");
			
		}catch(Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "ADD_ERR");
		}
		return "redirect:/teamManagement";
	}
}
