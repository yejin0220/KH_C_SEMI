package com.kh.mateboard.model.service;

import java.sql.Connection;  
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.model.PageInfo;
import com.kh.mateboard.model.dao.mateBoardDao;
import com.kh.mateboard.model.vo.Attachment;
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
	
	public int insertMateBoard(Board b, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new mateBoardDao().insertMateBoard(conn, b);
		
		int result2 = 1;
		
		if(at != null) {
			result2 = new mateBoardDao().insertAttachmentmate(conn, at);
		}
		
		if(result1>0 && result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result1*result2;
		
		
		
	}
	
	
}
