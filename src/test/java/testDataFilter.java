import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testDataFilter {

    WebDriver driver;

    @BeforeEach
    public void init()
    {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public String[] ReadFile()
    {
        List<String> namesList = new ArrayList<>();

        try{
            File text = new File("names.txt");
            Scanner nameScanner = new Scanner(text);
            while (nameScanner.hasNextLine()) {
                String name = nameScanner.nextLine();
                namesList.add(name);
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
        /*String[] namesArray = namesList.toArray(new String[namesList.size()]);
        String[] namesArray = new String[namesList.size()];
        namesArray = namesList.toArray(namesArray);
        return namesArray;*/
        return namesList.toArray(new String[0]);
    }


    @Test
    public void testNameData()
    {
        DataFilterPage dataFilterPage = new DataFilterPage(driver);
        dataFilterPage.navigate();

        String[] actual = dataFilterPage.getNames();
        String[] expected = ReadFile();

        Assertions.assertArrayEquals(expected, actual);
    }





}
