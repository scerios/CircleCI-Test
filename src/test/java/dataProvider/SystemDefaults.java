package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SystemDefaults {
  private Properties properties;
  private final String PROPERTY_FILE_PATH = "configs/Configuration.properties";
  private static final String NOT_SPECIFIED_ERR_MSG = " is not specified in the Configuration.properties file.";

  public SystemDefaults(){
    try (BufferedReader reader = new BufferedReader(new FileReader(PROPERTY_FILE_PATH))){
      properties = new Properties();
      properties.load(reader);
    } catch (FileNotFoundException e){
        throw new RuntimeException("Cannot find specific file.");
    } catch (IOException e) {
        throw new RuntimeException("Cannot read/write specific file.");
    } catch (Exception e) {
        throw new RuntimeException("Cannot identify the problem.");
    }
  }

  public String getUserName() {
    String userName = properties.getProperty("userName");
    if (userName != null) {
      return userName;
    }
    throw new RuntimeException("Username" + NOT_SPECIFIED_ERR_MSG);
  }

  public String getPwd() {
    String pwd = properties.getProperty("pwd");
    if (pwd != null) {
      return pwd;
    }
    throw new RuntimeException("Password" + NOT_SPECIFIED_ERR_MSG);
  }

  public String getUrl() {
    String url = properties.getProperty("url");
    if (url != null) {
      return url;
    }
    throw new RuntimeException("URL" + NOT_SPECIFIED_ERR_MSG);
  }

  public long getWaitTimeOut() {
    String timeOut = properties.getProperty("WAIT_TIMEOUT");
    if (timeOut != null) {
      return Long.parseLong(timeOut);
    }
    throw new RuntimeException("Timeout" + NOT_SPECIFIED_ERR_MSG);
  }

  public String getWebDriver() {
    String driver = properties.getProperty("webDriver");
    if (driver != null) {
      return driver;
    }
    throw new RuntimeException("Webdriver" + NOT_SPECIFIED_ERR_MSG);
  }

  public String getDriverPath() {
    String driver = properties.getProperty("driverPath");
    if (driver != null) {
      return driver;
    }
    throw new RuntimeException("Webdriver Path" + NOT_SPECIFIED_ERR_MSG);
  }
}
