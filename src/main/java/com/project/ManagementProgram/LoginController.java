package com.project.ManagementProgram;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.DTO.User;
import com.project.Service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(String id, String pwd, String toURL
			, HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		user.setId(id);
		user.setPwd(pwd);
		System.out.println(user.toString());
		
		if(!loginCheck(user)) {
			String msg = "";
			try {
				msg = URLEncoder.encode("ID 혹은 PW가 잘못되었습니다.", "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "redirect:/login?msg="+msg;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		toURL = toURL == null || toURL.equals("") ? "/" : toURL;
		System.out.println("toURL:"+toURL);
		return "redirect:"+toURL;
	}
	
	private boolean loginCheck(User user) {
		
		User tmp;
		try {
			
			tmp = userService.getUser(user);
			System.out.println(tmp);
			if(tmp!=null && tmp.getPwd().equals(user.getPwd())) return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return false;
		
	}
	
}


