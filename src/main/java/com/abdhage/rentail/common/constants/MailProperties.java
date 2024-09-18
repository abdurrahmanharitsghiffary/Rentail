package com.abdhage.rentail.common.constants;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = "mail.smtp")
public class MailProperties {
    private String host;
    private Long port;
    private String username;
    private String password;
    private Boolean auth;
    private StartTlls starttls = new StartTlls(true);
    private Boolean debug;

    public static record StartTlls(Boolean enable) {
    }
}
