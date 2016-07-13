package com.abcfund.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author 9age
 *
 */
public class SMTPAuthenticator extends Authenticator {
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("dlqjatlr990", "gore640714");
	}
}
