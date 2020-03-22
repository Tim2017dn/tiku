package com.tiku.ssm.service;

import com.tiku.ssm.pojo.Users;

public interface UserService {

	public boolean login(String username,String password);
	
	public Users findUserbyUsername(String username);
	
	public int UpdateUser(String username,String newpass);
	
	public String findRealname(String username);
	
//	public String login1(String username,String password);
	
	public void reg(String username,String password, String subject,String title,String realname);
}
