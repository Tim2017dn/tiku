package com.tiku.ssm.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiku.ssm.mapper.PapersMapper;
import com.tiku.ssm.pojo.Items;
import com.tiku.ssm.pojo.Papers;

@Service
public class PaperServiceImpl implements PaperService {
	
	private Random ran;
	
	@Autowired
	private PapersMapper paperMapper;
	
	public PaperServiceImpl() {
		ran=new Random();
	}
	public List<Items> paperPick(List<Items> it,Integer n){
		List<Items> result=new ArrayList();	
		int i=0;
		int len=it.size()-1;
		//num是生成的树
		int num=0;
		while(i<n) {
			num=ran.nextInt(len);
			Items tmp=it.get(num);
			if(!result.contains(tmp)) {result.add(tmp);i++;}
		}
		return result;
	}
		
	
	public List<Items> combinePick(List<Items> i1,List<Items> i2){
		Iterator<Items> iterator = i2.iterator();
		while(iterator.hasNext()) {
			Items tmp = iterator.next();
			i1.add(tmp);
		}
		return i1;
	}
	
	
	public void insertPaper(String pname,String creater) {	
		Papers paper=new Papers();
		paper.setPname(pname);
		paper.setCreater(creater);
		paperMapper.insert(paper);
		
		
	}
	
	
	public Integer findPid(String pname,String creater) {
		Papers paper=new Papers();
		paper.setPname(pname);
		paper.setCreater(creater);
		
		Papers p = paperMapper.findPaper(paper);
		return p.getPid();
		
	}
	
	public List<Papers> findAll(){
		List<Papers> papers = paperMapper.findAll();
		return papers;
	}
	
	public Papers findPaperByPid(Integer pid) {
		Papers paper = paperMapper.findPaperByPid(pid);
		return paper;
	}
	
	public void deletePaper(Integer pid) {
		paperMapper.deletePaper(pid);
	}
}
