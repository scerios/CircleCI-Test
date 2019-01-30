package tests;

import dataProvider.SystemDefaults;
import enums.IssueType;
import enums.Priority;
import enums.ProjectType;
import keywords.CreateIssue;
import keywords.LogIn;
import keywords.ViewIssue;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateIssueTest {
  private static WebDriver driver;
  private static SystemDefaults defaults = new SystemDefaults();
  private ProjectType projectType = ProjectType.SANDBOX;
  private IssueType issueType = IssueType.TASK;
  private Priority priority = Priority.LOWEST;
  private boolean isAssignToMe = true;
  private String summary = "Issue Test";

  @BeforeClass
  public static void setBase() {
    System.setProperty(defaults.getWebDriver(), defaults.getDriverPath());
    driver = new ChromeDriver();
  }

  @Before
  public void setUp() {
    driver.manage().deleteAllCookies();
    LogIn.setUp(driver);
    LogIn.logIn(driver, defaults.getUserName(), defaults.getPwd(), defaults);
  }

  @AfterClass
  public static void tearDown() {
    driver.quit();
  }

  @Test
  public void checkIssueMatchInput() {
    CreateIssue.selectCreateBtn(driver, defaults);
    CreateIssue.fillUpIssueForm(driver, projectType, issueType, summary, priority, isAssignToMe, defaults);
    CreateIssue.getNewIssuePage(driver, defaults);

    Assert.assertEquals(projectType.toString().toLowerCase(), ViewIssue.getProjectType(driver, defaults).toLowerCase());
    Assert.assertEquals(issueType.toString().toLowerCase(), ViewIssue.getIssueType(driver, defaults).toLowerCase());
    Assert.assertEquals(priority.toString().toLowerCase(), ViewIssue.getPriority(driver, defaults).toLowerCase());
    Assert.assertEquals(summary, ViewIssue.getSummary(driver, defaults));
    Assert.assertEquals(defaults.getUserName().toLowerCase(), ViewIssue.getAssignee(driver, defaults).toLowerCase());
  }
}
