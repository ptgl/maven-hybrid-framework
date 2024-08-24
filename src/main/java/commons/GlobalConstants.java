package commons;

public class GlobalConstants {
    // Server url
    // Database
    // Timeout: short, long
    // Username, pass
    // Third party
    // Download, upload
    // Relavtive project path

    public static final String DEV_USER_URL = "https://demo.nopcommerce.com/";
    public static final String DEV_ADMIN_URL = "https://admin-demo.nopcommerce.com/";
    public static final String DEV_ADMIN_USERNAME = "admin@yourstore.com";
    public static final String DEV_ADMIN_PASS = "admin";
    public static final long LONG_TIMEOUT = 30;
    public static final long SHORT_TIMEOUT = 5;
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir");
    public static final String UPLOAD_PATH = RELATIVE_PROJECT_PATH + "/uploadFiles/";
    public static final String JAVA_VERSION = System.getProperty("java.version");
    ;


}
