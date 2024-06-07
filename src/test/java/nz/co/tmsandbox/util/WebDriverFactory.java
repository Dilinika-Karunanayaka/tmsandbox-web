package nz.co.tmsandbox.util;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.time.Duration;

@Log4j2
public class WebDriverFactory {
    public static WebDriver createWebDriver() {
        String browser = ConfigManager.getConfig("browser");
        log.info("Browser=" + browser);
        WebDriver driver;
        switch (browser) {
            case "chrome":
                driver = getChromeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Unsupported Browser: " + browser);
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
        setProxy(options);
        return new ChromeDriver(options);
    }

    private static WebDriver getSafariDriver() {
        SafariOptions options = new SafariOptions();
        setProxy(options);
        return new SafariDriver(options);
    }

    private static WebDriver getFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        setProxy(options);
        return new FirefoxDriver(options);
    }

    private static void setProxy(AbstractDriverOptions options) {
        if (ConfigManager.getConfig("proxy.enabled").equals("true")) {
            String proxyServer = ConfigManager.getConfig("proxy.server");
            log.info("Setting proxy: " + proxyServer);
            Proxy proxy = new Proxy();
            proxy.setHttpProxy(proxyServer);
            proxy.setHttpProxy(proxyServer);
            if (options instanceof ChromeOptions) {
                ((ChromeOptions) options).setCapability("proxy", proxy);
            } else if (options instanceof FirefoxOptions) {
                ((FirefoxOptions) options).setCapability("proxy", proxy);
            } else if (options instanceof SafariOptions) {
                ((SafariOptions) options).setCapability("proxy", proxy);
            }
        }
    }
}