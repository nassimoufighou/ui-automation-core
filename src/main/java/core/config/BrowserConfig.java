package core.config;

import core.browser.SupportedBrowsers;

import java.util.HashMap;

public class BrowserConfig {

    private SupportedBrowsers browserType;
    private boolean startMaximized;
    private boolean isHeadless;
    private HashMap<SupportedBrowsers, String> remoteAddresses;

    public BrowserConfig(boolean startMaximized, boolean isHeadless) {
        this.startMaximized = startMaximized;
        this.isHeadless = isHeadless;
    }

    public BrowserConfig(HashMap<SupportedBrowsers, String> remoteAddresses, boolean startMaximized, boolean isHeadless) {
        this.remoteAddresses = remoteAddresses;
        this.startMaximized = startMaximized;
        this.isHeadless = isHeadless;
    }

    public String getRemoteAddress(SupportedBrowsers supportedBrowsers) {
        return remoteAddresses.get(supportedBrowsers);
    }

    public boolean startMaximized() {
        return startMaximized;
    }

    public boolean isHeadless() {
        return isHeadless;
    }

    public boolean isRemote(SupportedBrowsers browser) {
        String remoteAddress = null;
        if (remoteAddresses != null && !remoteAddresses.isEmpty()) remoteAddress = remoteAddresses.get(browser);
        return (remoteAddress != null && !remoteAddress.isEmpty());
    }

    public void setBrowserType(SupportedBrowsers browserType) {
        this.browserType = browserType;
    }

    public static class Builder {
        private HashMap<SupportedBrowsers, String> remoteAddresses = new HashMap<>();
        private boolean startMaximized = true;
        private boolean isHeadless = false;

        public Builder startMaximized(boolean startMaximized) {
            this.startMaximized = startMaximized;
            return this;
        }

        public Builder withRemoteAddress(SupportedBrowsers browsers, String remoteAddress) {
            this.remoteAddresses.put(browsers, remoteAddress);
            return this;
        }

        public Builder isHeadless(boolean isHeadless) {
            this.isHeadless = isHeadless;
            return this;
        }

        public BrowserConfig build() {
            if (remoteAddresses.isEmpty()) return new BrowserConfig(startMaximized, isHeadless);
            else return new BrowserConfig(remoteAddresses, startMaximized, isHeadless);
        }
    }
}