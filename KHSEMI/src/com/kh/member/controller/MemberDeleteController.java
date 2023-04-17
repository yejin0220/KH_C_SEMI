package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/deleteMem")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String userPwd = request.getParameter("userPwd");
		String userId  = ((Member)session.getAttribute("loginUser")).getUserId();
		
		// 로그인한 회원의 정보를 얻어오는 방법
		// 방법 1. session영역에 담겨있는 회원객체로부터 뽑아내기
		// 방법 2. input type="hidden" 회원정보를 숨겨서 요청시 함께 전달하기
		
		int result = new MemberService().deleteMember(userId, userPwd);
		System.out.println(userId);
		System.out.println(userPwd);
		if(result > 0) { 
			
			session.setAttribute("alertMsg","성공적으로 회원탈퇴 되었개냥. 그 동안 만나서 반가웠개냥! 다음에 또 보개냥!");
			
			session.removeAttribute("loginUser");
			
			response.sendRedirect(request.getContextPath());
			
			
		}else { 
			
			session.setAttribute("alertMsg","회원탈퇴에 실패했습니다.");
			
			response.sendRedirect( request.getContextPath()  +"/updateuser.me");
		}
	}

	

}
