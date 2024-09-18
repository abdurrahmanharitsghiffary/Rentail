package com.abdhage.rentail.common.service.impl;

import com.abdhage.rentail.common.constants.MailProperties;
import com.abdhage.rentail.common.dto.EmailDetailsDTO;
import com.abdhage.rentail.common.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);
    private final MailProperties mailProperties;

    public MailServiceImpl(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Override
    public void sendMail(EmailDetailsDTO dto) {

        Properties properties = getProperties();

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress("Rentail<" + mailProperties.getUsername() + ">"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(dto.getRecipient()));
            message.setText(dto.getBody());
            message.setSubject(dto.getSubject());
            log.info(dto.toString());

            Transport.send(message);
        } catch (Exception e) {
            log.info("Error sending Email");
            log.error(Arrays.toString(e.getStackTrace()));
            log.error(e.getMessage());
        }

    }

    private Properties getProperties() {
        log.info(mailProperties + " Mail properties");
        Properties properties = new Properties();

        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", mailProperties.getHost());
        properties.put("mail.smtp.host", mailProperties.getHost());
        properties.put("mail.smtp.port", mailProperties.getPort());
        log.info(properties + " Properties");
        return properties;
    }
}
