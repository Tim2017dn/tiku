package com.tiku.ssm.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tiku.ssm.pojo.Items;
import com.tiku.ssm.pojo.Papers;
import com.tiku.ssm.service.ItemService;
import com.tiku.ssm.service.PaperItemsService;
import com.tiku.ssm.service.PaperService;
import com.tiku.ssm.service.PaperServiceImpl;
import com.tiku.ssm.utils.MsHandler;
import com.tiku.ssm.utils.PaperItemsShou;

import cn.itcast.common.utils.Page;

@Controller
public class PaperController {
	
	@Autowired(required=true)
	private ItemService itemService;
	
	private String username;
	
	@Autowired
	private PaperService paperService;
	
	@Autowired
	private PaperItemsService pIService;
	
	private PaperItemsShou pis;
	
	@RequestMapping("/paperQuest.action")
	public String paperQuest(Model model,String title,Integer tian,Integer pan,Integer jian,String subject) {
		Items item=new Items();
		Page<Items> tianList = itemService.findItemsList(1, 10, "填空题","",subject);
		Page<Items> panList = itemService.findItemsList(1, 10, "判断题","",subject);
		Page<Items> jianList = itemService.findItemsList(1, 10, "简答题","",subject);
		
		List<Items> tianPick=paperService.paperPick(tianList.getRows(), tian);
		List<Items> panPick = paperService.paperPick(panList.getRows(), pan);
		List<Items> jianPick = paperService.paperPick(jianList.getRows(), jian);
		
		List<Items>result =paperService.combinePick(tianPick, panPick);
		result=paperService.combinePick(result, jianPick);
		
		model.addAttribute("item", result);
		return "paperList";
	}
	
	
	//generatePaperWord.action
	@RequestMapping("/generatePaperWord.action")
	public String generatePaperWord(String title,Integer[] ids) {
		
		List<Items> list = itemService.findItemsById(ids);
		if(title==null||title=="") {
			title="无标题";
		}
		
		MsHandler mh=new MsHandler(title,list);
		mh.generaePaper();		
		mh.generaeAnswer();
		return "paperList";
	}
	
	
	
	@RequestMapping("/paperAuto.action")
	public ModelAndView paperAuto() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("paperAuto");
		return mav;
	}
	
	
	//saveAsPaper.action
	@RequestMapping("/saveAsPaper.action")
	public ModelAndView saveAsPaper(String title,Integer[] ids) {
		
	//	username=UserController.getUsername();
		if(title==null||title=="") {
			title="未命名";
		}
		
		username=UserController.getRealname();
		paperService.insertPaper(title, username);
		Integer pid = paperService.findPid(title, username);
		
		
		pIService.insertPI(pid, ids);
		
		ModelAndView mav=new ModelAndView();
		//后面改
		
		mav.setViewName("paperList");
		return mav;
	}
	
	
	//linianPaper.action
	@RequestMapping("linianPaper.action")
	public ModelAndView linianPaper() {
	
		List<Papers> paper = paperService.findAll();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("allpapers");
		mav.addObject("paper", paper);
		return mav;
	}
	
	//paperExact.action
	@RequestMapping("paperExact.action")
	public String paperExact(Model model,Integer pid) {
		
		Integer[] ids = pIService.findbyPid(pid);
		Papers paper = paperService.findPaperByPid(pid);
		List<Items> list = itemService.findItemsById(ids);
		
		model.addAttribute("title", paper.getPname());
		model.addAttribute("item", list);
		
		return "paperList";
	}
	
	
	
	//}/savePaperShou.action
		@RequestMapping("/savePaperShou.action")
		public String savePaperShou(String title) {
			
			
		//	username=UserController.getUsername();
			username=UserController.getRealname();
			if(title==null||title=="") {
				title="未命名";
			}
			paperService.insertPaper(title, username);
			Integer pid = paperService.findPid(title, username);
	//		List<Items> items = PaperItemsShou.getPaper();
			List<Items> items = pis.getPaper();
			
			Integer[] ids=new Integer[items.size()];
			Iterator<Items> it = items.iterator();
			int i=0;
			while(it.hasNext()) {
				Items item = it.next();
				ids[i]=item.getId();
				i++;
			}
			pIService.insertPI(pid, ids);
			return"redirect:/paperShou.action";
		}
	
		@RequestMapping(value="/paperShou.action")
		public String paperShou(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="10")Integer rows,
				Model model) {	
//			if(pis==null) {
//				//pis=ItemController.getPis();
//			}
			Page<Items> list=null;
			if(pis!=null) {
				list=pis.findPaperShouList(page, rows);
			}
			if(list!=null) {
			model.addAttribute("page", list);
			}
			return "paperShou";
		}	
		
		
		@RequestMapping("/addToPaper.action")
		@ResponseBody
		public String addToPaper(Model model,String sids,Integer page,String type,String keyWord,String subject,RedirectAttributes attributes) {	//Integer[] ids,
			
			if(pis==null) {
				pis=new PaperItemsShou();
			}		
			JSONArray ja = new JSONArray(sids);
			
			Integer[] ids=new Integer[ja.length()];
			for(int i=0;i<ja.length();i++) {
				ids[i]=ja.getInt(i);
				System.out.println(ids[i]);
			}
			if(ids!=null) {
				for(int i=0;i<ids.length;i++) {
					int temp=ids[i];
					Items item = itemService.findItemById(temp);
					pis.addItem(item);
				}
			}

//			attributes.addAttribute("page",page);
//			attributes.addAttribute("type",type);
//			attributes.addAttribute("keyWord",keyWord);
//			attributes.addAttribute("subject",subject);
//			
			
			
			return "redirect:/itemlist.action";

		}
		
		
		@RequestMapping("/addToPaper2.action")
		public String addToPaper2(Integer[] ids) {	
			
			if(pis==null) {
				pis=new PaperItemsShou();
			}
			if(ids!=null) {
				for(int i=1;i<ids.length;i++) {
					if(ids[i]!=null) {
						int temp=ids[i];
						Items item = itemService.findItemById(temp);
						pis.addItem(item);
					}
				}
			}
			return "redirect:/linianPaper.action";
		}
		
		
		@RequestMapping("/paperDelete.action")
		public String paperDelete(Integer id) {
		//	itemService.deletePaperItem(id);
			pis.deleteItem(id);
			return "redirect:/paperShou.action";
		}
		
		@RequestMapping("/generatePaperShou.action")
		public String generatePaperShou(String title) {
			
			if(title==null||title.equals("")) {
				title="无标题";
			}
//			List<Items> list = PaperItemsShou.getPaper();
			List<Items> list = pis.getPaper();
			MsHandler mh=new MsHandler(title,list);
			mh.generaePaper();
			mh.generaeAnswer();
			
			return "redirect:/paperShou.action";
		}
		
		
		@RequestMapping("/paperDeleteMuti.action")
		public String paperDeleteMuti(Integer[] ids) {
			
			for(int i=0;i<ids.length;i++) {
		
				pis.deleteItem(ids[i]);
			}
			return "redirect:/paperShou.action";
		}
		
//		paperdelete.action
		@RequestMapping("/paperdelete.action")
		public String paperdelete(Model model,Integer pid) {
			
			paperService.deletePaper(pid);
			pIService.deletePI(pid);
			return "redirect:/linianPaper.action";
		}
		
		
		//paperShouClean.action
		@RequestMapping("/paperShouClean.action")
		public String paperShouClean() {
			
			pis.cleanAll();
			return "redirect:/paperShou.action";
		}
		
}
