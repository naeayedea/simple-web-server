package com.naeayedea.simplewebserver.config;

import com.naeayedea.simplewebserver.properties.AppProperties;
import com.naeayedea.simplewebserver.properties.AppProperty;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.*;

import static com.naeayedea.simplewebserver.util.StringUtil.ensureLeadingSlashOnPath;
import static com.naeayedea.simplewebserver.util.StringUtil.ensureTrailingSlashOnPath;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);

    private final AppProperties appProperties;

    public MvcConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Override
    public void addResourceHandlers(@Nonnull ResourceHandlerRegistry registry) {
        for (AppProperty app : appProperties.getApps().values()) {
            //we want the context path to be of format /<context path>/
            String contextPath = ensureTrailingSlashOnPath(ensureLeadingSlashOnPath(app.getContextPath()));

            //we want the resource path to be of format <resource path>/, leading slash is up to implementation.
            String resourcePath = ensureTrailingSlashOnPath(app.getResourcePath());

            logger.info("Resolving resources for {} under {} with the following file extensions: {}", contextPath, resourcePath, app.getAllowedFileExtensions());

            registry
                .addResourceHandler(contextPath+"**")
                .addResourceLocations("file:"+resourcePath+"/")
                .resourceChain(true)
                .addResolver(new RestrictedPathResourceResolver(app.getAllowedFileExtensions()));
        }
    }
}
