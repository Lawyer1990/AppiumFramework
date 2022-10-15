package elements;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtil;

import java.util.List;
import java.util.stream.Collectors;

import static configs.AssertMessages.MSG_ELEMENT_NOT_FOUND;
import static configs.Config.DRIVER_SLEEPS_MS;
import static configs.Config.IMPLICIT_WAIT;


@Log4j2
public class ElementActions {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        UiMapping.getInstance();
        this.wait = (WebDriverWait) new WebDriverWait(driver, IMPLICIT_WAIT, DRIVER_SLEEPS_MS)
                .ignoring(java.util.NoSuchElementException.class, StaleElementReferenceException.class);
    }

    /**
     * Click on an element
     */
    @Step("Click on an element [{locator.}]")
    public void click(String locator) {
        waitUntilAppear(locator);
        WebElement wel = waitToBeClickable(locator);
        wel.click();
    }

    @Step("Click on an element [{element}]")
    public void click(WebElement element) {
        waitUntilAppear(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    @Step("Insert [{text}] to input field [{locator}]")
    public void input(String locator, String text) {
        waitUntilAppear(locator);
        WebElement wel = waitToBeClickable(locator);
        wel.clear();
        wel.sendKeys(text);
    }

    @Step("Insert [{text}] to input field [{element}]")
    public void input(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    @Step("Insert [{text}] to input field [{locator}]")
    public void inputNoClear(String locator, String text) {
        WebElement wel = waitToBeClickable(locator);
        assert wel != null;
        wel.sendKeys(text);
    }

    @Step("Select from dropdown [{locator}] by value [{value}]")
    public void select(String locator, String value) {
        WebElement wel = driver.findElement(UiMapping.ui(locator));
        Select drp = new Select(wel);
        drp.selectByValue(value);
    }

    @Step("Select from dropdown [{element}] by value [{value}]")
    public void select(WebElement element, String value) {
        Select drp = new Select(element);
        drp.selectByValue(value);
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public List<WebElement> getElements(String locator) {
        return driver.findElements(UiMapping.ui(locator));
    }

    public List<String> getElementsText(String locator) {
        return getElements(locator).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void waitUntilAppear(String locator) {
        int count = 45;
        while (getElements(UiMapping.ui(locator)).isEmpty() && count-- > 0) {
            WaitUtil.setWaitMs(500);
        }
        if (!getElements(UiMapping.ui(locator)).isEmpty()) {
            while (!isDisplayed(locator) && count-- > 0) {
                WaitUtil.setWaitMs(500);
            }
        } else throw new org.openqa.selenium.NoSuchElementException(MSG_ELEMENT_NOT_FOUND.getValue());
    }

    public void waitUntilAppear(WebElement element) {
        int count = 45;
        while (element == null && count-- > 0) {
            WaitUtil.setWaitMs(500);
        }
        if (element != null) {
            while (!isDisplayed(element) && count-- > 0) {
                WaitUtil.setWaitMs(500);
            }
        } else throw new org.openqa.selenium.NoSuchElementException(MSG_ELEMENT_NOT_FOUND.getValue());
        while (!isDisplayed(element) && count-- > 0) {
            WaitUtil.setWaitMs(500);
        }
    }

    private WebElement waitToBeClickable(String locator) {
        int i = 1;
        while (i-- > 0) {
            try {
                WebElement wel = wait.until(ExpectedConditions.elementToBeClickable(UiMapping.ui(locator)));
                return wel;
            } catch (TimeoutException e) {
                log.error(MSG_ELEMENT_NOT_FOUND);
                driver.navigate().refresh();
                if (i == 0) throw e;
            }
        }
        return null;
    }

    public boolean isDisplayed(String locator) {
        return getElement(locator).isDisplayed();
    }

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    /**
     * Get web element
     *
     * @param locator
     * @return Web element
     */
    public WebElement getElement(String locator) {
        return driver.findElement(UiMapping.ui(locator));
    }
}
