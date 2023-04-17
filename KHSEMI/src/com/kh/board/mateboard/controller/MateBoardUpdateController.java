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
		
		//���۵Ǵ� ������ ó���� �۾�����
		//1)�������Ͽ뷮����
		int maxSize = 1024*1024*80;
		
		//2)������ �����Ҽ����� ������� �˾Ƴ���(String savePath)
		String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
		
		//3)���޵� ���ϸ� ���� �۾� �� ���� ���ε�
		//MultupartRequest multi = new MultipartRequest(request ��ü, ������ ���� ���, �뷮����, ���ڵ� ������, ���ϸ��� ���������ִ� ��ü);
		MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		
		//4)db�� �ѱ� ������ �����ϱ�	
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
			System.out.println("���ε� ����");
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