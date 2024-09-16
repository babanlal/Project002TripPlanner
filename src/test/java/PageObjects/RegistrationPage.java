package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {
	
	public RegistrationPage (WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//a[normalize-space()='TRIP PLANNER']")
WebElement verifyRegPage;
	
@FindBy(xpath="//select[@name='state']")
WebElement state;

@FindBy(xpath="//select[@name='district']")
WebElement district;

@FindBy(xpath="//div[3]//input[1]")
WebElement name;

@FindBy(xpath="//div[4]//input[1]")
WebElement age;

@FindBy(xpath="//div[5]//input[1]")
WebElement phoneNum;

@FindBy(xpath="//div[6]//input[1]")
WebElement email;

@FindBy(xpath="//input[@id='contact_name']")
WebElement password;

@FindBy(xpath="//input[@name='register']")
WebElement clickRegister;

public String msgConfirmationReg()
{
	try {
		return(verifyRegPage.getText());
	}
	catch(Exception e) {
	return(e.getMessage());
}
	}

public void setState()
{
	Select a = new Select(state);
	a.selectByIndex(1);
}

public void setDistrict()
{
	Select b = new Select(district);
	b.selectByIndex(12);
}

public void setName(String fname)
{
	name.sendKeys(fname);
}

public void setAge(String fage)
{
	age.sendKeys(fage);
}
public void setPhoneNum(String fpno)
{
	phoneNum.sendKeys(fpno);
}
public void setEmail(String femail)
{
	email.sendKeys(femail);
}
public void setPass(String fpass)
{
	password.sendKeys(fpass);
}
public void clickReg()
{
	clickRegister.click();
}

}