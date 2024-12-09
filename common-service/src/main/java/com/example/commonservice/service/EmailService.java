package com.example.commonservice.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final Configuration freemarkerConfig;

    public void sendEmail(String to, String subject, String text, boolean isHtml, File attachment) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, isHtml);

            // Add attachment if provided
            if (attachment != null) {
                FileSystemResource file = new FileSystemResource(attachment);
                helper.addAttachment(file.getFilename(), file);
            }
            javaMailSender.send(message);
            log.info("Email sent successfully to {}", to);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}", to, e);
        }
    }

    public void sendEmailWithTemplate(String to, String subject, String template, File attachment, Map<String, Object> placeholders) {
        try {
            Template t = freemarkerConfig.getTemplate(template);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, placeholders);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);

            if (attachment != null) {
                FileSystemResource file = new FileSystemResource(attachment);
                helper.addAttachment(file.getFilename(), file);
            }
            javaMailSender.send(message);
            log.info("Email sent successfully to {}", to);

        } catch (MessagingException | IOException | TemplateException e) {
            log.error("Failed to send email to {}", to, e);
        }
    }
}