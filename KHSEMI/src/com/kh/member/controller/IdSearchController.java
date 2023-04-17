package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.kh.pet.model.vo.Pet;

/**
 * Servlet implementation class IdSearchController
 */
@WebServlet("/idSearch.me")
public class IdSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdSearchController() {
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
		
		String inputName = request.getParameter("userName");
		String inputEmail = request.getParameter("email");
		
		String result = new MemberService().searchMemberId(inputName, inputEmail);
		
		if( !(result == "NNNNNN") ) {
			request.setAttribute("alertMsg", "아이디 찾기에 성공했습니다.");
			request.setAttribute("userId", result);
			
			request.getRequestDispatcher("views/member/memberIdSearch1.jsp").forward(request,response);
			return;
		}
		
		request.setAttribute("errorMsg", "아이디 찾기에 실패했습니다.");
		request.getRequestDispatcher("views/member/memberIdSearch1.jsp").forward(request, response);
	}

}
