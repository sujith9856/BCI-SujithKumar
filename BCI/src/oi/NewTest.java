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
public class NewTest {
	 public WebDriver driver;
	 	String url1 ="https://telcel-bci.cisco.com/";
	 	String urlserver ="https://ettcs-bci.cisco.com/en-US/app/good_morning/search";
	 	String url ="https://bci-c1.cisco.com/";
	 	String rmurl= "https://bcs-rm.cisco.com/#/deviceCrashRisk?cpyKey=191274";
	 	String umurl="https://bci-um.cisco.com/en-US/app/as_management/usage_metrics?form.customer=*&form.app=*&form.not_app=&form.timepicked.earliest=-60m%40m&form.timepicked.latest=now&form.bin_span=1d&form.portal_type=%20%7C%20lookup%20metrics.csv%20HostName%20output%20CustomerName%20%7C%20search%20HostName%3D%22*-bci*%22&form.user_type=%7C%20search%20Roles%3D%22*nce-in*%22%20OR%20Roles%3D%22*nos-admin*%22%20OR%20Roles%3D%22*nce-admin*%22%20OR%20Roles%3D%22*power*%22%7Ceval%20Usertype%3D%22Cisco%22";
	//public String excelpath=constants.filePath+constants.servername;
	 public String excelpath=(System.getProperty("user.dir") + "\\ExcelData\\" + constants.EvenClusters);
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
	
	@Test(priority=1,enabled = false)
	public void SSLcert() throws InterruptedException
		{
		
	 	 for(int i=1;i<=eu.getRowCountInSheet();i++)
	     {
		try {
			 eu.setExcelFile(excelpath,sheetname);
            // Create a trust manager that trusts all certificates
            TrustManager[] trustAllCertificates = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
            };

            // Create an SSL context with the custom trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());

            // Set the custom SSL context
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

            URL url = new URL(eu.getCellData(i, 0));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
                httpsConnection.setHostnameVerifier((hostname, session) -> true);

                InputStream inputStream = httpsConnection.getInputStream();

                Certificate[] certificates = httpsConnection.getServerCertificates();
                
                
                //for (Certificate certificate : certificates) {
                    if (certificates[0] instanceof X509Certificate) {
                        X509Certificate x509Certificate = (X509Certificate) certificates[0];
                        Date beforevalidity= x509Certificate.getNotBefore();
                        Date aftervalidity= x509Certificate.getNotAfter();
                        System.out.println("SSL Certificate Validity Start: " +beforevalidity);
                        System.out.println("SSL Certificate Validity End: " + aftervalidity);
                        eu.setCellValue(i, 7, beforevalidity.toString(), excelpath);
                        eu.setCellValue(i, 8, aftervalidity.toString(), excelpath);
                       
                    //}
                }

                inputStream.close();
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

		}
		}
		

