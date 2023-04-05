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
		
		
		//전송되는 파일을 처리할 작업내용
		//1)전송파일용량제한
		int maxSize = 1024*1024*10;
		
		//2)파일을 저장할서버의 폴더경로 알아내기(String savePath)
		String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
		
		//3)전달된 파일명 수정 작업 및 파일 업로드
		//MultupartRequest multi = new MultipartRequest(request 객체, 저장할 폴더 경로, 용량제한, 인코딩 설정값, 파일명을 수정시켜주는 객체);
		MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		
		//4)db에 넘길 데이터 저장하기	
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
				
				request.getSession().setAttribute("alertMsg", "수정이 완료되었습니다.");
				response.sendRedirect(request.getContextPath()+"/detail.mate?bno="+boardNo);
			}else {
				request.setAttribute("errorMsg", "게시글 수정 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
			
		}else {
			request.setAttribute("errorMsg", "잘못된 접근 방식입니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
