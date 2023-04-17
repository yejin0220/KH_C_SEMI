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
 * Servlet implementation class AdminProfileController
 */
@WebServlet("/adminProfile")
public class AdminProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfileController() {
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
		
		request.getRequestDispatcher("views/admin/adminProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10*1024*1024;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/profile_upfiles/");
			
			MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			String key = "file";
			int userNo = Integer.parseInt(multi.getParameter("userNo"));
			String userName = multi.getParameter("userName");
			String userNickname = multi.getParameter("userNickname");
			String userId = multi.getParameter("userId");
			String email = multi.getParameter("email");
			String address = multi.getParameter("address");
			String pet = multi.getParameter("pet");
			String fileName = multi.getParameter("fileName");
			String userPwd = "";
			if(multi.getParameter("newPwd").equals("")) {
				userPwd = multi.getParameter("originPwd");
			}else {
				userPwd = multi.getParameter("newPwd");
			}
			
			Member m = new Member();
			m.setUserName(userName);
			m.setUserNickname(userNickname);
			m.setUserId(userId);
			m.setEmail(email);
			m.setAddress(address);
			m.setUserPwd(userPwd);
			
			Pet p = new Pet();
			p.setUserNo(userNo);
			p.setSpecies(pet);
			
			Pet updatePet = new PetService().updatePet(p);
			Member updateMem = new MemberService().updateMember(m);
			
			int result = 1;
			Attachment at = new Attachment();
			at.setOriginName(multi.getOriginalFileName(key));
			at.setChangeName(multi.getFilesystemName(key));
			at.setFilePath("/resources/profile_upfiles/");
			
			if(multi.getOriginalFileName(key) != null) {
				result = new MemberService().insertProfileImg(userNo, at);
				updateMem.setFileName(at.getFilePath()+at.getChangeName());
			} else {
				updateMem.setFileName(fileName);
			}
			
			if(result > 0) {
				
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", updateMem);
				session.setAttribute("pet", updatePet);
				
				request.getRequestDispatcher("views/admin/adminProfile.jsp").forward(request, response);
				
			} else {
				
				request.setAttribute("errorMsg", "관리자정보 수정 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		}
	}

}
