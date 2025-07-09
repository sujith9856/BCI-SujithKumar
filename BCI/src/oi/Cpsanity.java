package oi;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cpsanity extends NewTest 
{
	String value=null;
	
	String nrf="No results found.";

	NewTest nt= new NewTest();
	 WebDriver driver2 = driver;
	
	public void rerun(WebDriver driver2,String cpurl) throws InterruptedException, NullPointerException
	{
		
		
		String customername=null;
		try {
			String swi="Search is waiting for input..."; 
  			String wtfd="Waiting for data...";
			driver.navigate().to(cpurl);
			
			System.out.println("Refreshing URL :"+driver.getCurrentUrl());
			Thread.sleep(20000);
				WebDriverWait wait = new WebDriverWait(driver, 30); 
				//Searching for customer name				
				customername=driver.findElement(By.cssSelector("p[id=\"customer-label\"]")).getText();
				if(customername.isBlank())
					{
					Thread.sleep(5000);
					customername=driver.findElement(By.cssSelector("p[id=\"customer-label\"]")).getText();
					System.out.println("Customer Name in if : "+customername);
					
					nt.countandsyslog[0]=customername;
					}
				else 
					{
					customername=driver.findElement(By.cssSelector("p[id=\"customer-label\"]")).getText();
					nt.countandsyslog[0]=customername;
					System.out.println("Customer Name in if : " +customername);
					}
				
				//Searching for Unique devices 
				WebElement uniquedevice = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id=\"element7\"]")));
				WebElement uniquecount= uniquedevice.findElement(By.cssSelector("text[class=\"single-result\"]"));
				value=uniquecount.getText();
				System.out.println("The unique count before logic check is "+value);
			if (value.matches(swi) | value.matches(wtfd) | value.isBlank())
				{
				Thread.sleep(25000);
				uniquecount=uniquedevice.findElement(By.cssSelector("text[class=\"single-result\"]"));
				value=uniquecount.getText();
				System.out.println("the unique device count in cpsanitywait" +value);
				nt.countandsyslog[1]=value;
				}
			else
				{

					System.out.println("the unique device count in cpsanitywait" +value);
					nt.countandsyslog[1]="Error in loading";
				}
			
			WebElement syslog = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id=\"element30\"]")));
			Thread.sleep(10000);
			List<WebElement> syslogs =syslog.findElements(By.cssSelector("tr[data-view=\"views/shared/results_table/ResultsTableRow\"]"));
			System.out.println("The Syslog available in GM page for Customer "+customername +syslogs.size());
			if((syslog.getText().matches(swi))|(syslog.getText().matches(wtfd)))
						{
					Thread.sleep(15000);
					syslog = driver.findElement(By.cssSelector("div[id=\"element30\"]"));
						if(syslog.getText().matches(nrf))
							{
							nt.countandsyslog[2]="No Record Found";
							}
						else if((syslog.getText().matches(swi))|(syslog.getText().matches(wtfd)))
							{
							Thread.sleep(10000);
							syslog =  driver.findElement(By.cssSelector("div[id=\"element30\"]"));
								if(syslog.getText().matches(nrf))
									{
										System.out.println("No Record Found");
										nt.countandsyslog[2]="No Record Found";
									}
								else if(syslogs.size()>0)
									{
									System.out.println("Available");
									nt.countandsyslog[2]="Available";
									}
								else 
									{
									System.out.println("Syslog is taking too much time to load");
									nt.countandsyslog[2]="Loading issue";
								
									}
							}
						}
						else if(syslogs.size()>0)
							{
								
								System.out.println("Syslog is available");
								nt.countandsyslog[2]="Available";
							}
						else if (syslog.getText().matches(nrf))
							{
								
								System.out.println("No Record Found");
								nt.countandsyslog[2]="No Record Found";
							}
						else
						{
							System.out.println("Syslog is taking too much time to load");
							nt.countandsyslog[2]="Loading issue";
						}
		}
			
					
			catch(TimeoutException e)
					{
			e.printStackTrace();
			System.out.println("This Url is having issue in accessing in refreshing" +cpurl);
					}
			catch(NoSuchElementException e)
			{
			e.printStackTrace();
			System.out.println("Loading issue "+cpurl);
			}
			catch(Exception e)
			{
			e.printStackTrace();
			System.out.println("Try Manually"+cpurl);
			}
			
					}
				}