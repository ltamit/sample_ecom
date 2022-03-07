package com.lambdatestassign4.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class userSignup {

    public RemoteWebDriver driver;
    private String status = "failed";
    public String username = "<Your Username>";
    public String accesskey = "<Your Access Key>";
    public String gridURL = "<Your Grid URL>";

    @Parameters(value = {"browser", "version", "platform"})

    @BeforeMethod
    public void setUp(String browser, String version, String platform) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "AmitAssignmentBuild2.3");
        capabilities.setCapability("name", "1.1");
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("version", version);

        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.executeScript("lambda-status=" + status);
        driver.close();
    }


    @Test (priority = 0)
    public void signUp() throws InterruptedException {
        
        driver.get("http://tutorialsninja.com/demo/index.php?route=account/register");

        String expectedUrl = "http://tutorialsninja.com/demo/index.php?route=account/success";
        String expectedTitle = "Your Account Has Been Created!";


        WebElement fname= driver.findElement(By.id("input-firstname"));
        fname.sendKeys("Rohit");
        Thread.sleep(1000);

        WebElement lname = driver.findElement(By.id("input-lastname"));
        lname.sendKeys("Jain");
        Thread.sleep(100);

        WebElement email = driver.findElement(By.id("input-email"));
        email.sendKeys("rajat.jain014@gmail.com");
        Thread.sleep(1000);

        WebElement telephone = driver.findElement(By.id("input-telephone"));
        telephone.sendKeys("8882223334");
        Thread.sleep(1000);


        WebElement pwd = driver.findElement(By.id("input-password"));
        pwd.sendKeys("123456");
        Thread.sleep(1000);


        WebElement pwdconfirm = driver.findElement(By.id("input-confirm"));
        pwdconfirm.sendKeys("123456");
        Thread.sleep(1000);

        WebElement newsletter = driver.findElement(By.xpath("//input[@value='0']"));
        newsletter.click();
        Thread.sleep(1000);

        WebElement privacy = driver.findElement(By.xpath("//input[@name='agree']"));
        privacy.click();
        Assert.assertTrue(privacy.isEnabled(), "Privacy Checked");

        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        driver.navigate().to(expectedUrl);

        WebDriverWait wait=new WebDriverWait(driver, 8);
        String actualUrl = driver.getCurrentUrl();
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualUrl, expectedUrl, "URL Match");
        Assert.assertEquals(actualTitle, expectedTitle, "Title Match");

        status = "passed";



    }

    @Test (priority = 1)
    public void productPurchase() throws InterruptedException {
        driver.get("http://tutorialsninja.com/demo/index.php?route=account/login");

        String expectedUrl1="http://tutorialsninja.com/demo/index.php?route=account/account";

        //Input Name
        WebElement email_login = driver.findElement(By.id("input-email"));
        email_login.sendKeys("rajat.jain014@gmail.com");
        Thread.sleep(1000);

        //Input Email
        WebElement email_pwd = driver.findElement(By.id("input-password"));
        email_pwd.sendKeys("123456");
        Thread.sleep(1000);

        //Click Login Button
        WebElement login_btn = driver.findElement(By.xpath("//input[@value='Login']"));
        login_btn.click();
        Thread.sleep(1000);

        //Check Landed URL
        String landedUrl = driver.getCurrentUrl();
        Assert.assertEquals(landedUrl, expectedUrl1, "URL Matched");
        Thread.sleep(1000);

        //Navigate to Homepage
        WebElement store_logo = driver.findElement(By.linkText("Your Store"));
        store_logo.click();
        Thread.sleep(1000);

        //Check HomePage URL
        String expectedhomeUrl = "http://tutorialsninja.com/demo/index.php?route=common/home";
        String actualhomeUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualhomeUrl, expectedhomeUrl, "Home URL Matched");

        //Click on Item
        WebElement itemImage = driver.findElement(By.xpath("//img[@title='MacBook']"));
        itemImage.click();
        Thread.sleep(3000);

        //Check Item URL
        String expectedItemUrl = "http://tutorialsninja.com/demo/index.php?route=product/product&product_id=43";
        String landedItemUrl = driver.getCurrentUrl();
        Assert.assertEquals(landedItemUrl, expectedItemUrl, "Item URL Matched");
        Thread.sleep(3000);

        //Add Item Button
        WebElement addItemBtn = driver.findElement(By.id("button-cart"));
        addItemBtn.click();
        Thread.sleep(2000);

        //Click Cart Button on Item Page
        WebElement itemCartBtn = driver.findElement(By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']"));
        itemCartBtn.click();
        Thread.sleep(2000);

        //Click Checkout button
        WebElement proceedToChkout = driver.findElement(By.xpath("//strong[normalize-space()='Checkout']"));
        proceedToChkout.click();
        Thread.sleep(2000);

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //Check Checkout Landed Page
        String expectedCheckoutUrl = "http://tutorialsninja.com/demo/index.php?route=checkout/checkout";
        String currentCheckoutUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentCheckoutUrl, expectedCheckoutUrl, "Checkout URL Matched!");

        status = "passed";

    }


}



