package com.naeayedea.simplewebserver.controller;

import com.naeayedea.simplewebserver.properties.AppProperties;
import com.naeayedea.simplewebserver.properties.AppProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    private final Map<String, AppProperty> appPropertyMap;

    public AppController(AppProperties appProperties) {
        this.appPropertyMap = appProperties.getApps();
    }

    @RequestMapping(value = "/{context-path}/", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getIndex(@PathVariable("context-path") String contextPath) throws IOException {
        AppProperty maybeProperty = appPropertyMap.get(contextPath);

        if (maybeProperty == null || !maybeProperty.getAllowedFileExtensions().contains("html")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No app found under that path.");
        }

        File index = new File(maybeProperty.getResourcePath() + "/index.html");

        if (!index.exists()) {
            logger.error("index file not found");
        } else {
            logger.debug("Resolved file {}", index.getCanonicalFile().getName());
        }

        return new FileSystemResource(index);
    }

}
