package com.spring.ball.persistence;
import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import com.spring.ball.vo.ClientVO;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	private JavaMailSender mailSender;
		
	// 회원가입 처리
	@Override
	public int insertClient(ClientVO vo) {
		return sqlSession.insert("com.spring.ball.persistence.ClientDAO.insertClient", vo);
	}

	// 이메일 인증
	@Override
	public void sendMail(String toEmail, String key) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			String txt = "회원가입 인증 메일입니다. 링크를 눌러 회원가입을 완료하세요."

					+ "<a href='http://localhost/ball/emailChk.do?email_key=" + key + "'>Please click</a>";
			System.out.println(txt);
			message.setSubject("회원가입 인증 메일입니다.");
			message.setText(txt, "UTF-8", "html");
			message.setFrom(new InternetAddress("gjehgod90@gmail.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress(toEmail));
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// 이메일 인증 확인
	@Override
	public int selectKey(String key) {
		return sqlSession.selectOne("com.spring.ball.persistence.ClientDAO.selectKey", key);
	}

	// 이메일 인증 완료 - 사용 가능(Enable : 1)
	@Override
	public int updateGrade(String key) {
		return sqlSession.update("com.spring.ball.persistence.ClientDAO.updateGrade", key);
	}
	
	// 중복확인 처리
	@Override
	public int idCheck(String strId) {
		return sqlSession.selectOne("com.spring.ball.persistence.ClientDAO.idCheck", strId);
	
		//String sql = "SELECT guestid FROM guest WHERE guestid=?";
	}
	
	
	// 로그인 처리
	@Override
	public int idPwdChk(Map<String, String> map) {
		return sqlSession.selectOne("com.spring.ball.persistence.ClientDAO.idPwdChk", map);
		//String sql = "SELECT * FROM guest WHERE guestId=?";
	}

	// 회원정보 인증
	@Override
	public String pwdCheck(String strId) {
		return sqlSession.selectOne("com.spring.ball.persistence.ClientDAO.pwdCheck", strId);
	}
	
	// 회원정보 삭제 처리
	@Override
	public int deleteClient(String strId) {
		return sqlSession.delete("com.spring.ball.persistence.ClientDAO.deleteClient", strId);
		// String sql = "DELETE guest WHERE guestId=?";
	}
	
	// 회원정보 상세 페이지
	@Override
	public ClientVO getClientInfo(String strId) {
		return sqlSession.selectOne("com.spring.ball.persistence.ClientDAO.getClientInfo", strId);
		// String sql = "SELECT * FROM guest WHERE guestid=?";
	}
	
	
	// 회원정보 수정 처리
	@Override
	public int updateClient(ClientVO vo) {
		return sqlSession.update("com.spring.ball.persistence.ClientDAO.updateClient", vo);
		// String sql = "UPDATE guest SET guestPwd=?, guestName=?, phone=?, guestEmail=?
		// WHERE guestId=?";

	}
	
}
