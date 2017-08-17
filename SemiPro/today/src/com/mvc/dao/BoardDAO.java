package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mvc.dto.BoardDTO;

public class BoardDAO {

	Connection conn = null; 
	PreparedStatement ps= null;
	ResultSet rs  = null;
	DataSource ds = null;
	
	public BoardDAO(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
}

	public int board(String id, String title, String tag, double latit, double longit, String area) {//문단
		int idx=0;
		System.out.println(id+"/"+title+"/"+tag+"/"+latit+"/"+longit+"/"+area);
		String sql = "INSERT INTO board(idx,user_id,title,tag,latit,longit,area) "
				+ "VALUES(board_seq.NEXTVAL,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, title);
			ps.setString(3, tag);
			ps.setDouble(4, latit);
			ps.setDouble(5, longit);
			ps.setString(6, area);
			if(ps.executeUpdate() !=0){//위의 쿼리문이 실행가능하면 밑에의 쿼리를 실행
				ps = null;
				String sql2 = "SELECT board_seq.CURRVAL FROM DUAL";//지금 넣은 idx를 가져오기위함
				ps = conn.prepareStatement(sql2);
				rs = ps.executeQuery();
				if(rs.next()){
					idx = rs.getInt("CURRVAL");//가져온 시퀀스를 idx에 넣음
					System.out.println(idx);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return idx;
	}

	

	public int text(String text, int idx) {//글
		int textidx = 0;
		String sql = "INSERT INTO text(textidx,idx,content) "
				+ "VALUES(text_seq.NEXTVAL,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.setString(2, text);
			if(ps.executeUpdate() !=0){//위의 쿼리문이 실행가능하면 밑에의 쿼리를 실행
				ps = null;
				String sql2 = "SELECT text_seq.CURRVAL FROM DUAL";//지금 넣은 idx를 가져오기위함
				ps = conn.prepareStatement(sql2);
				rs = ps.executeQuery();
				if(rs.next()){
					textidx = rs.getInt("CURRVAL");//가져온 시퀀스를 idx에 넣음
					System.out.println(idx);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return textidx;
	}

	public int upload(String oldFileName, String newFileName, int idx, int textidx) {//사진
		int success =0;
		
		String sql = "INSERT INTO photo(photoidx,idx,textidx,oldfile,newfile) "
				+ "VALUES(photo_seq.NEXTVAL,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.setInt(2, textidx);
			ps.setString(3, oldFileName);
			ps.setString(4, newFileName);
			success=ps.executeUpdate();
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

	public void feel(int idx) {//좋아요 싫어요 
		String sql = "INSERT INTO feel(idx,uplike,uphate) VALUES(?,0,0)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.executeUpdate();
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
		
	}

	public ArrayList<BoardDTO> area(String area) {//글 불러오기
		
		String sql="SELECT "
				+ "DISTINCT(b.idx),b.title,b.reg_date,b.hit,b.area,b.user_id,MIN(p.newfile),f.UPHATE,f.UPLIKE "
				+ "FROM board b,photo p,feel f WHERE  b.idx = p.idx AND b.idx=f.idx AND b.area=? "
				+ "group by (b.idx), b.title, b.reg_date, b.hit, b.area, b.user_id, f.UPHATE, f.UPLIKE";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();		
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, area);
			rs = ps.executeQuery();		
			
			while(rs.next()){
				BoardDTO dto = new BoardDTO();
				
				dto.setIdx(rs.getInt("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setHit(rs.getInt("hit"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setNewfile(rs.getString("min(p.newfile)"));
				dto.setUplike(rs.getInt("uplike"));
				dto.setUphate(rs.getInt("uphate"));
				dto.setArea(rs.getString("area"));
				list.add(dto);
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
		return list;
	}

	public ArrayList<BoardDTO> content(int idx) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();		
		String sql = "SELECT p.newfile,b.idx,b.area,b.user_id,f.uplike,f.uphate,b.reg_date,b.tag,b.title,t.content,b.hit FROM photo p, text t,board b,feel f "
				+ "WHERE p.textidx=t.textidx AND b.idx=t.idx AND b.idx=f.idx AND b.idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			rs = ps.executeQuery();		
			
			while(rs.next()){
				BoardDTO dto = new BoardDTO();
				dto.setTitle(rs.getString("title"));
				dto.setIdx(rs.getInt("idx"));
				dto.setArea(rs.getString("area"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setContent(rs.getString("content"));
				dto.setHit(rs.getInt("hit"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setNewfile(rs.getString("newfile"));
				dto.setUplike(rs.getInt("uplike"));
				dto.setUphate(rs.getInt("uphate"));
				dto.setTag(rs.getString("tag"));
				list.add(dto);
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
		
		
		return list;
	}

	public void hit(int idx) {
			String sql = "UPDATE board SET hit=hit+1 WHERE idx=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, idx);
				ps.executeUpdate();
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
		
	}

	public boolean likecheck(String id,int idx) {
		boolean check = false;
		String sql = "SELECT uplike FROM feelcheck WHERE user_id=? AND idx=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, idx);
			rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("uplike") ==1){
					check=true;
				}
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
		return check;
	}

	public boolean hatecheck(String id, int idx) {
		boolean check = false;
		String sql = "SELECT uphate FROM feelcheck WHERE user_id=? AND idx=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, idx);
			rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("uphate") ==1){
					check=true;
					System.out.println(check);
				}
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
		return check;
	}

	public int likeup(String id, int idx) {
		int success=0;
		
		String sql = "INSERT INTO feelcheck(idx,user_id,uplike) VALUES(?,?,1)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.setString(2, id);
			
			success=ps.executeUpdate();
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

	public void feellikeup(int idx) {
		String sql = "UPDATE feel SET uplike=uplike+1 WHERE idx=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.executeUpdate();
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

		
	}

	public int likedown(int idx) {
		int success=0;
		String sql ="DELETE FROM feelcheck WHERE idx=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
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

	public void feellikedown(int idx) {
		String sql = "UPDATE feel SET uplike=uplike-1 WHERE idx=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.executeUpdate();
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
		
	}

	public int hateup(String id, int idx) {
		int success=0;
		
		String sql = "INSERT INTO feelcheck(idx,user_id,uphate) VALUES(?,?,1)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.setString(2, id);
			
			success=ps.executeUpdate();
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

	public void feelhateup(int idx) {
		String sql = "UPDATE feel SET uphate=uphate+1 WHERE idx=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.executeUpdate();
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
		
	}

	public int hatedown(int idx) {
		int success=0;
		String sql ="DELETE FROM feelcheck WHERE idx=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
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

	public void feelhatedown(int idx) {
		String sql = "UPDATE feel SET uphate=uphate-1 WHERE idx=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.executeUpdate();
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
		
	}

	public int reply(String idx, String id, String reply) { //리플 쓰기, 저장
		
		String sql="INSERT INTO reply(reidx,idx,user_id, recontent) VALUES(reply_seq.NEXTVAL,?,?,?)";
		int success=0;
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,Integer.parseInt(idx));
			ps.setString(2, id);
			ps.setString(3, reply);
			success=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return success;
	}

	public ArrayList<BoardDTO> list2() {
		
		String sql="SELECT idx,user_id,recontent FROM reply";
		ArrayList<BoardDTO> list2 = new ArrayList<BoardDTO>();
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				BoardDTO dto= new BoardDTO();
				dto.setIdx(rs.getInt(1));
				dto.setUser_id(rs.getString(2));
				dto.setRecontent(rs.getString(3));
				list2.add(dto);
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
		
		return list2;
	}
	
	public ArrayList<BoardDTO> relist(int idx) {
		
		String sql="SELECT idx, user_id, recontent FROM reply WHERE idx=? ORDER BY reidx";
		ArrayList<BoardDTO> relist = new ArrayList<BoardDTO>();
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
			rs=ps.executeQuery();
			
			while(rs.next()){
				BoardDTO dto = new BoardDTO();
				dto.setIdx(rs.getInt(1));
				dto.setUser_id(rs.getString(2));
				dto.setRecontent(rs.getString(3));
				relist.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return relist;
	}

	public ArrayList<BoardDTO> myarea(String area, Double latit, Double longit) {
		String sql="SELECT DISTINCT(b.idx),b.title,b.reg_date,b.hit,b.area,b.user_id,"
				+ "MIN(p.newfile),f.UPHATE,f.UPLIKE FROM board b,photo p,feel f "
				+ "WHERE  b.idx = p.idx AND b.idx=f.idx "
				+ "AND latit>= ?-0.0045 "
				+ "AND latit<= ?+0.0045 "
				+ "AND longit>=?- 0.0057"
				+ "AND longit<= ?+ 0.0057"
				+ "group by (b.idx), b.idx, b.title, b.reg_date, b.hit, "
				+ "b.area, b.user_id,  f.UPHATE,  f.UPLIKE ORDER BY uplike DESC";
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setDouble(1, latit);
			ps.setDouble(2, latit);
			ps.setDouble(3, longit);
			ps.setDouble(4, longit);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				BoardDTO dto = new BoardDTO();
				
				dto.setIdx(rs.getInt("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setHit(rs.getInt("hit"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setNewfile(rs.getString("min(p.newfile)"));
				dto.setUplike(rs.getInt("uplike"));
				dto.setUphate(rs.getInt("uphate"));
				dto.setArea(rs.getString("area"));
				list.add(dto);
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
		return list;
	}
	
	
}
