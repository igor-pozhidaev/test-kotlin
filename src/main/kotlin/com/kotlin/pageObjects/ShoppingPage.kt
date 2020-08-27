package com.kotlin.pageObjects

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe
import org.openqa.selenium.support.ui.WebDriverWait


class ShoppingPage(val driver: WebDriver) {

    private val url = "https://my.whisk-dev.com/shopping-list/"
    private val wait = WebDriverWait(driver, 10)
    var action = Actions(driver)

    init {
        PageFactory.initElements(driver, this)
    }

    // Left menu

    @FindBy(css = "a[data-testid='create-new-shopping-list-button']")
    lateinit var createNewListButton: WebElement

    @FindBy(css = "a[data-testid='shopping-lists-list-name']")
    lateinit var lists: WebElement

    // Dots menu

    @FindBy(css = "button[data-testid='vertical-dots-shopping-list-button']")
    lateinit var verticalDotsButton: WebElement

    @FindBy(css = "button[data-testid='shopping-list-delete-menu-button']")
    lateinit var shoppingListDeleteButton: WebElement

    // PopUp

    @FindBy(css = "button[data-testid='create-new-shopping-list-create-button']")
    lateinit var createNewListPopUpButton: WebElement

    @FindBy(css = "button[data-testid='confirm-delete-button']")
    lateinit var confirmDeletePopUpButton: WebElement

    // Add item

    @FindBy(css = "input[data-testid='desktop-add-item-autocomplete']")
    lateinit var addItemInput: WebElement



    fun openShoppingPage() = driver.get(url)

    fun createNewList(){
        wait.until { createNewListButton.isDisplayed }
        createNewListButton.click()
        wait.until { createNewListPopUpButton.isDisplayed }
        createNewListPopUpButton.click()
    }

    fun openVerticalDotsMenu(){
        wait.until { verticalDotsButton.isDisplayed }
        verticalDotsButton.click()
    }

    fun deleteList(){
        openVerticalDotsMenu()
        wait.until { shoppingListDeleteButton.isDisplayed }
        shoppingListDeleteButton.click()
        wait.until { confirmDeletePopUpButton.isDisplayed }
        confirmDeletePopUpButton.click()
    }

    fun checkNumberOfLists(count: Int) {
        wait.until(numberOfElementsToBe(By.xpath("//div[@data-testid='shopping-lists-list-name']"), count))
    }

    fun openItemsList(){
        wait.until { addItemInput.isDisplayed }
        addItemInput.click()
    }

    fun selectItemToAdd(itemName: String){
        val item = driver.findElement(By.xpath("//div[@data-testid='autocomplete-item']//span[.='$itemName']"))
        wait.until { item.isDisplayed }
        item.click()
    }

    fun closeItemsList(){
        addItemInput.sendKeys(Keys.ESCAPE)
    }

    fun checkItemIsAtListByName(itemName: String){
        val item = driver.findElement(By.xpath("//span[@data-testid='shopping-list-item-name' and .='$itemName']"))
        wait.until { item.isDisplayed }
    }

}

