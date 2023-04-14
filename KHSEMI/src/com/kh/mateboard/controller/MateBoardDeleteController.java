package com.kh.mateboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mateboard.model.service.mateBoardService;
import com.kh.common.model.Attachment;
import com.kh.mateboard.model.vo.Reply;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MateBoardDeleteController
 */
@WebServlet("/deleteMate")
public class MateBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MateBoardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		int userNo = ((Member) request.getSession().getAttribute("loginUser")).getUserNo();
		ArrayList<Attachment> atList = new mateBoardService().selectAttachment(boardNo);
		ArrayList<Reply> list = new mateBoardService().selectReplyList(boardNo);
		
		int result= new mateBoardService().deleteMate(boardNo, userNo, atList, list);
				
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/list.mate");
		}else {
			request.setAttribute("errorMsg", "게시글 삭제 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
