package com.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.vo.Attachment;
import com.kh.common.MyFileRenamePolicy;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.kh.pet.model.service.PetService;
import com.kh.pet.model.vo.Pet;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UserProfileController
 */
@WebServlet("/userprofileupdate")
public class UserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Member loginUser = (Member) session.getAttribute("loginUser");
		Attachment at = new MemberService().memberListImg(loginUser.getUserId());
		
		if(at != null) {
			loginUser.setFileName(at.getFilePath() + at.getChangeName());
		}
		
		request.getRequestDispatcher("views/member/Mypage.jsp").forward(request, response);
	}
		
		
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10*1024*1024;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/profile_upfiles/");
			
			MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			HttpSession session = request.getSession();
			
			Member loginUser = (Member) session.getAttribute("loginUser");
			String key = "file";
			int userNo = Integer.parseInt(multi.getParameter("userNo"));
			String fileName = multi.getParameter("fileName");
	
			Attachment at = new Attachment();
			at.setOriginName(multi.getOriginalFileName(key));
			at.setChangeName(multi.getFilesystemName(key));
			at.setFilePath("/resources/profile_upfiles/");
			
			int result = new MemberService().insertProfileImg(userNo,at);
			
			if(result>0) {
				loginUser.setFileName(at.getFilePath()+at.getChangeName());
				
				session.setAttribute("loginUser", loginUser);
				request.getRequestDispatcher("views/member/Mypage.jsp").forward(request, response);
			} else {
				loginUser.setFileName(fileName);
			}
	
		}
	}
	}


