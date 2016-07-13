package com.abcfund.controller;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abcfund.model.dto.ABCMemberDto;
import com.abcfund.model.service.ABCMemberService;
import com.abcfund.util.SMTPAuthenticator;

/**
 * @author 9age
 *
 */
@Controller
public class ABCMemberController {
	@Autowired
	ABCMemberService service;
	
	/** 기본 제목 */
	public static String MAIN_TITLE = "ABC Funding";
	
	/**
	 * 메인 페이지 이동 메서드
	 * 제목 설정만 하고 바로 메인페이지로 이동
	 * @param model
	 * @return 메인 페이지의 타일즈명
	 * @throws Exception
	 */
	@RequestMapping(value = "main.do", method = RequestMethod.GET)
	public String main(Model model) throws Exception {
		// 제목 설정
		model.addAttribute("title", MAIN_TITLE);
		
		return "main.tiles";
	}
	
	/**
	 * 회원가입 페이지 이동 메서드
	 * 제목 설정만 하고 회원가입 페이지로 이동.
	 * @param model
	 * @return 회원가입 페이지의 타일즈명
	 * @throws Exception
	 */
	@RequestMapping(value = "regi.do", method = RequestMethod.GET)
	public String regi(Model model) throws Exception {
		// 기본제목에 해당 기능명을 붙인다.
		model.addAttribute("title", "회원가입 :: " + MAIN_TITLE);
		
		return "regi.tiles";
	}
	
	/**
	 * 회원등록 요청받는 메서드
	 * @param model 제목 설정을 위한 것
	 * @param dto 가입정보가 들어간 DTO 객체
	 * @return 회원가입 성공 페이지의 타일즈명
	 * @throws Exception
	 */
	@RequestMapping(value = "addMember.do", method = RequestMethod.POST)
	public String addMember(Model model, ABCMemberDto dto) throws Exception {
		// 기본제목에 해당 기능명을 붙인다.
		model.addAttribute("title", "회원가입 :: " + MAIN_TITLE);
		
		// 이메일 인증메일 발송
		emailAuthentication(dto, "authAf.do");
		
		// 회원 등록
		service.addMemeber(dto);		
		
		return "regiSuc.tiles";
	}
	
	/**
	 * 로그인 화면으로 이동하는 메서드
	 * @param model
	 * @return 로그인 타일즈명
	 */
	@RequestMapping (value="login.do", method = RequestMethod.GET)
	public String login(Model model, String isFail) {
		// 기본제목에 해당 기능명을 붙인다.
		model.addAttribute("title", "로그인 :: " + MAIN_TITLE);
		
		// 로그인 실패시 isFail이 yes로 넘어오고 그 외 null
		model.addAttribute("isFail", isFail);
		
		return "login.tiles";
	}
	
	/**
	 * 로그인 후 이동할 화면을 처리하는 메서드
	 * @param model 제목을 설정하는 것
	 * @param request 세션설정을 위한 것
	 * @return 로그인 후 이동할 타일즈명
	 */
	@RequestMapping (value="loginAf.do", method = RequestMethod.POST)
	public String loginAf(Model model, HttpServletRequest request ,ABCMemberDto dto) {
		// 서버에 로그인 요청이 실패한 경우
		if(!service.login(dto)) {
			return "redirect:/login.do?isFail=true"; // 로그인 화면으로 다시 돌아간다.
		}
		
		// 요청이 성공한 경우 세션 등록
		request.getSession().setAttribute("login", dto);
		
		/* 일반회원은 메인화면, 관리자는 관리자화면으로 간다. */
		String tile = "redirect:/main.do";
		if (dto.getGrade() == 2)
			tile = "redirect:/admin.do";
		
		return tile;
	}
	
	/**
	 * 로그아웃한 뒤 메인으로 넘어가는 메서드
	 * @param request 세션 해제를 위한 것
	 * @return 메인 페이지의 타일즈명
	 */
	@RequestMapping(value="logout.do", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		// 세션 해제
		request.getSession().invalidate();
		
		return "redirect:/main.do";
	}
	
	/**
	 * 비밀번호 찾기 이동 메서드
	 * @param model
	 * @return 비밀번호 찾기 페이지의 타일즈명
	 */
	@RequestMapping(value="searchpwd.do", method = RequestMethod.GET)
	public String searchpwd(Model model) {
		// 기본제목에 해당 기능명을 붙인다.
		model.addAttribute("title", "비밀번호 찾기" + MAIN_TITLE);
		
		return "searchpwd.tiles";
	}
	
	/**
	 * 비밀번호 찾기에서 인증메일 발송 할 때 처리되는 메서드
	 * @param dto 이메일 데이터만 있는 회원 DTO
	 * @return 메인 페이지로 리다이렉트
	 */
	@RequestMapping(value="searchpwdAf.do", method = RequestMethod.POST)
	public String searchpwdAf(ABCMemberDto dto) {
		// 인증 이메일 발송
		emailAuthentication(dto, "pwdAfAuth.do");
		
		// 새로운 인증코드로 변경
		service.changeAuthCode(dto);
		
		return "redirect:/main.do";
	}
	
	/**
	 * 로그인 화면에서 비밀번호 찾기를 누른 후 이메일 인증이되면
	 * 해당 비밀번호 변경 페이지로 이동하는 메서드
	 * @param model 제목 변경
	 * @return 비밀번호 변경 페이지의 타일즈명
	 */
	@RequestMapping(value="pwdAfAuth.do", method = RequestMethod.GET)
	public String pwdAfAuth(Model model, HttpServletRequest request ,ABCMemberDto dto) {
		// 기본제목에 해당 기능명을 붙인다.
		model.addAttribute("title", "비밀번호 변경 :: " + MAIN_TITLE);
		
		// 이메일 디코딩
		byte[] decoded = Base64.decodeBase64(dto.getEmail().getBytes());
		
		// 디코딩된 이메일로 설정
		dto.setEmail(new String(decoded));
		
		// 세션에 등록
		request.getSession().setAttribute("authAf", dto);
		
		return "pwdAfAuth.tiles";
	}
	
