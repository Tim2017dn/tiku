package com.tiku.ssm.pojo;

import java.io.Serializable;

public class Items implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String question;
	private String answer;
	private String type;
	private float dif;
	private String subject;
	private Integer start;
	
	private Integer rows;
	
	
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getDif() {
		return dif;
	}
	public void setDif(float dif) {
		this.dif = dif;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
}
