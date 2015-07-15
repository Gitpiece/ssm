package nut.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.*;


/**
 * An internationalization / localization helper class which reduces
 * the bother of handling ResourceBundles and takes care of the
 * common cases of message formating which otherwise require the
 * creation of Object arrays and such.
 * <p/>
 * <p>The StringManager operates on a package basis. One StringManager
 * per package can be created and accessed via the getManager method
 * call.
 * <p/>
 * <p>The StringManager will look for a ResourceBundle named by
 * the package name given plus the suffix of "LocalStrings". In
 * practice, this means that the localized information will be contained
 * in a LocalStrings.properties file located in the package
 * directory of the classpath.
 * <p/>
 * <p>Please see the documentation for java.utils.ResourceBundle for
 * more information.
 * <p/>
 * <p>Code from Apache Tomcat 6.0.
 *
 * @author James Duncan Davidson [duncan@eng.sun.com]
 * @author James Todd [gonzo@eng.sun.com]
 * @modify WangHuanyu 2013-11-11
 * @since JDK1.5
 */

public class StringManager {

    private final static Log LOGGER = LogFactory.getLog(StringManager.class);
    /**
     * The ResourceBundle for this StringManager.
     */

    private ResourceBundle bundle;

    /**
     * packageName for ResourceBundle
     */
    private String packageName = "";
    /**
     * bundleFileName for ResourceBundle
     */
    private String bundleFileName = "";

    /**
     * bundle file name
     */
    private static final String BUNDLE_FILE_NAME = "LocalStrings";

    /**
     * Creates a new StringManager for a given package. This is a
     * private method and all access to it is arbitrated by the
     * static getManager method call so that only one StringManager
     * per package will be created.
     *
     * @param packageName Name of package to create StringManager for.
     */

