import driver.DriverFactory;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.AuthorizationPage;
import pages.LoginPage;

@Log4j2
public class BaseTest {
    private EventFiringWebDriver driver;
    protected LoginPage loginPage;
    protected AuthorizationPage authorizationPage;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        driver = DriverFactory.initDriver();
        createData();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(ITestContext ctx) {
        driver.quit();
        log.info("FINISHING SUITE: " + ctx.getSuite().getName());
    }

    private void createData() {
        this.loginPage = new LoginPage(driver);
        this.authorizationPage = new AuthorizationPage(driver);
    }
}
