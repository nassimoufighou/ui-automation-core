package core.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import core.browser.SupportedBrowsers;
import core.config.BrowserConfig;

import java.net.MalformedURLException;
import java.net.URL;

public class WebdriverFactory {
    private static Logger logger = Logger.getLogger(WebdriverFactory.class);

    public static WebDriver getWebDriver(SupportedBrowsers browserType, BrowserConfig browserConfig) {
        WebDriver webDriver = null;
        switch (browserType){
            case CHROME:
                if (browserConfig.isRemote(SupportedBrowsers.CHROME)){
                    String remoteAddress = browserConfig.getRemoteAddress(SupportedBrowsers.CHROME);
                    Capabilities capabilities = getChromeOptions(browserConfig);
                    webDriver = remoteWebDriver(remoteAddress, capabilities);
                    logger.info("Instantiating Chrome RemoteWebdriver with address: " + remoteAddress);
                }
                else {
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver(getChromeOptions(browserConfig));
                }
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                if (browserConfig.isRemote(SupportedBrowsers.FIREFOX)){
                    String remoteAddress = browserConfig.getRemoteAddress(SupportedBrowsers.FIREFOX);
                    Capabilities capabilities = getFirefoxOptions(browserConfig);
                    webDriver = remoteWebDriver(remoteAddress, capabilities);
                    logger.info("Instantiating Firefox RemoteWebdriver with address: " + remoteAddress);
                }
                else webDriver = new FirefoxDriver(getFirefoxOptions(browserConfig));
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
        }
        if (browserConfig.startMaximized()) webDriver.manage().window().maximize();
        return webDriver;
    }

    private static WebDriver remoteWebDriver(String remoteAddress, Capabilities capabilities) {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = null;
        try {
            webDriver = new RemoteWebDriver(new URL(remoteAddress), capabilities);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return webDriver;
    }

    private static ChromeOptions getChromeOptions(BrowserConfig browserConfig) {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (browserConfig.isHeadless()) {
            chromeOptions.addArguments("--headless");
            logger.info("Instantiating local headless ChromeWebdriver");
        }
        return chromeOptions;
    }

    private static FirefoxOptions getFirefoxOptions(BrowserConfig browserConfig) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (browserConfig.isHeadless()){
            firefoxOptions.setHeadless(true);
            logger.info("Instantiating local headless FirefoxWebdriver");
        }
        return firefoxOptions;
    }
}