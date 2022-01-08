package core.strategies;

import org.openqa.selenium.By;

public class ByLocator {

    public static By attribute(String attributeName, String attributeValue) {
        return new ByAttribute(attributeName, attributeValue);
    }
}
