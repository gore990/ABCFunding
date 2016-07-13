package com.abcfund.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcfund.model.dao.ABCAccountDao;
import com.abcfund.model.dao.ABCMemberDao;
import com.abcfund.model.dto.ABCAccountDto;
import com.abcfund.model.dto.ABCMemberDto;

/**
 * ȸ�� �� ���� ���� Ŭ����
 * @author 9age
 *
 */
@Service
public class ABCMemberServiceImpl implements ABCMemberService {
	/** ȸ���� DAO */
	@Autowired
	private ABCMemberDao dao;
	
	/** ������ DAO */
	@Autowired
	private ABCAccountDao accDao;
	
	/**
	 * ȸ�� ��� �޼���
	 * ȸ�� ��ϰ� ���ÿ� ������µ� �߱�
	 * @param dto ȸ�� ��ü
	 */
	@Override
	public void addMemeber(ABCMemberDto dto) {
		// ȸ�� ���
		dao.addMemeber(dto);
		
		/* ������� ���� �� ��� */
		ABCAccountDto accDto = 
			new ABCAccountDto("", dto.getEmail(), dto.getName());
		
		accDao.addAccount(accDto);
	}
	
	/**
	 * ȸ�� ����� ���������� �Ϲ�ȸ������ �����ϴ� �޼���
	 * @param dto ȸ�� ��ü
	 */
	@Override
	public void doAuthMember(ABCMemberDto dto) {
		dao.doAuthCode(dto);
	}
	
	/**
	 * �α����ϴ� �޼���
	 * @param dto ȸ�� ��ü
	 * @return �α��� ������ true
	 */
	@Override
	public boolean login(ABCMemberDto dto) {
		return dao.login(dto);
	}
	
	/**
	 * �� �������� ��й�ȣ ������ �� ȣ���ϴ� �޼���
	 * @param dto ȸ�� ��ü
	 */
	@Override
	public void changePwd(ABCMemberDto dto) {
		dao.changePwd(dto);
	}
	
	/**
	 * ȸ���� �����ڵ带 �����ϴ� �޼���.
	 * ��й�ȣ ã��� �̸��� �����Ϸ��� ��� ���
	 * @param dto ȸ�� ��ü
	 */
	@Override
	public void changeAuthCode(ABCMemberDto dto) {
		dao.changeAuthCode(dto);
	}
	
	/**
	 * �̸��� �������� ��й�ȣ�� ������ �� ����ϴ� �޼���
	 * @param dto ȸ�� ��ü
	 */
	@Override
	public void changePwdOnAuth(ABCMemberDto dto) {
		dao.changePwdOnAuth(dto);
	}
}
