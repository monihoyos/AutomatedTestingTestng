import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestHelloWorld {

    WebDriver driver;
    ChromeOptions options;

    @BeforeTest
    void setupAll() {
        WebDriverManager.chromedriver().setup();
        options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("lang=en");
        options.addArguments("--start-maximized");
    }

    @BeforeMethod
    void setup() {
        driver = new ChromeDriver(options);
    }

    @AfterMethod
    void teardown() {
        driver.quit();
    }

    @Test
    @Description("Test case: verify when searching word: hello word, results are being shown")
    void test1() {
        openUrl("https://www.google.com/");
        searchText("hello world");
        isResultDisplayed();
    }

    @Test
    @Description("Test case: verify when searching word: testing, results are being shown")
    void test2() {
        openUrl("https://www.google.com/");
        searchText("testing");
        isResultDisplayed();
    }
    @Step("Entering value on a search box")
    public void searchText(String texto){
        WebElement Search= getSearchBox();
        Search.sendKeys(texto);
        Search.sendKeys(Keys.ENTER);
    }
    @Step("Checking if text hello world is displayed")
    public String isResultDisplayed(){
        WebElement result= getResult();
        return result.getText();
    }

    public WebElement getSearchBox(){
        return driver.findElement(By.id("APjFqb"));
    }

    public  WebElement getResult(){
        return driver.findElement(By.xpath("//span[text()=\"Videos\"]"));
    }

    public void openUrl(String url){
        driver.get(url);
    }
}