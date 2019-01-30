package keywords;

import dataProvider.SystemDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WatchIssue {
  private static WebDriverWait wait;
  private static WebElement myOpenIssues;
  private static WebElement issuesMenu;
  private static WebElement pageHeader;
  private static WebElement startWatchingLink;
  private static WebElement watcherCount;

  public static void listIssues(WebDriver driver, SystemDefaults defaults) {
    clickOnIssuesMenu(driver, defaults);
    myOpenIssues = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("filter_lnk_my_lnk")));
    myOpenIssues.click();
  }

  public static String getWatchListPageHeader(WebDriver driver, SystemDefaults defaults) {
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());
    pageHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"search-header-view\"]/div/h1")));

    return pageHeader.getAttribute("title").toLowerCase();
  }

  public static void setWatcherStatus(WebDriver driver, boolean watch, SystemDefaults defaults, String issueURL) {
    clickOnIssuesMenu(driver, defaults);
    driver.get(issueURL);
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());
    startWatchingLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("watching-toggle")));

    if ((watch && !isIssueAlreadyFollowed(driver, defaults)) || (!watch && isIssueAlreadyFollowed(driver, defaults))) {
      startWatchingLink.click();
    }
  }

  public static boolean isIssueAlreadyFollowed(WebDriver driver, SystemDefaults defaults) {
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());
    return wait
        .until(ExpectedConditions.presenceOfElementLocated(By.id("watching-toggle")))
        .getAttribute("class")
        .equals("watch-state-on");
  }

  public static void clickOnIssuesMenu(WebDriver driver, SystemDefaults defaults) {
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());
    issuesMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("find_link")));
    issuesMenu.click();
  }

  public static int getWatcherCount(WebDriver driver, SystemDefaults defaults) {
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());
    watcherCount = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("watcher-data")));
    return Integer.parseInt(watcherCount.getText());
  }
}
