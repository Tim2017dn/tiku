package com.tiku.ssm.controller;

import java.awt.Window;
import java.awt.event.WindowStateListener;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tiku.ssm.pojo.Items;
import com.tiku.ssm.pojo.Users;
import com.tiku.ssm.service.ItemService;
import com.tiku.ssm.utils.MsHandler;
import com.tiku.ssm.utils.PaperItemsShou;
import com.tiku.ssm.utils.sortByType;

import cn.itcast.common.utils.Page;




@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	private String username;
	
	




	@RequestMapping(value="/itemlist.action")
	public String itemList(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="10")Integer rows,
			String type,String keyWord,String subject,Model model) {
		
		System.out.println(subject);
		Page<Items> list = itemService.findItemsList(page, rows, type,keyWord,subject);
		model.addAttribute("page", list);			
		model.addAttribute("type",type);
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("subject", subject);
		
		return "itemList";
	}
	
	
	
	
	@RequestMapping(value="/home.action")
	public ModelAndView home(String username) {
		ModelAndView mav=new ModelAndView();
		
		this.username=username;
		
		mav.addObject("username", username);
		
		mav.setViewName("HomePage");
		
		return mav;
	}
	
	
	
	@RequestMapping(value="/gotoAddItem.action")
	public ModelAndView gotoAddItem() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("addItem");
		return mav;
	}
	
//	@RequestMapping(value="/paperShou.action")
//	public String paperList(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="10")Integer rows,
//			Model model) {	
//		Page<Items> list = pis.findPaperShouList(page, rows);
//		if(list!=null) {
//		model.addAttribute("page", list);
//		}
//		return "paperShou";
//	}
	
	
	@RequestMapping("/itemEdit.action")
	public String edit(Integer id,Model model) {
		Items item = itemService.findItemById(id);
		
		model.addAttribute("item",item);
		return "editItem";
	}
	
	//updateitem.action
	@RequestMapping("/updateitem.action")
	@ResponseBody
	public boolean updateItem(Integer id,String question,String answer,String type,float dif,String subject,Model model) {
		
		System.out.println(question);
		Items item=new Items();
		item.setId(id);
		item.setQuestion(question);
		item.setAnswer(answer);
		item.setType(type);
		item.setDif(dif);
		item.setSubject(subject);
		
		itemService.updateItem(item);
		return true;
	}
	
	//itemDelete
	@RequestMapping("/itemDelete.action")
	public String ItemDelete(Integer id) {
		Items item=new Items();
		item.setId(id);
		itemService.deleteItem(item);
		return "redirect:/itemlist.action";
	}
	
	
	///additem.action
	@RequestMapping("/additem.action")
	public String additem(String question,String answer,String type,float dif,String subject) {		
		itemService.insertItem(question, answer, type, dif,subject);	
		return "redirect:/gotoAddItem.action";
	}
	
//	addToPaper.action
//	@RequestMapping("/addToPaper.action")
//	public String addToPaper(Integer[] ids) {	
//		
//		
//	
//		if(ids!=null) {
//			for(int i=0;i<ids.length;i++) {
//				int temp=ids[i];
//				Items item = itemService.findItemById(temp);
//				pis.addItem(item);
//			}
//		}
//		return "redirect:/itemist.action";
//	}
//	
//	@RequestMapping("/paperDelete.action")
//	public String paperDelete(Integer id) {
//	//	itemService.deletePaperItem(id);
//		pis.deleteItem(id);
//		return "redirect:/paperShou.action";
//	}
	
	
//	generatePaperShou.action
//	@RequestMapping("/generatePaperShou.action")
//	public String generatePaperShou(String title) {
//		
//		
////		List<Items> list = PaperItemsShou.getPaper();
//		List<Items> list = pis.getPaper();
//		MsHandler mh=new MsHandler(title,list);
//		mh.generaePaper();
//		mh.generaeAnswer();
//		
//		
//		
//		return "redirect:/paperShou.action";
//	}
	
	
	//paperDeleteMuti
//	@RequestMapping("/paperDeleteMuti.action")
//	public String paperDeleteMuti(Integer[] ids) {
//		
//		for(int i=0;i<ids.length;i++) {
//	
//			pis.deleteItem(ids[i]);
//		}
//		return "redirect:/paperShou.action";
//	}
	
	
	
	
}
