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

import com.kh.common.model.PageInfo;
import com.kh.common.model.Attachment;
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
	
	public ArrayList<Board> selectMateList(Connection conn, PageInfo pi){
		
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMateList");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit()+1;
			int endRow = startRow+pi.getBoardLimit()-1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
					
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
			pstmt.setInt(3, Integer.parseInt(b.getBoardWriter()));
			pstmt.setString(4, b.getAddress());
			pstmt.setDouble(5, b.getLatitude());
			pstmt.setDouble(6, b.getLongitude());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		System.out.println(result);
		return result;
	}
	
	public int insertAttachment(Connection conn, ArrayList<Attachment> list) {
		
		
		int result = 1;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			for(Attachment at : list) {
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
			}
			
			
			result *= pstmt.executeUpdate();
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		System.out.println(result);
		return result;
		
		
	}
	
	public int increaseCount(Connection conn, int boardNo) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
		
		
	}
	
	
	public Board selectBoard(Connection conn, int boardNo) {
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board(rset.getInt("BOARD_NO"),
							  rset.getString("BOARD_TITLE"),
							  rset.getString("BOARD_CONTENT"),
							  rset.getString("USER_NICKNAME"),
							  rset.getDate("CREATE_DATE"),
							  rset.getString("ADDRESS"),
							  rset.getDouble("LATITUDE"),
							  rset.getDouble("LONGITUDE"));
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}
	
	public Attachment selectAttachment(Connection conn, int boardNo) {
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return at;
		
		
	}
	
	public int updateBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getAddress());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setDouble(4, b.getLatitude());
			pstmt.setDouble(5, b.getLongitude());
			pstmt.setInt(6, b.getBoardNo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
		
		
	}
	
	public int updateAttachment(Connection conn, Attachment at) {
		int result =1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
		
	}
	
	public int insertNewAttachment(Connection conn, ArrayList<Attachment> list) {
		int result=1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(Attachment at : list) {
				pstmt.setInt(1, at.getRefBno());
				pstmt.setString(2, at.getOriginName());
				pstmt.setString(3, at.getChangeName());
				pstmt.setString(4, at.getFilePath());
			}
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}

}
