package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver=null;
	public FileInputStream envfis=null;
	public FileInputStream locatorfis=null;
	public static Properties prop; 
	
	public TestBase() throws IOException {
	envfis=new FileInputStream(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"ConfigurationFiles"+File.separator+"Environment.properties");
	locatorfis=new FileInputStream(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"ConfigurationFiles"+File.separator+"Locators.properties");
	prop=new Properties();
	prop.load(envfis);
	prop.load(locatorfis);
	
	}
	public static void browserInitialization(String browser) throws Exception {
		if(browser.equalsIgnoreCase("chrome")) {
	
			if(driver==null) {
				WebDriverManager.chromedriver().setup();
				 driver=new ChromeDriver();
			}
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"drivers"+File.separator+"geckodriver_64bit.exe");
			if(driver==null) {
			driver=new FirefoxDriver();
			}
			
		}
		
		else if(browser.equalsIgnoreCase("Edge")){
			//set path to Edge.exe
			System.setProperty("webdriver.edge.driver",".\\MicrosoftWebDriver.exe");
			//create Edge instance
			driver = new EdgeDriver();
		}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://mvc.syncfusion.com/demos/web/datetimepicker/default");
}
}