package tests;

import dataProvider.SystemDefaults;
import keywords.LogIn;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LogInTest {
  private static WebDriver driver;
  private static ChromeOptions options = new ChromeOptions();

  private static SystemDefaults defaults = new SystemDefaults();
  private final String ERROR_MSG = "Sorry, your username and password are incorrect - please try again.";

  @BeforeClass
  public static void setBase() {
    System.setProperty(defaults.getWebDriver(), defaults.getDriverPath());
    options.setHeadless(true);
//    options.addArguments("disable-infobars"); // disabling infobars
//    options.addArguments("--disable-extensions"); // disabling extensions
//    options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//    options.addArguments("--no-sandbox"); // Bypass OS security model
    driver = new ChromeDriver(options);
  }

  @Before
  public void setUp() {
    driver.manage().deleteAllCookies();
//    LogIn.setUp(driver);
  }

  @After
  public void cleanHistory() {
  }

  @AfterClass
  public static void clearUp() {
    driver.quit();
  }

  @Test
  public void logInWithValidValues() {
    LogIn.logIn(driver, defaults.getUserName(), defaults.getPwd(), defaults);
    Assert.assertEquals(defaults.getUserName(), LogIn.getLoggedInUserName(driver, defaults));
  }

  @Test
  public void logInWithoutPwd() {
    LogIn.logIn(driver, defaults.getUserName(), "", defaults);
    Assert.assertEquals(ERROR_MSG, LogIn.getLogInErrorMsg(driver, defaults));
  }

  @Test
  public void logInWithoutUserName() {
    LogIn.logIn(driver, "", defaults.getPwd(), defaults);
    Assert.assertEquals(ERROR_MSG, LogIn.getLogInErrorMsg(driver, defaults));
  }

  @Test
  public void logInWithWrongPwd() {
    LogIn.logIn(driver, defaults.getUserName(), defaults.getPwd() + "fail", defaults);
    Assert.assertEquals(ERROR_MSG, LogIn.getLogInErrorMsg(driver, defaults));
  }
}
