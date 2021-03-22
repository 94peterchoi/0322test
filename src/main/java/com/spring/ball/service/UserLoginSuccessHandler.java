package com.spring.ball.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.spring.ball.vo.UserVO;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public UserLoginSuccessHandler(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 로그인이 성공한 경우에 실행한 코드
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		UserVO vo = (UserVO)authentication.getPrincipal();
		System.out.println("UserVO==> " + vo);
		
		String msg = authentication.getName() + "님 환영합니다";
		System.out.println("아이디 ==> " + authentication.getName());
		
		String grade = sqlSession.selectOne("com.spring.ball.persistence.ClientDAO.gradeCheck", authentication.getName());
		int gradeCnt = 0;
		
		if(grade.equals("ROLE_USER")) {
			gradeCnt = 1;
		}
		else if(grade.equals("ROLE_ADMIN")) {
			gradeCnt = 2;
		}
		else {
			gradeCnt = 0;
		}
		
		System.out.println("접속자 등급 : " + gradeCnt);
		
		request.setAttribute("msg", msg);
		request.getSession().setAttribute("clientId", authentication.getName());
		request.getSession().setAttribute("gradeCnt", gradeCnt);
		
		// 아래 화면 단에서 접속자 등급(gradeCnt) 체크 후 페이지 자동 이동 자바스크립트 실행
		// <body onload = "함수();">
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/junction.jsp");		
		
		rd.forward(request, response);
	}

}
