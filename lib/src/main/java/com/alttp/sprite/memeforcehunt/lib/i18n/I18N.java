package com.alttp.sprite.memeforcehunt.lib.i18n;

import java.io.InputStream;
import java.util.PropertyResourceBundle;

public class I18N {

    private static final String RESOURCE_NAME = "/alttp.lib.properties";

    public static String getString(final String key) {
        return getString(I18N.class.getCanonicalName(), RESOURCE_NAME, key);
    }

    /**
     * Returns the message string with the specified key from the
     * "properties" file in the package containing the class with
     * the specified name.
     */
    protected static String getString(final String className, final String resourceName, final String key) {
        try {
            final InputStream stream = Class.forName(className).getResourceAsStream(resourceName);
            final PropertyResourceBundle bundle = new PropertyResourceBundle(stream);

            return (String) bundle.handleGetObject(key);
        } catch (final Throwable i18nEx) {
            throw new RuntimeException(i18nEx); // Chain the exception.
        }
    }
}
