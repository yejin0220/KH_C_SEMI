package com.kh.reply.model.vo;

import java.util.Date;

public class Reply {

	private int reply_No;
	private int ref_Bno;
	private int reply_Writer;
	private String reply_Content;
	private Date create_Date;;
	private String status;
	
	
	public Reply() {
		super();
	
	}


	public Reply(int reply_No, int ref_Bno, int reply_Writer, String reply_Content, Date create_Date, String status) {
		super();
		this.reply_No = reply_No;
		this.ref_Bno = ref_Bno;
		this.reply_Writer = reply_Writer;
		this.reply_Content = reply_Content;
		this.create_Date = create_Date;
		this.status = status;
	}


	public int getReply_No() {
		return reply_No;
	}


	public void setReply_No(int reply_No) {
		this.reply_No = reply_No;
	}


	public int getRef_Bno() {
		return ref_Bno;
	}


	public void setRef_Bno(int ref_Bno) {
		this.ref_Bno = ref_Bno;
	}


	public int getReply_Writer() {
		return reply_Writer;
	}


	public void setReply_Writer(int reply_Writer) {
		this.reply_Writer = reply_Writer;
	}


	public String getReply_Content() {
		return reply_Content;
	}


	public void setReply_Content(String reply_Content) {
		this.reply_Content = reply_Content;
	}


	public Date getCreate_Date() {
		return create_Date;
	}


	public void setCreate_Date(Date create_Date) {
		this.create_Date = create_Date;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Reply [reply_No=" + reply_No + ", ref_Bno=" + ref_Bno + ", reply_Writer=" + reply_Writer
				+ ", reply_Content=" + reply_Content + ", create_Date=" + create_Date + ", status=" + status + "]";
	}
	
	
	
}
