package com.tiku.ssm.mapper;

import java.util.List;

import com.tiku.ssm.pojo.Items;

import cn.itcast.common.utils.Page;


public interface ItemsMapper {
	
	//查询全部题
	public List<Items> selectAll();
//	public Page<Items> findItemsList(Integer page, Integer rows, 
//	String custName,  String custSource,String custIndustry,String custLevel);
	
	public List<Items> findItemsList(Items item);
	
	
	public Integer findItemsListCount(Items item);
	
	public Items findItemById(Items item);
	
	
	public void updateItem(Items item);
	
	public void deleteItem(Items item);
	
	public void insertItem(Items item);
}
