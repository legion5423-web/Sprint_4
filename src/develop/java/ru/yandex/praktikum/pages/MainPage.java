package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;




    private By header = By.className("Home_Header__iJKdX");


    private By topOrderButton = By.className("Button_Button__ra12g");


    private By bottomOrderButton = By.xpath("(//button[contains(text(),'Заказать')])[2]");


    private By scooterLogo = By.className("Header_LogoScooter__3lsAR");


    private By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    private By cookieButton = By.id("rcc-confirm-button");

    private By question(int num) {
        return By.id("accordion__heading-" + (num - 1));
    }

    private boolean isElementVisible(By locator) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator))
                    .isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    private By answer(int num) {
        return By.id("accordion__panel-" + (num - 1));
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    public void acceptCookies() {
        try {
            WebElement button = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(cookieButton));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

            Thread.sleep(500);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        } catch (org.openqa.selenium.TimeoutException e) {
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton() {
        driver.findElement(bottomOrderButton).click();
    }

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public void clickQuestion(int num) {
        driver.findElement(question(num)).click();
    }

    public String getAnswerText(int num) {
        return driver.findElement(By.id("accordion__panel-" + (num - 1))).getText();
    }

}
