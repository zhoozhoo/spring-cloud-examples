package ca.zhoozhoo.springcloud.rooms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "ca.zhoozhoo.springcloud.rooms")
public class R2dbcConfig {
}
