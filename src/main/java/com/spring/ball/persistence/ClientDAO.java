package com.spring.ball.persistence;

import java.util.ArrayList;
import java.util.Map;

import com.spring.ball.vo.ClientVO;

// 공책방 회원 DAO
public interface ClientDAO {
	
	// 회원가입 처리
	public int insertClient(ClientVO vo);

	// 이메일 인증
	public void sendMail(String toEmail, String key);

	// 이메일 인증 확인
	public int selectKey(String key);

	// 이메일 인증 완료 - 사용 가능(Enable : 1)
	public int updateGrade(String key);

	// 중복확인 처리
	public int idCheck(String strId);

	// 로그인 처리
	public int idPwdChk(Map<String, String> map);

	// 회원정보 인증
	public String pwdCheck(String strId);

	// 회원정보 삭제 처리
	public int deleteClient(String strId);

	// 회원정보 상세 페이지
	public ClientVO getClientInfo(String strId);

	// 회원정보 수정 처리
	public int updateClient(ClientVO vo);
	
}
