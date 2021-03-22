package com.spring.ball.vo;

import java.sql.Timestamp;

public class ClientVO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private Timestamp reg_date;
	
	// 03.04 스프링 프로젝트 추가
	private String email_key;		// 이메일 인증 키
//	private String pwd_question;	// 비밀번호 찾기 질문 (나의 좌우명은?)
//	private String pwd_answer;		// 비밀번호 찾기 답변 (바르게살자..)
	
	private String enabled;			// 권한
	private String authority;		// 등급
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getEmail_key() {
		return email_key;
	}
	public void setEmail_key(String email_key) {
		this.email_key = email_key;
	}
//	public String getPwd_question() {
//		return pwd_question;
//	}
//	public void setPwd_question(String pwd_question) {
//		this.pwd_question = pwd_question;
//	}
//	public String getPwd_answer() {
//		return pwd_answer;
//	}
//	public void setPwd_answer(String pwd_answer) {
//		this.pwd_answer = pwd_answer;
//	}
	
	
}
