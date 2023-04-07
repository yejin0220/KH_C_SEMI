package com.kh.mateboard.controller;

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
import com.kh.common.model.Attachment;
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

		request.setCharacterEncoding("UTF-8");

		if (ServletFileUpload.isMultipartContent(request)) {

			// ���۵Ǵ� ������ ó���� �۾�����
			// 1)�������Ͽ뷮����
			int maxSize = 1024 * 1024 * 10;

			// 2)������ �����Ҽ����� ������� �˾Ƴ���(String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");

			// 3)���޵� ���ϸ� ���� �۾� �� ���� ���ε�
			// MultupartRequest multi = new MultipartRequest(request ��ü, ������ ���� ���, �뷮����,
			// ���ڵ� ������, ���ϸ��� ���������ִ� ��ü);
			MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8",
					new MyFileRenamePolicy());

			// 4)db�� �ѱ� ������ �����ϱ�
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String address = multi.getParameter("address1") + "," + multi.getParameter("address2");
			String userNo = multi.getParameter("userNo");
			double latitude = Double.parseDouble(multi.getParameter("latitude"));
			double longitude = Double.parseDouble(multi.getParameter("longitude"));

			Board b = new Board(title, content, address, userNo, latitude, longitude);

			ArrayList<Attachment> list = new ArrayList<>();
			Enumeration e = multi.getFileNames(); // ���޵� ���ϵ��� key���� �̾ƿ���

			int count = 0;
			while (e.hasMoreElements()) {
				String fileName = (String) e.nextElement();
				String originName = multi.getOriginalFileName(fileName);
				String changeName = multi.getFilesystemName(fileName);

				
				Attachment at = new Attachment();
				at.setOriginName(originName);
				at.setChangeName(changeName);
				at.setFilePath("resources/board_upfiles/");
				at.setFileLevel(count++);

				list.add(at);

				

			}
			int result = new mateBoardService().insertMateBoard(b, list);

			HttpSession session = request.getSession();

			if (result > 0) {

				request.getSession().setAttribute("alertMsg", "�Խñ� ��� ����");
				response.sendRedirect(request.getContextPath() + "/list.mate");
			} else {

				if (!list.isEmpty()) {
					for (Attachment a : list) {
						new File(savePath + a.getChangeName()).delete();
					}
				}

				request.setAttribute("errorMsg", "�Խñ� ��� ����!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}

		}

	}

}
