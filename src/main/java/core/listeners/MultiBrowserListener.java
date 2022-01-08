package core.listeners;

import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import core.common.BaseTest;
import core.common.CurrentTest;

public class MultiBrowserListener implements IInvokedMethodListener {

    private static Logger logger = Logger.getLogger(MultiBrowserListener.class);
    private static ThreadLocal<IInvokedMethod> currentMethods = new ThreadLocal<>();
    private static ThreadLocal<ITestResult> currentResults = new ThreadLocal<>();
    private ITestNGMethod currentTestMethod;

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (!method.isConfigurationMethod()) {
            currentMethods.set(method);
            currentResults.set(testResult);
            currentTestMethod = method.getTestMethod();
            CurrentTest.setCurrentTestMethod(currentTestMethod);
            //BaseTest.setCurrentMethod(currentTestMethod);
            BaseTest.runTest();
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        currentMethods.remove();
        currentResults.remove();
    }
}