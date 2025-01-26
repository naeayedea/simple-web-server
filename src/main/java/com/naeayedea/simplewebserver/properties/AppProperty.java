package com.naeayedea.simplewebserver.properties;

import java.util.List;

import static com.naeayedea.simplewebserver.util.StringUtil.ensureLeadingSlashOnPath;
import static com.naeayedea.simplewebserver.util.StringUtil.ensureTrailingSlashOnPath;

public class AppProperty {

    private String contextPath;

    private String resourcePath;

    private List<String> allowedFileExtensions;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = ensureTrailingSlashOnPath(ensureLeadingSlashOnPath(contextPath));
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = ensureTrailingSlashOnPath(resourcePath);
    }

    public List<String> getAllowedFileExtensions() {
        return allowedFileExtensions;
    }

    public void setAllowedFileExtensions(List<String> allowedFileExtensions) {
        this.allowedFileExtensions = allowedFileExtensions
            .stream()
            .map(extension -> extension.replaceAll("[.*]", ""))
            .toList();
    }

    @Override
    public String toString() {
        return "AppProperty{" +
            "contextPath='" + contextPath + '\'' +
            ", resourcePath='" + resourcePath + '\'' +
            ", allowedFileExtensions=" + allowedFileExtensions +
            '}';
    }
}
