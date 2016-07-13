package com.abcfund.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ���ڿ� ���õ� ��Ʈ�ѷ�
 * @author 9age
 *
 */
@Controller
public class ABCInvestController {
	/** �⺻ ���� */
	public static String MAIN_TITLE = "ABC Funding";
	
	/**
	 * �����ϱ� �������� �̵��ϴ� �޼���
	 * @param model ������ ������ ��ü
	 * @return �����ϱ� �������� Ÿ�����
	 */
	@RequestMapping(value="invest.do", method=RequestMethod.GET)
	public String invest(Model model) {
		model.addAttribute("title", "�����ϱ� :: "+MAIN_TITLE);
		
		return "invest.tiles";
	}
}
