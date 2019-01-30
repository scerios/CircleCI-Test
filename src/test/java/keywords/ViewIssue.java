package keywords;

import dataProvider.SystemDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewIssue {
  private static WebDriverWait wait;

  public static String getProjectType(WebDriver webDriver, SystemDefaults defaults) {
    wait = new WebDriverWait(webDriver, defaults.getWaitTimeOut());
    WebElement projectName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("project-name-val")));
    return projectName.getText();
  }

  public static String getSummary(WebDriver webDriver, SystemDefaults defaults) {
    wait = new WebDriverWait(webDriver, defaults.getWaitTimeOut());
    WebElement summary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary-val")));
    return summary.getText();
  }

  public static String getIssueType(WebDriver webDriver, SystemDefaults defaults) {
    wait = new WebDriverWait(webDriver, defaults.getWaitTimeOut());
    WebElement type = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("type-val")));
    return type.getText();
  }

  public static String getPriority(WebDriver webDriver, SystemDefaults defaults) {
    wait = new WebDriverWait(webDriver, defaults.getWaitTimeOut());
    WebElement priority = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("priority-val")));
    return priority.getText();
  }

  public static String getDescription(WebDriver webDriver, SystemDefaults defaults) {
    wait = new WebDriverWait(webDriver, defaults.getWaitTimeOut());
    WebElement description = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("description-val")));
    return description.getText();
  }

  public static String getStatus(WebDriver webDriver, SystemDefaults defaults) {
    wait = new WebDriverWait(webDriver, defaults.getWaitTimeOut());
    WebElement status = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("status-val")));
    return status.getText();
  }

  public static String getResolution(WebDriver webDriver, SystemDefaults defaults) {
    wait = new WebDriverWait(webDriver, defaults.getWaitTimeOut());
    WebElement resolution = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resolution-val")));
    return resolution.getText();
  }

  public static String getAssignee(WebDriver webDriver, SystemDefaults defaults) {
    wait = new WebDriverWait(webDriver, defaults.getWaitTimeOut());
    WebElement assignee = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("assignee-val")));
    return assignee.getText();
  }

  public static void goToIssue(WebDriver webDriver, String url, SystemDefaults defaults){
    wait = new WebDriverWait(webDriver, defaults.getWaitTimeOut());
    WebElement userNameMenu = wait.until(ExpectedConditions.elementToBeClickable(By.id("header-details-user-fullname")));
    webDriver.get(url);
  }
}
