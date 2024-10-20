package com.bankmisr.email.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	public void sendWelcomeEmail(String to, String name) {
		Context context = new Context();
		context.setVariable("name", name);

		String processHtml = templateEngine.process("welcome-email", context);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		try {
			helper.setText(processHtml, true); // true indicates HTML
			helper.setTo(to);
			helper.setSubject("Welcome to Our Service!");
			helper.setFrom("noreply@yourdomain.com");
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}