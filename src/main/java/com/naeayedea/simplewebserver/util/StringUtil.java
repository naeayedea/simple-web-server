package com.naeayedea.simplewebserver.util;

import jakarta.annotation.Nullable;
import org.springframework.lang.NonNull;

public class StringUtil {

    private StringUtil() {}

    @NonNull
    public static String ensureTrailingSlashOnPath(@Nullable String path) {
        if (path == null) {
            return "/";
        }

        if (path.endsWith("/")) {
            return path;
        }

        return path + "/";
    }

    @NonNull
    public static String ensureLeadingSlashOnPath(@Nullable String path) {
        if (path == null) {
            return "/";
        }

        if (path.startsWith("/")) {
            return path;
        }

        return "/" + path;
    }
}
