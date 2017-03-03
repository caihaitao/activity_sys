package com.cc;

import com.cc.config.StoragePropertiesConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

/**
 * Created by xn032607 on 2016/12/23.
 */
@SpringBootApplication
@EnableConfigurationProperties(StoragePropertiesConfig.class)
@ServletComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = applicationContext.getBeanDefinitionNames();

            Arrays.sort(beanNames);

            for(String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }
}
