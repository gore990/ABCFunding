package com.abcfund.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcfund.model.dao.ABCAccountDao;
import com.abcfund.model.dao.ABCMemberDao;
import com.abcfund.model.dto.ABCAccountDto;
import com.abcfund.model.dto.ABCMemberDto;

/**
 * 회원 및 계좌 서비스 클래스
 * @author 9age
 *
 */
@Service
public class ABCMemberServiceImpl implements ABCMemberService {
	/** 회원의 DAO */
	@Autowired
	private ABCMemberDao dao;
	
	/** 계정의 DAO */
	@Autowired
	private ABCAccountDao accDao;
	
	/**
	 * 회원 등록 메서드
	 * 회원 등록과 동시에 가상계좌도 발급
	 * @param dto 회원 객체
	 */
	@Override
	public void addMemeber(ABCMemberDto dto) {
		// 회원 등록
		dao.addMemeber(dto);
		
		/* 가상계좌 생성 및 등록 */
		ABCAccountDto accDto = 
			new ABCAccountDto("", dto.getEmail(), dto.getName());
		
		accDao.addAccount(accDto);
	}
	
	/**
	 * 회원 등급을 미인증에서 일반회원으로 변경하는 메서드
	 * @param dto 회원 객체
	 */
	@Override
	public void doAuthMember(ABCMemberDto dto) {
		dao.doAuthCode(dto);
	}
	
	/**
	 * 로그인하는 메서드
	 * @param dto 회원 객체
	 * @return 로그인 성공시 true
	 */
	@Override
	public boolean login(ABCMemberDto dto) {
		return dao.login(dto);
	}
	
	/**
	 * 내 정보에서 비밀번호 변경할 때 호출하는 메서드
	 * @param dto 회원 객체
	 */
	@Override
	public void changePwd(ABCMemberDto dto) {
		dao.changePwd(dto);
	}
	
	/**
	 * 회원의 인증코드를 변경하는 메서드.
	 * 비밀번호 찾기로 이메일 인증하려는 경우 사용
	 * @param dto 회원 객체
	 */
	@Override
	public void changeAuthCode(ABCMemberDto dto) {
		dao.changeAuthCode(dto);
	}
	
	/**
	 * 이메일 인증으로 비밀번호를 변경할 때 사용하는 메서드
	 * @param dto 회원 객체
	 */
	@Override
	public void changePwdOnAuth(ABCMemberDto dto) {
		dao.changePwdOnAuth(dto);
	}
}
