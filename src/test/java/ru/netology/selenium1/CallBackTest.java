package ru.netology.selenium1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallBackTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");

    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @AfterEach
    void tearsDown() {
        driver.quit();
        driver = null;

    }

    @Test
    public void shouldSendSuccessfully() {
        driver.get("http://localhost:9999");
        //  driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[view='default']")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector(".checkbox__text")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        //  String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        String text = driver.findElement(By.className("paragraph_theme_alfa-on-white")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());


    }

    @Test
    public void shouldNotSendIncorrectNameFormat() {
        driver.get("http://localhost:9999");
        // driver.findElement(By.cssSelector("[view='default']")).sendKeys("Ivan Ivanov");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Ivan Ivanov");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79999999999");
        // driver.findElement(By.cssSelector(".checkbox__text")).click();
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();
        //driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'] .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());


    }

    @Test
    public void shouldNotSendEmptyName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[view='default']")).sendKeys("");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector(".checkbox__text")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'] span.input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());


    }

    @Test
    public void shouldNotSendIncorrectTelephoneFormat() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[view='default']")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("9999999999");
        driver.findElement(By.cssSelector(".checkbox__text")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'] span.input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());


    }

    @Test
    public void shouldNotSendEmptyTelephone() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[view='default']")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("");
        driver.findElement(By.cssSelector(".checkbox__text")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'] .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());


    }

    @Test
    public void shouldNotSendCheckboxIsNotPressed() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79999999999");
        //driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__text")).getText();
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", text.trim());


    }


}
