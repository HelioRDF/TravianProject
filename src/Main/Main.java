package Main;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import InfosTravian.Links;
import Login.Entrar;
import coletaInfos.Campo;
import coletaInfos.RankJogadores;
import coletaInfos.Recursos;

public class Main {

	public static void main(String[] args) {
		WebDriver driver = configWebDriver();
		driver.manage().window().setPosition(new Point(-2000, 0));
		try {
			Entrar.login(driver);
			RankJogadores.listaDeJogadores(Links.getLinkrankpaginajogadores(), driver);
			//iniciaTarefaThread(driver, "https://ts29.lusobrasileiro.travian.com/dorf1.php?finished=true");
		} catch (Exception e) {
			esperar(10000);
		}
	}

	public static void iniciaTarefaThread(WebDriver driver, String linkAldeia) {
		new Thread() {
			@Override
			public void run() {
				boolean rodar = true;
				while (rodar) {
					try {
						Entrar.login(driver);
						esperar(1000);
						driver.get(linkAldeia);
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
			}
		}.start();

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
			System.out.println("Problemas na Classe esperar ");
		}
	}
}
