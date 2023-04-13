package com.kh.mateboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.mateboard.model.service.mateBoardService;
import com.kh.mateboard.model.vo.BoardLike;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class RecommendController
 */
@WebServlet("/recommend")
public class RecommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendController() {
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

		response.setCharacterEncoding("UTF-8");
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		int result = new mateBoardService().selectRecommend(boardNo, userNo);
		int result2 = 0;
		if(result == 0) { //��õ���� �ִ��� �������� ���� �˻�
			result2 = new mateBoardService().insertRecommend(boardNo, userNo); 
			
		}else {
			result2 = new mateBoardService().deleteRecommend(boardNo, userNo); 
		}
		
		if(result2>0) {
			
			response.getWriter().print(result2);
		}
			
		
	}

}
