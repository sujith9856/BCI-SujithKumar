/**
 * 
 */
package oi;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author sibi
 *
 */
public class GlobalFilter extends NewTest {
	
	public void gf(WebDriver driver2) throws InterruptedException
	{
		this.driver=driver2;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement dataintegritymenu = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title=\"Data Integrity\"]")));
		dataintegritymenu.click();
		WebElement dataintegritydetails= wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Data Integrity Details")));
		dataintegritydetails.click();
		Thread.sleep(5000);
		WebElement filters=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class=\"ButtonStyles__StyledContentWrapper-sc-1py1q27-2 iyATXc\"]")));
		filters.click();
		List<WebElement> customers=driver.findElements(By.cssSelector("button[role=\"option\"]"));
		System.out.println("The Customer size is "+ customers.size());
		String customer = null;
		Thread.sleep(3000);
		WebElement gm=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-cid=\"view10117\"]")));
		gm.click();
		WebElement gm1=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dialog-view29788\"]/div[2]/ul/li[1]/a")));
		gm1.click();
		Thread.sleep(3000);
		for(int i=0;i<customers.size();i++)
		{
			System.out.println("Customer names are : "+customers.get(i).getText());
			customer=customers.get(i).getText();
			
		WebElement dataintegrity = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class=\"icon-chevron-down arrow \"]")));
		dataintegrity.click();
		Thread.sleep(10000);
		WebElement filter=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class=\"icon-chevron-down accordion-filter customer-caret\"]")));
		filter.click();
		WebElement searchbox=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id=\"customer-input-search\"]")));
		searchbox.sendKeys(customer);
		WebElement radiobutton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class=\"customer-panel show-panel\"]")));
		WebElement radiobuttonclick = radiobutton.findElement(By.cssSelector("input[id=\""+customer+"\"]"));
		radiobuttonclick.click();
		WebElement submit=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class=\"submit\"]")));
		submit.click();
		
	}
	}

}