	@Test(priority=2,enabled=true)
	public void cpsanity() throws InterruptedException, IOException
	{
		String swi="Search is waiting for input...";
		String wtfd="Waiting for data...";
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
		
		Thread.sleep(2500);
		String customername=driver.findElement(By.cssSelector("p[id=\"customer-label\"]")).getText();
		if(customername.isBlank())
			{
				Thread.sleep(5000);
				customername=driver.findElement(By.cssSelector("p[id=\"customer-label\"]")).getText();
				System.out.println("Customer Name in if : "+customername);
			}
			else {
				Thread.sleep(3000);
				customername=driver.findElement(By.cssSelector("p[id=\"customer-label\"]")).getText();
				System.out.println("Customer Name in else : "+customername);
			}
			
		WebElement uniquedevice = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id=\"element7\"]")));
		Actions builder = new Actions(driver);
		
		//Thread.sleep(20000);
		String count= uniquedevice.getText();
		WebElement uniquecount= uniquedevice.findElement(By.cssSelector("text[class=\"single-result\"]"));
		String value=uniquecount.getText();
		System.out.println("The  unique device count is "+value);
		
		
		//This block handles the input - Search is waiting for input...
		
		if (((count.matches(swi)) | count.matches(wtfd) | (count.isBlank())))
			{
				Thread.sleep(12000);
				WebElement uniquecount1= uniquedevice.findElement(By.cssSelector("text[class=\"single-result\"]"));
				String value1=uniquecount1.getText();
				if((value1.matches(swi)) | (value1.matches(wtfd)) | (value1.isBlank()) )
				{
					Thread.sleep(10000);
					WebElement uqcount=uniquedevice.findElement(By.cssSelector("text[class=\"single-result\"]"));
					System.out.println("The unique device count 2nd wait "+uqcount.getText());
					value=uqcount.getText();
				
						if((value.matches(swi)) | (value.matches(wtfd)) | (value.isBlank()))
							{
							Thread.sleep(20000);
							 uqcount=uniquedevice.findElement(By.cssSelector("text[class=\"single-result\"]"));
							System.out.println("The unique device count 3rd wait "+uqcount.getText());
							value=uqcount.getText();
							}
						else 
						{
							System.out.println("the unique device count : "+value1);
							value=value1;
						}
				}
				else {
					System.out.println("the unique device count : "+value1);
					value=value1;
				}	
			}
			
			
			
			WebElement syslog = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id=\"element30\"]")));
			Thread.sleep(5000);
			List<WebElement> syslogs =syslog.findElements(By.cssSelector("tr[data-view=\"views/shared/results_table/ResultsTableRow\"]"));
			System.out.println("The Syslog available in GM page for Customer "+customername +syslogs.size());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
			
			 eu.setExcelFile(excelpath,sheetname);
		  	  //iterating the url values
		  	 for(int i=1;i<=eu.getRowCountInSheet();i++)
		     {
		  		 System.out.println("the total number of Links available is "+eu.getRowCountInSheet());
		  		 int totalrow =eu.getRowCountInSheet();
		  		 int balrow = totalrow-i;
		  		 System.out.println("No.of customers left :"+balrow);
		  		String countitr=null;
		  		int customercount = 0;
		  		String syslogavailable=null;
		  		String customernameitr=null;
		  		String saml="SAML ISSUE";
		  		String Httperror="Http/1.1 Service Unavailable";
		  		String sslerror="Your connection is not private";
		  		String accesserror=" You are not authorized to access this account";
		  		try {

		  			driver.navigate().to(eu.getCellData(i,0));
		  			Thread.sleep(13000);
		  			if(driver.getPageSource().contains("Verification of SAML assertion"))
					{
						System.out.println("Server is having SAML issues" +(eu.getCellData(i,0)));
						customernameitr=saml;
						countitr=saml;
						syslogavailable=saml;
					}
		  			else if(driver.getPageSource().contains(Httperror))
		  			{
		  				System.out.println("Server is having Http/1.1 Service Unavailable "+ (eu.getCellData(i,0)));
						customernameitr=Httperror;
						countitr=Httperror;
						syslogavailable=Httperror;
		  			}
		  			else if(driver.getPageSource().contains(sslerror))
		  			{
		  				System.out.println("Server is having SSL Error "+ (eu.getCellData(i,0)));
						customernameitr=sslerror;
						countitr=sslerror;
						syslogavailable=sslerror;
		  			}
		  			
		  			else if(driver.getPageSource().contains(" You are not authorized to access this account"))
		  			{
		  				System.out.println("Access Error for the user "+ (eu.getCellData(i,0)));
						customernameitr=accesserror;
						countitr=accesserror;
						syslogavailable=accesserror;
		  			}
		  			
		  			else {
		  			
		  			
		  				Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		  				WebElement custname=fwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[id=\"customer-label\"]")));
		  				customernameitr=custname.getText();
		  				if(customernameitr.isBlank())
		  				{
		  					Thread.sleep(5000);
		  					customernameitr=driver.findElement(By.cssSelector("p[id=\"customer-label\"]")).getText();
		  					System.out.println("Customer Name in customernameitr if : "+customernameitr);
		  				}
		  				else
		  					{
		  					customernameitr=driver.findElement(By.cssSelector("p[id=\"customer-label\"]")).getText();
		  					System.out.println("Customer Name  : "+customernameitr);
		  					}
		  			
		  			
		  		

		  			//This block handles the input - Search is waiting for input...
		  			WebElement uniquedeviceitr = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id=\"element7\"]")));
		  			countitr= uniquedeviceitr.getText();
				
		  				if (((countitr.matches(swi)) | countitr.matches(wtfd) | (countitr.isBlank())))
		  					{
		  						Thread.sleep(13000);
		  						WebElement uniquecount1itr= uniquedeviceitr.findElement(By.cssSelector("text[class=\"single-result\"]"));
		  						countitr=uniquecount1itr.getText();
		 
		  						if((countitr.matches(swi)) | (countitr.matches(wtfd) | (countitr.isBlank())) )
		  						{
		  							Thread.sleep(15000);
		  							WebElement uqcount=uniquedeviceitr.findElement(By.cssSelector("text[class=\"single-result\"]"));
		  							countitr=uqcount.getText();
		  							System.out.println("The unique device count 2nd wait "+uqcount.getText());
		  								if((countitr.matches(swi)) | (countitr.matches(wtfd) | (countitr.isBlank())) )
		  								{
		  									Thread.sleep(12000);
		  			  						WebElement uniquecount1itr1= uniquedeviceitr.findElement(By.cssSelector("text[class=\"single-result\"]"));
		  			  						countitr=uniquecount1itr1.getText();
		  			  					System.out.println("The unique device count 3rd wait "+countitr);
		  								}
		  								else 
		  								{
		  									System.out.println("the unique device count : "+countitr);
		  								}
		  							
		  						}
		  						else {
		  							System.out.println("the unique device count : "+countitr);
		  							
		  						}
		  					}
		  				else
		  					{
		  						System.out.println("The Unique device count is :"+countitr);
		  					}
		
		  				
									   
		  				WebElement syslogitr = driver.findElement(By.cssSelector("div[id=\"element30\"]"));
		  				String nrf="No results found.";
		  				List<WebElement> syslogsitr =syslogitr.findElements(By.cssSelector("tr[data-view=\"views/shared/results_table/ResultsTableRow\"]"));
		  				if((syslogitr.getText().matches(swi))|(syslogitr.getText().matches(wtfd)))
		  				{
		  					Thread.sleep(7000);
		  					syslogitr=driver.findElement(By.cssSelector("div[id=\"element30\"]"));
		  					if((syslogitr.getText().matches(swi))|(syslogitr.getText().matches(wtfd))|(syslogitr.getText().isBlank()))
		  					{
		  						System.out.println("Performance Issue in syslog");
		  						Thread.sleep(10000);
		  						syslogitr=driver.findElement(By.cssSelector("div[id=\"element30\"]"));
		  						System.out.println("Syslog for customer "+customernameitr+" "+syslogsitr.size());
		  							if((syslogitr.getText().matches(swi))|(syslogitr.getText().matches(wtfd))|(syslogitr.getText().isBlank()))
		  							{
		  								syslogavailable="Loading issue - UI is"+syslogitr.getText();
		  								System.out.println("Syslog is taking too much time to load");
		  							}
		  						
		  							else if (syslogitr.getText().matches(nrf)) 
		  							{
		  								System.out.println("Syslog not Available for customer "+customernameitr);
		  								syslogavailable="No Record Found";
		  							}
		  							
		  							else if(syslogitr.getText().isBlank())
			  						{
			  							syslogavailable="Loading issue";
			  							System.out.println(" Issue in Loading Syslog"+syslogitr.getText());
			  						}	
		  							
		  							else if (syslogsitr.size()>0)
		  							{
		  								syslogavailable="Available";
			  							System.out.println("The No. of Syslog Available for customer: "+customernameitr+ " is" +syslogsitr.size() );
		  							}
		  							
		  							else 
		  			  				{
		  			  					syslogavailable="loading issue";
		  			  					System.out.println(" Issue in Loading Syslog"+syslogitr.getText());
		  			  				}
		  						}
		  							
		  					else if(syslogitr.getText().matches(nrf)) 
		  						{
		  							System.out.println("Syslog not Available for customer "+customernameitr);
	  								syslogavailable="No Record Found";
	  							}
		  						
		  					else if(syslogitr.getText().isBlank())
		  						{
		  							syslogavailable="Loading issue";
		  							System.out.println(" Issue in Loading Syslog"+syslogitr.getText());
		  						
		  						}	
		  							
		  					else if(syslogsitr.size()>0)
		  						{
		  							syslogavailable="Available";
		  							System.out.println("The No. of Syslog Available for customer: "+customernameitr+ " is" +syslogsitr.size() );
		  						}
		  					else 
		  						{
				  					syslogavailable="loading issue";
				  					System.out.println(" Issue in Loading Syslog"+syslogitr.getText());
				  				
		  						}
		  						
		  					
		  				}
		  				else if(syslogitr.getText().matches(nrf)) 
		  					{
		  						System.out.println("Syslog not Available for customer "+customernameitr);
		  						syslogavailable="No Record Found";
		  					}

		  				
		  				else if(syslogitr.getText().isBlank())
  						{
  							syslogavailable="Loading issue";
  							System.out.println(" Issue in Loading Syslog"+syslogitr.getText());
  						}	
		  				
		  				else if ((syslogsitr.size()>0))
	  					{
	  						System.out.println("The Syslog available in GM page for customer "+customernameitr+ " : " +syslogsitr.size());
	  						syslogavailable="Available";
	  					}	
		  				else 
		  				{
		  					syslogavailable="loading issue";
																																																																																	  
		  					System.out.println(" Issue in Loading Syslog"+syslogitr.getText());
																   
		  				}
	   
			  
		 
															   
																																		
		  
	   

		  				WebElement globalfilter = driver.findElement(By.cssSelector("span[class=\"icon-chevron-down arrow \"]"));
		  				globalfilter.click();
		  				Thread.sleep(4000);
		  				WebElement customerdropdown=driver.findElement(By.cssSelector("span[class=\"icon-chevron-down accordion-filter customer-caret\"]"));
		  				customerdropdown.click();
		  				Thread.sleep(1000);
		  				WebElement customerpanel=driver.findElement(By.cssSelector("div[class=\"customer-panel show-panel\"]"));
		  				List<WebElement> customers=customerpanel.findElements(By.cssSelector("span[class=\"list-item\"]"));
		  				customercount= customers.size();
		  				if(customercount>1)
		  				{
		  					System.out.println("The customer size is"+customercount);
		  				}
		  				
		  				else {
		  					System.out.println("The customer size is 1");
		  				}

		  		 }
		  		  		  		}
		  		 catch (NoSuchElementException e) 
		  		 {
		  			e.printStackTrace();
		  			 System.out.println("Issue in accessing the URL trying one more time" +eu.getCellData(i, 0));
		  			 Cpsanity cp =new Cpsanity();
		  			cp.rerun(driver, eu.getCellData(i, 0));
		  		
		  		 }		
		  		 catch (TimeoutException e)
		  		 {
		  			 e.printStackTrace();
		  			 Cpsanity cp =new Cpsanity();
		  			 cp.rerun(driver, eu.getCellData(i, 0));
		  		
		  		 }
		  		catch(Exception e)
		  		{
		  			System.out.println("Issue in accessing URL"+eu.getCellData(i, 0));
		  			e.printStackTrace();
		  		}
		  		finally
		  		{
		  			String servererrormsg="error while Loading";
		  			String serversuccessmsg="Success";
		  			String ssl="SSL ERROR";
		  			String access="Access Error";
		  			if ((customernameitr==null)|(countitr==null)|(syslogavailable==null))
			  		{
		  				eu.setCellValue(i, 4, servererrormsg, excelpath);
			  			System.out.println("Try accessing the link manually "+eu.getCellData(i, 0));
			  			
			  		}	
		  			else {
		  				eu.setCellValue(i, 1, customernameitr, excelpath);
				  		eu.setCellValue(i, 2, countitr, excelpath);
				  		eu.setCellValue(i, 3, syslogavailable, excelpath);
				  		eu.setCellValue(i, 5, String.valueOf(customercount), excelpath);
				  		
				  		if(customernameitr.matches(Httperror)|countitr.matches(Httperror)|syslogavailable.matches(Httperror))
				  		{
				  			eu.setCellValue(i, 4, Httperror, excelpath);
				  		}
				  		else if(customernameitr.matches(saml)|countitr.matches(saml)|syslogavailable.matches(saml))
				  		{
				  			eu.setCellValue(i, 4, saml, excelpath);
				  		}
				  		else if(customernameitr.isBlank() &  countitr.isBlank() & syslogavailable.isBlank())
				  		{
				  			eu.setCellValue(i, 4, servererrormsg, excelpath);
				  		}
				  		else if(customernameitr.matches(sslerror)|countitr.matches(sslerror)|syslogavailable.matches(sslerror))
				  		{
				  			eu.setCellValue(i, 4, ssl, excelpath);
				  		}
				  		else if(customernameitr.matches(accesserror)|countitr.matches(accesserror)|syslogavailable.matches(accesserror))
				  		{
				  			eu.setCellValue(i, 4, access, excelpath);
				  		}
				  		else 
				  		{
				  			eu.setCellValue(i, 4, serversuccessmsg, excelpath);
				  		}
		  			}
		  		}
	}
}
				
	 //clicking the help button
  	@Test(priority=3,enabled=false)
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
  	
  	
  	
  	@Test (priority=4,enabled = false)
  	public void umsanity() throws InterruptedException, IOException
  	{
  		try {
  			System.out.println("Running Bci Usage metrics");
  	  		driver.get(umurl);
  	  		Thread.sleep(7000);
  			String actualTitle = driver.getTitle();
  	  	  	System.out.println(actualTitle);
  		
  			 if(actualTitle.matches("Cisco")||actualTitle.matches("Log In to Cisco"))
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
    	 	 
  	  	  	}
  		
  		Thread.sleep(60000);
  		WebDriverWait wait = new WebDriverWait(driver, 60);
  		WebElement usertable = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"panel7\"]/div/h2")));
  		JavascriptExecutor js = (JavascriptExecutor) driver;
  		js.executeScript("arguments[0].scrollIntoView();", usertable);
  		Actions ac = new Actions(driver);
  		ac.moveToElement(usertable);
  		Thread.sleep(5000);
  		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  		String currentDir = System.getProperty("user.dir");
  		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
  		WebElement umuserfilter = usertable.findElement(By.xpath("//*[@id=\"statistics\"]/table/thead/tr/th[5]/a"));
  		umuserfilter.click();
  		Thread.sleep(10000);

  			List<WebElement> userrow = driver.findElements(By.xpath("/html/body/div[2]/div/div[3]/div[8]/div/div/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div/div/table/tbody/tr"));
  			System.out.println(userrow.size());
	  		WebElement usertablerow= driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[8]/div/div/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div/div/table/tbody"));
	  		List<WebElement> usertablerow2 = driver.findElements(By.xpath("/html/body/div[2]/div/div[3]/div[8]/div/div/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div/div/table/tbody/tr"));
	  		Iterator<WebElement> itr=usertablerow2.iterator();
	  		System.out.println();
	  		Excelutil eu= new Excelutil();
		  	eu.setExcelFile(excelpath,UMsheetname);
	  	
	  	  for (int i =0;i<usertablerow2.size();i++)
	  	  		{
  			 eu.setCellValue(i,0,usertablerow2.get(i).getText(),excelpath);
  			 System.out.println(usertablerow2.get(i).getText());
  			 
	  	  		}
  			}
  		catch(TimeoutException e)
  		{
  			
  	  		WebDriverWait wait = new WebDriverWait(driver, 60);
  	  		WebElement usertable = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"panel7\"]/div/h2")));
  	  		JavascriptExecutor js = (JavascriptExecutor) driver;
  	  		js.executeScript("arguments[0].scrollIntoView();", usertable);
  	  		Actions ac = new Actions(driver);
  	  		ac.moveToElement(usertable);
  	  		Thread.sleep(5000);
  	  		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  	  		String currentDir = System.getProperty("user.dir");
  	  		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
  	  		WebElement umuserfilter = usertable.findElement(By.xpath("//*[@id=\"statistics\"]/table/thead/tr/th[5]/a"));
  	  		umuserfilter.click();
  	  		Thread.sleep(10000);

  	  		//WebElement tableuserlevel = driver.findElement(By.cssSelector("div[id=\"element5\"]"));
  	  		
  	  		List<WebElement> userrow = driver.findElements(By.xpath("/html/body/div[2]/div/div[3]/div[8]/div/div/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div/div/table/tbody/tr"));
  	  	
  	  		//System.out.println(tableuserlevel.getText());
  	  		System.out.println(userrow.size());
  	  		
  		  		WebElement usertablerow= driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[8]/div/div/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div/div/table/tbody"));
  		  		List<WebElement> usertablerow2 = driver.findElements(By.xpath("/html/body/div[2]/div/div[3]/div[8]/div/div/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div/div/table/tbody/tr"));
  		  		 Iterator<WebElement> itr=usertablerow2.iterator();
  		  		 System.out.println();
  		  		 Excelutil eu= new Excelutil();
  			  	 eu.setExcelFile(excelpath,UMsheetname);
  		
  		  	  for (int i =0;i<usertablerow2.size();i++)
  	  		 {
  	  			 
  	  			 
  	  			 
  	  			 eu.setCellValue(i,0,usertablerow2.get(i).getText(),excelpath);
  	  			 System.out.println(usertablerow2.get(i).getText());
  	  			 
  	  		 }
  			
  		}
  		
    			   		
  	}
  	
  	@Test(priority=5,enabled=false)
  	public void buildverification() throws InterruptedException, IOException
  	{
  		driver.get("https://bva1-bci.cisco.com/en-US/app/good_morning/search?q=%7C%20rest%20servicesNS%2Fnobody%2Fsystem%2Fapps%2Flocal%0A%7C%20search%20core%3D0%0A%7C%20table%20label%20build%20version&display.page.search.mode=smart&dispatch.sample_ratio=1&workload_pool=&earliest=-24h%40h&latest=now&display.general.type=statistics&sid=1685876199.1571");
  		Thread.sleep(7000);
  		 Excelutil eu= new Excelutil();
	  	  eu.setExcelFile(excelpath,sheetname);
	  	  //iterating the url values
	  	 for(int i=1;i<=eu.getRowCountInSheet();i++)
	     {
	  		 try {
	  		 System.out.println("the total number of Links available is "+eu.getRowCountInSheet());
	  		 int totalrow =eu.getRowCountInSheet();
	  		 int balrow = totalrow-i;
	  		 System.out.println("No.of customers left :"+balrow);
	  		 driver.get(eu.getCellData(i, 0)+"?q=%7C%20rest%20servicesNS%2Fnobody%2Fsystem%2Fapps%2Flocal%0A%7C%20search%20core%3D0%0A%7C%20table%20label%20build%20version&display.page.search.mode=smart&dispatch.sample_ratio=1&workload_pool=&earliest=-24h%40h&latest=now&display.general.type=statistics&sid=1685876199.1571");
	  		 Thread.sleep(6000);
	  		 System.out.println(eu.getCellData(i, 0)+"?q=%7C%20rest%20servicesNS%2Fnobody%2Fsystem%2Fapps%2Flocal%0A%7C%20search%20core%3D0%0A%7C%20table%20label%20build%20version&display.page.search.mode=smart&dispatch.sample_ratio=1&workload_pool=&earliest=-24h%40h&latest=now&display.general.type=statistics&sid=1685876199.1571");
	  		 WebElement tabledata = driver.findElement(By.cssSelector("div[class=\"tab-content\"]"));
	  		 WebElement tablerows=tabledata.findElement(By.tagName("tbody"));
	  		 List <WebElement> rowtable = tablerows.findElements(By.tagName("tr"));
	  		 int rowsize = rowtable.size();
	  		 
	  		 
	  		 
	  		 
	  		 for(int j=0;j<rowsize;j++)
	  		 {
	  			 System.out.println(rowtable.get(j).getText());
	  			 if(rowtable.get(j).getText().contains("AS-Data"))
	  			 {
	  				String as_dataversion=rowtable.get(j).getText(); 
	  				eu.setCellValue(i, 5, as_dataversion, excelpath);
	  			 }
	  			 else if(rowtable.get(j).getText().contains("Good Morning"))
	  			 {
	  				String gm=rowtable.get(j).getText(); 
	  				eu.setCellValue(i, 4, gm, excelpath); 
	  			 }
	  			 else if(rowtable.get(j).getText().contains("NOS KPI Portal"))
	  			 {
	  				String kpi=rowtable.get(j).getText(); 
	  				eu.setCellValue(i, 6, kpi, excelpath); 
	  			 }
	  			 
	  			 else
	  			 {
	  				 
	  			 }
	  			 
	  		 }
	  		 
	  		 }
	  		 
	  		 catch(Exception e)
	  		 {
	  			 e.printStackTrace();
	  		 }
	     }
  		
  		//System.out.println(tablerows.getText());
  	}
  	
  	@Test(priority=6,enabled=false)
  	public void oktatest () throws InterruptedException, IOException {
  		 
  		 eu.setExcelFile(excelpath,sheetname); 
  		for(int i=1;i<eu.getRowCountInSheet();i++)
  		{
  			System.out.println("The no.of total customers"+eu.getRowCountInSheet());
  			System.out.println("Current customers number"+i);
  			String customername = null;
  			String value=null;
  			//System.setProperty("webdriver.edge.driver", "C:\\Users\\sibmanic\\OneDrive - Cisco\\Desktop\\Automation setup\\msedgedriver.exe");
  			
  			//System.setProperty("webdriver.edge.driver", "C:\\Users\\mumariap\\OneDrive - Cisco\\Documents\\OnPrem\\Drivers\\msedgedriver.exe");
  			System.setProperty("webdriver.edge.driver", "Dependencies//" +edgeDriver+".exe");
  		  	WebDriver driver2= new EdgeDriver();
  		  	driver2.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  		  	driver2.manage().window().maximize();
  			
  			try {
  			Wait <WebDriver> fwait = new FluentWait<>(driver2).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
  			driver2.manage().deleteAllCookies();
  			eu.setExcelFile(excelpath,sheetname);
  			driver2.get(eu.getCellData(i, 0));
  		 
    		  
    		  if(constants.username.contains("@gmail.com"))
    		  {
    			String auth=driver2.getCurrentUrl();
    	  	  System.out.println(auth);
    	  	  try {
    	  		  Thread.sleep(1500);
    	  	  int alertboxispresent =driver2.findElements(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).size();
    	  	  if(alertboxispresent>0)
    	  	  {
    	  		  driver2.findElement(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).click();
    	  		System.out.println("Alert window closed");
    	  	  }}
    	  	  catch (Exception e)
    	  	  {
    	  		  e.printStackTrace();
    	  	  }
    	  	  finally {
    	  	  WebElement oktausername=fwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[id=\"idp-discovery-username\"]")));
    	  	  //WebElement oktausername=driver2.findElement(By.cssSelector("input[id=\"idp-discovery-username\"]"));
    	 	  oktausername.click();
    	 	  oktausername.sendKeys(constants.username);
    	 	  WebElement oktasubmit=driver2.findElement(By.cssSelector("input[id=\"idp-discovery-submit\"]"));
    	 	  oktasubmit.click();
    	 	  Thread.sleep(3000);
    	  	  WebElement oktapassword=driver2.findElement(By.cssSelector("input[id=\"okta-signin-password\"]"));
  	 	  oktapassword.click();
  	 	  oktapassword.sendKeys(constants.pwd);
  	 	  WebElement oktapage2submit=driver2.findElement(By.cssSelector("input[id=\"okta-signin-submit\"]"));
   	 	  oktapage2submit.click();
  	 	  
    		  }
    		  }
    		  
    		  else if (constants.username.contains(".gen"))
    		  {
    			  String auth=driver.getCurrentUrl();
        	  	  System.out.println(auth);
        	  	  
        	  	  int alertboxispresent =driver2.findElements(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).size();
        	  	  if(alertboxispresent>0)
        	  	  {
        	  		  driver2.findElement(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).click();
        	  		System.out.println("Alert window closed");
        	  	  }
        	  	  WebElement oktausername=fwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[id=\"idp-discovery-username\"]")));
        	  	  //WebElement oktausername=driver2.findElement(By.cssSelector("input[id=\"idp-discovery-username\"]"));
        	 	  oktausername.click();
        	 	  oktausername.sendKeys(constants.username);
        	 	  WebElement oktasubmit=driver2.findElement(By.cssSelector("input[id=\"idp-discovery-submit\"]"));
        	 	  oktasubmit.click();
        	 	  WebElement singlesignonemail = fwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type=\"email\"]")));
        	 	  singlesignonemail.sendKeys(constants.username);
        	 	  driver2.findElement(By.cssSelector("button[type=\"button\"]")).click();
        	 	  WebElement singlesignonpwd = fwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type=\"password\"]")));
       	 	  		singlesignonpwd.sendKeys(constants.pwd);
       	 	  		//Thread.sleep(3000); 
