import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Test {

	public static void main(String[] args) {
		
		// Initialize ChromeDriver
		WebDriver driver = new ChromeDriver();
				
		// Opens up the link "10.0.1.86/tatoc"
		driver.get("http://10.0.1.86/tatoc");
				
		// Maximize the window
		driver.manage().window().maximize();
				
		// Click on Basic Course
		driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(4)")).click();
				
		// Click on the green box
		driver.findElement(By.className("greenbox")).click();

		
		// Clicking on the Repaint Box 2 button until both colors are same
		WebElement mainDiv = driver.findElement(By.id("main"));
		driver.switchTo().frame(mainDiv);
		
		String actualAnswer = driver.findElement(By.id("answer")).getAttribute("class");
		Boolean condition = false;
		
		while(!condition) 
		{
			driver.findElement(By.cssSelector("body > center > a:nth-child(7)")).click();
			WebElement childDiv = driver.findElement(By.id("child"));
			driver.switchTo().frame(childDiv);
			
			String expectedAnswer = driver.findElement(By.id("answer")).getAttribute("class");
			driver.switchTo().parentFrame();
			
			if (actualAnswer.equals(expectedAnswer)) {
				condition = true;
			}
		}
		
		// When colors matched clicking on proceed button
		driver.findElement(By.cssSelector("body > center > a:nth-child(9)")).click();
		
		// Applying Drag and Drop Operation using Actions Class
		Actions actions = new Actions(driver);
		WebElement drop = driver.findElement(By.id("dropbox"));
		WebElement drag = driver.findElement(By.id("dragbox"));
		actions.dragAndDrop(drag, drop).build().perform();
		
		// When the item dragged to the required location proceed is clicked
		driver.findElement(By.cssSelector("body > div > div.page > a")).click();
		
		// Clicking on Launch Popup Window
		driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(4)")).click();
		
		// Changing the tab
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		
		// Sending my name as a key and submitting
		driver.findElement(By.cssSelector("#name")).sendKeys("Megha Gangal");
		driver.findElement(By.cssSelector("#submit")).click();
		
		// Back to previous tab
		driver.switchTo().window(tabs2.get(0));
		
		// Clicking on proceed
		driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(6)")).click();
		
		// Clicking on generate token to generate token
		driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(8)")).click();
		String token = driver.findElement(By.cssSelector("#token")).getText();
		
		// Extracting the token value
		String tokenValue = token.substring(7);
		
		// Setting up the cookie
		Cookie name = new Cookie("Token", tokenValue);
		driver.manage().addCookie(name);
		
		// Proceeding to complete the Basic Course
		driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(10)")).click();
		
		// Closing the opened Window
		driver.close();
	}

}