package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ;
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("Setup Test: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Teardown Test: TestCases");
        driver.quit();
    }

    @Test
    public void testCase01() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        System.out.println("Test Case 01: Launch Browser");

        try {
            System.out.println("Test Case 01: Launch Browser");
            // Search for the product
            Utilites.searchProduct(driver, By.name("q"), "Washing Machine");
            // Wait for sorting option to be clickable
            WebElement sortByPriceHighToLow = driver.findElement(By.xpath("//div[text()='Popularity']"));
            Utilites.waitForElementClickable(driver, sortByPriceHighToLow, 10);
            SeleniumWrapper.sortByPopularityTab(driver, By.xpath("//div[text()='Popularity']"));
            // Wait for sorting to be applied (you might need to adjust this wait time)
            Thread.sleep(5000);
            By Selector_rating = By
                    .xpath("//div[@class='_4rR01T']/ancestor::div[@class='col col-7-12']//div[2]/span/div[1]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(Selector_rating));
            int count = Utilites.countItemsWithLowRating(driver, Selector_rating);
            Utilites.scrollByPixels(driver, 7000);

            // Validate the count using TestNG assertion
            Assert.assertTrue(count >= 0, "Count of items with low rating should be non-negative");
            System.out.println("Number of washing machines with rating <= 4 stars: " + count);
            Thread.sleep(5000);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testCase02() throws InterruptedException {
        Utilites.searchProduct(driver, By.name("q"), "iphone");
        List<WebElement> products = Utilites.getProductList(driver);
        Utilites.printProductDeatils(products, 17);
    }

    @Test
    public void testCase03() throws InterruptedException {
        Utilites.searchProduct(driver, By.name("q"), "CoffeeMug");
        SeleniumWrapper.sortByCostomerReviewsCheckox(driver);
        Thread.sleep(5000);
        List<CoffeeMug> coffeeMugs = Utilites.scrapeCoffeeMugs(driver);
        for (int i = 0; i < Math.min(5, coffeeMugs.size()); i++) {
            CoffeeMug coffeeMug = coffeeMugs.get(i);
            System.out.println("Tittle:" + coffeeMug.getTittle());
            System.out.println("URL:" + coffeeMug.getImageUrl());
            System.out.println("Reviews:" + coffeeMug.getReviews());
            System.out.println();
        }
    }

}
