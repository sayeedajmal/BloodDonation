package com.strong.BloodDonation.Email;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.strong.BloodDonation.Utils.BloodType;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.NonNull;

/**
 * Service class for sending emails for various events in the Blood Donation
 * application.
 */
@Service
@EnableAsync
public class MailService {

  @Value("${bloodDonation.organisation.name}")
  private String OrganisationName;

  @Value("${bloodDonation.organisation.website}")
  private String OrganisationWebsite;

  @Value("${bloodDonation.organisation.PhoneNumber}")
  private String OrganisationPhone;

  @Value("${bloodDonation.organisation.Location}")
  private String OrganisationLocation;

  @Autowired
  private JavaMailSender emailSender;

  /**
   * Sends a MimeMessage email with the provided details.
   * 
   * @param to      the recipient email address
   * @param subject the subject of the email
   * @param text    the content of the email
   */
  @Async
  public void sendEmail(@NonNull String to, @NonNull String subject, @NonNull String htmlContent) {
    try {
      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(htmlContent, true);

      emailSender.send(message);
    } catch (MessagingException e) {
      System.out.println("Failed to send email: " + e.getMessage());
    }
  }

  /**
   * Sends a welcome email to a new donor who signs up.
   * 
   * @param to        the recipient email address
   * @param donorName the name of the donor
   * @param donorId   the unique ID of the donor
   */
  @Async
  public void sendDonorSignUpEmail(String to, String donorName, int donorId) {
    String subject = "Welcome to Blood Donation Portal";
    String htmlContent = "<html><head><style>"
        + "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f5f5f5; }"
        + ".container { background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }"
        + "h1 { text-align: center; font-size: 24px; color: #333; }"
        + "p { line-height: 1.5; color: #666; }"
        + ".heart { color: #e74c3c; font-size: 2em; margin-right: 5px; }"
        + ".button { display: block; background-color: #e74c3c; color: #fff; padding: 10px 20px; border: none; border-radius: 5px; text-align: center; cursor: pointer; margin-top: 20px; text-decoration: none; }"
        + ".button:hover { background-color: #c0392b; }"
        + "</style></head><body>"
        + "<div class='container'>"
        + "<h1><span class='heart'>&hearts;</span> You're a Lifesaver!</h1>"
        + "<p>Dear <b>" + donorName + "</b>, Your ID : <b>" + donorId + "</b>,</p>"
        + "<p>From the bottom of our hearts, thank you for choosing to donate blood! Your generous act will touch the lives of countless individuals in need.</p>"
        + "<p>Your decision doesn't just fill a vial, it fills their hope, their laughter, and their second chances. You are a true hero!</p>"
        + "<p>We will send you a separate email with your appointment confirmation and details once you have scheduled your donation.</p>"
        + "<p>In the meantime, you can visit our website to learn more about the heroes saving lives every day and how you can be a part of it:</p>"
        + "<p><a href='" + OrganisationWebsite + "' class='button'>Visit our website</a></p>"
        + "<p>Thank you again for making a difference!</p>"
        + "<p>Sincerely,<br/>" + OrganisationName + "Team. </p>"
        + "</div>"
        + "</body></html>";
    sendEmail(to, subject, htmlContent);
  }

  /**
   * Sends an appointment confirmation email to a donor.
   * 
   * @param fullName        the full name of the recipient
   * @param to              the recipient email address
   * @param appointmentDate the date of the appointment
   * @param appointmentTime the time of the appointment
   */
  public void sendAppointmentNotification(String fullName, String to, LocalDate appointmentDate,
      LocalTime appointmentTime) {
    String subject = "Appointment Confirmation";
    String htmlContent = "<html><head><style>"
        + "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f5f5f5; }"
        + ".container { background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }"
        + "h1 { text-align: center; font-size: 24px; color: #333; }"
        + "p { line-height: 1.5; color: #666; }"
        + ".info { list-style: none; padding: 0; }"
        + ".info li { margin-bottom: 10px; }"
        + ".button { display: block; background-color: #e74c3c; color: #fff; padding: 10px 20px; border: none; border-radius: 5px; text-align: center; cursor: pointer; margin-top: 20px; text-decoration: none; }"
        + ".button:hover { background-color: #c0392b; }"
        + "</style></head><body>"
        + "<div class='container'>"
        + "<h1>Your Lifesaving Appointment is Set!</h1>"
        + "<p>Dear <b>" + fullName + "</b>,</p>"
        + "<p>We're pleased to confirm your appointment for blood donation.</p>"
        + "<p>Here are the details:</p>"
        + "<ul class='info'>"
        + "<li><b>Date and Time:</b> " + appointmentDate + " at " + appointmentTime + "</li>"
        + "<li><b>Location:</b> " + OrganisationLocation + "</li>"
        + "<li><b>What to bring: Please bring your medical history records. </b></li>"
        + "</ul>"
        + "<p>If you need to reschedule your appointment, please contact us at " + OrganisationPhone
        + " or reply to this email.</p>"
        + "<p>See you soon!</p>"
        + "<p>Sincerely,<br/>" + OrganisationName + " Team. </p>"
        + "</div>"
        + "</body></html>";
    sendEmail(to, subject, htmlContent);
  }

  /**
   * Sends a donation confirmation email to a donor.
   * 
   * @param to        the recipient email address
   * @param donorName the name of the donor
   * @param quantity  the quantity of blood donated
   * @param bloodType the blood type donated
   */
  public void sendDonationConfirmation(String to, String donorName, Double quantity, BloodType bloodType) {
    String subject = "Blood Donation Confirmation";
    String htmlContent = "<html><head><style>"
        + "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f5f5f5; }"
        + ".container { background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }"
        + "h1 { text-align: center; font-size: 24px; margin-bottom: 10px; color: #333; }"
        + "p { line-height: 1.5; color: #666; }"
        + ".heart { color: #e74c3c; font-size: 2em; margin-right: 5px; }"
        + ".blood-details { font-weight: bold; }"
        + "</style></head><body>"
        + "<div class='container'>"
        + "<h1><span class='heart'>&hearts;</span> Thank You for Donating Blood!</h1>"
        + "<p>Dear <b>" + donorName + "</b>,</p>"
        + "<p>We are writing to express our sincere gratitude for your recent blood donation. Your generosity will make a real difference in the lives of others.</p>"
        + "<p>Here are the details of your donation:</p>"
        + "<ul class='blood-details'>"
        + "<li>Date: " + LocalDateTime.now() + "</li>"
        + "<li>Location: " + OrganisationLocation + "</li>"
        + "<li>Blood type: " + bloodType + "</li>"
        + "<li>Amount donated: " + quantity + " ml</li>"
        + "</ul>"
        + "<p>Your blood donation will be used to help patients in need of medical treatment. You have truly made a lifesaving contribution.</p>"
        + "<p>In the coming days, you will receive a notification regarding your blood test results. You can also access your donor profile and donation history on our website: "
        + OrganisationWebsite + "</p>"
        + "<p>Thank you again for your kindness and compassion. We encourage you to continue donating blood in the future.</p>"
        + "<p>Sincerely,<br/>" + OrganisationName + " Team</p>"
        + "</div>"
        + "</body></html>";
    sendEmail(to, subject, htmlContent);
  }
}
