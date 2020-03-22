package com.tiku.ssm.utils;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tiku.ssm.pojo.Items;

public class MsHandler {

	private String title;
	private List<Items> list;
	public MsHandler(String title,List<Items> list){
		this.title=title;
		this.list=list;
	}
	public void generaePaper() {
		MSWordManager ms=new MSWordManager(true);
		   ms.createNewDocument();  
		   ms.insertText(title+"\n");
		   ms.moveEnd();
		   
		   List<Items> tianList=new ArrayList<Items>();
		   List<Items> panList=new ArrayList<Items>();
		   List<Items> jianList=new ArrayList<Items>();
		   Iterator<Items> iterator = list.iterator();
		   while(iterator.hasNext()) {
			   Items item = iterator.next();
			   if(item.getType().equals("填空题")) {
				   tianList.add(item);
			   }else if(item.getType().equals("判断题")) {
				   panList.add(item);
			   }else {
				   jianList.add(item);
			   }
		   }
		   
		   int da=1;
		   int xiao=1;
		   
		   
		   if(!tianList.isEmpty()) {
			   String DaTitle=DaTi(da)+"填空题";
			   ms.insertText(DaTitle+"\n");
			   ms.moveEnd();
			   Iterator<Items> it = tianList.iterator();
			   while(it.hasNext()) {
				   Items item = it.next();
				   String con=xiao+"."+item.getQuestion()+"\n";
				   ms.insertText(con);
				   ms.moveEnd();
				   xiao++;
			   }
			   da++;
			   ms.insertText("\n\n");
			   ms.moveEnd();
		   }
		   
		   if(!panList.isEmpty()) {
			   String DaTitle=DaTi(da)+"判断题";
			   ms.insertText(DaTitle+"\n");
			   ms.moveEnd();
			   Iterator<Items> it = panList.iterator();
			   while(it.hasNext()) {
				   Items item = it.next();
				   String con=xiao+".  ( ) "+item.getQuestion()+"\n";
				   ms.insertText(con);
				   ms.moveEnd();
				   xiao++;
			   }
			   da++;
			   ms.insertText("\n\n");
			   ms.moveEnd();
		   }
		   
		   
		   if(!jianList.isEmpty()) {
			   String DaTitle=DaTi(da)+"简答题";
			   ms.insertText(DaTitle+"\n");
			   ms.moveEnd();
			   Iterator<Items> it = jianList.iterator();
			   while(it.hasNext()) {
				   Items item = it.next();
				   String con=xiao+". "+item.getQuestion()+"\n\n\n\n\n\n";
				   ms.insertText(con);
				   ms.moveEnd();
				   xiao++;
			   }
			   da++;
			   ms.insertText("\n\n");
			   ms.moveEnd();
		   }
		   ms.save("C:\\Users\\dolly\\Desktop\\"+title+".doc"); 
		   ms.close();     
		   ms.closeDocument();     
	}
	
	public void generaeAnswer() {
		MSWordManager ms=new MSWordManager(true);
		   ms.createNewDocument();  
		   ms.insertText(title+"答案\n");
		   ms.moveEnd();
		   
		   List<Items> tianList=new ArrayList<Items>();
		   List<Items> panList=new ArrayList<Items>();
		   List<Items> jianList=new ArrayList<Items>();
		   Iterator<Items> iterator = list.iterator();
		   while(iterator.hasNext()) {
			   Items item = iterator.next();
			   if(item.getType().equals("填空题")) {
				   tianList.add(item);
			   }else if(item.getType().equals("判断题")) {
				   panList.add(item);
			   }else {
				   jianList.add(item);
			   }
		   }
		   
		   int da=1;
		   int xiao=1;
		   
		   
		   if(!tianList.isEmpty()) {
			   String DaTitle=DaTi(da)+"填空题";
			   ms.insertText(DaTitle+"\n");
			   ms.moveEnd();
			   Iterator<Items> it = tianList.iterator();
			   while(it.hasNext()) {
				   Items item = it.next();
				   String con=xiao+"."+item.getQuestion()+"  "+item.getAnswer()+"\n";
				   ms.insertText(con);
				   ms.moveEnd();
				   xiao++;
			   }
			   da++;
			   ms.insertText("\n\n");
			   ms.moveEnd();
		   }
		   
		   if(!panList.isEmpty()) {
			   String DaTitle=DaTi(da)+"判断题";
			   ms.insertText(DaTitle+"\n");
			   ms.moveEnd();
			   Iterator<Items> it = panList.iterator();
			   while(it.hasNext()) {
				   Items item = it.next();
				   String con=xiao+". ( "+item.getAnswer()+" ) "+item.getQuestion()+"\n";
				   ms.insertText(con);
				   ms.moveEnd();
				   xiao++;
			   }
			   da++;
			   ms.insertText("\n\n");
			   ms.moveEnd();
		   }
		   
		   
		   if(!jianList.isEmpty()) {
			   String DaTitle=DaTi(da)+"简答题";
			   ms.insertText(DaTitle+"\n");
			   ms.moveEnd();
			   Iterator<Items> it = jianList.iterator();
			   while(it.hasNext()) {
				   Items item = it.next();
				   String con=xiao+". "+item.getQuestion()+"\n"+"  答:"+item.getAnswer()+"\n";
				   ms.insertText(con);
				   ms.moveEnd();
				   xiao++;
			   }
			   da++;
			   ms.insertText("\n\n");
			   ms.moveEnd();
		   }
		   ms.save("C:\\Users\\dolly\\Desktop\\"+title+"答案.doc"); 
		   ms.close();     
		   ms.closeDocument();     
	}
	
	
	public static String DaTi(int i) {
		String str="";
		if(i==1) {str="一、";}
		if(i==2) {str="二、";}
		if(i==3) {str="三、";}
		if(i==4) {str="四、";}
		if(i==5) {str="五、";}
		if(i==6) {str="六、";}
		if(i==7) {str="七、";}
		if(i==8) {str="八、";}
		if(i==9) {str="九、";}
		return str;
	}
	
}
