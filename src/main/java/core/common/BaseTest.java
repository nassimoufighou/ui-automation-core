package core.common;

import org.apache.log4j.Logger;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import core.config.SuiteConfig;
import core.reporting.Report;
import core.reporting.Step;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    private static Logger logger = Logger.getLogger(BaseTest.class);
    private static List<Step> steps = new ArrayList<>();

    public BaseTest() {
    }

    public static List<Step> getTestSteps() {
        return steps;
    }

    public static void runTest() {
        int currentInvocationCount = CurrentTest.getCurrentCount();
        ITestNGMethod method = CurrentTest.getCurrentTestMethod();
        logger.info("Current RUN#" + currentInvocationCount + " for method " + method.getMethodName() + " with " + SuiteConfig.getBrowserForIteration(currentInvocationCount));
        SuiteConfig.getTestConfig().setActiveBrowser(currentInvocationCount);
        BasePage.instantiateWebDriver(SuiteConfig.getTestConfig(),
                SuiteConfig.getBrowserForIteration(currentInvocationCount),
                SuiteConfig.getBrowserConfig());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        Report.resetStepPool();
        BasePage.quitWebdriver();
    }
}