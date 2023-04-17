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

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.vo.Attachment;
import com.kh.common.model.MyFileRenamePolicy;
import com.kh.board.mateboard.model.service.mateBoardService;
import com.kh.board.mateboard.model.vo.Board;
import com.kh.member.model.vo.Member;
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
		ArrayList<Attachment> atList = new mateBoardService().selectAttachment(boardNo);
		request.setAttribute("b", b);
		request.setAttribute("atList", atList);
		
		
		request.getRequestDispatcher("views/mateboard/mateboardUpdate.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(ServletFileUpload.isMultipartContent(request)) {
		
		//전송되는 파일을 처리할 작업내용
		//1)전송파일용량제한
		int maxSize = 1024*1024*80;
		
		//2)파일을 저장할서버의 폴더경로 알아내기(String savePath)
		String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
		
		//3)전달된 파일명 수정 작업 및 파일 업로드
		//MultupartRequest multi = new MultipartRequest(request 객체, 저장할 폴더 경로, 용량제한, 인코딩 설정값, 파일명을 수정시켜주는 객체);
		MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		
		//4)db에 넘길 데이터 저장하기	
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
		
	
		ArrayList<Attachment> atList = new ArrayList<>();
		if(multi.getFileNames() != null) {
			Enumeration e = multi.getFileNames();
			int fileLevel=1;
			while(e.hasMoreElements()) {
				String fileName = (String)e.nextElement();
				String originName = multi.getOriginalFileName(fileName);
				String changeName = multi.getFilesystemName(fileName);
				
				Attachment atImg = new Attachment();
				atImg.setOriginName(originName);
				atImg.setChangeName(changeName);
				atImg.setFilePath("resources/board_upfiles");
				atImg.setFileLevel(fileLevel++);
				atImg.setRefBno(Integer.parseInt(multi.getParameter("bno")));
				
				atList.add(atImg);
			}
			
			int index  =0;
			while(true) {
				String changeFileName = multi.getParameter("changeFileName"+index++);
				if(changeFileName == null) {
					break;
				}else {
					new File(savePath+changeFileName).delete();
				}
			}
		}
		
		
		
		ArrayList<Integer> originFileNos = new ArrayList<>();
		int index2 = 0;
		while(true) {
			String originFileNo = multi.getParameter("originFileNo"+index2++);
			//System.out.println(originFileNo);
			
			if(originFileNo == null) {
				break;
			}else {
				originFileNos.add(Integer.parseInt(originFileNo));
			}
		}
		
		int result = new mateBoardService().updateMate(b, atList, originFileNos);
		
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