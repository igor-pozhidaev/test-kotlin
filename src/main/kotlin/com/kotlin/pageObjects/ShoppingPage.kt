package com.kotlin.pageObjects

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class ShoppingPage(val driver: WebDriver) {

    init {
        PageFactory.initElements(driver,this)
    }
}