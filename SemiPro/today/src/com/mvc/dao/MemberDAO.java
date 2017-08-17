package com.mvc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mvc.dto.BoardDTO;

public class MemberDAO {

	Connection conn = null; 
	PreparedStatement ps= null;
	ResultSet rs  = null;
	DataSource ds = null;
	
	public MemberDAO(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean login(String id, String pw) {//로그인 db
		boolean success = false;
		String sql = "SELECT user_id FROM members WHERE user_id=? AND user_pw=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			success = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	public boolean check(String id){//아이디 중복확인 db
		boolean success = false;
		String sql = "SELECT user_id FROM members WHERE user_id=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			success = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return success;
	}

	public int join(String id, String pw,String name, int phone, String email, Date date) {//회원가입 db
		int success =0;
		String sql ="INSERT INTO members(user_id,user_pw,user_name,user_phone,email,lovedate) "
				+ "VALUES(?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3,name);
			ps.setInt(4, phone);
			ps.setString(5, email);
			ps.setDate(6,date);
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return success;
	}

	public int lv(String id) {
		String sql = "SELECT lovelv FROM members WHERE user_id=?";
		
		int lv =0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				
				lv = rs.getInt("lovelv");
				System.out.println(rs.getInt("lovelv"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}		
		return lv;
	}
}
