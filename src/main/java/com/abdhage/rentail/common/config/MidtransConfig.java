package com.abdhage.rentail.common.config;

import com.abdhage.rentail.common.constants.MidtransProperties;
import com.midtrans.Config;
import com.midtrans.ConfigFactory;
import com.midtrans.service.MidtransSnapApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("payment.midtrans")
public class MidtransConfig {
    private final MidtransProperties midtransProperties;

    public MidtransConfig(MidtransProperties midtransProperties) {
        this.midtransProperties = midtransProperties;
    }

    private Config getConfig() {
        return Config.builder()
                .setClientKey(midtransProperties.getClientKey())
                .setServerKey(midtransProperties.getServerKey())
                .setIsProduction(midtransProperties.getIsProduction())
                .build();
    }

    @Bean
    MidtransSnapApi midtransSnapApi() {
        return new ConfigFactory(getConfig()).getSnapApi();
    }
}
