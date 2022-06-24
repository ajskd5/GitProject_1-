package com.sist.data;

import java.io.Serializable;

/*
 *   NO      NOT NULL NUMBER         
TITLE   NOT NULL VARCHAR2(200)  
POSTER  NOT NULL VARCHAR2(300)  
MSG     NOT NULL VARCHAR2(4000) 
ADDRESS NOT NULL VARCHAR2(300)
 */
public class SeoulDataVO implements Serializable{
    private int no, cno;
    public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	private String title,poster,msg,address;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
  
}
