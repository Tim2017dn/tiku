package com.tiku.ssm.mapper;

import java.util.List;

import com.tiku.ssm.pojo.Papers;

public interface PapersMapper {

	public void insert(Papers paper);
	
	public Papers findPaper(Papers paper);
	
	public List<Papers> findAll();
	
	public Papers findPaperByPid(Integer pid);
	
	public void deletePaper(Integer pid);
}
