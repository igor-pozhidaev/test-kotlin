package com.kotlin.pageObjects

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

class SignInPage (private val driver: WebDriver) {
    private val url = "https://my.whisk-dev.com/"
    private fun open() = driver.get(url)
    private val wait = WebDriverWait(driver, 10)

    init {
        PageFactory.initElements(driver,this)
    }

    //First form
    @FindBy(xpath = "//input[@type='email']")
    lateinit var emailInput: WebElement
    @FindBy(xpath = "//div[@data-testid='authentication-form']//button//div[.='Continue']")
    lateinit var continueButton: WebElement

    //Sign in by email -> password form
    @FindBy(xpath = "//input[@type='password']")
    lateinit var pwdInput: WebElement
    @FindBy(xpath = "//div[@data-testid='authentication-form']//button//div[.='Log in']")
    lateinit var logInButton: WebElement

    //Introducing popup
    @FindBy(xpath = "//button[@data-testid='community-onboarding-continue']")
    lateinit var letsGetCookingButton: WebElement

    fun signInByEmail(){
        open()
        wait.until { emailInput.isDisplayed }
        emailInput.sendKeys("ipozhidaev13@gmail.com")
        continueButton.click()
        wait.until { pwdInput.isDisplayed }
        pwdInput.sendKeys("testKotlin")
        logInButton.click()
        wait.until { letsGetCookingButton.isDisplayed }
        letsGetCookingButton.click()
    }


}