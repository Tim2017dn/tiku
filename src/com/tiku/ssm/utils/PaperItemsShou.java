package com.tiku.ssm.utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.tiku.ssm.pojo.*;

import cn.itcast.common.utils.Page;

public class PaperItemsShou {

	private  List<Items> itemList;
	
	public PaperItemsShou(){
		itemList=new ArrayList();
	}
	
	//添加试题
	public void addItem(Items i) {
		itemList.add(i);
	}
	//获取试题
	public  List<Items> getPaper(){
		if(itemList!=null) {
			Collections.sort(itemList, new sortByType());
		}
		return itemList;
	}
	//删除试题
	public void deleteItem(Integer id) {
		Iterator<Items> iterator = itemList.iterator();
		while(iterator.hasNext()) {
			Items item = iterator.next();
			if(item.getId()==id) {
				itemList.remove(item);
				break;
			}
		}
	}
	//获取试题列表
	public Page<Items> findPaperShouList(Integer page, Integer rows){

		List<Items> list =itemList;
		Page<Items> result = new Page<Items>();
		result.setPage(page);
		if(list!=null) {
		result.setRows(list);
		}
		result.setSize(rows);
		
		if(list!=null) {
		result.setTotal(list.size());
		}else {
			result.setTotal(0);
		}
		return result;
	}
	
	//清除全部
	public  void cleanAll() {
		itemList.clear();
	}
	
	
}

