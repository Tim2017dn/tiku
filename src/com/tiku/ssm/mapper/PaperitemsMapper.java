package com.tiku.ssm.mapper;

import com.tiku.ssm.pojo.Paperitems;

public interface PaperitemsMapper {

	public void insertPI(Paperitems pi);
	
	public Integer[] findbyPid(Integer pid);
	
	public void deletePI(Integer pid);
}
