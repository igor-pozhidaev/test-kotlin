package com.kotlin

import com.kotlin.pageObjects.SignInPage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit


class UITests {

    private var driver: WebDriver = ChromeDriver()
    private var signInPage = SignInPage(driver)

    init {
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()
    }

    @BeforeEach
    fun setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/com/kotlin/drivers/chromedriver")
        signInPage.signInByEmail()
    }

    @AfterEach
    fun tearDown(){
        driver!!.quit()
    }

    @Test
    fun test1() {


    }

    @Test
    fun test2() {

    }
}