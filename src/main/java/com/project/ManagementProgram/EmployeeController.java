package com.project.ManagementProgram;

import java.io.UnsupportedEncodingException;
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
import com.project.DTO.PageHandler2;
import com.project.DTO.Position;
import com.project.DTO.SearchCondition;
import com.project.DTO.Team;
import com.project.DTO.User;
import com.project.Service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeservice; 
	
	public List<Employee> employeeList() throws Exception{
		List<Employee> list = employeeservice.getList();
		return list;
	}
	
	@RequestMapping("/employee")
	public String employee(Model m, SearchCondition sc) throws Exception {
		
		try {
			int totalCnt = employeeservice.getCount(sc);
			PageHandler2 pageHandler = new PageHandler2(totalCnt, sc);
			
			List<Employee> list = employeeservice.getPage(sc);
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
	
	@GetMapping("/employee/update")
	public String update(int eno, Model m, RedirectAttributes rattr) {
		try {
			Employee employee = employeeservice.selectEmployee(eno);
			List<Team> tList = employeeservice.getTeamList();
			List<Position> pList = employeeservice.getPositionList();
			
			m.addAttribute("employee", employee);
			m.addAttribute("team", tList);
			m.addAttribute("position", pList);
			
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg","UPDATE_LOAD_ERR");
			return "redirect:/employee";
		}
		
		return "employeeUpdate";
	}
	
	@PostMapping("/employee/update")
	public String update(Employee employee, Model m, RedirectAttributes rattr){
		try {
			employeeservice.updateEmployee(employee);
			rattr.addFlashAttribute("msg", "UPDATE_OK");
			return "redirect:/employee";
			
		}catch(Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "UPDATE_ERR");
		}
		return "redirect:/employee/update?eno="+employee.getEno();
	}
	
	@GetMapping("/teamManagement")
	public String teamManagement(SearchCondition sc, Model m) {
		
		try {
			int totalCnt = employeeservice.getTeamCount(sc);
			PageHandler2 pageHandler = new PageHandler2(totalCnt, sc);
			List<Team> teamList = employeeservice.getTeamList(sc);
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
	
	@GetMapping("/teamManagement/member")
	public String teamMember(SearchCondition sc, Team team, Model m) {
		sc.setKeyword(Integer.toString(team.getTno()));
		try {
			int totalCnt = employeeservice.getTeamMemberCount(sc);
			
			PageHandler2 pageHandler = new PageHandler2(totalCnt, sc);
			
			List<Employee> employeeList = employeeservice.getTeamMember(sc);
			m.addAttribute("employeeList", employeeList);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("team", team);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "teamMember";
	}
	
	@PostMapping("/teamManagement/delete")
	public String deleteTeam(Team team, RedirectAttributes rattr) {
		try {
			int res = employeeservice.deleteTeam(team.getTno());
			
			if(res==0) 
				throw new Exception();
			
			rattr.addFlashAttribute("msg", "DEL_OK");
			return "redirect:/teamManagement";
		}catch(Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "DEL_ERR");
			
			String name;
			try {
				name = URLEncoder.encode(team.getName(), "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
				return "redirect:/teamManagement";
			}
			return "redirect:/teamManagement/member?tno="+team.getTno()+"&name="+name;
		}
		
	}
	
	@GetMapping("/positionManagement")
	public String positionManagement(SearchCondition sc, Model m) {
		
		try {
			int totalCnt = employeeservice.getPositionCount(sc);
			PageHandler2 pageHandler = new PageHandler2(totalCnt, sc);
			List<Position> positionList = employeeservice.getPositionList(sc);
			m.addAttribute("positionList", positionList);
			m.addAttribute("ph", pageHandler);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "positionManagement";
	}
	
	@PostMapping("/positionManagement/save")
	public String positionManagementSave(Position position, Model m, RedirectAttributes rattr){
		try {
			System.out.println("position = " + position);
			employeeservice.insertPosition(position);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return "redirect:/positionManagement";
	}
	
	@GetMapping("/positionManagement/member")
	public String positionManagementMember(SearchCondition sc, Position position, Model m) {
		sc.setKeyword(Integer.toString(position.getPno()));
		try {
			int totalCnt = employeeservice.getPositionMemberCount(sc);
			
			PageHandler2 pageHandler = new PageHandler2(totalCnt, sc);
			
			List<Employee> employeeList = employeeservice.getPositionMember(sc);
			m.addAttribute("employeeList", employeeList);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("position", position);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "positionMember";
	}
}
