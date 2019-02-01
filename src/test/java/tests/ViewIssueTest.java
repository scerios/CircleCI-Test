package tests;

import dataProvider.SystemDefaults;
import enums.IssueType;
import enums.Priority;
import enums.ProjectType;
import keywords.LogIn;
import keywords.ViewIssue;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ViewIssueTest {
  private static WebDriver driver;
  private static ChromeOptions options = new ChromeOptions();


  private static SystemDefaults defaults = new SystemDefaults();

  @BeforeClass
  public static void setBase() {
    System.setProperty(defaults.getWebDriver(), defaults.getDriverPath());
    options.setHeadless(true);
    driver = new ChromeDriver(options);
  }

  @Before
  public void setUp() {
    driver.manage().deleteAllCookies();
    LogIn.setUp(driver);
    LogIn.logIn(driver, defaults.getUserName(), defaults.getPwd(), defaults);
  }

  @Test
  public void assertViewIssue() {
    ViewIssue.goToIssue(driver, defaults.getUrl()+"/browse/SANDBOX-275", defaults);

    Assert.assertEquals(ProjectType.SANDBOX.toString().toLowerCase(), ViewIssue.getProjectType(driver,defaults).toLowerCase());
    Assert.assertEquals("Testing Sandbox Project name", ViewIssue.getSummary(driver, defaults));
    Assert.assertEquals(IssueType.EPIC.toString().toLowerCase(), ViewIssue.getIssueType(driver, defaults).toLowerCase());
    Assert.assertEquals(Priority.MEDIUM.toString().toLowerCase(), ViewIssue.getPriority(driver, defaults).toLowerCase());
    Assert.assertEquals("Click to add description", ViewIssue.getDescription(driver, defaults));
    Assert.assertEquals("TO DO", ViewIssue.getStatus(driver, defaults));
    Assert.assertEquals("Unresolved", ViewIssue.getResolution(driver, defaults));
    Assert.assertEquals("User4", ViewIssue.getAssignee(driver, defaults));
  }

  @AfterClass
  public static void clearUp() {
    driver.quit();
  }
}

