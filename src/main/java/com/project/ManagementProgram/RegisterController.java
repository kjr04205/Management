package com.project.ManagementProgram;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.DTO.User;

@Controller
public class RegisterController {
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register/save")
	public String save(User user, Model m) throws Exception {
		// 유효성 검사
		if(!isValid(user)) {
			String msg = URLEncoder.encode("회원가입에 실패하였습니다. 다시 시도해주세요.","utf-8");
			return "redirect:/register?msg="+msg;
		}
		
		// DB에 insert
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
