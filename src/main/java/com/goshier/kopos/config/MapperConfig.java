package com.goshier.kopos.config;

import com.goshier.kopos.mapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public KosMapper kosMapper() {
        return new KosMapperImpl();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }

    @Bean
    public TokenMapper tokenMapper() {
        return new TokenMapperImpl();
    }

    @Bean
    public KosCollaboratorMapper kosCollaboratorMapper() {
        return new KosCollaboratorMapperImpl();
    }
}
