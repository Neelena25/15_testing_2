
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class MtsOnlineRechargeTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/lkhatmullina/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.mts.by");
    }

    @Test
    public void testBlockTitle() {
        WebElement titleElement = driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение без комиссии')]"));
        Assertions.assertNotNull(titleElement, "Заголовок блока не найден!");
    }

    @Test
    public void testPaymentSystemLogos() {
        List<WebElement> logos = driver.findElements(By.cssSelector(".payment-systems img"));
        Assertions.assertTrue(logos.size() > 0, "Логотипы платёжных систем не найдены!");
    }

    @Test
    public void testMoreInfoLink() {
        WebElement moreInfoLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        Assertions.assertTrue(moreInfoLink.isDisplayed(), "Ссылка 'Подробнее о сервисе' неактивна!");
        moreInfoLink.click();

        Assertions.assertEquals("О свете", driver.getTitle(), "Неверный заголовок страницы после перехода!");

        driver.navigate().back();
    }

    @Test
    public void testContinueButton() {
        driver.findElement(By.xpath("//input[@name='serviceType'][@value='connectionServices']")).click();

        WebElement phoneInput = driver.findElement(By.name("phoneNumber"));
        phoneInput.sendKeys("297777777");

        WebElement continueButton = driver.findElement(By.xpath("//button[contains(text(), 'Продолжить')]"));
        Assertions.assertTrue(continueButton.isEnabled(), "Кнопка 'Продолжить' неактивна!");
        continueButton.click();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

