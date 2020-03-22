package com.tiku.ssm.service;

import java.util.Iterator;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiku.ssm.mapper.UsersMapper;
import com.tiku.ssm.pojo.Users;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersMapper usermapper;
	
	public boolean login(String username,String password) {
		
		List<Users> userlist = usermapper.findUserByUsername(username);
		Iterator<Users> it = userlist.iterator();
		while(it.hasNext()) {
			Users user = it.next();
			if(password.equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	
//	public  String login1(String username,String password) {
//		String result="no";
//		boolean flag=false;
//		
//		List<Users> userlist = usermapper.findUserByUsername(username);
//		Iterator<Users> it = userlist.iterator();
//		while(it.hasNext()) {
//			Users user = it.next();
//			if(password.equals(user.getPassword())) {
//				flag=true;
//				break;
//			}
//		}
//		
//		if(flag) {
//			result="yes";
//		}
//		
//		return result;
//	}
//	
	
	public Users findUserbyUsername(String username) {
		Users user = usermapper.findOneUserByUsername(username);
		return user;
	}
	
	public int UpdateUser(String username,String newpass) {
		Users u=new Users();
		u.setUsername(username);
		u.setPassword(newpass);
		usermapper.updateUser(u);
		return 0;
	}
	
	public String findRealname(String username) {
		List<Users> userlist = usermapper.findUserByUsername(username);
		Iterator<Users> it = userlist.iterator();
		String result="";
		while(it.hasNext()) {
			Users user = it.next();
			result= user.getRealname();
			}
		return result;
		}
	
	
	public void reg(String username,String password, String subject,String title,String realname) {
		Users u=new Users();
		u.setUsername(username);
		u.setPassword(password);
		u.setRealname(realname);
		u.setSubject(subject);
		u.setTitle(title);
		usermapper.RegUser(u);
	}
	
	
}
