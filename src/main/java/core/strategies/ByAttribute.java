package core.strategies;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.io.Serializable;
import java.util.List;

public class ByAttribute extends By implements Serializable {

    private String attributeName, attributeValue;

    public ByAttribute(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
        String cssSelector = String.format("[%s = %s]", attributeName, attributeValue);
        return context.findElements(By.cssSelector(cssSelector));
    }

    public WebElement findElement(SearchContext context) {
        String cssSelector = String.format("[%s = %s]", attributeName, attributeValue);
        return context.findElement(By.cssSelector(cssSelector));
    }
}
