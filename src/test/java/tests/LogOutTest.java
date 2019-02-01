package tests;

import dataProvider.SystemDefaults;
import keywords.LogIn;
import keywords.LogOut;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LogOutTest {
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

  @After
  public void cleanHistory() {
  }

  @AfterClass
  public static void clearUp() {
    driver.quit();
  }

  @Test
  public void logOut() {
    LogOut.logOut(driver, defaults);
    Assert.assertFalse(LogOut.isLoggedInUserMenuVisible(driver));
    Assert.assertFalse(LogOut.getViewProfilePage(driver, defaults));
  }
}
