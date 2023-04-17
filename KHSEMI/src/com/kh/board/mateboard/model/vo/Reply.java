package com.kh.board.mateboard.model.vo;

import java.sql.Date;

public class Reply {

	private int replyNo;
	private int refBno;
	private int replyWriter;
	private String replyContent;
	private Date createDate;
	private String status;
	private String userNickname;
	private String userId;
	
	public Reply() {
		super();
	}

	
	
	
	public Reply(int replyNo, int refBno, String replyContent, Date createDate, String status, String userNickname, String userId ) {
		super();
		this.replyNo = replyNo;
		this.refBno = refBno;
		this.replyContent = replyContent;
		this.createDate = createDate;
		this.status = status;
		this.userNickname = userNickname;
		this.userId=userId;
	}




	public Reply(int replyNo, int refBno, int replyWriter, String replyContent, Date createDate, String status) {
		super();
		this.replyNo = replyNo;
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.replyContent = replyContent;
		this.createDate = createDate;
		this.status = status;
	}
	
	
	

	public Reply(int refBno, int replyWriter, String replyContent) {
		super();
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.replyContent = replyContent;
	}


	public int getReplyNo() {
		return replyNo;
	}


	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}


	public int getRefBno() {
		return refBno;
	}


	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}


	public int getReplyWriter() {
		return replyWriter;
	}


	public void setReplyWriter(int replyWriter) {
		this.replyWriter = replyWriter;
	}


	public String getReplyContent() {
		return replyContent;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	

	public String getUserNickname() {
		return userNickname;
	}




	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", refBno=" + refBno + ", replyWriter=" + replyWriter + ", replyContent="
				+ replyContent + ", createDate=" + createDate + ", status=" + status + ", userNickname=" + userNickname
				+ "]";
	}




	
	
	
	
}
