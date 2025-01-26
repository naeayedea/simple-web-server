package com.naeayedea.simplewebserver;

import com.naeayedea.simplewebserver.properties.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SimpleWebServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(SimpleWebServerApplication.class);

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SimpleWebServerApplication.class, args);
    }

}
