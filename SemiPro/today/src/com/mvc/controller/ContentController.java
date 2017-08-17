package com.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dto.BoardDTO;
import com.mvc.service.BoardService;


@WebServlet("/content")
public class ContentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("idx"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		String area= request.getParameter("area");
		BoardService service = new BoardService();
		ArrayList<BoardDTO> list = service.content(idx);
		
		String page = "./area?area="+area;
		if(list != null){
			page="content.jsp";
			service.hit(idx);
		}
		request.setAttribute("content", list);
		RequestDispatcher dis = request.getRequestDispatcher(page);
		dis.forward(request, response);
		
	}

}
