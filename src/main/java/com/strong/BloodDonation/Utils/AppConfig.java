package com.strong.BloodDonation.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Value("${bloodDonation.organisation.Email}")
	private String email;

	@Value("${bloodDonation.organisation.EmailPassword}")
	private String password;

	@Value("${bloodDonation.organisation.TimeZone}")
	private String timeZone;

	@PostConstruct
	public void SetTimeDate() {
		TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
		System.out.println("Started At: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername(email);
		mailSender.setPassword(password);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");

		return mailSender;
	}

	@Override
	public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://127.0.0.1:5500")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
		WebMvcConfigurer.super.addCorsMappings(registry);
	}

}
