package oi;

import org.testng.annotations.Test;

import com.google.common.base.Verify;

import Utilities.Excelutil;
import Utilities.constants;
import junit.framework.Assert;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
public class ServerHostName {
	 public WebDriver driver;
	 	String url1 ="https://telcel-bci.cisco.com/";
	 	String urlserver ="https://ettcs-bci.cisco.com/en-US/app/good_morning/search";
	 	String url ="https://bci-c1.cisco.com/";
	 	String rmurl= "https://bcs-rm.cisco.com/#/deviceCrashRisk?cpyKey=191274";
	 	String umurl="https://bci-um.cisco.com/en-US/app/as_management/usage_metrics?form.customer=*&form.app=*&form.not_app=&form.timepicked.earliest=-60m%40m&form.timepicked.latest=now&form.bin_span=1d&form.portal_type=%20%7C%20lookup%20metrics.csv%20HostName%20output%20CustomerName%20%7C%20search%20HostName%3D%22*-bci*%22&form.user_type=%7C%20search%20Roles%3D%22*nce-in*%22%20OR%20Roles%3D%22*nos-admin*%22%20OR%20Roles%3D%22*nce-admin*%22%20OR%20Roles%3D%22*power*%22%7Ceval%20Usertype%3D%22Cisco%22";
	//public String excelpath=constants.filePath+constants.servername;
	 public String excelpath=(System.getProperty("user.dir") + "\\ExcelData\\" + constants.servername);
	 public String sheetname="CP";
	 public String serversheetname="CP";
	 public String UMsheetname="UM";
	 public WebDriverWait wait;
	 public List<WebElement> customers;
	 String[] countandsyslog=null;
	 String stringDate; 
	 String outputfile=excelpath.substring(45);
	 int unsuccessful=0;
	 HashMap <String,String> hm= new HashMap <String,String>(); 
	  Excelutil eu= new Excelutil();
	 public String edgeDriver="msedgedriver";
  	  
	
	  @BeforeTest
		public void login() throws InterruptedException, IOException {
	  System.setProperty("webdriver.edge.driver", "Dependencies//" +edgeDriver+".exe");
	  driver = new EdgeDriver();
	  driver.get(url);
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  Thread.sleep(2000);
	  String actualTitle = driver.getTitle();
	  System.out.println(actualTitle);
	  driver.manage().window().maximize();
	  
	  
	  if(actualTitle.matches("Cisco")||actualTitle.matches("Log In to Cisco"))
	  	{
		  
		  if(constants.username.contains("@gmail.com"))
		  {
			String auth=driver.getCurrentUrl();
	  	  System.out.println(auth);
	  	  int alertboxispresent =driver.findElements(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).size();
	  	  if(alertboxispresent>0)
	  	  {
	  		  driver.findElement(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).click();
	  		System.out.println("Alert window closed");
	  	  }
	  	  WebElement oktausername=driver.findElement(By.cssSelector("input[id=\"idp-discovery-username\"]"));
	 	  oktausername.click();
	 	  oktausername.sendKeys(constants.username);
	 	  WebElement oktasubmit=driver.findElement(By.cssSelector("input[id=\"idp-discovery-submit\"]"));
	 	  oktasubmit.click();
	 	  Thread.sleep(3000);
	  	  WebElement oktapassword=driver.findElement(By.cssSelector("input[id=\"okta-signin-password\"]"));
	 	  oktapassword.click();
	 	  oktapassword.sendKeys(constants.pwd);
	 	  WebElement oktapage2submit=driver.findElement(By.cssSelector("input[id=\"okta-signin-submit\"]"));
	 	  oktapage2submit.click();
	 	  
		  }
		  
		  else {
	  //passing Username & Password
	  String auth=driver.getCurrentUrl();
	  System.out.println("auth-----"+auth);
	  int alertboxispresent =driver.findElements(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).size();
	  	  if(alertboxispresent>0)
	  	  {
	  		  driver.findElement(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).click();
	  		System.out.println("Alert window closed");
	  	  }
	  
	  WebElement oktausername=driver.findElement(By.cssSelector("input[id=\"idp-discovery-username\"]"));
	  oktausername.click();
	  oktausername.sendKeys(constants.username);
	  WebElement oktasubmit=driver.findElement(By.cssSelector("input[id=\"idp-discovery-submit\"]"));
	  oktasubmit.click();
	  Thread.sleep(3000);
	  if(driver.findElement(By.cssSelector("h1[class=\"card--focusable-text\"]")).getText().matches("Single Sign-On"))
	  {
		  driver.findElement(By.cssSelector("input[aria-label=\"Email Address\"]")).sendKeys(constants.username);
		  driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
		   driver.findElement(By.cssSelector("input[aria-label=\"Password\"]")).sendKeys(constants.pwd);
		  driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		  Thread.sleep(16000);
	  }
	  else {
	  WebElement element =driver.findElement(By.id("userInput"));
	  element.click();
	  //element.sendKeys(constants.username);
	  WebElement element1= driver.findElement(By.id("login-button"));
	  element1.click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  WebElement element2=driver.findElement(By.id("passwordInput"));
	  element2.click();
	  element2.sendKeys(constants.pwd);
	  WebElement element3 = driver.findElement(By.id("login-button"));
	  element3.click();
	  }
		  }
	  	}

	  else {
		  System.out.println("One id");
		  System.out.println(driver.getTitle());
		  int alertboxispresent =driver.findElements(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).size();
	  	  if(alertboxispresent>0)
	  	  {
	  		  driver.findElement(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).click();
	  		  System.out.println("Alert window closed");
	  	  }
		  WebElement element =driver.findElement(By.id("userInput"));
  	  element.click();
  	  element.sendKeys(constants.username);
  	  WebElement element1= driver.findElement(By.id("login-button"));
  	  element1.click();
  	  Thread.sleep(3000);
  	  int available = driver.findElements(By.id("okta-signin-password")).size();
  	  if(available>0) {
  		  WebElement element2=driver.findElement(By.cssSelector("input[type=\"password\"]"));
      	  element2.click();
      	  element2.sendKeys(constants.pwd);
      	  WebElement element3 = driver.findElement(By.id("okta-signin-submit"));
      	  element3.click();
  	  }
  	  else {
  		  System.out.println("The login page at password section");
  	  WebElement element2=driver.findElement(By.id("passwordInput"));
  	  element2.click();
  	  element2.sendKeys(constants.pwd);
  	  WebElement element3 = driver.findElement(By.id("login-button"));
  	  element3.click();
  	  }
	  		}
	  Thread.sleep(20000);

	  
	 }
	
@Test(priority=1,enabled=true)
  		public void serverhostname() throws IOException
  		{
  		driver.get(urlserver);
  	  //clicking the help button
  	  WebElement Helpbutton =driver.findElement(By.cssSelector("a[title=\"Help\"]"));
  	  Helpbutton.click();
  	  WebElement element5 =driver.findElement(By.linkText("About"));
  	  element5.click();
  	  //handling the popup window
  	  driver.switchTo().parentFrame();
  	  WebElement element6 = driver.findElement(By.cssSelector("dd[data-content=server-name]"));
  	  WebElement splunk_version =driver.findElement(By.cssSelector("dd[data-content=\"splunk-version\"]"));
  	  WebElement Good_morning = driver.findElement(By.cssSelector("dd[data-content=\"app-version\"]"));
  	  //WebElement element6 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/dl/dd[3]"));
  	  String servername = element6.getText();
  	  String splunkversion = splunk_version.getText();
  	  String goodmorning =Good_morning.getText();
  	  System.out.println("The Splunk version is " +splunkversion);
  	  System.out.println("The Server name is :" +servername);
  	  System.out.println("The Good Morning version is "+goodmorning);
  	 
  	  
  	  //moving on to next URL
  	  Excelutil eu= new Excelutil();
  	  eu.setExcelFile(excelpath,serversheetname);
  	  //iterating the url values
  	 for(int i=1;i<=eu.getRowCountInSheet();i++)
     {
  		 System.out.println("the total number of Links available is "+eu.getRowCountInSheet());
  		 int totalrow =eu.getRowCountInSheet();
  		 int balrow = totalrow-i;
  		 System.out.println("No.of customers left :"+balrow);
  		// boolean status =driver.findElement(By.cssSelector("a[role=button]")).isDisplayed();
  		
  		 try {
  		 driver.navigate().to(eu.getCellData(i,0)+"/en-US/app/good_morning/search");
  		 //clicking the help button
  		 WebElement Helpbuttonitr =driver.findElement(By.cssSelector("a[title=\"Help\"]"));
  		 Helpbuttonitr.click();
  	  	  WebElement element8 =driver.findElement(By.linkText("About"));
  	  	  element8.click();
  	  	  WebElement hostnameitr;
  	  	  WebElement splunk_versionitr;
  	  	  WebElement Good_morningitr;
  	  	  //handling the popup window

  	  	try {
  	  		driver.switchTo().parentFrame();
  	  		hostnameitr = driver.findElement(By.cssSelector("dd[data-content=server-name]"));
  	  		splunk_versionitr =driver.findElement(By.cssSelector("dd[data-content=\"splunk-version\"]"));
  	  		Good_morningitr = driver.findElement(By.cssSelector("dd[data-content=\"app-version\"]"));
  	  		}
  	  	catch (NoSuchElementException e) 
  	  		{
  	  		Thread.sleep(2000);
  	  		 hostnameitr = driver.findElement(By.cssSelector("dd[data-content=server-name]"));
  	  		 splunk_versionitr =driver.findElement(By.cssSelector("dd[data-content=\"splunk-version\"]"));
  	  		 Good_morningitr = driver.findElement(By.cssSelector("dd[data-content=\"app-version\"]"));
  	  		}  
  	
  	  		servername= hostnameitr.getText();
  	  		splunkversion = splunk_versionitr.getText();
  	  		goodmorning =Good_morningitr.getText();
  	  		String replace = servername.replace("-splunk", "");  
  	  		StringBuffer sb= new StringBuffer(replace);  
			sb.deleteCharAt(sb.length()-1);  
		//prints the string after deleting the character   
			String hostname=sb.toString();
  	  	  
  	  	  System.out.println("The Server name is :"+eu.getCellData(i,0));
  	  	  eu.setCellValue(i, 2, splunkversion, excelpath);
  	  	  eu.setCellValue(i, 3, servername, excelpath);
  	  	  eu.setCellValue(i, 4, hostname, excelpath);
  	  	  eu.setCellValue(i, 5, goodmorning, excelpath);
  	  	  System.out.println(splunkversion+" , "+servername+" , "+hostname+" ,"+goodmorning);
  	  	  
  		 }
  		 catch (NoSuchElementException e) 
  		 {
  			 System.out.println("page direction/Warning message in "+eu.getCellData(i,0));
  			 
  		 }		
  		 catch (Exception e)
  		 {
  			 System.out.println("Server not reachable"+eu.getCellData(i,0));
  		 }
     } 
  
  }
  	
  	
  	
  @AfterSuite
  	public void mail()
  	{
   		System.out.println("Mail processing");
   		mailer ml=new mailer();
  		//ml.sendmail(excelpath);
  		driver.close();
  		driver.quit();
  	}

}
