package seleniumpractice;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.makemytrip.com/");

		driver.findElement(By.id("fromCity")).sendKeys("var");
		
		
		driver.findElement(By.xpath("//p[text()='Varanasi Airport']")).click();
		
		
//		WebDriverWait wait = 
//				new WebDriverWait(driver, Duration.ofSeconds(30));
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated
//				(By.id("fromCity"))).sendKeys("ban");
		
		
	}

}