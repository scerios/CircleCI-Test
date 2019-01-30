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

public class ViewIssueTest {
  private static WebDriver webDriver;

  private static SystemDefaults defaults = new SystemDefaults();

  @BeforeClass
  public static void setBase() {
    System.setProperty(defaults.getWebDriver(), defaults.getDriverPath());
    webDriver = new ChromeDriver();
  }

  @Before
  public void setUp() {
    webDriver.manage().deleteAllCookies();
    LogIn.setUp(webDriver);
    LogIn.logIn(webDriver, defaults.getUserName(), defaults.getPwd(), defaults);
  }

  @Test
  public void assertViewIssue() {
    ViewIssue.goToIssue(webDriver, defaults.getUrl()+"/browse/SANDBOX-275", defaults);

    Assert.assertEquals(ProjectType.SANDBOX.toString().toLowerCase(), ViewIssue.getProjectType(webDriver,defaults).toLowerCase());
    Assert.assertEquals("Testing Sandbox Project name", ViewIssue.getSummary(webDriver, defaults));
    Assert.assertEquals(IssueType.EPIC.toString().toLowerCase(), ViewIssue.getIssueType(webDriver, defaults).toLowerCase());
    Assert.assertEquals(Priority.MEDIUM.toString().toLowerCase(), ViewIssue.getPriority(webDriver, defaults).toLowerCase());
    Assert.assertEquals("Click to add description", ViewIssue.getDescription(webDriver, defaults));
    Assert.assertEquals("TO DO", ViewIssue.getStatus(webDriver, defaults));
    Assert.assertEquals("Unresolved", ViewIssue.getResolution(webDriver, defaults));
    Assert.assertEquals("User4", ViewIssue.getAssignee(webDriver, defaults));
  }

  @AfterClass
  public static void clearUp() {
    webDriver.quit();
  }
}

