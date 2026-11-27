
package package1;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Window {
public static void main(String[] args) {
WebDriver driver = new ChromeDriver();
driver.get("https://the-internet.herokuapp.com/windows");
String parent = driver.getWindowHandle();
driver.findElement(By.linkText("Click Here")).click();
Set<String> windows = driver.getWindowHandles();
for (String w : windows) {
if (!w.equals(parent)) {
driver.switchTo().window(w);
System.out.println(driver.getTitle());
driver.close();
}
}
driver.switchTo().window(parent);
System.out.println(driver.getTitle());
driver.quit();
}
}
