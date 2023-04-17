package com.kh.member.model.service;

import java.sql.SQLException ;
import java.sql.Connection;  
import java.util.ArrayList;


import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.chat.model.vo.Chatroom;
import com.kh.common.JDBCTemplate;

import static com.kh.common.JDBCTemplate.*;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
import com.kh.pet.model.vo.Pet;
import com.kh.reply.model.vo.Reply;
import com.kh.save.model.vo.Save;

public class MemberService {
	
	public Member loginMember(String userId, String userPwd) {
		Connection conn = getConnection();
		
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return m;
		
	}
	
	public Member updateMember(Member m) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result > 0) {
			commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, m.getUserId());
			
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem;
		
	}
	
	public int deleteMember(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	public int insertProfileImg(int userNo, Attachment at) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().insertProfileImg(conn, userNo, at);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}
	
	public ArrayList<Attachment> selectProfileImg(int userNo){
		
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new MemberDao().selectProfileImg(conn, userNo);
		
		close(conn);
		
		return list;
		
	}
	
	public ArrayList<Member> selectMemberList(){
		
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberDao().selectMemberList(conn);
		
		close(conn);
		
		return list;
			
	}
	
	public ArrayList<Board> selectBoardList(int boardWriter){
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new MemberDao().selectBoardList(conn, boardWriter);
		
		close(conn);
		
		
		return list;
			
	}
	
	public ArrayList<Board> selectMBoardList(int boardWriter){
		
		Connection conn = getConnection();
		
		ArrayList<Board> mlist = new MemberDao().selectMBoardList(conn, boardWriter);
		
		close(conn);
		
		
		return mlist;
			
	}
	
	public ArrayList<Chatroom> selectChatroomList(int seller){
		
		Connection conn = getConnection();
		
		ArrayList<Chatroom> clist = new MemberDao().selectChatroomList(conn, seller);
		
		close(conn);
		
		System.out.println(seller);
		return clist;
			
	}
	
	public ArrayList<Save> selectSaveList(int userNo){
		
		Connection conn = getConnection();
		
		ArrayList<Save> slist = new MemberDao().selectSaveList(conn, userNo);
		
		close(conn);
		
		System.out.println(userNo);
		
		return slist;
			
	}
	
	public ArrayList<Reply> selectReplyList(int userNo){
		
		Connection conn = getConnection();
		
		ArrayList<Reply> rlist = new MemberDao().selectReplyList(conn, userNo);
		
		close(conn);
		
		return rlist;
			
	}
	
	public Member memberListDetail(String userId) {
		Connection conn = getConnection();
		
		Member m = new MemberDao().selectMember(conn, userId);
		
		close(conn);
		
		return m;
	}
	
	public Attachment memberListImg(String userId){
		Connection conn = getConnection();
		
		Attachment at = new MemberDao().memberListImg(conn, userId);
		
		close(conn);
		
		return at;
	}
	
	
	
	
	public String updateStatusM(String status, String userId) {
		Connection conn = getConnection();
		
		
		int result =  new MemberDao().updateStatusM(conn, userId, status);
		
		String updateStatus = null;
		
		if(result > 0) {
			commit(conn);
			
			updateStatus = new MemberDao().selectStatus(conn, userId);
			
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateStatus;
	}
	
	// �븘�씠�뵒 以묐났泥댄겕
		public int idCheck(String userId) {

			Connection conn = JDBCTemplate.getConnection();

			int count = new MemberDao().idCheck(conn, userId);

			JDBCTemplate.close(conn);

			return count;
		}

		// �땳�꽕�엫 以묐났泥댄겕
		public int nickCheck(String userNickname) {

			Connection conn = JDBCTemplate.getConnection();

			int count1 = new MemberDao().nickCheck(conn, userNickname);

			JDBCTemplate.close(conn);

			return count1;
		}
		
		// �씠硫붿씪 以묐났泥댄겕
		public int emailCheck(String email) {

			Connection conn = JDBCTemplate.getConnection();

			int count1 = new MemberDao().emailCheck(conn, email);

			JDBCTemplate.close(conn);

			return count1;
		}

		// �쉶�썝媛��엯 硫ㅻ쾭, �렖�젙蹂� �궫�엯
		public int insertMember(Member m, Pet p) {

			Connection conn = JDBCTemplate.getConnection();

			int result1 = new MemberDao().insertMember(conn, m);
			int result2 = 1;

			if (p != null) {
				result2 = new MemberDao().insertPet(conn, p);
			}

			if (result1 > 0 && result2 > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

			JDBCTemplate.close(conn);

			return result1 * result2;
		}

		MemberDao dao = new MemberDao();
		
		// �씠由�,�씠硫붿씪濡� �븘�씠�뵒 李얘린
		public String searchMemberId(String inputName, String inputEmail) { 
			return dao.searchMemberId(inputName, inputEmail);
		}
		
		// �븘�씠�뵒,�씠由꾩쑝濡� �씠硫붿씪 李얘린
		public String searchMemberEmail(String inputId, String inputName) { 
			return dao.searchMemberEmail(inputId, inputName);
		}
		
		// 鍮꾨�踰덊샇 �뾽�뜲�씠�듃
		public int UpdateMemberPwd(Member member) { 
			return dao.UpdateMemberPwd(member);
		}

		
	
}
