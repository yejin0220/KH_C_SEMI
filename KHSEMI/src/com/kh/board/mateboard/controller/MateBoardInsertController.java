package com.kh.board.mateboard.controller;

import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.model.MyFileRenamePolicy;
import com.kh.board.model.vo.Attachment;
import com.kh.board.mateboard.model.service.mateBoardService;
import com.kh.board.mateboard.model.vo.Board;
import com.kh.member.model.vo.Member;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("views/mateboard/mateboardInsertForm.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
	
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 80*1024*1024;
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			Board b = new Board();
			b.setBoardTitle(multi.getParameter("title"));
			b.setBoardContent(multi.getParameter("content"));
			b.setBoardWriter(((Member)request.getSession().getAttribute("loginUser")).getUserNo()+"");
			b.setAddress(multi.getParameter("address1")+","+multi.getParameter("address2"));
			if(multi.getParameter("latitude") != null) {
				b.setLatitude(Double.parseDouble(multi.getParameter("latitude")));
			}
			if(multi.getParameter("longitude") != null) {
				b.setLongitude(Double.parseDouble(multi.getParameter("longitude")));
			}
			
			ArrayList<Attachment> atList = new ArrayList();
			Enumeration e = multi.getFileNames();
			int fileLevel = Integer.parseInt(multi.getParameter("fileLength"));
			while(e.hasMoreElements()) {
				String fileName =  (String)e.nextElement();
				String originName = multi.getOriginalFileName(fileName);
				String changeName = multi.getFilesystemName(fileName);
				
				Attachment atImg = new Attachment();
				atImg.setOriginName(originName);
				atImg.setChangeName(changeName);
				atImg.setFilePath("/resources/board_upfiles/");
				atImg.setFileLevel(fileLevel--);
				
				atList.add(atImg);
			}
			
			int result = new mateBoardService().insertMateBoard(b, atList);
			
			if(result > 0) {
				System.out.println("업로드 성공");
			}else {
				if(!atList.isEmpty()) {
					for(Attachment a : atList) {
						new File(savePath+a.getChangeName()).delete();
					}
				}
			}
		}
	}		
	
}
