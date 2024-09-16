package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage (WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//h2[@class='tm-section-title']")
WebElement verifySigninPage;

@FindBy(xpath="//input[@id='contact_email']")
WebElement user;

@FindBy(xpath="//input[@id='contact_name']")
WebElement password;

@FindBy(xpath="//input[@name='login']")
WebElement submit;

public String msgConfirmationSignin()
{
	try {
		return(verifySigninPage.getText());
	}
	catch(Exception e) {
	return(e.getMessage());
}
	}
public void setUserId(String fuser)
{
	user.sendKeys(fuser);
}
public void setPassword(String fpass)
{
	password.sendKeys(fpass);
}

public void setSubmit()
{
	submit.click();
}
}
