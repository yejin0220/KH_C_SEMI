package com.kh.mateboard.model.dao;

import java.io.FileInputStream;  
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.kh.mateboard.model.vo.Board;

import static com.kh.common.JDBCTemplate.close;

public class mateBoardDao {
	
	private Properties prop = new Properties();
	
	public mateBoardDao() {
		
		try {
			prop.loadFromXML(new FileInputStream (mateBoardDao.class.getResource("/sql/board/board-mapper.xml").getPath()));
			
			
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
		
	}
	
	public ArrayList<Board> selectMateList(Connection conn){
		
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMateList");
		
		try {
			pstmt=conn.prepareStatement(sql);
					
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
						b.setBoardNo(rset.getInt("BOARD_NO"));
						b.setBoardTitle(rset.getString("BOARD_TITLE"));
						b.setBoardContent(rset.getString("BOARD_CONTENT"));
						b.setBoardWriter(rset.getString("USER_NICKNAME"));
						b.setCount(rset.getInt("COUNT"));
						b.setCreateDate(rset.getDate("CREATE_DATE"));
						b.setAddress(rset.getString("ADDRESS"));
				list.add(b);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
	
	public int insertMateBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	
}
