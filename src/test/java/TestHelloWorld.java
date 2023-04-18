import app.getxray.xray.testng.annotations.XrayTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
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
        Allure.step("Adding arguments to chrome driver");
        WebDriverManager.chromedriver().setup();
        options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("lang=en");
        options.addArguments("--start-maximized");
        //options.addArguments("--headless=new");
    }

    @BeforeMethod
    void setup() {
        Allure.step("Starting Chrome Browser");
        driver = new ChromeDriver(options);
    }

    @AfterMethod
    void teardown() {

        Allure.step("Closing browser");
        driver.quit();
    }

    @Test (description="Scenario to check if hello word is shown on Google search")
    @Description("Test case1: verify when searching word: hello word, results are being shown")
    @XrayTest(key="TNP-22")
    void testToSearchHelloWorldInChrome() {
        Allure.step("Open Url");
        openUrl("https://www.google.com/");
        Allure.step("Entering word hello world");
        searchText("hello world");
        Allure.step("Checking results");
        isResultDisplayed();
    }

    @Test
    @Description("Test case2: verify when searching word: testing, results are being shown")
    void test2() {
        openUrl("https://www.google.com/");
        searchText("testing");
        isResultDisplayed();
    }
    @Step("Entering value on a search text box")
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
