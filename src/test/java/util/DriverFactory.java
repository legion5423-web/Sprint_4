package ru.yandex.praktikum.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
        }
        return driver;
    }
}

