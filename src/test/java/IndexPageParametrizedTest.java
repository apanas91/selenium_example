import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.IndexPage;

import java.util.concurrent.TimeUnit;

@RunWith(DataProviderRunner.class)
public class IndexPageParametrizedTest {

    static {
        CommonMethods.loadProperties();
    }

    public WebDriver driver;
    public WebDriverWait wait;
    private IndexPage indexPage;
    private String login = System.getProperty("github.login");
    private String password = System.getProperty("github.password");
    private String nodeURL1 = System.getProperty("selenium.server.remote");


    @DataProvider
    public static Object[][] browsers() {
        return new Object[][]{
                {"chrome", "remote"},
                {"chrome", "local"},
                {"firefox", "remote"},
                {"firefox", "local"}
        };
    }

    @Test
    @UseDataProvider("browsers")
    public void githubTest(String br, String type) {
        driver = CommonMethods.getDriver(br, type);
        wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        indexPage = PageFactory.initElements(driver, IndexPage.class);

        driver.get("https://github.com/login");
        indexPage.typeLogin(login).typePassword(password).clickSubmit();
    }

    @After
    public void close() {
        driver.quit();
    }


}
