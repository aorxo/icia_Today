package com.mvc.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.service.MemberService;


@WebServlet("/join")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userID");
		String pw = request.getParameter("userPW");
		String name = request.getParameter("userName");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String email = request.getParameter("userEmail");//각각 파라메터 불러오기
		
		request.setCharacterEncoding("UTF-8");// 한글깨짐 방지
		
			Date date = Date.valueOf(request.getParameter("userDate"));//date 형변환
			
			System.out.println(date);
			
			MemberService service = new MemberService();
			
			String msg="회원가입 실패";
			String page="join.jsp";
					if(service.join(id,pw,name,phone,email,date)==1){
						msg="회원가입 성공";
						page="index.jsp";
					}	
					
					request.setAttribute("result", msg);//result 라는 것에 담음
					RequestDispatcher dis = request.getRequestDispatcher(page);
					dis.forward(request, response);

		} 
}
