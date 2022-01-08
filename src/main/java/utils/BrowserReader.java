package utils;

import core.browser.Browser;
import core.browser.SupportedBrowsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrowserReader {

    public static List<SupportedBrowsers> getBrowsersFromEnvironment() {
        List<SupportedBrowsers> browsers = new ArrayList<>();
        //System.getenv("SELENIUM_BROWSERS")
        String SELENIUM_BROWSERS = "CHROME;FIREFOX;EDGE";
        String[] envVariables = System.getenv("SELENIUM_BROWSERS").split(";");
        for (String browser : envVariables) {
            browsers.add(SupportedBrowsers.valueOf(browser.toUpperCase()));
        }
        return browsers;
    }

    public static List<SupportedBrowsers> getTargetBrowserFromAnnotations(Browser browserAnnotation) {
        return Arrays.asList(browserAnnotation.browser());
    }
}
