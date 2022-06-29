package com.sist.curd;
/*
 * NO                                                                                                                NOT NULL NUMBER
 NAME                                                                                                              NOT NULL VARCHAR2(34)
 SUBJECT                                                                                                           NOT NULL VARCHAR2(2000)
 CONTENT                                                                                                           NOT NULL CLOB
 PWD                                                                                                               NOT NULL VARCHAR2(10)
 REGDATE                                                                                                                    DATE
 HIT                                                                                                                        NUMBER
 
 	문자형 : char, varchar2, clob	=> String
 	숫자형 : number, number(7, 2)	=> int, double
 	날짜형 : date	=>
	
 */
import java.util.*;
public class BoardVO {
	private int no;
	private String name;
	private String subject;
	private String content;
	private String pwd;
	private Date regdate;
	private int hit;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
}
