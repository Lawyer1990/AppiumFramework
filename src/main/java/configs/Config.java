package configs;

public class Config {

    public static final String PATH_APPLICATION = System.getProperty("user.dir") + "/src/main/resources/payback.apk";
    public static final String DEVICE_NAME_APP = "emulator-5554";
    public static final String PLATFORM_NAME_APP = "Android";
    public static final String PLATFORM_VERSION_APP = "7.0";
    public static final String APP_PACKAGE = "de.payback.client.android";
    public static final String APP_ACTIVITY = "de.payback.app.ui.main.MainActivity";
    public static final String URL_APP = "http://0.0.0.0:4723/wd/hub";

    public static final int IMPLICIT_WAIT = 10;
    public static final int LOAD_PAGE_WAIT = 30;
    public static final int DRIVER_SLEEPS_MS = 50;

    public static final String UI_MAPPING_PATH = System.getProperty("user.dir") + "/src/main/resources/uiLocators.properties";
}