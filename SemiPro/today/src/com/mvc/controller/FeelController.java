package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.service.BoardService;

@WebServlet("/feel")
public class FeelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String feel = request.getParameter("feel");
		int idx = Integer.parseInt(request.getParameter("idx"));
		String id = request.getParameter("loginID");
		System.out.println(id+"/"+feel+"/"+idx);
		
		BoardService service = new BoardService();
		
		String msg= "";
		if(feel.equals("like")){
			if(service.likecheck(id,idx)==false){
				if(service.hatecheck(id,idx)==false){
					if(service.likeup(id, idx)==1){
						service.feellikeup(idx);
					}					
				}else{
				}
				
			}else{
				if(service.likedown(idx)==1){
					service.feellikedown(idx);
				}
			}
			RequestDispatcher dis = request.getRequestDispatcher("content?idx="+idx);
			dis.forward(request, response);
		}
		if(feel.equals("hate")){
			if(service.hatecheck(id,idx)==false){
				if(service.likecheck(id,idx)==false){
					if(service.hateup(id, idx)==1){
						service.feelhateup(idx);
					}					
				}else{
				}
				
			}else{
				if(service.hatedown(idx)==1){
					service.feelhatedown(idx);
				}
			}
			RequestDispatcher dis = request.getRequestDispatcher("content?idx="+idx);
			dis.forward(request, response);
		}
		
		
		
	}

}
