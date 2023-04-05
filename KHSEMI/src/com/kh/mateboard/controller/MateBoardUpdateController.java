package com.kh.mateboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.Attachment;
import com.kh.common.model.MyFileRenamePolicy;
import com.kh.mateboard.model.service.mateBoardService;
import com.kh.mateboard.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MateBoardUpdateController
 */
@WebServlet("/update.mate")
public class MateBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MateBoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		Board b = new mateBoardService().selectBoard(boardNo);
		Attachment at = new mateBoardService().selectAttachment(boardNo);
		
		request.setAttribute("b", b);
		request.setAttribute("at", at);
		
		request.getRequestDispatcher("views/mateboard/mateboardUpdate.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//���۵Ǵ� ������ ó���� �۾�����
		//1)�������Ͽ뷮����
		int maxSize = 1024*1024*10;
		
		//2)������ �����Ҽ����� ������� �˾Ƴ���(String savePath)
		String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
		
		//3)���޵� ���ϸ� ���� �۾� �� ���� ���ε�
		//MultupartRequest multi = new MultipartRequest(request ��ü, ������ ���� ���, �뷮����, ���ڵ� ������, ���ϸ��� ���������ִ� ��ü);
		MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		
		//4)db�� �ѱ� ������ �����ϱ�	
		int boardNo = Integer.parseInt(multi.getParameter("bno"));
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String address = multi.getParameter("address1")+","+multi.getParameter("address2");
		String userNo = multi.getParameter("userNo");
		
	
		Board b = new Board(boardNo ,title, content, address, userNo);
		
		ArrayList<Attachment> list = new ArrayList<>();
		
		
		if(multi.getOriginalFileName("upfile") != null) {
			
			Attachment at = new Attachment();
			at.setOriginName(multi.getOriginalFileName("upfile"));
			at.setChangeName(multi.getFilesystemName("upfile"));
			at.setFilePath("resources/board_upfiles/");
	       
			list.add(at);
	
				if(multi.getParameter("originFileNo") != null) {
					
					at.setFileNo(Integer.parseInt(multi.getParameter("originFileNo")));
					new File(savePath+multi.getParameter("changeFileName")).delete();
					
				}else {
					
					at.setRefBno(Integer.parseInt(multi.getParameter("bno")));
				}
			
			
			int result = new mateBoardService().updateMate(b, at);
			
			if(result > 0) {
				
				request.getSession().setAttribute("alertMsg", "������ �Ϸ�Ǿ����ϴ�.");
				response.sendRedirect(request.getContextPath()+"/detail.mate?bno="+boardNo);
			}else {
				request.setAttribute("errorMsg", "�Խñ� ���� ����");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
			
		}else {
			request.setAttribute("errorMsg", "�߸��� ���� ����Դϴ�.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
