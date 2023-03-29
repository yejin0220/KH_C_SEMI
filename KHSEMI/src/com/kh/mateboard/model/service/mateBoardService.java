package com.kh.mateboard.model.service;

import java.sql.Connection; 
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.mateboard.model.dao.mateBoardDao;
import com.kh.mateboard.model.vo.Board;

public class mateBoardService {
	
	public int selectListCount(){
		
		Connection conn = getConnection();
		
		int listCount = new mateBoardDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
				
	}
	
	public ArrayList<Board> selectMateList(){
		Connection conn = getConnection();
		
		ArrayList<Board> list = new mateBoardDao().selectMateList(conn);
		
		close(conn);
		

		return list;
	}
	
	public int insertMateBoard(Board b) {
		Connection conn = getConnection();
		
		int result = new mateBoardDao().insertMateBoard(conn, b);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
		
		
		
		
	}
	
	
}
