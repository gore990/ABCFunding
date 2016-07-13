package com.abcfund.util;

import java.util.Random;

/**
 * ABC 펀딩에 사용하는 유틸 클래스
 * 상속하지 않고 클래스(static) 메서드만을 사용
 * @author 9age
 *
 */
public final class ABCUtility {
	/**
	 * 지정된 자릿수만큼 난수를 생성하는 메서드
	 * @param count 생성할 난수의 자릿수
	 * @return 생성된 난수
	 */
	public static String randomNumber(int count) {
		// 난수 생성기
		Random random = 
			new Random((long)(Math.random()*System.currentTimeMillis()));
		
		// 난수 붙이기
		StringBuilder randNum = new StringBuilder();
		for(int idx = 0; idx < count; idx++) {
			randNum.append(random.nextInt(10));
		}
		
		return randNum.toString();
	}
}
