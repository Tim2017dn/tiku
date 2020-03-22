package com.tiku.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tiku.ssm.pojo.Users;
import com.tiku.ssm.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userservice;
	
	private static String username;
	private static String realname;
	private static String password;
	
	@RequestMapping("/UserLogin.action")
	public String  login(Model model) {
			
			model.addAttribute("realname", realname);
			return "HomePage";
	}
	
	public static String getRealname() {
		return realname;
	}

	public static void setRealname(String realname) {
		UserController.realname = realname;
	}

	public static String getUsername() {
		return username;
	}

	@RequestMapping({"/LoginJsp.action","/UserLogout.action"})
	public String LoginJSP() {
		return "login";
	}
	

	@RequestMapping("/gotoUserInfo.action")
	public String UserInfo(Model model) {
		
		Users user = userservice.findUserbyUsername(this.username);
		model.addAttribute("user", user);
		return "UserInfo";
	}
	
	
	@RequestMapping("/EditUserPass.action")
	public String EditUserPass(Model model) {
		
		Users user = userservice.findUserbyUsername(this.username);
		model.addAttribute("user", user);
		return "UserEdit";
	}
	
	
	@RequestMapping("/updateUser.action")
	  @ResponseBody
	public boolean updateUser(Model model,String oldpass,String newpass) {
		System.out.println(oldpass+"   "+password);
		if(oldpass.equals(password)) {
			userservice.UpdateUser(username, newpass);
			password=newpass;
			return true;
		}
		return false;
	}
	
	
	
	 @RequestMapping("/checklogin.action")
	  @ResponseBody
	  public boolean checklogin( String loginname,String password){
	        boolean login=userservice.login(loginname, password);      
	        if(login) {
	        	this.username=loginname; 
	        	this.password=password;
	        	this.realname=userservice.findRealname(loginname);
	        	System.out.println(username+""+password);
            return true;
	        }else {
	        
	            return false;
	        }       
	 }
	
	// gotoReg.action
	 @RequestMapping("/gotoReg.action")
	 public String gotoReg(){
		 return "gotoReg";
	 }
	 

//	 RegUser.action
	 @RequestMapping("/RegUser.action")
	 public String RegUser(String username,String password, String subject,String title,String realname){
		userservice.reg(username, password, subject, title, realname);
		return "redirect:/LoginJsp.action";
	 }
	 
}
