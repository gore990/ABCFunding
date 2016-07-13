package com.abcfund.model.service;

import com.abcfund.model.dto.ABCAccountDto;
import com.abcfund.model.dto.ABCMemberDto;

/**
 * @author Hankyung_EDU
 *
 */
public interface ABCMemberService {
	void addMemeber(ABCMemberDto dto);
	void doAuthMember(ABCMemberDto dto);
	boolean login(ABCMemberDto dto);
	void changePwd(ABCMemberDto dto);
	void changeAuthCode(ABCMemberDto dto);
	void changePwdOnAuth(ABCMemberDto dto);
}