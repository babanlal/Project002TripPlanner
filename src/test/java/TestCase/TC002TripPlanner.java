package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.RegistrationPage;
import TestBase.BaseClass;

public class TC002TripPlanner extends BaseClass {
	
@Test(groups={"Regression","Master"})

public void VerifyRegPage()
{
	logger.info("******Starting Registration Process******");
	try {
		
	HomePage hp = new HomePage(driver);
	hp.clickRegister();
	
	RegistrationPage Rp = new RegistrationPage(driver);
	Rp.setState();
	Rp.setDistrict();
	Rp.setName(randomeString(6).toUpperCase());
	Rp.setAge(randomeNumeric(2));
	Rp.setPhoneNum(randomeNumeric(10));
	
	String userName = (randomeAlphaNum(3,3)+"@gmail.com");
	Rp.setEmail(userName);
	System.out.println(userName);
	
	String password = randomeAlphaNum(4,4);
	System.out.println(password);
	Rp.setPass(password);
	
	Rp.clickReg();
	
	String title1=Rp.msgConfirmationReg();
	System.out.println(title1);
	
	logger.info("Validating expected message....");
	String confrmMsg1=Rp.msgConfirmationReg();
	if(confrmMsg1.equals("TRIP PLANNER"))
	{
		Assert.assertTrue(true);
	}
	else
	{
		Assert.assertTrue(false);
		logger.debug("Debug logs....");
		logger.error("Test failed...");
	}
	}

	catch(Exception e)
	{
		Assert.fail();
	}

	logger.info("********TC001 Finished Registration Test***********");
	}
}
