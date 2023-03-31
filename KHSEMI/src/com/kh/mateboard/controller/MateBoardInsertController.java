package com.kh.mateboard.controller;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.model.MyFileRenamePolicy;
import com.kh.mateboard.model.vo.Attachment;
import com.kh.mateboard.model.service.mateBoardService;
import com.kh.mateboard.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MateBoardInsertController
 */
@WebServlet("/insert.mate")
public class MateBoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MateBoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.getRequestDispatcher("views/mateboard/mateboardInsertForm.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			//전송되는 파일을 처리할 작업내용
			//1)전송파일용량제한
			int maxSize = 1024*1024*10;
			
			//2)파일을 저장할서버의 폴더경로 알아내기(String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			
			//3)전달된 파일명 수정 작업 및 파일 업로드
			//MultupartRequest multi = new MultipartRequest(request 객체, 저장할 폴더 경로, 용량제한, 인코딩 설정값, 파일명을 수정시켜주는 객체);
			MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			
			//4)db에 넘길 데이터 저장하기			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String address = request.getParameter("address1")+","+request.getParameter("address2");
			
			Board b = new Board(title, content, address);
			
			Attachment at = null;
			
			if(multi.getOriginalFileName("upfile") != null) {
				at = new Attachment();
				at.setOriginName(multi.getOriginalFileName("upfile"));
				at.setChangeName(multi.getFilesystemName("upfile"));
				at.setFilePath("resources/board_upfiles/");
			}
			
			int result = new mateBoardService().insertMateBoard(b, at);
			
			
			
			HttpSession session = request.getSession();
			
			if(result > 0) {
				request.getSession().setAttribute("alertMsg", "게시글 등록 성공");
				response.sendRedirect(request.getContextPath());
			}else {
				request.setAttribute("errorMsg", "게시글 등록 실패!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
			
			
			
			
	}
		
		
		
		
	
	
	
	}

}
