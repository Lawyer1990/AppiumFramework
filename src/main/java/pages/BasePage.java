package pages;

import elements.ElementActions;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected ElementActions actions;

    public BasePage(WebDriver driver) {
        this.actions = new ElementActions(driver);
    }
}
