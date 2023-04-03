package com.kh.mateboard.model.service;

import java.sql.Connection;   
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.model.PageInfo;
import com.kh.mateboard.model.dao.mateBoardDao;
import com.kh.common.model.Attachment;
import com.kh.mateboard.model.vo.Board;

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
	
	public int insertMateBoard(Board b, ArrayList<Attachment> list) {
		Connection conn = getConnection();

		
		int result1 = new mateBoardDao().insertMateBoard(conn, b);
		
		int result2 = 1;
		
		if(list != null) {
			result2 = new mateBoardDao().insertAttachment(conn, list);
		}
		
		if(result1>0 && result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
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
	
	public Attachment selectAttachment(int boardNo) {
		Connection conn = getConnection();
		
		Attachment at = new mateBoardDao().selectAttachment(conn, boardNo);
		
		close(conn);
		
		return at;
	}
	
}
