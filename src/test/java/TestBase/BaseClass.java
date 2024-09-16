package TestBase;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
@BeforeClass(groups= {"Sanity","Master","Regression","DataDriven"})
@Parameters({"os","browser"})
public void setUp(String os, String br) throws IOException

{
	FileReader file = new FileReader("./src//test//resources//config.properties");
	p= new Properties();
	p.load(file);
	
	logger=LogManager.getLogger(this.getClass());
	
	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		
		//os
		if(os.equalsIgnoreCase("windows"))
		{
			cap.setPlatform(Platform.WIN10);
		}
		else if (os.equalsIgnoreCase("mac"))
		{
			cap.setPlatform(Platform.MAC);
		}
		else
		{
			System.out.println("no matching os");
			return;
		}
		//browser
		switch(br.toLowerCase())
		{
		case "chrome": cap.setBrowserName("chrome"); break;
		case "firefox": cap.setBrowserName("firefox"); break;
		case "edge": cap.setBrowserName("MicrosoftEdge"); break;
		default: System.out.println("Invalid browser name..."); return;
		}
		
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
	}
	
	if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	{
	switch(br.toLowerCase())
	{
	case "chrome": driver=new ChromeDriver(); break;
	case "firefox": driver=new FirefoxDriver(); break;
	case "edge": driver=new EdgeDriver(); break;
	default: System.out.println("Invalid browser name..."); return;
	}
	}
	
	
//	System.setProperty("webdriver.crome.driver", "E:\\selenium\\New chromedriver\\chromedriver-win64\\chromedriver.exe");
	
	driver.get(p.getProperty("url1"));
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
@AfterClass (groups= {"Sanity","Master","Regression","DataDriven"})
public void teardown()
{
	driver.quit();
}

public String randomeString(int length)
{
	return RandomStringUtils.randomAlphabetic(length);
}
public String randomeNumeric(int length)
{
	return RandomStringUtils.randomNumeric(length);
}
public String randomeAlphaNum(int Alphalength, int Numlength)
{
	return RandomStringUtils.randomAlphabetic(Alphalength)+"&"+RandomStringUtils.randomNumeric(Numlength);
}

public String captureScreen (String tname) throws IOException 
{
	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	
	TakesScreenshot takesScreenshot = (TakesScreenshot) driver; 
	File sourceFile = takesScreenshot.getScreenshotAs (OutputType.FILE);
	
	String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + " " + timeStamp +".png";
	File targetFile=new File(targetFilePath);
	
	sourceFile.renameTo(targetFile);
	return targetFilePath;

}
}