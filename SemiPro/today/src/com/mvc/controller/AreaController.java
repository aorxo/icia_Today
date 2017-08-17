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

@WebServlet("/area")
public class AreaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request,response);
	}
	
	protected void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String area = request.getParameter("area");
		
		BoardService service = new BoardService();
		ArrayList<BoardDTO> dto = service.area(area);
		
		if(area.equals("내주변")){ //내주변 게시판
			Double latit = Double.parseDouble(request.getParameter("latit"));
			System.out.println(latit);
			Double longit = Double.parseDouble(request.getParameter("longit"));
			System.out.println(longit);
			dto = service.myarea(area,latit,longit);
			
		}
		
		
			
		String page ="main.jsp";
		if(dto != null){
			page ="detail.jsp";
		}
		
		request.setAttribute("list", dto);
		RequestDispatcher dis = request.getRequestDispatcher(page);
		dis.forward(request, response);
	}
}
