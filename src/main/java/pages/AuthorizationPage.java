package pages;

import org.openqa.selenium.WebDriver;

public class AuthorizationPage extends BasePage {

    private static final String AUTHORIZATION_INPUT = "authorization.page.edit";
    private static final String ENTER_BUTTON = "authorization.page.enter.button";
    private static final String MESSAGEBOX = "authorization.page.messagebox";

    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }

    public AuthorizationPage inputCode(String code) {
        actions.input(AUTHORIZATION_INPUT, code);
        return this;
    }

    public AuthorizationPage clickEnterButton() {
        actions.click(ENTER_BUTTON);
        return this;
    }

    public String receiveTextMessageBox() {
        return actions.getElement(MESSAGEBOX).getText();
    }
}
