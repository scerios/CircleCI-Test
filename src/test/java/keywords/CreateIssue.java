package keywords;

import dataProvider.SystemDefaults;
import enums.IssueType;
import enums.Priority;
import enums.ProjectType;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssue {
  private static WebDriverWait wait;

  private static WebElement createBtn;
  private static WebElement projectTypeInput;
  private static WebElement issueTypeInput;
  private static WebElement summaryInput;
  private static WebElement priorityInput;
  private static WebElement assignToMeBtn;
  private static WebElement submitBtn;
  private static WebElement issueCreatedMsg;
  private static WebElement isAssignedToMe;

  public static void selectCreateBtn(WebDriver driver, SystemDefaults defaults) {
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());

    createBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
    createBtn.click();
  }

  public static void fillUpIssueForm(WebDriver driver, ProjectType projectType, IssueType issueType, String summary,
                                     Priority priority, boolean isAssignToMe, SystemDefaults defaults) {
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());

    projectTypeInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
    projectTypeInput.sendKeys(projectType.toString(), Keys.ENTER);

    issueTypeInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
    issueTypeInput.sendKeys(issueType.toString(), Keys.ENTER);

    summaryInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("summary")));
    summaryInput.sendKeys(summary);

    priorityInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("priority-field")));
    priorityInput.sendKeys(priority.toString());

    if (isAssignToMe) {
      assignToMeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("assign-to-me-trigger")));
      assignToMeBtn.click();
    }

    submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("create-issue-submit")));
    submitBtn.click();
  }

  public static boolean isCreatedIssuePopupVisible(WebDriver driver, SystemDefaults defaults) {
    boolean isCreated = false;
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());

    try {
      issueCreatedMsg = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='aui-flag-container']/div/div/a")));
      isCreated = true;
    } catch (NoSuchElementException e) {
    }
    return isCreated;
  }

  public static void getNewIssuePage(WebDriver driver, SystemDefaults defaults) {
    if (isCreatedIssuePopupVisible(driver, defaults)) {
      issueCreatedMsg.click();
    }
  }

  public static boolean isProjectTypeMatch(WebDriver driver, SystemDefaults defaults, ProjectType projectType) {
    boolean isMatch = false;
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());

    projectTypeInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("project-name-val")));
    if (projectTypeInput.getText().toLowerCase().equals(projectType.toString().toLowerCase())) {
      isMatch = true;
    }
    return isMatch;
  }

  public static boolean isIssueTypeMatch(WebDriver driver, SystemDefaults defaults, IssueType issueType) {
    boolean isMatch = false;
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());

    issueTypeInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("type-val")));
    if (issueTypeInput.getText().toLowerCase().equals(issueType.toString().toLowerCase())) {
      isMatch = true;
    }
    return isMatch;
  }

  public static boolean isPriorityMatch(WebDriver driver, SystemDefaults defaults, Priority priority) {
    boolean isMatch = false;
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());

    priorityInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("priority-val")));
    if (priorityInput.getText().toLowerCase().equals(priority.toString().toLowerCase())) {
      isMatch = true;
    }
    return isMatch;
  }

  public static boolean isSummaryMatch(WebDriver driver, SystemDefaults defaults, String summary) {
    boolean isMatch = false;
    wait = new WebDriverWait(driver, defaults.getWaitTimeOut());

    summaryInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("summary-val")));
    if (summaryInput.getText().toLowerCase().equals(summary.toLowerCase())) {
      isMatch = true;
    }
    return isMatch;
  }
}
