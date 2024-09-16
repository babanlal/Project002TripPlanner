package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage{
	
public MyAccount(WebDriver driver)
	{
		super(driver);
	}

@FindBy(xpath="//a[normalize-space()='Trip Planner']")
WebElement title3;

@FindBy (xpath="//a[normalize-space()='Log out']")
WebElement logout;

public void clickLogout()
{
	logout.click();
}

public boolean verifyMyAccountPage()
{
	try
	{
		return(title3.isDisplayed());
	}
	catch(Exception e)
	{
		return false;
	}
}
}
