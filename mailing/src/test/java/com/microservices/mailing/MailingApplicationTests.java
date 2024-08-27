package com.microservices.mailing;

import com.microservices.mailing.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService emailService;

	@Test
	public void testSendEmailMessage() {
		// Настройте данные для теста
		String to = "ratatyi04@gmail.com";
		String subject = "Test Subject";
		String text = "This is a test email.";

		// Вызов метода для отправки email
		emailService.sendEmailMessage(to, subject, text);

		// После отправки вы можете проверить почтовый ящик получателя, чтобы убедиться, что email был доставлен.
		// Также можно настроить Mock SMTP-сервер для автоматического тестирования.
	}

}
