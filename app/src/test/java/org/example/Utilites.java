package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilites {

    public static void waitForElementClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public void waitForAndClick(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    // private WebElement waitForElement(By locator) {
    // return wait.until(ExpectedConditions.elementToBeClickable(locator));
    // }

    public static int countItemsWithLowRating(WebDriver driver, By Selector) {
        int count = 0;
        List<WebElement> ratings = driver.findElements(Selector);
        for (WebElement rating : ratings) {
            String ratingText = rating.getText();
            double stars = Double.parseDouble(ratingText.split(" ")[0]);
            if (stars >= 4) {
                count++;
            }
        }
        return count;

    }
    public static void scrollByPixels(WebDriver driver, int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ");");
    }
    

    public static void searchProduct(WebDriver driver, By Selector, String productName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement product = driver.findElement(Selector);
        Thread.sleep(5000);
        product.sendKeys(productName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector));
        product.sendKeys(Keys.ENTER);
    }

    public static List<WebElement> getProductList(WebDriver driver) {
        return driver.findElements(By.xpath("//div[contains(@class, '_3pLy-c')]"));
    }

    public static String productTitle(WebElement product) {
        return product.findElement(By.xpath(".//div[@class='_4rR01T']")).getText();
    }

    public static int getProductDiscount(WebElement product) {
        String discountText = product.findElement(By.xpath(".//div[2]//div[@class='_25b18c']//span")).getText();
        return Integer.parseInt(discountText.replaceAll("[^0-9]", ""));
    }

    public static List<CoffeeMug> scrapeCoffeeMugs(WebDriver driver) {
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='slAVV4']"));
        List<CoffeeMug> coffeeMugs = new ArrayList<>();
        for (WebElement result : searchResults) {
            WebElement titleElement = result.findElement(By.xpath(".//a[@class='wjcEIp']"));
            String title = titleElement.getText();

            WebElement imageElement = result.findElement(By.xpath(".//a[@class='wjcEIp']"));
            String imageUrl = imageElement.getAttribute("href");

            WebElement reviewsElement = result.findElement(By.xpath(".//span[@class='Wphh3N']"));
            int reviews = Integer.parseInt(reviewsElement.getText().replaceAll("[^0-9]", ""));

            CoffeeMug cm = new CoffeeMug(title, imageUrl, reviews);
            coffeeMugs.add(cm);
        }
        coffeeMugs.sort(Comparator.comparingInt(CoffeeMug::getReviews).reversed());
        return coffeeMugs;
    }








    public static void printProductDeatils(List<WebElement>products,int threshold)
    {
        for(WebElement product :products)
        {
            String product_Tittle = productTitle(product);
            int dis = getProductDiscount(product);
            if(dis>=threshold)
            {
                System.out.println("Title: " + product_Tittle);
                System.out.println("Discount: " + dis + "%");
            }
        }
        

    }

}
