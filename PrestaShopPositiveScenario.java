package automationhomework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrestaShopPositiveScenario {

	WebDriver driver;
	Faker faker;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		String url = "http://automationpractice.com";
		driver.get(url);
	}

	@Test(priority = 1)
	public void wrongCredentialTest() {
		driver.manage().window().fullscreen();
		String url = "http://automationpractice.com";
		driver.get(url);
		faker = new Faker();
		String email = faker.internet().emailAddress();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String pass = faker.internet().password();
		String address = faker.address().streetAddress();
		String city = faker.address().city();
		String phoneNumber = faker.phoneNumber().cellPhone();

		driver.findElement(By.className("login")).click();
		driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[1]"))
				.sendKeys(email);
		driver.findElement(By.id("SubmitCreate")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
		driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		driver.findElement(By.id("passwd")).sendKeys(pass);
		driver.findElement(By.id("address1")).sendKeys(address);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.xpath("//option[@value='46']")).click();
		driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("phone_mobile")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("(//span)[42]")).click();
		driver.findElement(By.linkText("Sign out")).click();
		driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[2]"))
				.sendKeys(email);
		driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[3]"))
				.sendKeys(pass);
		driver.findElement(By.id("SubmitLogin")).click();

		String actual = driver.findElement(By.xpath("(//span)[1]")).getText();
		String expected = firstName + " " + lastName;

		Assert.assertEquals(actual, expected);

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
