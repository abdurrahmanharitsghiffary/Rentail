package com.abdhage.rentail.common.config;

import com.abdhage.rentail.common.dto.EmailDetailsDTO;
import com.abdhage.rentail.common.service.MailService;
import com.abdhage.rentail.token.TokenRepository;
import com.abdhage.rentail.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TaskScheduling {

    private static final Logger log = LoggerFactory.getLogger(TaskScheduling.class);
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final MailService mailService;

    public TaskScheduling(TokenRepository tokenRepository, UserRepository userRepository, MailService mailService) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

//    @Bean
//    @Scheduled(cron = "0 0 1 * * *")
//    public void handleTokenExpiryDeletion() {
//        tokenRepository
//                .findAll().stream()
//                .filter(token -> token.getCreatedAt().getTime() + Objects.requireNonNullElse(token.getExpiresAt(), 3600L) > System.currentTimeMillis())
//                .forEach(tokenRepository::delete);
//    }
//
//    @Bean
//    @Async
//    @Scheduled(cron = "0 0 5 * * *")
//    public void handleNewsInformations() {
//        userRepository.findByInformLatestNewsTrue().forEach(user -> {
//            EmailDetailsDTO emailDto = EmailDetailsDTO.builder()
//                    .recipient(user.getEmail())
//                    .body("This is our latest news")
//                    .subject("News Title")
//                    .build();
//
//            mailService.sendMail(emailDto);
//        });
//    }
}
