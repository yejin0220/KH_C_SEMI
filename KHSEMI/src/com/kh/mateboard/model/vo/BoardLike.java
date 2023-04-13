package com.kh.mateboard.model.vo;

public class BoardLike {

	private int boardNo;
	private int userNo;
	
	public BoardLike() {
		super();
	}

	public BoardLike(int boardNo, int userNo) {
		super();
		this.boardNo = boardNo;
		this.userNo = userNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "BoardLike [boardNo=" + boardNo + ", userNo=" + userNo + "]";
	}
	
	
	
}
