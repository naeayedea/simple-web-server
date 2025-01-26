package com.naeayedea.simplewebserver.config;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.EncodedResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import java.util.List;

public class RestrictedPathResourceResolver extends EncodedResourceResolver {

    private final List<String> allowedFileExtensions;

    public RestrictedPathResourceResolver(List<String> allowedFileExtensions) {
        super();

        this.allowedFileExtensions = allowedFileExtensions;
    }

    @Override
    public Resource resolveResource(HttpServletRequest request, @Nonnull String requestPath, @Nonnull List<? extends Resource> locations, @Nonnull ResourceResolverChain chain) {
        Resource resolvedResource = super.resolveResource(request, requestPath, locations, chain);

        if (checkFileExtensionValid(resolvedResource)) {
            return resolvedResource;
        }

        return null;
    }

    private boolean checkFileExtensionValid(@Nullable Resource resource) {
        if (resource == null || resource.getFilename() == null) {
            return false;
        }

        for (String allowedFileExtension : allowedFileExtensions) {
            //if the file extension is allowed then allow the base implementation to do its thing
            if (resource.getFilename().endsWith(allowedFileExtension)) {
                return true;
            }
        }

        return false;
    }
}
