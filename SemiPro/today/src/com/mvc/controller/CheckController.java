package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mvc.service.MemberService;


@WebServlet("/check")
public class CheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userID");
		
		MemberService service = new MemberService();
		
			System.out.println(id);
			String msg="사용 가능한 아이디 입니다.";
			if(service.check(id)){
				msg="이미 사용중인 아이디 입니다.";
				
			}
			HashMap map = new HashMap();
			map.put("result",msg);
			//Gson을 사용 해서 json 으로 만들어 줌
			Gson json = new Gson();
			String result = json.toJson(map);
			System.out.println(result);
			
			//한글 깨짐 방지
			response.setContentType("text/html; charset=UTF-8");
			//크로스도메인 허용
			response.setHeader("Access-Control-Allow-Origin", "*");
			//프린트 객체를 만들어 페이지에 내용 쓰기
			PrintWriter out = response.getWriter();
			out.println(result);
		} 
		
	}

