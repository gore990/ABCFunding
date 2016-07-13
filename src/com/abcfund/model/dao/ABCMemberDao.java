package com.abcfund.model.dao;

import com.abcfund.model.dto.ABCMemberDto;

/**
 * 회원에 대한 DAO 인터페이스
 * @author Beom
 *
 */
public interface ABCMemberDao {
	void addMemeber(ABCMemberDto dto);
	void doAuthCode(ABCMemberDto dto);
	boolean login(ABCMemberDto dto);
	void changePwd(ABCMemberDto dto);
	void changeAuthCode(ABCMemberDto dto);
	void changePwdOnAuth(ABCMemberDto dto);
}
