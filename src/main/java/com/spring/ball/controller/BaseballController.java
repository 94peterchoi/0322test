// 안녕하세요

package com.spring.ball.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ball.service.ClientService;
import com.spring.ball.service.ClientServiceImpl;

@Controller
public class BaseballController {

	private static final Logger logger = LoggerFactory.getLogger(BaseballController.class);
	
	// service 객체 생성
	@Autowired
	ClientServiceImpl clientService;
	
//	@Autowired
//	ProductServiceImpl productService;
//	
//	@Autowired
//	BuyServiceImpl buyService;
//	
//	@Autowired
//	CartServiceImpl cartService;
//	
//	@Autowired
//	BoardServiceImpl boardService;
	
	// 분리된 관리자 로그인 첫 화면 - 시큐리트 적용
	@RequestMapping("/main.ad")
	public String loginAdmin(HttpServletRequest req, Model model) {
		System.out.println("로그인 : " + (String) req.getSession().getAttribute("clientId"));

		return "/admin/admin-main";
	}
	
	// 분리된 비회원 로그인(실패) 첫 화면 - 시큐리트 적용
	@RequestMapping("/main.do")
	public String loginFailed(HttpServletRequest req, Model model) {
		System.out.println("로그인 : " + (String) req.getSession().getAttribute("clientId"));

		return "/common/main";
	}
	
	// 분리된 회원 로그인 첫 화면 - 시큐리트 적용
	@RequestMapping("/main.cl")
	public String loginClient(HttpServletRequest req, Model model) {
		System.out.println("로그인 : " + (String) req.getSession().getAttribute("clientId"));

		return "/client/login-news";
	}
	
	// 로그아웃
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest req, Model model) {
		logger.info("url ==> logout");

		req.getSession().invalidate();
		System.out.println("clientId : " + (String) req.getSession().getAttribute("clientId"));

		return "/common/main";
	}
	
	// 가입신청
	@RequestMapping("/signInAction.do")
	public String signInAction(HttpServletRequest req, Model model) {
		logger.info("url ==> signInAction");
		
		clientService.signInAction(req, model);	// 메서드 호출
		
		return "/client/signInAction";
	}
	
	// 중복확인
	@RequestMapping("/confirmId.do")
	public String confirmId(HttpServletRequest req, Model model) {
		logger.info("url ==> confirmId");
		
		clientService.confirmId(req, model); // 메서드 호출

		return "/client/confirmId";
	}
	
	// 회원가입 성공 - 시큐리티 적용 시 사용 안 하는 것 같음
	@RequestMapping("/mainSuccess.do")
	public String mainSuccess(HttpServletRequest req, Model model) {
		logger.info("url ==> mainSuccess");

		int cnt = Integer.parseInt(req.getParameter("insertCnt"));
		model.addAttribute("insertCnt", cnt); // 성공 시 1이 전송

		return "/common/main";
	}

	// 이메일 인증 확인
	@RequestMapping("/emailChk.do")
	public String emailChk(HttpServletRequest req, Model model) {
		logger.info("url ==> /emailChk");

		clientService.emailChk(req, model);

		return "/common/emailChk";
	}
	
	// 로그인 - 회원정보 수정 처리
	@RequestMapping("/modifyClientAction.cl")
	public String modifyClientAction(HttpServletRequest req, Model model) {
		logger.info("url ==> modifyClientAction");

		clientService.modifyClientAction(req, model);

		return "/client/modifyClientAction";
	}

	// 로그인 - 회원정보 수정 후 복귀(id/pw 조회절차 생략) - 테스트용(실제로 사용되진 않는다.)
	@RequestMapping("/login.do")
	public String login(HttpServletRequest req, Model model) {
		logger.info("url ==> login");

		return "/client/login-news";
		// 암호화 적용 이전에 이메일 인증 적용여부 확인 => 테스트 성공
		// 다음 단계 : 컨트롤러 @RequestMapping을 영역별로 나눈다. security-context.xml 명시된 영역 설정 확인
		// /**.lo와 같은 방식은 내 프로젝트에 적합하지 않으므로, /client/** 이런 식으로 처리
	}
	
	// 로그인 -회원탈퇴
	@RequestMapping("/deleteClientAction.cl")
	public String deleteClientAction(HttpServletRequest req, Model model) {
		logger.info("url ==> deleteClientAction");

		clientService.deleteClientAction(req, model);

		return "/common/deleteClientAction";
	}
	
	// 관리자
	// 첫 화면 - 결산
	@RequestMapping("/admin.ad")
	public String closing(HttpServletRequest req, Model model) {
		logger.info("url ==> admin");

		return "/admin/admin-main";
	}

	
}
