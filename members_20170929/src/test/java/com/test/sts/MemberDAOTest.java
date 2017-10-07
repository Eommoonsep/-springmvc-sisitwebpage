package com.test.sts;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {

	@Inject
	private MemberDAO dao;
	
	@Test
	public void testMemberList()throws Exception{
		
		List<Member> list = dao.memberList();
		System.out.println("size:"+list.size());
		for (Member m : list) {
			System.out.printf("%s %s %s %s %s %s %n"
					, m.getMid(), m.getName_(), m.getPhone(), m.getEmail(), m.getRegDate(), m.getClientIP());
		}
		
	}	

}


