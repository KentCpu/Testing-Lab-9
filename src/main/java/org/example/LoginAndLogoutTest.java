package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginAndLogoutTest {
    WebDriver driver;

    @Before
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rusla\\IdeaProjects\\Selenium-Test\\drivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void loginAndLogout() {
        login();
        logout();
    }

    private void login() {
        driver.get("https://account.mail.ru/login");
        String email = "test-email31@mail.ru";
        String password = "Selenium123";

        WebElement loginField = driver.findElement(By.xpath("//input[@name='username']"));
        loginField.clear();
        loginField.sendKeys(email);

        WebElement nextStepBtn = driver.findElement(By.xpath("//button[@data-test-id='next-button']"));
        nextStepBtn.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("//button[@data-test-id='submit-button']"));
        loginBtn.click();

        driver.findElement(By.xpath("//*[@class='ph-avatar-img svelte-dfhuqc']")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Assert.assertEquals("test test", driver.findElement(By.xpath("//*[@class='ph-name svelte-1popff4']")).getText());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    private void logout() {
        driver.findElement(By.xpath("//*[@data-testid='whiteline-account-exit']")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@class='resplash-btn resplash-btn_outlined-themed resplash-btn_mailbox-big mfflmpk__b85yax']")).isDisplayed();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
