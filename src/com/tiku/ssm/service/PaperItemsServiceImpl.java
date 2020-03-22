package com.tiku.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiku.ssm.mapper.PaperitemsMapper;
import com.tiku.ssm.pojo.Paperitems;

@Service
public class PaperItemsServiceImpl implements PaperItemsService {

	@Autowired
	private PaperitemsMapper paperitemsMapper;
	
	public void insertPI(Integer pid,Integer[] ids) {
		Paperitems pi=new Paperitems();
		pi.setPid(pid);
		for(int i=0;i<ids.length;i++) {
			if(ids[i]!=null) {
			pi.setId(ids[i]);
			paperitemsMapper.insertPI(pi);
			}
		}
	}
	
	
	
	public Integer[] findbyPid(Integer pid) {
		Integer[] ids = paperitemsMapper.findbyPid(pid);
		return ids;
	}
	
	
	public void deletePI(Integer pid) {
		paperitemsMapper.deletePI(pid);
	}
	
}
