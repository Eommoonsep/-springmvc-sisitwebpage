package com.test.sts;


import java.util.*;

//Spring MVC Framework에서는 추상화를 위해서 interface를 사용한다.
public interface MemberService {

	// 전체 자료 반환 메소드
	public List<Member> memberList();
	
	
	// 회원 정보 저장 메소드
	public String memberAdd(Member m);	
	
	//검색 출력 메소드
	public List<Member> memberList(String key, String value);
	
	
	
	
}
