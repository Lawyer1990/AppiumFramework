package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static configs.Config.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;


@Log4j2
public class DriverFactory {
    private static EventFiringWebDriver eventDriver;
    private static final EventHandler eventHandler = new EventHandler();

    private static void createDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(DEVICE_NAME, DEVICE_NAME_APP);
            capabilities.setCapability(PLATFORM_VERSION, PLATFORM_VERSION_APP);
            capabilities.setCapability(PLATFORM_NAME, PLATFORM_NAME_APP);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
            capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
            capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
            capabilities.setCapability(APP, PATH_APPLICATION);
            URL url = new URL(URL_APP);
            AppiumDriver<MobileElement> driver = new AppiumDriver<>(url, capabilities);
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
            eventDriver = new EventFiringWebDriver(driver);
            eventDriver.register(eventHandler);
            SessionId sessionId = ((RemoteWebDriver) eventDriver.getWrappedDriver()).getSessionId();
            log.info("Android driver is instantiated");
            log.info("Session ID is: " + sessionId.toString());
        } catch (MalformedURLException e) {
            log.fatal(String.valueOf(e));
        }
    }

    public static EventFiringWebDriver initDriver() {
        if (eventDriver == null) {
            createDriver();
        }
        return eventDriver;
    }
}
