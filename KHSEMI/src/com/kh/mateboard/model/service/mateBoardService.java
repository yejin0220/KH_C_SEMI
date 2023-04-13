package com.kh.mateboard.model.service;

import java.sql.Connection;   
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.model.PageInfo;
import com.kh.mateboard.model.dao.mateBoardDao;
import com.kh.common.model.Attachment;
import com.kh.mateboard.model.vo.Board;
import com.kh.mateboard.model.vo.BoardLike;
import com.kh.mateboard.model.vo.Reply;

public class mateBoardService {
	
	public int selectListCount(){
		
		Connection conn = getConnection();
		
		int listCount = new mateBoardDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
				
	}
	
	
	public ArrayList<Board> selectMateList(PageInfo pi){
		Connection conn = getConnection();
		
		ArrayList<Board> list = new mateBoardDao().selectMateList(conn, pi);
		
		close(conn);
		return list;
	}
	
	public int insertMateBoard(Board b, ArrayList<Attachment> atList) {
		Connection conn = getConnection();
		
		int result1 = new mateBoardDao().insertMateBoard(conn, b);
		
		int result2 = new mateBoardDao().insertAttachment(conn, atList);
		

		if(result1>0 && result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1*result2;
		
		
	}
	
	public int increaseCount(int boardNo) {
		Connection conn = getConnection();
		
		int result = new mateBoardDao().increaseCount(conn, boardNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}
	
	public Board selectBoard(int boardNo) {
		Connection conn = getConnection();
		
		Board b = new mateBoardDao().selectBoard(conn, boardNo);
		
		close(conn);
		
		return b;
				
	}
	
	public ArrayList<Attachment> selectAttachment(int boardNo) {
		Connection conn = getConnection();
		
		ArrayList<Attachment> atList = new mateBoardDao().selectAttachment(conn, boardNo);
		
		close(conn);
		
		return atList;
	}
	
	
	public int updateMate(Board b, ArrayList<Attachment> atList, ArrayList<Integer> originFileNos) {
		Connection conn = getConnection();
		
		int result1 = new mateBoardDao().updateBoard(conn, b);
		int result2 = 1;
		int result3 = 1;
		
		
		if(atList.size()>0) {
			result2 = new mateBoardDao().updateAttachment(conn, atList);
			if(originFileNos != null) {
				result3 = new mateBoardDao().updateAttachmentDelete(conn, originFileNos);
			}
		}
		
		if(result1>0 && result2>0&&result3>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2 *result3;
	}
	
	public int insertReply(Reply r) {
		Connection conn = getConnection();
		int result = new mateBoardDao().insertReply(conn, r);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;

	}
	
	public ArrayList<Reply> selectReplyList(int boardNo){
		Connection conn = getConnection();
		ArrayList<Reply> list = new mateBoardDao().selectReplyList(conn, boardNo);
		close(conn);
		return list;
	}
	
	public int selectRecommend(int boardNo, int userNo) {
		Connection conn = getConnection();
		int result = new mateBoardDao().selectRecommend(conn, boardNo, userNo);
		close(conn);
		return result;
	}
	
	public int insertRecommend(int boardNo, int userNo) {
		Connection conn = getConnection();
		int result= new mateBoardDao().insertRecommend(conn, boardNo, userNo);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteRecommend(int boardNo, int userNo) {
		Connection conn = getConnection();
		int result = new mateBoardDao().deleteRecommend(conn, boardNo, userNo);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	

}
