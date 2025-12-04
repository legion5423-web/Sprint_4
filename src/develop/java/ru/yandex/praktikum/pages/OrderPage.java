package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private WebDriver driver;



    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезём заказ']");
    private By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    private By metroOption = By.className("Order_Text__2broi");
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");



    private By whenBringDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriod = By.className("Dropdown-placeholder");
    private By rentalOption(String period) {
        return By.xpath("//div[text()='" + period + "']");
    }
    private By colorBlack = By.id("black");
    private By colorGrey = By.id("grey");
    private By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.xpath("//button[text()='Заказать']");



    private By confirmButton = By.xpath("//button[text()='Да']");
    private By orderSuccessMessage = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(nextButton));
    }

    public void fillFirstPage(String name, String surname, String address, String metro, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroField).sendKeys(metro);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(metroOption))
                .click();
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    public void fillSecondPage(String date, String period, boolean black, boolean grey, String comment) {
        driver.findElement(whenBringDate).sendKeys(date);
        driver.findElement(rentalPeriod).click();
        driver.findElement(rentalOption(period)).click();
        if (black) driver.findElement(colorBlack).click();
        if (grey) driver.findElement(colorGrey).click();
        driver.findElement(commentField).sendKeys(comment);
        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        driver.findElement(confirmButton).click();
    }

    public String getSuccessMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage));
        return driver.findElement(orderSuccessMessage).getText();
    }
}

