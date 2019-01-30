package tests;

import dataProvider.SystemDefaults;
import keywords.LogIn;
import keywords.WatchIssue;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class WatchIssueTest {

  private static SystemDefaults defaults = new SystemDefaults();
  private static WebDriver driver;
  private String issueURL = defaults.getUrl() + "/browse/JTA-57";

  @BeforeClass
  public static void setBase() {
    System.setProperty(defaults.getWebDriver(), defaults.getDriverPath());
    driver = new ChromeDriver();
  }

  @Before
  public void setup() {
    LogIn.setUp(driver);
    driver.manage().deleteAllCookies();
    LogIn.logIn(driver, defaults.getUserName(), defaults.getPwd(), defaults);
  }

  @AfterClass
  public static void clearUp() {
    driver.quit();
  }

  @After
  public void cleanHistory() {
    WatchIssue.setWatcherStatus(driver, false, defaults, issueURL);
  }

  @Test
  public void watchIssue() {
    WatchIssue.setWatcherStatus(driver, false, defaults, issueURL);
    driver.navigate().refresh();
    int originalWatcherCount = WatchIssue.getWatcherCount(driver, defaults);
    WatchIssue.setWatcherStatus(driver, true, defaults, issueURL);
    driver.navigate().refresh();
    int refreshedWatcherCount = WatchIssue.getWatcherCount(driver, defaults);

    assertEquals(originalWatcherCount + 1, refreshedWatcherCount);
  }
}
