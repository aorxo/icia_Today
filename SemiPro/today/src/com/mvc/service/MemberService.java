package com.mvc.service;



import java.sql.Date;


import com.mvc.dao.MemberDAO;
import com.mvc.dto.BoardDTO;

public class MemberService {
	
	public MemberService() {
		
	}

	public boolean login(String id, String pw) {
		MemberDAO dao = new MemberDAO();
		
		return dao.login(id, pw);
	}

	public boolean check(String id){
		MemberDAO dao = new MemberDAO();
		
		return dao.check(id);
	}

	public int join (String id, String pw,String name, int phone, String email, Date date)  {
		MemberDAO dao = new MemberDAO();
		
		return dao.join(id,pw,name,phone,email,date);
	}

	public int lv(String id) {
		MemberDAO dao = new MemberDAO();
		return dao.lv(id);
	}

}
