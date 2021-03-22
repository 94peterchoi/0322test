package com.spring.ball.service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ball.persistence.ClientDAO;
import com.spring.ball.persistence.ClientDAOImpl;
import com.spring.ball.vo.ClientVO;
	
@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	ClientDAO dao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	// 회원가입 처리
	@Override
	public void signInAction(HttpServletRequest req, Model model) {
		// 바구니 생성
		ClientVO vo = new ClientVO();
		
		String encPwd = passwordEncoder.encode(req.getParameter("pwd"));
		System.out.println("암호화된 패스워드 : " + encPwd);
		
		// 화면으로부터 입력받은 값 가져오기 -> 바구니 담기
		vo.setId(req.getParameter("id"));
		vo.setPwd(encPwd);
		vo.setName(req.getParameter("name"));
		
		String phone = "";
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		
		// hp가 필수가 아니므로 null 값이 들어올 수 있으므로 값이 존재할 때만 처리.
		if (!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
			phone = hp1 + "-" + hp2 + "-" + hp3;
		}
		vo.setPhone(phone);
		//phone = hp1 + "-" + hp2 + "-" + hp3;
		//vo.setPhone(phone);
		
		String email = "";
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		email = email1 + "@" + email2;
		vo.setEmail(email);
		
		// reg_date는 입력값이 없으면 default가 sysdate
		vo.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		// Email 인증
		// 인증 키 부분
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 6; i++) {
			int rIndex = rnd.nextInt(2);
			switch (rIndex) {
			case 0:
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 1:
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		String key = temp.toString();

		vo.setEmail_key(key);
		
		// 회원가입 처리
		int insertCnt = dao.insertClient(vo);
		
		if(insertCnt != 0) {
			dao.sendMail(email, key);
		}
		
		// 처리결과 저장
		model.addAttribute("insertCnt", insertCnt);
		System.out.println("insertCnt ==> " + insertCnt);
	}
	
	// 이메일 인증 확인
	@Override
	public void emailChk(HttpServletRequest req, Model model) {
		String key = req.getParameter("email_key");

		int selectCnt = dao.selectKey(key);
		System.out.println("이메일 인증 키 확인(1:성공 / 0:실패) ==> " + selectCnt);
		if (selectCnt == 1) {
			int insertCnt = dao.updateGrade(key);
			model.addAttribute("insertCnt", insertCnt);
		}
	}
	
	// 중복확인 처리
	@Override
	public void confirmId(HttpServletRequest req, Model model) {
		
		String strId = req.getParameter("id");
		int cnt = dao.idCheck(strId);
		
		model.addAttribute("selectCnt", cnt);
		model.addAttribute("id", strId);
		
	}
	
	// 로그인 처리
	@Override
	public void loginAction(HttpServletRequest req, Model model) {
		
		String strId = req.getParameter("input_id");
		String strPwd = req.getParameter("input_pwd");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		
		int selectCnt = dao.idPwdChk(map);
		
		if(selectCnt == 1) {
			req.getSession().setAttribute("clientId", strId);
		}
		
		req.setAttribute("selectCnt", selectCnt);
	}
	
	// 회원정보 인증 및 삭제(탈퇴) 처리
	@Override
	public void deleteClientAction(HttpServletRequest req, Model model) {
		
		int selectCnt = 0;
		int deleteCnt = 0;
		
		String strId = (String)req.getSession().getAttribute("clientId");
		String strPwd = req.getParameter("pwd");
		
		// 시큐리티 적용 -> dao에서 pwdCheck 가져오기
		String pwd = dao.pwdCheck(strId);
		System.out.println("DB 비밀번호 : " + strPwd);
		System.out.println("입력 비밀번호 : " + pwd);

		// passwordEncoder.matches(입력비밀번호, DB비밀번호) 적용
		System.out.println("비밀번호 대조 결과 : " + passwordEncoder.matches(strPwd, pwd));

		// 비밀번호가 일치하는 경우
		if (passwordEncoder.matches(strPwd, pwd)) {
			selectCnt = 1;
		}
		// 비밀번호가 불일치하는 경우
		else {
			selectCnt = -1;
		}
		
		if(selectCnt == 1) {
			deleteCnt = dao.deleteClient(strId);
		}
		
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("deleteCnt", deleteCnt);
	}
	
	// 회원정보 인증 및 상세 페이지
	@Override
	public void modifyViewAction(HttpServletRequest req, Model model) {
		
		int selectCnt = 0;
		ClientVO vo = null;
		
		String strId = (String)req.getSession().getAttribute("clientId");
		String strPwd = req.getParameter("pwd");
		
		// 시큐리티 적용 -> dao에서 pwdCheck 가져오기
		String pwd = dao.pwdCheck(strId);
		System.out.println("입력 비밀번호 : " + strPwd);
		System.out.println("DB 비밀번호 : " + pwd);

		// passwordEncoder.matches(입력비밀번호, DB비밀번호) 적용
		System.out.println("비밀번호 대조 결과 : " + passwordEncoder.matches(strPwd, pwd));

		// 비밀번호가 일치하는 경우
		if (passwordEncoder.matches(strPwd, pwd)) {
			selectCnt = 1;
		}
		
		if(selectCnt == 1) {
			vo = dao.getClientInfo(strId);
		}
		
		req.setAttribute("selectCnt", selectCnt);
		req.setAttribute("vo", vo);
		
	}

	// 회원정보 수정처리
	@Override
	public void modifyClientAction(HttpServletRequest req, Model model) {
		
		ClientVO vo = new ClientVO();
		
		vo.setId((String)req.getSession().getAttribute("clientId"));	
		String encryptPassword = passwordEncoder.encode(req.getParameter("mod_pwd"));
		System.out.println("암호화된 패스워드 : " + encryptPassword);
		
		vo.setPwd(encryptPassword);
		vo.setName(req.getParameter("mod_name"));
		
		String phone = "";
		String hp1 = req.getParameter("mod_hp1");
		String hp2 = req.getParameter("mod_hp2");
		String hp3 = req.getParameter("mod_hp3");
		if (!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
			phone = hp1 + "-" + hp2 + "-" + hp3;
		}
		vo.setPhone(phone);
		
		String email = "";
		String email1 = req.getParameter("mod_email1");
		String email2 = req.getParameter("mod_email2");
		email = email1 + "@" + email2;
		vo.setEmail(email);
		
		int updateCnt = dao.updateClient(vo);
		
		model.addAttribute("updateCnt", updateCnt);
		req.getSession().setAttribute("clientId", vo.getId());	// 수정 후 알 수 없는 이유로 세션이 삭제돼 이 코드 삽입

	}

}
