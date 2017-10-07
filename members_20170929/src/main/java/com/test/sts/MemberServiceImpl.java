package com.test.sts;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;


//MemberService 인터페이스에 대한 구현 클래스
//-> 추상 메소드에 대한 구현 필수
/* @Service 어노테이션을 지정해서 스프링 객체로 등록 */
@Service
public class MemberServiceImpl implements MemberService {
	
	/* MemberDAO myBatis - 객체(MemberJDBCTemplate) 에 대한 의존 주입 설정 추가 */
	/* @Inject 어노테이션을 사용하면 byType에 의한 의존주입이 실행된다. */
	@Inject
	private MemberDAO dao;

	@Override
	public List<Member> memberList() {
		//memberList() 메소드 내에서 Spring JDBC 를 사용하는지 myBatis를 사용하는지 알 필요 없다.
		return dao.memberList();
	
	}

	@Override
	public String memberAdd(Member m) {
		
		String temp = "101";
		
		/* INSERT 쿼리 실행 메소드 호출 */
		/* Member 객체(데이터베이스에 입력할 데이터가 저장된 객체)를 전달해야 한다. */
		int result = dao.memberAdd(m);
		
		if(result == 1) {
		} else {
			temp = "100";
		}
		return null; 
	}

	@Override
	public List<Member> memberList(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
