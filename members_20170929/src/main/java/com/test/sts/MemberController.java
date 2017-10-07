package com.test.sts;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.ModelMap;

/* Spring MVC에서 Controller 클래스로 등록하려면 @Controller 어노테이션 사용 */
/* @RequestMapping 어노테이션은 요청 주소 지정시 사용. 클래스 단위 또는 메소드 단위로 지정 */

//공통적인 부모 주소(/member)를 지정한다.
@Controller
@RequestMapping("/member") //부모주소
public class MemberController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	/*MemberService 객체(MemberServiceImpl)에 대한 의존 주입 설정 추가*/
	//@Autowired 어노테이션을 사용하면 byType에 의한 의존주입이 설정된다.
	@Inject
	private MemberService service;

	
	
	/* SELECT */
	
	/* @RequestMapping 어노테이션 지정시 요청 방식(GET or POST) 지정 가능 */
	/* 요청 주소와 매핑되는 메소드 이름은 사용자가 지정하고, 매개변수 목록도 사용자가 지정한다. */
	/* 주의) 매개변수 목록에 지정된 객체는 Spring MVC에서 자동 제공한다. */
	//세부적인 자식 주소(value="/list)를 지정한다.
	//전체 요청주소는 '/member/list' 가 된다.
	@RequestMapping(value="/list", method = RequestMethod.GET) //자식주소
	public String printHello(ModelMap model) {
		
		
		//MemberService 객체의 특정 메소드 호출;
		List<Member> members = service.memberList();
		String count = String.valueOf(members.size());
		
		logger.info(count);

		/* Spring MVC에서 ModelMap 객체는 컨트롤러에서 발생한 결과를 뷰로 전달하기 위한 객체 */
		/* addAttribute("식별자", 뷰로 전달한 데이터) */
		model.addAttribute("members", members);
		model.addAttribute("count", count);

		/* 메소드의 마지막 반환값은 뷰 정보(파일이름만 지정) */
		/* 경로명과 .jsp 지정은 ViewResolver 객체에 이미 지정된 상태 */
		return "member";
	}
	
	
	
	/* INSERT */
	
	//전체 요청주소는 '/member/add' 가 된다.
	/* method 속성 생략시 GET and POST 방식 모두 처리 가능*/
	/* 클라이언트가 전송하는 데이터 수신은 매개변수를 사용한다.*/
	/* RedirectAttributes 리다이렉트 액션시 클라이언트에게 전송할 데이터 지정시 사용 */
	/* 입력 */
	@RequestMapping("/add")
	public String add(Member m, HttpServletRequest request, RedirectAttributes rttr) { /* (Member m)  -> 데이터 수신이다 RedirectAttributes rttr -> 결과 코드 값 보낼때 list?할 필요가없다 */
		
		/*request 객체를 이용해서 클라이언트 IP를 확인 필요.*/
		m.setClientIP(request.getRemoteAddr());
		
		String code = service.memberAdd(m);
		
		/* member.jsp 에서 입력이 완료되었는지 확인하기 위해 if ("${code}" == "200") { */
		rttr.addFlashAttribute("code", code);
		
		//리다이렉트 액션 -> redirect: 키워드 사용
		return "redirect:/member/list";
	}	
	

}