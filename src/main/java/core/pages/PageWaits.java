package core.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import core.common.BasePage;

import java.time.Duration;

public class PageWaits {

    public static WebDriverWait wait(int timeoutInSeconds) {
        return new WebDriverWait(BasePage.getWebDriver(), timeoutInSeconds);
    }

    public static Wait<WebDriver> fluentWait(int pollingEvery, int maxTimeout) {
        Wait<WebDriver> wait = new FluentWait<>(BasePage.getWebDriver())
                .withTimeout(Duration.ofSeconds(maxTimeout))
                .pollingEvery(Duration.ofSeconds(pollingEvery));

        return wait;
    }

}
