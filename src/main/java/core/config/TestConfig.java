package core.config;

import core.browser.SupportedBrowsers;

import java.util.List;

public class TestConfig {

    private List<SupportedBrowsers> browsers;
    private SupportedBrowsers activeBrowser;
    private BrowserConfig browserConfig;
    private String baseURL;
    private String reportPath;

    protected TestConfig(List<SupportedBrowsers> browsers, BrowserConfig browserConfig, String baseURL, String reportPath) {
        this.browsers = browsers;
        this.browserConfig = browserConfig;
        this.baseURL = baseURL;
        this.reportPath = reportPath;
    }

    public List<SupportedBrowsers> getBrowsers() {
        return browsers;
    }

    public BrowserConfig getBrowserConfig() {
        return browserConfig;
    }

    public SupportedBrowsers getActiveBrowser() {
        return activeBrowser;
    }

    public String getBaseURL() { return baseURL; }

    public String getReportPath() { return  reportPath; }

    public void setActiveBrowser(int browserIndex) {
        this.activeBrowser = browsers.get(browserIndex);
    }

    public void setBrowserConfig(BrowserConfig browserConfig) {
        this.browserConfig = browserConfig;
    }

    public static class Builder {
        private List<SupportedBrowsers> browsers;
        private BrowserConfig browserConfig;
        private String baseURL;
        private String reportPath;


        public Builder browsers(List<SupportedBrowsers> browsers) {
            this.browsers = browsers;
            return this;
        }

        public Builder browserConfiguration(BrowserConfig browserConfig){
            this.browserConfig = browserConfig;
            return this;
        }

        public Builder withBaseURL(String baseURL) {
            this.baseURL = baseURL;
            return this;
        }

        public Builder saveReportIn(String reportPath) {
            this.reportPath = reportPath;
            return this;
        }

        public TestConfig build() {
            if (reportPath == null) reportPath = "./reports";
            return new TestConfig(browsers, browserConfig, baseURL, reportPath);
        }
    }
}