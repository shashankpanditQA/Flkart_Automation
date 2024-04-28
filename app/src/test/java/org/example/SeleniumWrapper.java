package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {

    static void sortByPopularityTab(WebDriver driver, By Selector) throws InterruptedException {
        WebElement sortByDropdown = driver.findElement(Selector);
        sortByDropdown.click();
    }

    static void sortByCostomerReviewsCheckox(WebDriver driver)
    {
       WebElement ele =driver.findElement(By.xpath("//div[@class='SDsN9S']//div[contains(text(),'4')]//preceding-sibling::div"));
       ele.click();
    }

}
