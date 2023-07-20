package com.manager.kafka.launch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

/**
 * @author <a href="mailto:is_fire_subscribe@hotmail.com">清汤白面</a>
 * @description
 * @datetime 2023-07-19 20:33:52
 */
@Slf4j
@Component
public final class DbLauncher implements ApplicationListener<ApplicationReadyEvent> {

    private static final String SPECIAL_CHAR = "!@#$%^&*()_+{}|:\"<>?~-=;,./abcdefghijklmnop"
            + "qrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Value("${spring.datasource.password}")
    private String password;
    private ConfigurableApplicationContext context = null;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (StringUtils.isNotBlank(password)) {
            return;
        }
        context = event.getApplicationContext();
        String randomStr = RandomStringUtils.random(RandomUtils.nextInt(16, 32), SPECIAL_CHAR);
        this.writePassword(randomStr);
    }

    private void writePassword(String password) {
        try {
            Resource resource = context.getResource("classpath:application.yaml");
            File yamlFile = resource.getFile();
            Yaml yaml = new Yaml();
            String yamlStr = FileUtils.readFileToString(yamlFile, StandardCharsets.UTF_8);
            Map<String, Object> yamlData = yaml.loadAs(yamlStr, Map.class);
            Map<String, Object> datasource;
            if ((datasource = (Map<String, Object>) yamlData.get("spring")) != null &&
                    (datasource = (Map<String, Object>) datasource.get("datasource")) != null) {
                String pass = (String) datasource.get("password");
                if (StringUtils.isNotBlank(pass)) {
                    return;
                }
                datasource.put("password", password);
            }
            log.info("spring.datasource.password is empty, generate random password: {}", password);

            // 写回文件
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(FlowStyle.BLOCK);
            Yaml newYaml = new Yaml(options);
            try(Writer writer = new FileWriter(yamlFile)) {
                newYaml.dump(yamlData, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
