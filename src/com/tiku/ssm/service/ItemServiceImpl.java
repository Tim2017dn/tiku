package com.tiku.ssm.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.internal.compiler.util.FloatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tiku.ssm.mapper.ItemsMapper;
import com.tiku.ssm.pojo.Items;
import com.tiku.ssm.pojo.Users;
import com.tiku.ssm.utils.PaperItemsShou;

import cn.itcast.common.utils.Page;



@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemsMapper itemsMapper;
	
//	private PaperItemsShou pis;
	
	
	public List<Items> selectItemsAll(){
		return itemsMapper.selectAll();
	}
	
	public Page<Items> findItemsList(Integer page, Integer rows,String type,String question,String subject){
		Items item= new Items();
		if(StringUtils.isNotBlank(type)){
			item.setType(type);
		}
		if(StringUtils.isNotBlank(question)) {
			item.setQuestion(question);
		}
		if(StringUtils.isNotBlank(subject)) {
			item.setSubject(subject);;
		}
		
		item.setStart((page-1) * rows);
		item.setRows(rows);
		 List<Items> itemlist = itemsMapper.findItemsList(item);
		Integer count=itemsMapper.findItemsListCount(item);
		Page<Items> result = new Page<Items>();
		result.setPage(page);
		result.setRows(itemlist);
		result.setSize(rows);
		result.setTotal(count);
		return result;
	}
	
	
	public Page<Items> findItemsList(Integer page, Integer rows,String type){
		Items item= new Items();
		if(StringUtils.isNotBlank(type)){
			item.setType(type);
		}
		item.setStart((page-1) * rows);
		item.setRows(rows);
		 List<Items> itemlist = itemsMapper.findItemsList(item);
		Integer count=itemsMapper.findItemsListCount(item);
		Page<Items> result = new Page<Items>();
		result.setPage(page);
		result.setRows(itemlist);
		result.setSize(rows);
		result.setTotal(count);
		return result;
	}
	
//	public Page<Items> findPaperShouList(Integer page, Integer rows){
////		List<Items> list = PaperItemsShou.getPaper();
//		List<Items> list = pis.getPaper();
//		Page<Items> result = new Page<Items>();
//		result.setPage(page);
//		if(list!=null) {
//		result.setRows(list);
//		}
//		result.setSize(rows);
//		
//		if(list!=null) {
//		result.setTotal(list.size());
//		}else {
//			result.setTotal(0);
//		}
//		return result;
//	}
	
	public Items findItemById(Integer id) {
		Items item= new Items();
		item.setId(id);
		Items result = itemsMapper.findItemById(item);
		return result;
	}
	
	
	public List<Items> findItemsById(Integer[] ids){
		List<Items> it=new ArrayList();
		for(int i=0;i<ids.length;i++) {
			if(ids[i]!=null) {
			Items item = this.findItemById(ids[i]);
			it.add(item);
			}
		}
		return it;
	}
	
	
	public void updateItem(Items item) {
		itemsMapper.updateItem(item);
	}
	
	public void deleteItem(Items item) {
		itemsMapper.deleteItem(item);
	}
	
	
	
//	public void deletePaperItem(Integer id) {
//		List<Items> list = PaperItemsShou.getList();
//				Iterator<Items> iterator = list.iterator();
//		while(iterator.hasNext()) {
//			Items item = iterator.next();
//			if(item.getId()==id) {
//				list.remove(item);
//				break;
//			}
//		}
//		pis.deleteItem(id);
//	}
	
//	public void addPaperItem(Items i) {
//		pis.addItem(i);
//	}
//	
	
	public void insertItem(String question,String answer,String type,float dif,String subject) {
		Items item=new Items();
		item.setQuestion(question);
		item.setAnswer(answer);
		item.setType(type);
		item.setDif(dif);
		item.setSubject(subject);
		itemsMapper.insertItem(item);
		
	}
	
//	public void initPaperShou() {
//		pis=new PaperItemsShou();
//	}
	
}
