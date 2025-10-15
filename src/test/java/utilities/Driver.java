package utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
public class Driver {
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driverThread.get() == null) {
            // Default to chrome if no browser is set
            String browser = System.getProperty("browser");
            if (browser== null || browser.isEmpty()) {
                browser = ConfigReader.getProperty("browser");
            }
            initializeDriver(browser);
        }
        return driverThread.get();
    }


    // New method to accept browser parameter
    public static WebDriver getDriver(String browser) {
        if (driverThread.get() == null) {
            initializeDriver(browser);
        }
        return driverThread.get();
    }

    private static void initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "grid":
                ChromeOptions opt = new ChromeOptions();
                opt.setCapability("se:name","SÃ¼leyman");
                try {
                    driverThread.set(new RemoteWebDriver(
                            new URL("http://localhost:4444"),
                            opt
                    ));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edge":
                driverThread.set(new EdgeDriver());
                break;
            case "firefox":
                driverThread.set(new FirefoxDriver());
                break;
            case "headless":
                driverThread.set(new ChromeDriver(new ChromeOptions().addArguments("--headless")));
                break;
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-features=PasswordManagerEnabled");
                driverThread.set(new ChromeDriver(options));
        }

        driverThread.get().manage().window().maximize();
        driverThread.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void quitDriver() {
        if (driverThread.get() != null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}