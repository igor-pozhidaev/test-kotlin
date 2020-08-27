package com.kotlin

import com.kotlin.pageObjects.ShoppingPage
import com.kotlin.pageObjects.SignInPage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit


class UITests {

    private val driver: WebDriver = ChromeDriver()
    private var signInPage = SignInPage(driver)
    private var shoppingPage = ShoppingPage(driver)

    init {
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()
    }

    @BeforeEach
    fun preconditions(){
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/com/kotlin/drivers/chromedriver")
        signInPage.signInByEmail()
        shoppingPage.openShoppingPage()
        shoppingPage.createNewList()
        shoppingPage.checkNumberOfLists(2)
    }

    @AfterEach
    fun tearDown(){
        driver.quit()
    }

    @Test
    fun test1() {
        shoppingPage.openItemsList()

        val items = listOf("Milk", "Eggs", "Bread", "Potatoes", "Onions", )
        shoppingPage.selectItemToAdd(items[0])
        shoppingPage.selectItemToAdd(items[1])
        shoppingPage.selectItemToAdd(items[2])
        shoppingPage.selectItemToAdd(items[3])
        shoppingPage.selectItemToAdd(items[4])

        shoppingPage.closeItemsList()

        shoppingPage.checkItemIsAtListByName(items[0])
        shoppingPage.checkItemIsAtListByName(items[1])
        shoppingPage.checkItemIsAtListByName(items[2])
        shoppingPage.checkItemIsAtListByName(items[3])
        shoppingPage.checkItemIsAtListByName(items[4])
    }

   @Test
   fun test2() {
        shoppingPage.deleteList()

        // CHeck there is only default list
        shoppingPage.checkNumberOfLists(1)
   }
}