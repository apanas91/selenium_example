import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.IndexPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class IndexPageChromeRemoteTest {
    static {
        CommonMethods.loadProperties();
    }
    public WebDriver driver;
    public WebDriverWait wait;
    private IndexPage indexPage;
    private String login = System.getProperty("github.login");
    private String password = System.getProperty("github.password");
    private String nodeURL1 = System.getProperty("selenium.server.remote");

    @Before
    public void init(){
        DesiredCapabilities capability1 = new DesiredCapabilities();
        capability1.setBrowserName("chrome");
        capability1.setPlatform(Platform.WINDOWS);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL1), capability1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
