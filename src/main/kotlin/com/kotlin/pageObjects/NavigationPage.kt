package com.kotlin.pageObjects

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class NavigationPage(val driver: WebDriver) {

    init {
        PageFactory.initElements(driver,this)
    }

}