    private StringManager(String packageName, String bundleFileName) {
        this.packageName = packageName;
        this.bundleFileName = bundleFileName;
        String bundleName = packageName + "." + bundleFileName;
        try {
            bundle = ResourceBundle.getBundle(bundleName);
            return;
        } catch (MissingResourceException ex) {
            LOGGER.error(ex.getMessage(), ex);
            // Try from the current loader ( that's the case for trusted apps )
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            if (cl != null) {
                try {
                    bundle = ResourceBundle.getBundle(bundleName, Locale.getDefault(), cl);
                    return;
                } catch (MissingResourceException ex2) {
                    LOGGER.error(ex2.getMessage(), ex2);
                }
            }
            if (cl == null) {
                cl = this.getClass().getClassLoader();
            }

            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Can't find resource " + bundleName + " " + cl);
            }
            if (cl instanceof URLClassLoader) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(((URLClassLoader) cl).getURLs());
                }
            }
        }
    }

    /**
     * Get a string from the underlying resource bundle.
     *
     * @param key The resource name
     */
    public String getString(String key) {
        return MessageFormat.format(getStringInternal(key), (Object[]) null);
    }


    protected String getStringInternal(String key) {
        if (key == null) {
            String msg = "key is null";
            throw new NullPointerException(msg);
        }

        String str = null;

        if (bundle == null) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Bundle for \'" + this.packageName + "." + this.bundleFileName + "\' is null,maybe not init or destroyed, key is \'" + key + "\'.");
            }
            return key;
        }
        try {
            str = bundle.getString(key);
        } catch (MissingResourceException mre) {
            str = "Cannot find message associated with key \'" + key + "\'";
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn(str, mre);
            }
        }

        return str;
    }

    /**
     * Get a string from the underlying resource bundle and format
     * it with the given set of arguments.
     *
     * @param key  The resource name
     * @param args Formatting directives
     */

    public String getString(String key, Object[] args) {
        String iString = null;
        String value = getStringInternal(key);

        // this check for the runtime exception is some pre 1.1.6
        // VM's don't do an automatic toString() on the passed in
        // objects and barf out

        iString = format(value, args);
        return iString;
    }


    /**
     * Get a string from the underlying resource bundle and format it
     * with the given object argument. This argument can of course be
     * a String object.
     *
     * @param key The resource name
     * @param arg Formatting directive
     */

    public String getString(String key, Object arg) {
        Object[] args = new Object[]{arg};
        return getString(key, args);
    }

    /**
     * Get a string from the underlying resource bundle and format it
     * with the given object arguments. These arguments can of course
     * be String objects.
     *
     * @param key  The resource name
     * @param arg1 Formatting directive
     * @param arg2 Formatting directive
     */

    public String getString(String key, Object arg1, Object arg2) {
        Object[] args = new Object[]{arg1, arg2};
        return getString(key, args);
    }

    /**
     * Get a string from the underlying resource bundle and format it
     * with the given object arguments. These arguments can of course
     * be String objects.
     *
     * @param key  The resource name
     * @param arg1 Formatting directive
     * @param arg2 Formatting directive
     * @param arg3 Formatting directive
     */

    public String getString(String key, Object arg1, Object arg2,
                            Object arg3) {
        Object[] args = new Object[]{arg1, arg2, arg3};
        return getString(key, args);
    }

    /**
     * Get a string from the underlying resource bundle and format it
     * with the given object arguments. These arguments can of course
     * be String objects.
     *
     * @param key  The resource name
     * @param arg1 Formatting directive
     * @param arg2 Formatting directive
     * @param arg3 Formatting directive
     * @param arg4 Formatting directive
     */

    public String getString(String key, Object arg1, Object arg2,
                            Object arg3, Object arg4) {
        Object[] args = new Object[]{arg1, arg2, arg3, arg4};
        return getString(key, args);
    }

    public String getPackageName() {
        return packageName;
    }

    public String getBundleFileName() {
        return bundleFileName;
    }

    /**
     * destroy current object
     */
    private void destroy() {
        this.bundle = null;
    }

    // --------------------------------------------------------------
    // STATIC SUPPORT METHODS
    // --------------------------------------------------------------

    private static Map<String, StringManager> managers = new HashMap<String, StringManager>();

    /**
     * Get the StringManager for a particular package. If a manager for
     * a package already exists, it will be reused, else a new
     * StringManager will be created and returned.
     *
     * @param packageName The package name
     */

    public synchronized static StringManager getManager(String packageName) {

        return getManager(packageName, BUNDLE_FILE_NAME);
    }

    public synchronized static StringManager getManager(String packageName, String bundleFileName) {
        StringManager mgr = managers.get(packageName + "." + bundleFileName);

        if (mgr == null) {
            mgr = new StringManager(packageName, bundleFileName);
            managers.put(packageName + "." + bundleFileName, mgr);
        }
        return mgr;
    }

    /**
     * Remove the StringManager for a particular package.
     *
     * @param packageName The package name
     * @return removed StringManager
     */
    public synchronized static void removeManager(String packageName) {
        removeManager(packageName, BUNDLE_FILE_NAME);
    }

    /**
     * Remove the StringManager for a particular package.
     *
     * @param packageName The package name
     * @return removed StringManager
     */
    public synchronized static void removeManager(String packageName, String bundleFileName) {
        StringManager sm = managers.remove(packageName + "." + bundleFileName);
        if (sm != null) {
            sm.destroy();
        }
    }

    /**
     * 格式化
     *
     * @param value 带有占位符的字符串
     * @param args  占位符参数
     * @return 格式化后的字符串
     */
    public static String format(String value, Object[] args) {
        String iString;
        try {
            // ensure the arguments are not null so pre 1.2 VM's don't barf
            Object nonNullArgs[] = args;
            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) {
                    if (nonNullArgs == args) {
                        nonNullArgs = args.clone();
                    }
                    nonNullArgs[i] = "null";
                }
            }

            iString = MessageFormat.format(value, nonNullArgs);
        } catch (IllegalArgumentException e) {
            //LOGGER.error(e.getMessage(),e);
            StringBuilder buf = new StringBuilder();
            buf.append(value);
            for (int i = 0; i < args.length; i++) {
                buf.append(" arg[" + i + "]=" + args[i]);
            }
            iString = buf.toString();
            //LOGGER.error(iString,e);
        }
        return iString;
    }
}
