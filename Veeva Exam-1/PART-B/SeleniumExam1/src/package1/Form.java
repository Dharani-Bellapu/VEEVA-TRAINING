
package package1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Form {
public static void main(String[] args) {
WebDriver driver = new ChromeDriver();
driver.get("https://the-internet.herokuapp.com/login");
driver.findElement(By.id("username")).sendKeys("tomsmith");
driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
driver.findElement(By.className("radius")).click();
String message = driver.findElement(By.id("flash")).getText();
System.out.println(message);
driver.quit();
}
}
