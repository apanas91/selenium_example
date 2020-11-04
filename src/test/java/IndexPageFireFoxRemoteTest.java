import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.IndexPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class IndexPageFireFoxRemoteTest {
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
        capability1.setBrowserName("firefox");
        capability1.setPlatform(Platform.WINDOWS);
        capability1.setVersion("");

//        ProfilesIni profile = new ProfilesIni();
//        FirefoxProfile myprofile = profile.getProfile("default");
//        capability1.setCapability(FirefoxDriver.PROFILE, myprofile);

        try {
            driver = new RemoteWebDriver(new URL(nodeURL1), capability1);
//            driver = new RemoteWebDriver(new URL(nodeURL1), DesiredCapabilities.firefox());
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
