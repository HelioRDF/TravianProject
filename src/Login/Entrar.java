package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import InfosTravian.Links;

public class Entrar {
	
	static final String loginT = "furioso"; // https://ts4.lusobrasileiro.travian.com
	static final String senhaT =  "killer17";
	
	public static void login(WebDriver driver){
		driver.get(Links.getLinkpaginalogin());	
		
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
		
		
		WebElement campoDeTexto = driver.findElement(By.name("name"));
		campoDeTexto.sendKeys(loginT);
		
		WebElement campoDeSenha = driver.findElement(By.name("password"));
		campoDeSenha.sendKeys(senhaT);
		
		//WebElement checkBoxNetLenta = driver.findElement(By.id("lowRes"));
		//checkBoxNetLenta.click();
		
		campoDeTexto.submit();

	}

}
