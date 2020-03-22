package com.tiku.ssm.service;

public interface PaperItemsService {

	
	public void insertPI(Integer pid,Integer[] ids);
	
	public Integer[] findbyPid(Integer pid);
	
	public void deletePI(Integer pid);
 }
