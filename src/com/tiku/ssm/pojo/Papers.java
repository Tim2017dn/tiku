package com.tiku.ssm.pojo;

import java.io.Serializable;

public class Papers implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer pid;
	private String pname;
	private String creater;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	
}
