package com.kh.chat.model.vo;

import java.sql.Date;

public class Chatroom {

   private   int chatroomNo; //   CR_NO
   private String chatroomName; //   CR_NAME
   private Date createDate; //   CREATE_DATE
   private String status;  //   STATUS
   private String seller; //   SELLER 작성시 회원번호(user_no), 조회시 회원아이디(user_id)로 조회
   private String buyer; //   BUYER 작성시 회원번호(user_no), 조회시 회원아이디(user_id)로 조회
   
   public Chatroom() {
      super();
   }
   public Chatroom(int chatroomNo, String chatroomName, Date createDate, String status, String seller, String buyer) {
      super();
      this.chatroomNo = chatroomNo;
      this.chatroomName = chatroomName;
      this.createDate = createDate;
      this.status = status;
      this.seller = seller;
      this.buyer = buyer;
   }
   
   public int getChatroomNo() {
      return chatroomNo;
   }
   public void setChatroomNo(int chatroomNo) {
      this.chatroomNo = chatroomNo;
   }
   public String getChatroomName() {
      return chatroomName;
   }
   public void setChatroomName(String chatroomName) {
      this.chatroomName = chatroomName;
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
   public String getSeller() {
      return seller;
   }
   public void setSeller(String seller) {
      this.seller = seller;
   }
   public String getBuyer() {
      return buyer;
   }
   public void setBuyer(String buyer) {
      this.buyer = buyer;
   }
}