package core.listeners;

import core.browser.Browser;
import core.browser.SupportedBrowsers;
import core.config.SuiteConfig;
import utils.BrowserReader;
import org.apache.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InvocationCountListener implements IAnnotationTransformer {

    private static Logger logger = Logger.getLogger(InvocationCountListener.class);
    private List<SupportedBrowsers> browsers = new ArrayList<>();

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        if (testMethod != null) {
            testClass = testMethod.getDeclaringClass();
            if (browsers.isEmpty()){
                logger.info("InvocationCountListener - Setting annotation for the first time");
                if (testClass.isAnnotationPresent(Browser.class)) {
                    Browser browserAnnotation = (Browser) testClass.getAnnotation(Browser.class);
                    browsers = BrowserReader.getTargetBrowserFromAnnotations(browserAnnotation);
                }
                else browsers = BrowserReader.getBrowsersFromEnvironment();
            }
            SuiteConfig.setBrowsersList(browsers);
            annotation.setInvocationCount(browsers.size());
        }
    }
}
