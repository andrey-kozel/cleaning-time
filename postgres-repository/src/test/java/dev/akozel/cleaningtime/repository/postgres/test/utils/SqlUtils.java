package dev.akozel.cleaningtime.repository.postgres.test.utils;

import org.jooq.DSLContext;
import org.testcontainers.shaded.com.google.common.base.Charsets;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * SqlUtils. This class executes provided script using context
 * <p>
 * Date: 09/05/2020
 *
 * @author Andrey Kozel
 */
public final class SqlUtils {

    private SqlUtils() {
    }

    public static void execute(DSLContext context, String path) {
        try {
            InputStream scriptResource = SqlUtils.class.getClassLoader().getResourceAsStream(path);
            String scriptContent = IOUtils.toString(scriptResource, Charsets.UTF_8);
            context.execute(scriptContent);
        } catch (IOException e) {
            throw new RuntimeException("Failed to execute script: " + path, e);
        }
    }

}
