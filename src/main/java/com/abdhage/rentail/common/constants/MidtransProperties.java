package com.abdhage.rentail.common.constants;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = "payment.midtrans")
public class MidtransProperties {
    private String serverKey;
    private String clientKey;
    private Boolean isProduction;
}
