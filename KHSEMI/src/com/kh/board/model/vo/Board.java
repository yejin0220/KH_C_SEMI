package com.kh.board.model.vo;

import java.sql.Date;

public class Board {

	private int boardNo; //	BOARD_NO
	private int boardType; //	BOARD_TYPE
	private String boardCategory; //	BOARD_CATEGORY 게시글 작성시 번호(category_no)를 그대로 받는 경우와 조회시 카테고리명(category_name)으로 조회하는 경우
	private String boardTitle; //	BOARD_TITLE
	private String boardContent; //	BOARD_CONTENT
	private String boardWriter; //	BOARD_WRITER 작성시 회원번호(user_no), 조회시 회원아이디(user_id)로 조회
	private int count; //	COUNT
	private Date createDate;//	CREATE_DATE
	private String sale; //	SALE
	private String address; //	ADDRESS
	private double latitude; //	LATITUDE
	private double longitude; //	LONGITUDE
	private String status; //	STATUS
	
	public Board() {
		super();
	}
	public Board(int boardNo, int boardType, String boardCategory, String boardTitle, String boardContent,
			String boardWriter, int count, Date createDate, String sale, String address, double latitude,
			double longitude, String status) {
		super();
		this.boardNo = boardNo;
		this.boardType = boardType;
		this.boardCategory = boardCategory;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.sale = sale;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.status = status;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getBoardType() {
		return boardType;
	}
	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	public String getBoardCategory() {
		return boardCategory;
	}
	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
