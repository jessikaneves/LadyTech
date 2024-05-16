package com.elastech.LadyTech.configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class WebConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        logger.info("HiddenHttpMethodFilter bean created");
        return new HiddenHttpMethodFilter();
    }
}