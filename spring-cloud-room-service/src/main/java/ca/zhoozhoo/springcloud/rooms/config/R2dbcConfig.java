package ca.zhoozhoo.springcloud.rooms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories(basePackages = "ca.zhoozhoo.springcloud.rooms")
public class R2dbcConfig {

    // @Bean(value = "r2dbcEntityTemplate")
    // public R2dbcEntityTemplate getR2dbcEntityTemplate(ConnectionFactory connectionFactory) {
    //     return new R2dbcEntityTemplate(connectionFactory);
    // }
}
