package pages;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final String LOGIN_BUTTON = "login.page.login.button";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        actions.click(LOGIN_BUTTON);
    }
}
