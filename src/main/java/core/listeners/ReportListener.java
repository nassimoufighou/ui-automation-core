package core.listeners;

import core.browser.SupportedBrowsers;
import core.common.BaseTest;
import core.config.SuiteConfig;
import core.reporting.Report;
import org.apache.log4j.Logger;
import org.testng.*;

public class ReportListener implements ISuiteListener, ITestListener {

    private static Logger logger = Logger.getLogger(ReportListener.class);
    private Report report;

    @Override
    public void onStart(ISuite suite) {
        logger.info("onSuiteStart");
        Report.init(SuiteConfig.getTestConfig().getReportPath());
        report = Report.getInstance();
        String url = String.format("<a href=\"%s\">%s</a>", SuiteConfig.getTestConfig().getBaseURL(), SuiteConfig.getTestConfig().getBaseURL());
        report.logSystemInfo("URL", url);
        for (SupportedBrowsers browser : SuiteConfig.getTestConfig().getBrowsers())  report.logSystemInfo("Browser", browser.name());
        if (SuiteConfig.getTestConfig().getBrowserConfig().isHeadless()){
            SupportedBrowsers browser = SuiteConfig.getTestConfig().getActiveBrowser();
            report.logSystemInfo("Remote webdriver address", SuiteConfig.getTestConfig().getBrowserConfig().getRemoteAddress(browser));
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("onSuiteFinish");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("onTestStart");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("onTestSuccess");
        createTest(result.getMethod().getRealClass().getSimpleName(),
                result.getMethod().getMethodName(), SuiteConfig.getTestConfig().getActiveBrowser().name());
        report.logTestSteps(BaseTest.getTestSteps());
        report.logPassed(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("onTestFailure");
        createTest(result.getMethod().getRealClass().getSimpleName(),
                result.getMethod().getMethodName(), SuiteConfig.getTestConfig().getActiveBrowser().name());
        report.logTestSteps(BaseTest.getTestSteps());
        report.logFailed(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("onTestSkipped");
        createTest(result.getMethod().getRealClass().getSimpleName(),
                result.getMethod().getMethodName(), SuiteConfig.getTestConfig().getActiveBrowser().name());
        report.logTestSteps(BaseTest.getTestSteps());
        report.logSkipped(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {
        logger.info("onTestStart");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("onTestFinish");
        report.buildReport();
    }

    private void createTest(String className, String testName, String browser){
        report.appendTest(className, testName, browser);
    }
}