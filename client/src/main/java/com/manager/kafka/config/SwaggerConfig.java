package com.manager.kafka.config;

import com.manager.kafka.consts.ApiConst;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description:  OpenApi Config </p>
 * <p>Company: 古尘工作室</p>
 * <p>Date：2023-07-20 17:23:05 </p>
 *
 * @author <a href="mailto:is_fire_subscribe@hotmail.com">古尘</a>
 * @version V1.0
 */
@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Kafka Manger API")
                        .version(ApiConst.API_VERSION)
                        .description("Kafka Manger API")
                        .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0")
                                .url("https://doc.kafka_manager.com"))
                );
    }

}
