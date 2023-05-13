import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DataFilterPage {

    WebDriver driver;

    public DataFilterPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private final String url = "https://demo.seleniumeasy.com/data-list-filter-demo.html";

    public void navigate()
    {
        driver.navigate().to(url);
    }

    private final By nameTags = By.xpath("//div[@class=\"searchable-container\"]//div[contains(@class, 'info')]");

    public String[] getNames()
    {
        List<WebElement> names = driver.findElements(nameTags);

        String[] namesArray = new String[names.size()];
        int count = 0;

        for(WebElement name: names)
        {
            WebElement fullName= name.findElement(By.xpath("./h4"));
            namesArray[count] = fullName.getText().replace("Name: ", "");
            count++;
        }
        return namesArray;
    }
}
