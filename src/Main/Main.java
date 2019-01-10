package Main;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Login.Entrar;
import coletaInfos.Campo;
import coletaInfos.Recursos;

public class Main {

	public static void main(String[] args) {
		boolean rodar = true;
		WebDriver driver = configWebDriver();
		driver.manage().window().setPosition(new Point(-2000, 0));
		while (rodar) {

			try {
				iniciaTarefa(driver);
			} catch (Exception e) {
				esperar(3000);
			}
		}
	}

	public static void iniciaTarefa(WebDriver driver) {

		try {
			Entrar.login(driver);
			esperar(1000);
			Recursos.quantidadeDeRecursos(driver);
			String link = Recursos.orderCamposNvl(Recursos.verificaNVLCampos(driver));
			esperar(1000);
			driver.get(link);
			Campo campo = new Campo();
			campo.custoMelhoria(driver);
			Long tempoMin = Campo.tempoDeMelhoria(driver);
			Campo.confirmarMelhoriaCampo(driver);
			esperar(tempoMin);
		} catch (Exception e) {
			System.out.println("Não foi possível confirmar melhoria");
			esperar(4000);
		}

	}

	public static WebDriver configWebDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hfranca\\Downloads\\Selenium/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		return driver;
	}

	public static void esperar(long tempo) {
		try {
			System.out.println("Tempo de espera na thread: " + tempo);
			Thread.sleep(tempo);

		} catch (InterruptedException e) {
			System.out.println("Problemas na Classe esperar " );
		}
	}
}
