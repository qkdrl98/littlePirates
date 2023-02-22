package com.littlePirates.project.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.littlePirates.project.dao.IEmailDAO;

@Service
public class EmailService implements IEmailService {

	@Autowired
	@Qualifier("IEmailDAO")
	IEmailDAO dao;

	@Autowired
	JavaMailSender mailSender;

	@Override // 이메일 인증 시 테이블 생성
	public void emailAuthCreate(String memId) {
		dao.emailAuthCreate(memId);
	}

	@Override // 이메일 테이블에 이미 아이디 있는지 확인
	public String emailMemIdCheck(String memId) {
		String email_result = dao.emailMemIdCheck(memId);

		return email_result;
	}

	@Override // 이메일 인증 키 DB 저장
	public void updateEmailAuthKey(String memId, String authKey) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memId", memId);
		map.put("authKey", authKey);

		dao.updateEmailAuthKey(map);
	}

	@Override // 인증 이메일 보내기
	public String sendAuthEamil(String memEmail, String body) {

		EmailHandler mail;

		try {
			mail = new EmailHandler(mailSender);
			mail.setFrom("dev.dkska0306@gmail.com", "Little Pirates");
			mail.setTo(memEmail);
			mail.setSubject("꼬마 해적단(Little Pirates) 입단을 위해 인증해 주세요!");
			mail.setText(body);
			mail.send();

		} catch (Exception e) {
			e.printStackTrace();
		}

		String sendResult = "인증 메일이 발송되었으니 확인해 주세요. 인증키는 30초 후 만료됩니다.";

		return sendResult;
	}

	@Override // 아이디 찾기 인증 이메일 보내기
	public String findIdSendEamil(String memEmail, String body) {

		EmailHandler mail;

		try {
			mail = new EmailHandler(mailSender);
			mail.setFrom("dev.dkska0306@gmail.com", "Little Pirates");
			mail.setTo(memEmail);
			mail.setSubject("꼬마 해적단(Little Pirates) 아이디를 찾기 위해 인증해 주세요!");
			mail.setText(body);
			mail.send();

		} catch (Exception e) {
			e.printStackTrace();
		}

		String sendResult = "인증 메일이 발송되었으니 확인해 주세요. 인증키는 30초 후 만료됩니다.";

		return sendResult;
	}
	
	@Override // 비밀번호 변경 인증 이메일 보내기
	public String findPwdSendEamil(String memEmail, String body) {
		
		EmailHandler mail;
		
		try {
			mail = new EmailHandler(mailSender);
			mail.setFrom("dev.dkska0306@gmail.com", "Little Pirates");
			mail.setTo(memEmail);
			mail.setSubject("꼬마 해적단(Little Pirates) 비밀번호를 변경 하기 위해 인증해 주세요!");
			mail.setText(body);
			mail.send();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sendResult = "인증 메일이 발송되었으니 확인해 주세요. 인증키는 30초 후 만료됩니다.";
		
		return sendResult;
	}

	@Override // 인증키와 시간 DB에서 가져오기
	public HashMap<String, Object> getKeyAndTime(String memId) {
		
		HashMap<String, Object> map = dao.getKeyAndTime(memId);
		
		return map;
	}
	
	@Override // 인증 상태 변경0
	public void updateEmailAuth0(String memId) {
		dao.updateEmailAuth0(memId);
	}

	@Override // 인증 상태 변경1
	public void updateEmailAuth1(String memId, String authKeyCheck) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memId", memId);
		map.put("authKeyCheck", authKeyCheck);

		dao.updateEmailAuth1(map);
	}
	
	@Override // 인증 상태 변경2
	public void updateEmailAuth2(String memId) {
		dao.updateEmailAuth2(memId);
	}
	
	@Override // 회원가입1 창 나가면 인증 DB 삭제
	public void emailAuthDelete(String memId) {
		dao.emailAuthDelete(memId);
	}

}
