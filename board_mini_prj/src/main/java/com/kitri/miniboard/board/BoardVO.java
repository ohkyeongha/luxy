package com.kitri.miniboard.board;

import java.util.Date;

public class BoardVO {
	private int bno;
	private String bTitle;
	private String bWriter;
	private String bContent;
	private Date bRegDate;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public Date getbRegDate() {
		return bRegDate;
	}
	public void setbRegDate(Date bRegDate) {
		this.bRegDate = bRegDate;
	}
	
	
}
