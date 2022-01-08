package core.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import core.browser.SupportedBrowsers;
import core.config.BrowserConfig;
import core.config.TestConfig;
import core.factory.WebdriverFactory;

public class BasePage {

    private static WebDriver webDriver;
    private static Logger logger = Logger.getLogger(BasePage.class);

    public BasePage() {
    }

    public static void instantiateWebDriver(TestConfig testConfig, SupportedBrowsers browser, BrowserConfig browserConfig) {
        webDriver = WebdriverFactory.getWebDriver(browser, browserConfig);
        webDriver.get(testConfig.getBaseURL());
        logger.info("--------------------------------------------------------------------------------");
        logger.info("Opening browser with URL: " + testConfig.getBaseURL());
        logger.info("Instantiate " + testConfig.getActiveBrowser().name() + " webdriver");
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void quitWebdriver() {
        if (webDriver != null) {
            logger.info("--------------------------------------------------------------------------------");
            logger.info("Quitting webdriver...");
            webDriver.close();
            webDriver.quit();
        } else logger.info("No webdriver was instantiated. Nothing to quit. ");
    }

    public void init(BasePage page) {
        PageFactory.initElements(webDriver, page);
    }
}
