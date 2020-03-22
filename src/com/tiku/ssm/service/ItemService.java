package com.tiku.ssm.service;

import java.util.List;

import com.tiku.ssm.pojo.Items;
import com.tiku.ssm.pojo.Users;

import cn.itcast.common.utils.Page;

public interface ItemService {
	public List<Items> selectItemsAll();
	public Page<Items> findItemsList(Integer page, Integer rows,String type,String keyWord,String subject);
	public Page<Items> findItemsList(Integer page, Integer rows,String type);
	public Items findItemById(Integer id);
	
	public List<Items> findItemsById(Integer[] ids);
	
	public void updateItem(Items item);
	
	public void deleteItem(Items item);
	
	
//	public Page<Items> findPaperShouList(Integer page, Integer rows);
	
//	public void deletePaperItem(Integer id);
//	public void addPaperItem(Items i);
	
	public void insertItem(String question,String answer,String type,float dif,String subject);
	
//	public void initPaperShou();
	
}
