package com.abcfund.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abcfund.model.dto.ABCAccountDto;

/**
 * 계좌 DAO 구현 클래스
 * @author 9age
 *
 */
@Repository
public class ABCAccountDaoImpl implements ABCAccountDao {
	@Autowired
	private SqlSession sqlSession;
	
	/** MyBatis 계좌의 네임스페이스 */
	private String namespace = "ABCAccount.";

	@Override
	public void addAccount(ABCAccountDto dto) {
		sqlSession.insert(namespace + "addAccount", dto);
	}

}
