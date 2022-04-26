package com.project.ManagementProgram;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.DTO.User;
import com.project.Service.OAuthService;
import com.project.Service.UserService;

@Controller
@PropertySource("classpath:/config/otherAccess.properties")
public class LoginController {
	@Autowired
	UserService userService;
	
	@Autowired
	OAuthService oAuthService;
	
	@Autowired
	Environment env;
	
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
	
	@GetMapping("/kakao/login")
	public String kakakoLogin() {
		String REST_API_KEY = env.getProperty("kakao.REST_API_KEY");
		String REDIRECT_URI = env.getProperty("kakao.REDIRECT_URI");
		String url = 
		"kauth.kakao.com/oauth/authorize?client_id="+REST_API_KEY
		+"&redirect_uri=" + REDIRECT_URI
		+"&response_type=code";
		
		return "redirect:https://"+url;
	}
	
	@GetMapping("/kakao/oauth")
	public String kakakoLogin(@RequestParam String code, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rattr) {
		String access_Token = oAuthService.getKakaoAccessToken(code);
		if(access_Token.isEmpty())
			return "login"; 
		
		try {
			Map map = oAuthService.createKakaoUser(access_Token);
			User user = new User();
			user.setId(map.get("id").toString());
			user.setPwd(map.get("id").toString());
			user.setName(map.get("nickname").toString());
			user.setBirth(map.get("birthDay").toString());
			user.setEmail(map.get("email").toString());
			
			if(!loginCheck(user)) {
				String msg="";
				try {
					User tmp = userService.getUser(user);
					if(tmp==null) {
						int re = userService.insertUser(user);
						if(re <= 0) {
							throw new Exception("User insert ERR");
						}
						System.out.println("insert success:"+user);
					}
					else {
						throw new Exception("Kakao ID Exists");
					}
					
				} catch(Exception e) {
					rattr.addFlashAttribute("msg", "ADD_ERR");
					e.printStackTrace();
					return "redirect:/login?msg="+msg;
				}
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("id", user.getId());
			
			rattr.addAttribute("nickname", user.getName());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	
}


