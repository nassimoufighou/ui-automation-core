package core.config;

import core.browser.SupportedBrowsers;

import java.util.List;

public class SuiteConfig {

    private static List<SupportedBrowsers> browserTypes;
    private static BrowserConfig browserConfig;
    private static TestConfig testConfig;

    public SuiteConfig() {}

    public static BrowserConfig getBrowserConfig() {
        return browserConfig;
    }

    public static List<SupportedBrowsers> getBrowserList() {
        return browserTypes;
    }

    public static TestConfig getTestConfig() {
        return testConfig;
    }

    public static SupportedBrowsers getBrowserForIteration(int iteration) {
        return browserTypes.get(iteration);
    }

    public static void setBrowserConfig(BrowserConfig browserConfig) {
        SuiteConfig.browserConfig = browserConfig;
    }

    public static void setTestConfig(TestConfig testConfig) {
        SuiteConfig.testConfig = testConfig;
    }

    public static void setBrowsersList(List<SupportedBrowsers> browsersList){
        browserTypes = browsersList;
    }
}