//       	 	  		Robot robot = new Robot();
////       	 	  		Actions action = new Actions(driver2);
////       	 	  		action.moveByOffset(772, 772).click().perform();
//       	 	  		robot.keyPress(KeyEvent.VK_ESCAPE);  // Simulate pressing the Escape key to dismiss the dialog
//       	 	  		robot.keyRelease(KeyEvent.VK_ESCAPE);
//       	 	  		System.out.println(driver2.getWindowHandles().size());
//       	 	  		String windowname =driver2.getWindowHandle();
//       	 	  		driver2.switchTo().window(windowname);
       	 	  		driver2.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        	 	  
    		  }
    		  
    		  else {
    	  //passing Username & Password
    	  String auth=driver2.getCurrentUrl();
    	  System.out.println(auth);
    	  int alertboxispresent =driver2.findElements(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).size();
  	  	  if(alertboxispresent>0)
  	  	  {
  	  		  driver2.findElement(By.cssSelector("button[class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]")).click();
  	  		System.out.println("Alert window closed");
  	  	  }
    	  
    	  WebElement oktausername=driver2.findElement(By.cssSelector("input[id=\"idp-discovery-username\"]"));
   	  oktausername.click();
   	  oktausername.sendKeys(constants.username);
   	  WebElement oktasubmit=driver2.findElement(By.cssSelector("input[id=\"idp-discovery-submit\"]"));
   	  oktasubmit.click();
   	  Thread.sleep(3000);
   	  if(driver2.findElement(By.cssSelector("h1[class=\"card--focusable-text\"]")).getText().matches("Single Sign-On"))
   	  {
   		  driver2.findElement(By.cssSelector("input[aria-label=\"Email Address\"]")).sendKeys(constants.username);
   		  driver2.findElement(By.cssSelector("button[type=\"button\"]")).click();
   		  Thread.sleep(16000);
   		  
   	  }
   	  else {
    	  WebElement element =driver2.findElement(By.id("userInput"));
    	  element.click();
    	  //element.sendKeys(constants.username);
    	  WebElement element1= driver2.findElement(By.id("login-button"));
    	  element1.click();
    	    WebElement element2=driver2.findElement(By.id("passwordInput"));
    	  element2.click();
    	  element2.sendKeys(constants.pwd);
    	  WebElement element3 = driver2.findElement(By.id("login-button"));
    	  element3.click();
    	  }
    		  }
    	  	
    		  Thread.sleep(6000);
    		  
    		  if(driver2.findElements(By.cssSelector("button[onclick=\"closebanner()\"]")).size()>0)
    		  {
    		  WebElement bannerclose = driver2.findElement(By.cssSelector("button[onclick=\"closebanner()\"]"));
    		  bannerclose.click();
    		  System.out.println("Banner closed");
    		  }
    		  
    		 WebElement customernameelement = fwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[id=\"customer-label\"]")));
    	 	
    		   customername=customernameelement.getText();
    			if(customername.isBlank())
    				{
    					Thread.sleep(5000);
    					customername=driver2.findElement(By.cssSelector("p[id=\"customer-label\"]")).getText();
    					System.out.println("Customer Name in if : "+customername);
    				}
    				else {
    					Thread.sleep(3000);
    					customername=driver2.findElement(By.cssSelector("p[id=\"customer-label\"]")).getText();
    					System.out.println("Customer Name in else : "+customername);
    				}
    				
    			WebElement uniquedevice = fwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id=\"element7\"]")));
    			
    			//Thread.sleep(20000);
    			String count= uniquedevice.getText();
    			WebElement uniquecount= uniquedevice.findElement(By.cssSelector("text[class=\"single-result\"]"));
    			value=uniquecount.getText();
    			System.out.println("The  unique device count is "+value);
    			String swi="Search is waiting for input...";
    			String wtfd="Waiting for data...";
    			
    			//This block handles the input - Search is waiting for input...
    			
    			if (((count.matches(swi)) | count.matches(wtfd) | (count.isBlank())))
    				{
    					Thread.sleep(12000);
    					WebElement uniquecount1= uniquedevice.findElement(By.cssSelector("text[class=\"single-result\"]"));
    					String value1=uniquecount1.getText();
    					if((value1.matches(swi)) | (value1.matches(wtfd)) | (value1.isBlank()) )
    					{
    						Thread.sleep(10000);
    						WebElement uqcount=uniquedevice.findElement(By.cssSelector("text[class=\"single-result\"]"));
    						System.out.println("The unique device count 2nd wait "+uqcount.getText());
    						value=uqcount.getText();
    					
    							if((value.matches(swi)) | (value.matches(wtfd)) | (value.isBlank()))
    								{
    								Thread.sleep(20000);
    								 uqcount=uniquedevice.findElement(By.cssSelector("text[class=\"single-result\"]"));
    								System.out.println("The unique device count 3rd wait "+uqcount.getText());
    								value=uqcount.getText();
    								}
    							else 
    							{
    								System.out.println("the unique device count : "+value1);
    								value=value1;
    							}
    					}
    					else {
    						System.out.println("the unique device count : "+value1);
    						value=value1;
    					}	
    				}
    			System.out.println(driver2.getCurrentUrl());
    			driver2.quit();
  		}
  	
  			catch(Exception e)
  	  	  	{
  	  	  		e.printStackTrace();
  	  	  	}
  			
  			finally {
  				eu.setCellValue(i,2,customername, excelpath);
  				eu.setCellValue(i, 3, value, excelpath);
  				
  			}
  			}
  		
  	}
  	
  	@Test (priority=7 , enabled = false)

  	public void runquery() throws IOException{
  		 eu.setExcelFile(excelpath,sheetname);
  		for(int i=1;i<=eu.getRowCountInSheet();i++)
	     {
  			try
  			{
  				
  				 driver.get(eu.getCellData(i, 0)+"/en-US/app/good_morning/search?s=%2FservicesNS%2Fnobody%2Fas_data%2Fsaved%2Fsearches%2Fuser_customer_mapping_lookup_population&display.page.search.mode=smart&dispatch.sample_ratio=1&workload_pool=&q=search%20index%3Dglobal-np%20sourcetype%3Dnetwork_filter%20earliest%3D-3d%40d%20%7C%20eval%20customerName%20%3D%20upper(cpyname)%20%7C%20rename%20subnetwork%20as%20cpyName%20%7C%20join%20cpyKey%20%5B%7C%20inputlookup%20kpi_customer.csv%20%7C%20eval%20actCustName%20%3D%20customerName%20%7C%20eval%20customerName%20%3D%20upper(customerName)%20%7C%20table%20cpyKey%2CactCustName%2CisMultipleMarket%20%5D%7C%20dedup%20username%2CcpyKey%20%7C%20rename%20actCustName%20as%20customerName%7C%20eval%20has_data%3D2%7C%20inputlookup%20append%3Dt%20kvstore_user_customer_mapping%7C%20eval%20has_data%3Dif(has_data%3D%3D2%2Chas_data%2C1)%20%7C%20eventstats%20max(has_data)%20as%20max_data%20%7C%20where%20has_data%3Dmax_data%20%7C%20eval%20customerName%20%3D%20replace(customerName%2C\"%27\"%2C\"\")%20%7C%20table%20cpyKey%2C%20customerName%2C%20indexString%2C%20cpyName%2C%20username%2C%20isMultipleMarket%7C%20outputlookup%20kvstore_user_customer_mapping%7Csearch%20username%3D\"00ubeq4lilQ9emGJI5d7\"&earliest=&latest=&display.general.type=statistics&sid=1695275213.1940&display.page.search.tab=statistics");
  				 Wait <WebDriver> fwait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
  				 WebElement customernameelement = fwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td[data-cell-index=\"1\"]")));
  				 System.out.println(customernameelement.getText());
  				 eu.setCellValue(i, 6, customernameelement.getText(), excelpath);
  				
  			
  			}
  			
  			catch(Exception e)
  			{
  				e.printStackTrace();
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
