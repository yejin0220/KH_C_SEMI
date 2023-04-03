package com.kh.mateboard.controller;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.Attachment;
import com.kh.mateboard.model.service.mateBoardService;
import com.kh.mateboard.model.vo.Board;

/**
 * Servlet implementation class MateBoardDetailController
 */
@WebServlet("/detail.mate")
public class MateBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MateBoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		//조회수 증가
		int result = new mateBoardService().increaseCount(boardNo);
		
		if(result>0) {
			Board b = new mateBoardService().selectBoard(boardNo);
			Attachment at = new mateBoardService().selectAttachment(boardNo);
			
			request.setAttribute("b", b);
			request.setAttribute("at", at);
			
			request.getRequestDispatcher("views/mateboard/mateboardDetail.jsp").forward(request, response);
			
			
		}else {
			request.setAttribute("errorMsg", "게시글 조회 실패");
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