	/**
	 * 비밀번호 찾기로 비밀번호 변경 후 페이지 이동하는 메서드
	 * @param request 등록한 세션을 가져오는 객체
	 * @param pwd 변경할 비밀번호
	 * @return 로그인 페이지의 타일즈로 리다이렉트
	 */
	@RequestMapping(value="pwdAfAuthAf.do", method = RequestMethod.POST)
	public String pwdAfAuthAf(HttpServletRequest request , String pwd) {
		/* 세션에 등록한 회원객체에 변경할 비밀번호를 설정 */
		ABCMemberDto dto = (ABCMemberDto)(request.getSession().getAttribute("authAf"));
		dto.setPwd(pwd);
		
		// 쓸모없어진 세션을 해제
		request.getSession().invalidate();
		
		// 인증코드에 따른 비밀번호 변경 요청
		service.changePwdOnAuth(dto);
		
		return "redirect:/login.do";
	}
	
	/**
	 * 이메일 인증하는 메서드
	 * @param dto 인증할 회원정보
	 * @param path 인증할 때 찾아갈 경로
	 */
	public void emailAuthentication(ABCMemberDto dto, String path) {
		try {
			// 콘솔 출력
			System.out.println(dto);
			
			// 인증키 생성
			String uuid = UUID.randomUUID().toString().replace("-", "");
			dto.setAuthCode(uuid);
			
			/* 발송할 메일 정보 */
			// 송신 메일주소
			String from = "dlqjatlr990@gmail.com";
			
			// 메일 제목
			String subject = "[ABC Funding]요청하신 인증메일입니다.";
			
			// 이메일 인코딩
			byte[] encodeEmail = Base64.encodeBase64(dto.getEmail().getBytes());
			String content =
				"http://192.168.2.9:8090/ABCFunding/"+path+"?email="+
						new String(encodeEmail)+"&authCode="+uuid;

			/* SMTP 서버에 접속하기 위한 정보들 */
			Properties p = new Properties();
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "465");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback", "false");			
			
			// SMTP 인증정보 생성
			Authenticator auth = new SMTPAuthenticator();
			
			// SMTP의 연결정보와 인증정보로 세션 생성
			Session session = Session.getInstance(p, auth);
			
			// 세션 디버그 셋
			session.setDebug(true);
			
			/* 메일의 내용을 담을 객체생성 */
			MimeMessage msg = new MimeMessage(session);
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(dto.getEmail());
			String emailContent = makeEmail(content, dto.getEmail());
			
			// 제목
			msg.setSubject(subject);

			// 보내는 사람
			msg.setFrom(fromAddr); 

			// 받는 사람
			msg.addRecipient(Message.RecipientType.TO, toAddr); 
			
			// 작성된 이메일
			msg.setText(emailContent, "UTF-8");
			
			// MIME 설정
			msg.setHeader("content-Type", "text/html;charset=utf-8");
			
			// 인증메일 전송
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 이메일 내용 작성 메서드
	 * @param content 메일 내용
	 * @param email 받는 이 메일주소
	 * @return 생성된 HTML String
	 */
	public String makeEmail(String content, String email){
		StringBuffer sb = new StringBuffer(); 

		// 항목 
		sb.append("<HTML>\n"); 
		
		sb.append("<HEAD>\n"); 
		sb.append("<TITLE>"+"안내문입니다."+"</TITLE>\n"); 
		sb.append("<META content=text/html; charset=utf-8>\n"); 
		sb.append("</HEAD>\n"); 
		sb.append("<BODY text=black vLink=blue aLink=red link=blue bgColor=#ffffff>\n"); 
		//기본정보 소제목 
		sb.append("<P align=left><B><FONT size=8px face=굴림 color=black>메일 인증하기</FONT></B></P>\n");
		sb.append("<P align=left><B><FONT size=6px face=굴림 color=black>"+email+"님!! 환영합니다.</FONT></B></P>\n");
		sb.append("<P align=left><B><FONT size=6px face=굴림 color=black>아래의 링크를 눌러 인증을 완료하세요.</FONT></B></P>\n");
		
		sb.append("<P align=left><a href='" + content +"'><FONT size=5px face=굴림 color=blue>지금 인증하기!!</FONT></a></P>\n");

		//기본정보 소제목 종료
		sb.append("</BODY>\n"); 
		sb.append("</HTML>\n"); 
		
		return sb.toString();
	}
	
	/**
	 * 이메일 인증 확인 메서드
	 * @param dto 회원 정보
	 * @param model
	 * @return 로그인 페이지의 타일즈명
	 */
	@RequestMapping(value = "authAf.do", method = RequestMethod.GET)
	public String authAf(ABCMemberDto dto, Model model) {
		// 기본제목에 해당 기능명을 붙인다.
		model.addAttribute("title", "이메일 인증 :: " + MAIN_TITLE);
		
		try {
			// 이메일 디코딩
			byte[] decoded = Base64.decodeBase64(dto.getEmail().getBytes());
			
			// 디코딩된 이메일 설정
			dto.setEmail(new String(decoded));
			
			// 일반회원으로 권한 부여
			System.out.println(dto);
			service.doAuthMember(dto);
			
			// 의미불명 : model.addAttribute("status", "100");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/login.do";
	}
}