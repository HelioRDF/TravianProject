package Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {
	public static WebDriver driver;
	
	
	public static WebDriver configWebDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\helio\\Documents\\Desenvolvimento/chromedriver.exe");

	    driver = new ChromeDriver();
		return driver;
	}

}
