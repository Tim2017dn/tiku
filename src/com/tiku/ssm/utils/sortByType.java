package com.tiku.ssm.utils;

import java.util.Comparator;

import com.tiku.ssm.pojo.Items;

public class sortByType implements Comparator {
	
	public static int Trans(String type) {
		if(type.equals("填空题"))return 1;
		if(type.equals("判断题"))return 2;
		if(type.equals("简答题"))return 3;
		return 0;
		
	}
	
	public int compare(Object o1, Object o2) {
		 if(Trans( ((Items)o1).getType())> Trans(((Items)o2).getType())) {
			 return 1;
		 }
		 return -1;
	    }

}
