package core.common;

import org.testng.ITestNGMethod;

public class CurrentTest {
    private static ITestNGMethod currentTestMethod;
    private static int currentCount;

    public static ITestNGMethod getCurrentTestMethod() {
        return currentTestMethod;
    }

    public static int getCurrentCount() {
        return currentCount;
    }

    public static void setCurrentTestMethod(ITestNGMethod currentTestMethod) {
        CurrentTest.currentTestMethod = currentTestMethod;
        CurrentTest.currentCount = currentTestMethod.getCurrentInvocationCount();
    }
}
