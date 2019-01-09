package Main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Login.Entrar;
import coletaInfos.Campo;
import coletaInfos.Recursos;

public class Main {

	public static void main(String[] args) {
		boolean rodar = true;
		while(rodar) {
			
			try {
				iniciaTarefa();
			} catch (Exception e) {
				esperar(3000);
			}

		}
	


	}

	public static void iniciaTarefa() {
		WebDriver driver = configWebDriver();
		Entrar.login(driver);
		esperar(1000);
		Recursos.quantidadeDeRecursos(driver);
		String link = Recursos.orderCamposNvl(Recursos.verificaNVLCampos(driver));
		esperar(1000);
		driver.get(link);
		Campo campo = new Campo();
		campo.custoMelhoria(driver);
		Long tempoMin = Campo.tempoDeMelhoria(driver);
		try {
			Campo.confirmarMelhoriaCampo(driver);
		} catch (Exception e) {
			System.out.println("Não foi possível confirmar melhoria");
		}

		driver.close();
		esperar(tempoMin);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
