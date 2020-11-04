import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.IndexPage;

import java.util.concurrent.TimeUnit;

public class IndexPageFireFoxTest {
    static {
        CommonMethods.loadProperties();
    }

    public WebDriver driver;
    public WebDriverWait wait;
    private IndexPage indexPage;

    private String login = System.getProperty("github.login");
    private String password = System.getProperty("github.password");

    @Before
    public void init(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        indexPage = PageFactory.initElements(driver, IndexPage.class);
    }

    @Test
    public void githubTest(){
        driver.get("https://github.com/login");
        indexPage.typeLogin(login).typePassword(password).clickSubmit();
    }

    @After
    public void close(){
        driver.quit();
    }
}
