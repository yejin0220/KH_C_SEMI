package com.kh.board.mateboard.model.dao;

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
import com.kh.board.model.vo.Attachment;
import com.kh.board.mateboard.model.vo.Board;
import com.kh.board.mateboard.model.vo.BoardLike;
import com.kh.board.mateboard.model.vo.Reply;

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
						b.setLcount(rset.getInt("B_LIKE"));
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
	
	public Attachment selectMateProfile(Connection conn, int boardWriter) {
		Attachment at = new Attachment();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		System.out.println(at);
		String sql = prop.getProperty("seleteMateProfile");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardWriter);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at.setRef_Mno(rset.getInt("REF_MNO"));
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
				at.setBoardNo(rset.getInt("BOARD_NO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}return at;
		
	}
	
	public int insertMateBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println(b);
		
		String sql = prop.getProperty("insertMateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getBoardWriter());
			pstmt.setString(4, b.getAddress());
			pstmt.setDouble(5, b.getLatitude());
			pstmt.setDouble(6, b.getLongitude());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
	public int insertAttachment(Connection conn, ArrayList<Attachment> atList) {
		
		
		int result = 1;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			for(Attachment atImg : atList) {
				pstmt.setString(1, atImg.getOriginName());
				pstmt.setString(2, atImg.getChangeName());
				pstmt.setString(3, atImg.getFilePath());
				pstmt.setInt(4, atImg.getFileLevel());
				
				result *= pstmt.executeUpdate();
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
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
							  rset.getDouble("LONGITUDE"),
							  rset.getInt("COUNT"));
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}
	
	public ArrayList<Attachment> selectAttachment(Connection conn, int boardNo) {
		ArrayList<Attachment> atList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
			Attachment atImg = new Attachment();
				atImg.setRefBno(rset.getInt("REF_BNO"));
				atImg.setFileNo(rset.getInt("FILE_NO"));
				atImg.setOriginName(rset.getString("ORIGIN_NAME"));
				atImg.setChangeName(rset.getString("CHANGE_NAME"));
				atImg.setFilePath(rset.getString("FILE_PATH"));
				atImg.setFileLevel(rset.getInt("FILE_LEVEL"));
				
				atList.add(atImg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return atList;
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
	
	public int updateAttachment(Connection conn, ArrayList<Attachment> atList) {
		int result =1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			for(Attachment atImg : atList) {
				pstmt.setString(1, atImg.getOriginName());
				pstmt.setString(2, atImg.getChangeName());
				pstmt.setString(3, atImg.getFilePath());
				pstmt.setInt(4, atImg.getFileLevel());
				pstmt.setInt(5, atImg.getFileNo());
				atList.add(atImg);
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateAttachmentDelete(Connection conn, ArrayList<Integer> originFileNos) {
		int result =1;
		PreparedStatement pstmt =null;
		String sql = prop.getProperty("updateAttachmentDelete");
		
		try {
			pstmt = conn.prepareStatement(sql);
			for(int no : originFileNos) {
				pstmt.setInt(1, no);
				
				result *=pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
		
	}

	
	public int insertReply(Connection conn, Reply r) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReply");
		try {
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setInt(1, r.getRefBno());
			pstmt.setInt(2, r.getReplyWriter());
			pstmt.setString(3, r.getReplyContent());
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public ArrayList<Reply> selectReplyList(Connection conn, int boardNo){
		ArrayList<Reply> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Reply(
							rset.getInt("REPLY_NO"),
							rset.getInt("REF_BNO"),
							rset.getString("REPLY_CONTENT"),
							rset.getDate("CREATE_DATE"),
							rset.getString("STATUS"),
							rset.getString("USER_NICKNAME"),
							rset.getString("USER_ID")
						));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	
	public int insertRecommend(Connection conn, int boardNo, int userNo) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertRecommend");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, userNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int selectRecommend(Connection conn, int boardNo, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectRecommend");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, boardNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("COUNT");
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public int deleteRecommend(Connection conn, int boardNo, int userNo) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteRecommend");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, userNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
		
	public int selectRecommendCount(Connection conn, int boardNo) {
		int Lcount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectRecommendCount");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				Lcount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}return Lcount;
	}
	
	public int deleteMate(Connection conn, int boardNo, int userNo) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMate");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, userNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
		
	}
	
	public int deleteAttachment(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteReply(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteReply");
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
	
	public int deleteDetailReply(Connection conn, int rno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteDetailReply");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
}

