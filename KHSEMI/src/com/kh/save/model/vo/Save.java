package com.kh.save.model.vo;

public class Save {

	private int userNo;
	private int boardNo;
	private String boardTitle;
	private String sale;
	private String boardContent;
	
	public Save() {
		super();
		
	}

	
	public Save(int userNo, int boardNo, String boardTitle, String sale, String boardContent) {
		super();
		this.userNo = userNo;
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.sale = sale;
		this.boardContent = boardContent;
	}


	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	@Override
	public String toString() {
		return "Save [userNo=" + userNo + ", boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", sale=" + sale
				+ ", boardContent=" + boardContent + "]";
	}

	
	
}