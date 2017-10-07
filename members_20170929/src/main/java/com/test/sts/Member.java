package com.test.sts;

//자료형 클래스
public class Member {
	
	//항목 설정
	//회원번호, 이름, 전화번호, 이메일, 등록일(yyyy-MM-dd), 클라이언트IP
	//회원 한 명의 정보 중에서 복수개의 자료(->컬렉션)가 있는지 확인 필요
	private int mid;
	private String name_;
	private String phone;
	private String email;
	private String regDate;
	private String clientIP;
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getName_() {
		return name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

}