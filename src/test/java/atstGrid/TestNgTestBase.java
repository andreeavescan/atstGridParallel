package atstGrid;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

/**
 * Base class for TestNG-based test classes
 */
public class TestNgTestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected Capabilities capabilities;

  protected WebDriver driver;


  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod(alwaysRun = true)
  public void initWebDriver() {

  }

  @Parameters({"browser"})
  @BeforeTest (alwaysRun = true)
  public void setup(String browser){
    if (browser.equalsIgnoreCase("chrome")) {
      capabilities = new ChromeOptions();
      if (gridHubUrl==null) {driver = new ChromeDriver(); }
    }
    if (browser.equalsIgnoreCase("edge")) {
      capabilities = new EdgeOptions();
      if (gridHubUrl==null) {driver = new EdgeDriver();}
    }
    if (gridHubUrl!=null) { driver = new RemoteWebDriver(gridHubUrl, capabilities); }
  }


  @AfterTest(alwaysRun = true)
  public void tearDown() {
    if (driver!=null) driver.quit();
  }
}
