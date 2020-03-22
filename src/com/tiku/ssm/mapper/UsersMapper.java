package com.tiku.ssm.mapper;

import java.util.List;

import com.tiku.ssm.pojo.Users;

public interface UsersMapper {

	public List<Users> findUserByUsername(String username);
	
	public Users findOneUserByUsername(String username);
	
	public void updateUser(Users user);
	
	public void RegUser(Users user);
}
