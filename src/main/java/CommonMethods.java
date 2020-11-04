import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class CommonMethods {
    public static void loadProperties() {
        try (InputStream input = new FileInputStream("src/test/resources/application.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            System.setProperty("selenium.server.remote", prop.getProperty("selenium.server.remote"));
            System.setProperty("github.login", prop.getProperty("github.login"));
            System.setProperty("github.password", prop.getProperty("github.password"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static WebDriver getDriver(String browser, String runType) {
//        CommonFunctions.loadProperties();
        if (browser.equals("chrome")) {
            if (runType.equals("remote")) {
                return getChromeRemoteDriver();
            } else {
                return new ChromeDriver();
            }
        } else if (browser.equals("firefox")) {
            if (runType.equals("remote")) {
                return getFirefoxRemoteDriver();
            } else {
                return new FirefoxDriver();
            }

        } else return null;
    }

    public static WebDriver getChromeRemoteDriver() {
        WebDriver driver = null;
        String nodeURL1 = System.getProperty("selenium.server.remote");
        DesiredCapabilities capability1 = new DesiredCapabilities();
        capability1.setBrowserName("chrome");
        capability1.setPlatform(Platform.WINDOWS);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL1), capability1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    //todo Разобраться с запуском FF браузера на удаленной машине
    public static WebDriver getFirefoxRemoteDriver() {
        WebDriver driver = null;
        String nodeURL1 = System.getProperty("selenium.server.remote");
        DesiredCapabilities capability1 = new DesiredCapabilities();
        capability1.setBrowserName("firefox");
        capability1.setPlatform(Platform.WINDOWS);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL1), capability1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }


}
