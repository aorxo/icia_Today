package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mvc.dao.BoardDAO;
import com.mvc.dto.BoardDTO;
import com.mvc.service.BoardService;

@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		String idx = request.getParameter("idx");
		String id = request.getParameter("id");
		String reply = request.getParameter("reply");
		System.out.println(idx+" / "+id+" / "+reply);
		
		BoardService service = new BoardService();
		
		String page = service.reply(idx, id, reply);	
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list2=dao.list2();
		
		HashMap map = new HashMap();
		map.put("list2", list2);
		Gson json = new Gson();
		String result=json.toJson(map);
		//System.out.println(result);
		
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		out.println(result);
	}
		
}