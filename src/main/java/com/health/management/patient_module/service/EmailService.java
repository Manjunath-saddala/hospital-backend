package com.health.management.patient_module.service;

import com.health.management.patient_module.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // ✅ Confirmation email for appointments
    public void sendAppointmentConfirmationEmail(Appointment appointment) {
        String subject = "Appointment Confirmation";
        String body = String.format(
                """
                Dear Patient,

                Your appointment with Doctor ID: %d has been confirmed.
                Phone: %s
                Email: %s
                Reason: %s
                Date: %s

                Thank you!
                """,
                appointment.getDoctor().getId(),
                appointment.getPhone(),
                appointment.getEmail(),
                appointment.getReason(),
                appointment.getAppointmentDate()
        );

        try {
            sendEmail(appointment.getEmail(), subject, body);
            System.out.println("✅ Email sent to: " + appointment.getEmail());  // Log email status
        } catch (Exception e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());  // Log error
        }
    }

    // ✅ Send email method
    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            mailSender.send(message);
            System.out.println("✅ Email sent successfully to: " + to);

        } catch (MessagingException e) {
            System.err.println("❌ Error sending email: " + e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
