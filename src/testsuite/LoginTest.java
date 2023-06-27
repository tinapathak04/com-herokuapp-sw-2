package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    String baseUrl = "http://the-internet.herokuapp.com/login ";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials(){
        //Enter “tomsmith” username
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("tomsmith");
        //Enter “tomsmith” password
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("tomsmith");
        //Click on ‘LOGIN’ button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //Verify the text “Secure Area”
        driver.findElement(By.xpath("//h4[@class='subheader']")).getText();

    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        //Enter “tomsmith1” username
         driver.findElement(By.xpath("//input[@name='username']")).sendKeys("tomsmith1");
        // Enter “SuperSecretPassword!” password
         driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
        // Click on ‘LOGIN’ button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        // Verify the error message “Your username is invalid!”
        driver.findElement(By.xpath("//div[@id='flash']")).getText();
    }
    @Test
    public void verifyThePasswordErrorMessage(){
        //Enter “tomsmith” username
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("tomsmith");
        // Enter “SuperSecretPassword” password
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword");
        // Click on ‘LOGIN’ button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        // Verify the error message “Your password is invalid!”
        String expectedText = "Your Password is invalid";
        String actualTextElement = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        Assert.assertEquals("Invalid password not displayed",expectedText,actualTextElement);
    }


    @After
    public void tearDown(){
        closeBrowser();
    }
}
