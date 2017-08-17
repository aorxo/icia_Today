package com.mvc.service;


import java.util.ArrayList;

import com.mvc.dao.BoardDAO;
import com.mvc.dto.BoardDTO;

public class BoardService {

	public BoardService() {
		
	}

	public int board(String id, String title, String tag, double latit, double longit, String area) {//문단
		BoardDAO dao = new BoardDAO();
		
		return dao.board(id,title,tag,latit,longit,area);
	}

	public int text(String text, int idx) {//글업로드
		BoardDAO dao = new BoardDAO();
		return dao.text(text, idx);
	}

	public int upload(String oldFileName, String newFileName, int idx, int textidx) {//사진 업로드
		BoardDAO dao = new BoardDAO();
		return dao.upload(oldFileName, newFileName, idx, textidx);
	}

	public ArrayList<BoardDTO> area(String area) {//지역게시판으로 보낼 목록담기
		BoardDAO dao = new BoardDAO();
		return dao.area(area);
	}

	public void feel(int idx) {//좋아요,싫어요
		BoardDAO dao = new BoardDAO();
		dao.feel(idx);
		
	}

	public ArrayList<BoardDTO> content(int idx) {
		BoardDAO dao = new BoardDAO();
		return dao.content(idx);
	}

	public void hit(int idx) {
		BoardDAO dao = new BoardDAO();
		dao.hit(idx);
		
	}

	public boolean likecheck(String id, int idx) {//좋아요 눌렀는지 안눌렀는지 체크
		BoardDAO dao = new BoardDAO();
		return dao.likecheck(id,idx);
	}

	public boolean hatecheck(String id, int idx) {
		BoardDAO dao = new BoardDAO();
		return dao.hatecheck(id,idx);
	}

	public int likeup(String id, int idx) {
		BoardDAO dao = new BoardDAO();
		return dao.likeup(id,idx);
	}

	public void feellikeup(int idx) {
		BoardDAO dao = new BoardDAO();
		dao.feellikeup(idx);
		
	}

	public int likedown(int idx) {
		BoardDAO dao = new BoardDAO();
		return dao.likedown(idx);
	}

	public void feellikedown(int idx) {
		BoardDAO dao = new BoardDAO();
		dao.feellikedown(idx);
		
	}

	public int hateup(String id, int idx) {
		BoardDAO dao = new BoardDAO();
		return dao.hateup(id,idx);
	}

	public void feelhateup(int idx) {
		BoardDAO dao = new BoardDAO();
		dao.feelhateup(idx);
		
	}

	public int hatedown(int idx) {
		BoardDAO dao = new BoardDAO();
		return dao.hatedown(idx);
	}

	public void feelhatedown(int idx) {
		BoardDAO dao = new BoardDAO();
		dao.feelhatedown(idx);
		
	}

	public String reply(String idx, String id, String reply) {

		String page = "reply.jsp";
		
		BoardDAO dao = new BoardDAO();
		if(dao.reply(idx, id, reply)==1){
			page="list2";
		}
		return page;
	}

	public ArrayList<BoardDTO> list2() {
		BoardDAO dao = new BoardDAO();		
		return dao.list2();

	}

	public ArrayList<BoardDTO> myarea(String area, Double latit, Double longit) { //내주변 리스트
		BoardDAO dao = new BoardDAO();
		return dao.myarea(area,latit,longit);
	}


	
	}

	
