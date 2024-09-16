package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccount;
import TestBase.BaseClass;

public class TC001SigninTest extends BaseClass {
	
@Test(groups={"Sanity","Master"})

public void SignIn()
{
logger.info("******Starting Login Process******");
try
{
	HomePage hp = new HomePage(driver);
	logger.info("******Open sign-in page....");
			hp.clickLogin();
			
	LoginPage lp = new LoginPage(driver);
	logger.info("*****Input Login details now.....");
	lp.setUserId(p.getProperty("email2"));
	lp.setPassword(p.getProperty("password2"));
	lp.setSubmit();
	
	
	String title = lp.msgConfirmationSignin();
	System.out.println(title);

logger.info("Validating expected message....");
String confrmMsg=lp.msgConfirmationSignin();
if(confrmMsg.equals("TOURS SUGGESTED FOR YOU"))
{
	Assert.assertTrue(true);
}
else
{
	Assert.assertTrue(false);
	logger.debug("Debug logs....");
	logger.error("Test failed...");
}
MyAccount acc = new MyAccount(driver);
boolean chkTitle = acc.verifyMyAccountPage();

Assert.assertTrue(chkTitle);
}
catch(Exception e)
{
Assert.fail();
}
logger.info("********TC001 Finished LogIn Test***********");
}
}