package com.kh.board.model.vo;

import java.sql.Date;

public class Attachment {

	private int fileNo; //	FILE_NO
	private int refBno; //	REF_BNO
	private int ref_Mno; //	REF_MNO
	private String originName; //	ORIGIN_NAME
	private String changeName; //	CHANGE_NAME	
	private String filePath; //	FILE_PATH
	private Date uploadDate; //	UPLOAD_DATE
	private int fileLevel; //	FILE_LEVEL
	private String status; //	STATUS
	private int boardNo;
	
	public Attachment() {
		super();
	}
	public Attachment(int fileNo, int refBno, int ref_Mno, String originName, String changeName, String filePath,
			Date uploadDate, int fileLevel, String status) {
		super();
		this.fileNo = fileNo;
		this.refBno = refBno;
		this.ref_Mno = ref_Mno;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.status = status;
	}
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getRefBno() {
		return refBno;
	}
	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}
	public int getRef_Mno() {
		return ref_Mno;
	}
	public void setRef_Mno(int ref_Mno) {
		this.ref_Mno = ref_Mno;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public int getFileLevel() {
		return fileLevel;
	}
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", refBno=" + refBno + ", ref_Mno=" + ref_Mno + ", originName="
				+ originName + ", changeName=" + changeName + ", filePath=" + filePath + ", uploadDate=" + uploadDate
				+ ", fileLevel=" + fileLevel + ", status=" + status + "]";
	}
	
	
}
