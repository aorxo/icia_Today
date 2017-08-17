package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.service.MemberService;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id =request.getParameter("userID");
		String pw =request.getParameter("userPW");
		
		MemberService service = new MemberService();
		request.setCharacterEncoding("UTF-8");
		
		int lv = service.lv(id);
		
			if(service.login(id,pw)){
				request.getSession().setAttribute("loginID",id);// 세션 아이디 : loginID
				request.getSession().setAttribute("lv",lv);
				response.sendRedirect("main.jsp");//list 로보냄 성공하면
				
			}else{
				request.setAttribute("result", "로그인에 실패");//result 라는 것에 담음
				RequestDispatcher dis = request.getRequestDispatcher("index.jsp");//실패시 HOME으로
				dis.forward(request, response);
			}
	}

}
