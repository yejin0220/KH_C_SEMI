package com.kh.member.model.dao;

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

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.chat.model.vo.Chatroom;
import com.kh.common.JDBCTemplate;

import static com.kh.common.JDBCTemplate.*;
import com.kh.member.model.vo.Member;
import com.kh.pet.model.vo.Pet;
import com.kh.reply.model.vo.Reply;
import com.kh.save.model.vo.Save;

public class MemberDao {

	private Properties prop = new Properties();
	
	public MemberDao() {
	
		String fileName = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member loginMember(Connection conn, String userId, String userPwd) {
		
		Member m = null;
		
		ResultSet rset = null;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_PWD"),
						rset.getString("USER_NAME"),
						rset.getString("USER_NICKNAME"),
						rset.getString("PHONE"),
						rset.getString("EMAIL"),
						rset.getString("ADDRESS"),
						rset.getString("HOBBY"),
						rset.getDate("ENROLL_DATE"),
						rset.getDate("MODIFY_DATE"),
						rset.getString("STATUS"),
						rset.getString("SPECIES"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return m;
		
	}
	
	public int updateMember(Connection conn, Member m) {
		
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getUserNickname());
			pstmt.setString(3, m.getUserPwd());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
		
	}

	public Member selectMember(Connection conn, String userId) {
		
		Member m = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_PWD"),
						rset.getString("USER_NAME"),
						rset.getString("USER_NICKNAME"),
						rset.getString("PHONE"),
						rset.getString("EMAIL"),
						rset.getString("ADDRESS"),
						rset.getString("HOBBY"),
						rset.getDate("ENROLL_DATE"),
						rset.getDate("MODIFY_DATE"),
						rset.getString("STATUS"),
						rset.getString("SPECIES"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
		
	}
	
	public int deleteMember(Connection conn, String userId, String userPwd) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertProfileImg(Connection conn, int userNo, Attachment at) {
		
		int result = 1;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertProfileImg");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Attachment> selectProfileImg(Connection conn, int userNo){
		
		ArrayList<Attachment> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProfileImg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				Attachment at = new Attachment();
				at.setFilePath(rset.getString("FILE_PATH"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				
				list.add(at);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Member> selectMemberList(Connection conn){
		
		ArrayList<Member> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMemberList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Member m = new Member(rset.getInt("USER_NO"),
						              rset.getString("USER_ID"),
						              rset.getString("USER_PWD"),
						              rset.getString("USER_NAME"),
						              rset.getString("USER_NICKNAME"),
						              rset.getString("PHONE"),
						              rset.getString("EMAIL"),
						              rset.getString("ADDRESS"),
						              rset.getString("HOBBY"),
						              rset.getDate("ENROLL_DATE"),
						              rset.getDate("MODIFY_DATE"),
						              rset.getString("STATUS"),
						              rset.getString("SPECIES"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Board> selectBoardList(Connection conn, int boardWriter){
		
		ArrayList<Board> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("myPageBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardWriter);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));		              
				b.setBoardWriter(rset.getString("USER_NICKNAME"));					  				
				b.setCreateDate(rset.getDate("CREATE_DATE"));		              
				b.setCount(rset.getInt("COUNT"));		              
				
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
	
	public ArrayList<Board> selectMBoardList(Connection conn, int boardWriter){
		
		ArrayList<Board> mlist = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("myPageMBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardWriter);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board m = new Board();
				m.setBoardNo(rset.getInt("BOARD_NO"));
				m.setBoardTitle(rset.getString("BOARD_TITLE"));		              
				m.setBoardWriter(rset.getString("USER_NICKNAME"));					  				
				m.setCreateDate(rset.getDate("CREATE_DATE"));		              	              
				
				mlist.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return mlist;
	}
	
	
	public ArrayList<Chatroom> selectChatroomList(Connection conn, int seller){
		
		ArrayList<Chatroom> clist = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("myPageChatList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, seller);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Chatroom c = new Chatroom();
				c.setChatroomNo(rset.getInt("CR_NO"));
				c.setChatroomName(rset.getString("CR_NAME"));		              
				c.setBuyer(rset.getString("USER_NICKNAME"));					  				
				c.setCreateDate(rset.getDate("CREATE_DATE"));		              
						              
				
				clist.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
	
		return clist;
	}
	
	public ArrayList<Save> selectSaveList(Connection conn, int userNo){
		
		ArrayList<Save> slist = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("mySaveList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Save s = new Save();
				s.setBoardNo(rset.getInt("BOARD_NO"));
				s.setBoardTitle(rset.getString("BOARD_TITLE"));
				s.setUserNo(rset.getInt("USER_NO"));		              
				s.setBoardContent(rset.getString("BOARD_CONTENT"));
						              					              
				slist.add(s);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
	
		return slist;
	}
	
	
	public ArrayList<Reply> selectReplyList(Connection conn, int userNo){
		
		ArrayList<Reply> rlist = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("myReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply r = new Reply();
				r.setReply_No(rset.getInt("REPLY_NO"));
				r.setRef_Bno(rset.getInt("REF_BNO"));		              
				r.setReply_Content(rset.getString("REPLY_CONTENT"));
				r.setCreate_Date(rset.getDate("CREATE_DATE"));
						              					              
				rlist.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return rlist;
	}

	
	public Attachment memberListImg(Connection conn, String userId) {
		
		Attachment at = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("memberListImg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment();
				at.setFilePath(rset.getString("FILE_PATH"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return at;
		
	}
	
	
	public int updateStatusM(Connection conn, String userId, String status) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateStatusM");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
		
	}
	
	public String selectStatus(Connection conn, String userId) {
		
		String updateStatus = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				updateStatus = rset.getString("STATUS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return updateStatus;
	}
	
	// 아이디 중복체크
		public int idCheck(Connection conn, String userId) {
			
			int count = 0 ;
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null; 
			
			String sql = prop.getProperty("idCheck");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					count = rset.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return count;
		}
		
		// 닉네임 중복체크
		public int nickCheck(Connection conn, String userNickname) {
			
			int count1 = 0 ;
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null; 
			
			String sql = prop.getProperty("nickCheck");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userNickname);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					count1 = rset.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return count1;
		}
		
		// 이메일 중복체크
		public int emailCheck(Connection conn, String email) {
				
			int count1 = 0 ;
				
			PreparedStatement pstmt = null;
				
			ResultSet rset = null; 
				
			String sql = prop.getProperty("emailCheck");
				
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
					
				rset = pstmt.executeQuery();
					
				if(rset.next()) {
					count1 = rset.getInt(1);
				}
					
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return count1;
		}
			
		// 회원가입 멤버정보 삽입
		public int insertMember(Connection conn, Member m) {
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("insertMember");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, m.getUserId());
				pstmt.setString(2, m.getUserName());
				pstmt.setString(3, m.getUserNickname());
				pstmt.setString(4, m.getUserPwd());
				pstmt.setString(5, m.getEmail());
				pstmt.setString(6, m.getPhone());
				pstmt.setString(7, m.getAddress());
				pstmt.setString(8, m.getHobby());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		// 회원가입 펫정보 삽입
		public int insertPet(Connection conn, Pet p) {
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("insertPet");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, p.getSpecies());
				pstmt.setString(2, p.getGender());
				pstmt.setString(3, p.getPetName());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		// 아이디 찾기
		public String searchMemberId(String inputName, String inputEmail) {
			String userId = "NNNNNN";
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			
			ResultSet rset = null; 
	        
	        String sql = prop.getProperty("searchMemberId");
	        
	        try {
	            pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setString(1, inputName);
	            pstmt.setString(2, inputEmail);
	            
	            rset = pstmt.executeQuery();
	            
	            if (rset.next()){
	            	userId = rset.getString("USER_ID");
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	        	close(rset);
				close(pstmt);
				close(conn);
				
	        }
	        
	        return userId;
	    }
		
		// 비밀번호 찾기 1-1 (이메일 찾기)
		public String searchMemberEmail(String inputId, String inputName) {
			String userEmail = "NNNNNN";
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			
			ResultSet rset = null; 
	        
	        String sql = prop.getProperty("searchMemberEmail");
	        
	        try {
	            pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setString(1, inputId);
	            pstmt.setString(2, inputName);
	            
	            rset = pstmt.executeQuery();
	            
	            if (rset.next()){
	            	userEmail = rset.getString("EMAIL");
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	        	close(rset);
				close(pstmt);
				close(conn);
				
	        }
	        
	        return userEmail;
	    }
		
		// 비밀번호 찾기 1-2 (비밀번호 정보변경)
		public int UpdateMemberPwd(Member member) {
			
			int result = 0;
			
			Connection conn = getConnection();
			
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("UpdateMemberPwd");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, member.getUserPwd());
				pstmt.setString(2, member.getUserId());
				pstmt.setString(3, member.getUserName());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(conn);
			}
			
			return result;
			
		}
	
		
}
