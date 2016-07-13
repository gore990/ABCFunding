package com.abcfund.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abcfund.model.dto.ABCMemberDto;

/**
 * @author 9age
 *
 */
@Repository
public class ABCMemberDaoImpl implements ABCMemberDao {
	@Autowired
	private SqlSession sqlSession;
	
	/** MyBatis 회원 네임스페이스 */
	private String nameSpace = "ABCMember.";
	
	@Override
	public void addMemeber(ABCMemberDto dto) {
		sqlSession.insert(nameSpace + "addMember", dto);		
	}

	@Override
	public void doAuthCode(ABCMemberDto dto) {
		sqlSession.update(nameSpace + "doAuthCode", dto);
	}

	@Override
	public boolean login(ABCMemberDto dto) {
		/* null이 반환되면 실패 */
		Object obj = sqlSession.selectOne(nameSpace + "login", dto);
		if (obj == null)
			return false;
		return true;
	}

	@Override
	public void changePwd(ABCMemberDto dto) {
		sqlSession.update(nameSpace + "changePwd", dto);
	}

	@Override
	public void changeAuthCode(ABCMemberDto dto) {
		sqlSession.update(nameSpace + "changeAuthCode", dto);
	}

	@Override
	public void changePwdOnAuth(ABCMemberDto dto) {
		sqlSession.update(nameSpace + "changePwdOnAuth", dto);
	}

}
