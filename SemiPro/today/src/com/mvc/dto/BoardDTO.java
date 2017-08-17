package com.mvc.dto;

import java.sql.Date;

public class BoardDTO {
	
	private int idx;
	private String user_id;
	private String title;
	private Date reg_date;
	private String tag;
	private int hit;
	private Double latit;
	private Double longit;
	private String area;
	private int photoidx;
	private String oldfile;
	private String newfile;
	private int textidx;
	private String content;
	private int uplike;
	private int uphate;
	private int lovelv;
	private String recontent;
	
	public String getRecontent() {
		return recontent;
	}

	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}

	public int getLovelv() {
		return lovelv;
	}

	public void setLovelv(int lovelv) {
		this.lovelv = lovelv;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Double getLatit() {
		return latit;
	}

	public void setLatit(Double latit) {
		this.latit = latit;
	}

	public Double getLongit() {
		return longit;
	}

	public void setLongit(Double longit) {
		this.longit = longit;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getPhotoidx() {
		return photoidx;
	}

	public void setPhotoidx(int photoidx) {
		this.photoidx = photoidx;
	}

	public String getOldfile() {
		return oldfile;
	}

	public void setOldfile(String oldfile) {
		this.oldfile = oldfile;
	}

	public String getNewfile() {
		return newfile;
	}

	public void setNewfile(String newfile) {
		this.newfile = newfile;
	}

	public int getTextidx() {
		return textidx;
	}

	public void setTextidx(int textidx) {
		this.textidx = textidx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUplike() {
		return uplike;
	}

	public void setUplike(int uplike) {
		this.uplike = uplike;
	}

	public int getUphate() {
		return uphate;
	}

	public void setUphate(int uphate) {
		this.uphate = uphate;
	}

	public BoardDTO() {
	
	}


}
