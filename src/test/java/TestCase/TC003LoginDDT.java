package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccount;
import TestBase.BaseClass;
import Utilities.DataProviders;

public class TC003LoginDDT extends BaseClass
{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="DataDriven")
	public void verifyLoginDDT(String fuser,String fpass,String exp) throws InterruptedException
	{
		logger.info("******T003_Starting Login validation test*******");	

		try
		{
		logger.info("******Open sign-in page....");
		
		HomePage hp = new HomePage(driver);
		hp.clickLogin();
					
		LoginPage lp = new LoginPage(driver);
		lp.setUserId(fuser);
		lp.setPassword(fpass);
		lp.setSubmit();
		
		MyAccount acc = new MyAccount(driver);
		boolean chkTitle = acc.verifyMyAccountPage();
		Assert.assertTrue(chkTitle);
		
		/*Data is valid = login success -test pass - logout
		 * 		     	- login failed - test fail
		 * 
		 * data is invalid - login success - test fail - logout
		                   - login fail - test pass
		 */
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(chkTitle==true)
			{
				acc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) 
		{
			if(chkTitle==true)
			{
				acc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		
			catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("******Finished T003_Login validation test*******");
	}
	
	}