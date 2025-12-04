package ru.yandex.praktikum.tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;
    private String date;
    private String period;
    private boolean colorBlack;
    private boolean colorGrey;
    private String comment;

    public OrderTest(String name, String surname, String address, String metro, String phone,
                     String date, String period, boolean colorBlack, boolean colorGrey, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.colorBlack = colorBlack;
        this.colorGrey = colorGrey;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "ул. Ленина, д.1", "Черкизовская", "+79001234567",
                        "15.04.2025", "двое суток", true, false, "Оставьте у двери"},

                {"Анна", "Петрова", "пр-т Мира, д.50", "Тверская", "+79876543210",
                        "20.04.2025", "четверо суток", false, true, "Позвоните перед приездом"}
        });
    }

    @Before
    public void setUp() {
        driver = ru.yandex.praktikum.util.DriverFactory.createDriver("chrome");
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        mainPage.open();
        mainPage.waitForLoad();
    }

    @Test
    public void orderScooter_CheckSuccessMessage_Appears() {
        mainPage.clickTopOrderButton();
        orderPage.waitForLoad();
        orderPage.fillFirstPage(name, surname, address, metro, phone);
        orderPage.fillSecondPage(date, period, colorBlack, colorGrey, comment);
        orderPage.confirmOrder();
        String message = orderPage.getSuccessMessage();
        assertThat(message, is("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

