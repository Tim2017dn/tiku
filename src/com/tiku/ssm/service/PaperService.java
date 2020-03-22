package com.tiku.ssm.service;

import java.util.List;

import com.tiku.ssm.pojo.Items;
import com.tiku.ssm.pojo.Papers;

public interface PaperService {

	//自动选题
	public List<Items> paperPick(List<Items> it,Integer num);
	//把选的题目组在一起
	public List<Items> combinePick(List<Items> i1,List<Items> i2);
	
	public void insertPaper(String pname,String creater);
	
	public Integer findPid(String pname,String creater);
	
	public List<Papers> findAll();
	
	public Papers findPaperByPid(Integer pid);
	
	public void deletePaper(Integer pid);
	
}
