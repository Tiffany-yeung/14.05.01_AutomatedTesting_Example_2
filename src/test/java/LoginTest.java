import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

public class LoginTest {
	private WebDriver driver;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/Users/Tiffany/Development/Chrome_Driver/chromedriver");
		driver = new ChromeDriver();
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Test
	public void methodTest() throws InterruptedException {
		
		driver.get("http://thedemosite.co.uk/addauser.php");

		
		WebElement selectLogin = driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td[1]/blockquote/blockquote[2]/blockquote"));

		//selects the first index of "The username: ", need to +14 since the string has 14 characters
		//cannot use lastIndexOf because it will select the first index of the last occurence of that string
		//i.e. bread hello bread, lastIndexOf will select the first index of 2nd bread, i.e. 13
		int loginStart = selectLogin.getText().indexOf("The username: ") + 14; //output: 14
		
		//selects the first index of "The password: ", i.e. where password ends
		int loginEnd = selectLogin.getText().indexOf("The password: "); //output: 23
		
		String loginString = selectLogin.getText().substring(loginStart, loginEnd);
		
		int passwordStart = selectLogin.getText().indexOf("The password: ") + 14; //output: 14
		
		//selects length of the whole string which is also the index of where password ends
		int passwordEnd = selectLogin.getText().length(); //output: 37
		
		String passwordString = selectLogin.getText().substring(passwordStart, passwordEnd);
		
		
		driver.get("http://thedemosite.co.uk/login.php");
		
		WebElement login = driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
		login.sendKeys(loginString);
		
		WebElement password = driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
		password.sendKeys(passwordString);
		
		WebElement testLoginButton = driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
		testLoginButton.submit();
		Thread.sleep(5000);
	}
}
