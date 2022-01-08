package core.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import core.common.BasePage;

public class PageActions {

    public static void clickOnElement(WebElement element) {
        try {
            element.click();
        }
        catch (ElementClickInterceptedException e){
            JavascriptExecutor executor = (JavascriptExecutor) BasePage.getWebDriver();
            executor.executeScript("arguments[0].click();", element);
        }
    }

    public static void moveToElement(WebElement webElement) {
        Actions actions = new Actions(BasePage.getWebDriver());
        actions.moveToElement(webElement);
        actions.build().perform();
    }
}
