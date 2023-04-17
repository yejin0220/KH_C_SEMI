package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.kh.utils.MailUtil;
import com.kh.utils.RandomPasswordUtil;

/**
 * Servlet implementation class PwdSearchController
 */
@WebServlet("/pwdSearch.me")
public class PwdSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdSearchController() {
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
		
		// 받아온 파라미터 값 
		String inputId = request.getParameter("userId");
		String inputName = request.getParameter("userName");
		
		// Member 객체 저장
		Member member = new Member();
		member.setUserId(inputId);
		member.setUserName(inputName);
		
		// 아이디와 이름으로 이메일 찾기 
		String userEmail = new MemberService().searchMemberEmail(inputId, inputName);
		
		// 성공 
		if( !(userEmail == "NNNNNN") ) {
			// 패스워드 업데이트를 위한 패스워드 생성
			String randomPwd = RandomPasswordUtil.getTempPassword();
			// 받아온 패스워드를 member 객체에 저장
			member.setUserPwd(randomPwd);
			// 패스워드 업데이트
			int result = new MemberService().UpdateMemberPwd(member);
			if( result > 0 ) {
				// 업데이트한 비밀번호를 메일로 전송한다.
				MailUtil.sendMailToSearchPwdMember(userEmail, randomPwd);		
			}
			request.setAttribute("alertMsg", "success");
			request.getRequestDispatcher("views/member/memberPwdSearch1.jsp").forward(request,response);
			return;
		}
		
		request.setAttribute("alertMsg", "fail");
		request.getRequestDispatcher("views/member/memberPwdSearch1.jsp").forward(request, response);
	}
	

}
