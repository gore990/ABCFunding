package com.abcfund.model.dao;

import com.abcfund.model.dto.ABCAccountDto;

/**
 * 계좌 DAO 인터페이스
 * @author 9age
 *
 */
public interface ABCAccountDao {
	void addAccount(ABCAccountDto dto);
}
