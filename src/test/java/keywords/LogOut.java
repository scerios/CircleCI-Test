package keywords;

import dataProvider.SystemDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOut {
  private static WebDriverWait wait;

  private static WebElement loggedInUserMenu;
  private static WebElement logOutBtn;
  private static WebElement noAccessErrorMsg;

  public static void logOut(WebDriver driver, SystemDefaults defaults) {
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());

    loggedInUserMenu = wait.until(ExpectedConditions.elementToBeClickable(By.id("header-details-user-fullname")));
    loggedInUserMenu.click();

    logOutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("log_out")));
    logOutBtn.click();
  }

  public static boolean isLoggedInUserMenuVisible(WebDriver driver) {
    boolean isVisible = false;

    try {
      loggedInUserMenu = driver.findElement(By.id("header-details-user-fullname"));
      isVisible = true;
    } catch (NoSuchElementException e) {
    }

    return isVisible;
  }

  public static boolean getViewProfilePage(WebDriver driver, SystemDefaults defaults) {
    boolean isGainedAccess = true;
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());
    driver.navigate().to(defaults.getUrl() + "/secure/ViewProfile.jspa");

    noAccessErrorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//form[@id='login-form']/div[1]/div[1]/p[1]")));

    if (noAccessErrorMsg.getText().equals("You must log in to access this page.")) {
      isGainedAccess = false;
    }

    return isGainedAccess;
  }
}
