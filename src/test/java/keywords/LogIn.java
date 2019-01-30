package keywords;

import dataProvider.SystemDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogIn {
  private static WebDriverWait wait;

  private static WebElement logInUserName;
  private static WebElement logInPwd;
  private static WebElement logInBtn;
  private static WebElement loggedInUserName;
  private static WebElement logInErrorMsg;

  public static void setUp(WebDriver driver) {
    driver.manage().window().maximize();
  }

  public static void logIn(WebDriver driver, String userName, String password, SystemDefaults defaults) {
    driver.get(defaults.getUrl());
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());

    logInUserName = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
    logInUserName.clear();
    logInUserName.sendKeys(userName);

    logInPwd = driver.findElement(By.id("login-form-password"));
    logInPwd.clear();
    logInPwd.sendKeys(password);

    logInBtn = driver.findElement(By.id("login"));
    logInBtn.click();
  }

  public static String getLoggedInUserName(WebDriver driver, SystemDefaults defaults) {
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());

    loggedInUserName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname")));
    return loggedInUserName.getAttribute("data-username");
  }

  public static String getLogInErrorMsg(WebDriver driver, SystemDefaults defaults) {
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("usernameerror")));

    logInErrorMsg = driver.findElement(By.xpath("//div[@id='usernameerror']/p"));
    return logInErrorMsg.getText();
  }
}
