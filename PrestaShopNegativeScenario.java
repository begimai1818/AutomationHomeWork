package automationhomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrestaShopNegativeScenario {
	
	
	
	WebDriver driver;

	@BeforeMethod
	public void setUp() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.switchTo();
		driver.manage().window().fullscreen();
		Thread.sleep(1000);
	}

	@Test
	public void testWrongCredentials() {

		driver.get("http://automationpractice.com");
		driver.findElement(By.xpath("//a[@class='login']")).click();
		String email = "trying@gmail.com";
		driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[2]"))
				.sendKeys(email);
		driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[3]"))
				.sendKeys("123456");
		driver.findElement(By.xpath("//p[@class='submit']//span")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger']//ol//li")).getText(),
				"Authentication failed.");
	}

	@Test
	public void testInvalidEmail() {
		driver.get("http://automationpractice.com");
		driver.findElement(By.xpath("//a[@class='login']")).click();
		String email = "tryingmail.com";
		driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[2]"))
				.sendKeys(email);
		driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[3]"))
				.sendKeys("123456");
		driver.findElement(By.xpath("//p[@class='submit']//span")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger']//ol//li")).getText(),
				"Invalid email address.");
	}

	@Test
	public void testBlankEmail() {
		driver.get("http://automationpractice.com");
		driver.findElement(By.xpath("//a[@class='login']")).click();
		String email = "";
		driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[2]"))
				.sendKeys(email);
		driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[3]"))
				.sendKeys("123456");
		driver.findElement(By.xpath("//p[@class='submit']//span")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger']//ol//li")).getText(),
				"An email address required.");
	}

	@Test
	public void testBlankPassword() {
		driver.get("http://automationpractice.com");
		driver.findElement(By.xpath("//a[@class='login']")).click();
		String email = "trying@gmail.com";
		driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[2]"))
				.sendKeys(email);
		driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[3]")).sendKeys("");
		driver.findElement(By.xpath("//p[@class='submit']//span")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger']//ol//li")).getText(),
				"Password is required.");

	}

	@AfterMethod
	public void closeUp() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
	
	
	

}
