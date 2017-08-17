package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mvc.dao.BoardDAO;
import com.mvc.dto.BoardDTO;

@WebServlet("/read")
public class ReplyReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> relist=dao.relist(idx);
		
		HashMap map = new HashMap();
		map.put("relist", relist);
		Gson json = new Gson();
		String result = json.toJson(map);
		
		response.setContentType("text/http; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}
}
