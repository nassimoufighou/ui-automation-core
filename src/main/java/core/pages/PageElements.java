package core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import core.common.BasePage;

import java.util.List;

public class PageElements {

    public static WebElement findElement(By locator) {
        return BasePage.getWebDriver().findElement(locator);
    }

    public static List<WebElement> findElements(By locator){
        return BasePage.getWebDriver().findElements(locator);
    }
}
