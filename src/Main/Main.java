package Main;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Login.Entrar;
import coletaInfos.Campo;
import coletaInfos.Recursos;

public class Main {

	public static void main(String[] args) {
		// WebDriver driver = configWebDriver();
		// driver.manage().window().setPosition(new Point(-2000, 0));
		try {
			// Entrar.login(driver);
			// RankJogadores.listaDeJogadores(Links.getLinkrankpaginajogadores(), driver);
			ArrayList<String> aldeias = new ArrayList<String>();
			aldeias.add("https://tx3.lusobrasileiro.travian.com/dorf1.php?newdid=2071&");//A
			aldeias.add("https://tx3.lusobrasileiro.travian.com/dorf1.php?newdid=15676&");//B
			
			for (String aldeia : aldeias) {
				String idAldeia[] = new String[2];
				idAldeia=aldeia.split("id=");
				iniciaTarefaThread(aldeia, idAldeia[1].toString());
				esperar(240000);
			}
			
			
			//iniciaTarefaThread("https://tx3.lusobrasileiro.travian.com/dorf1.php?newdid=2071&");// A
			// iniciaTarefaThread("https://tx3.lusobrasileiro.travian.com/dorf1.php?newdid=15676&");//B

		} catch (Exception e) {
			esperar(10000);
		}
	}

	public static void iniciaTarefaThread(String linkAldeia, String idAldeia) {
		new Thread() {
			@Override
			public void run() {
				boolean rodar = true;
				WebDriver driver;
				while (rodar) {
					driver = configWebDriver();
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
						Long tempoMin = Campo.tempoDeMelhoria(driver,idAldeia);
						Campo.confirmarMelhoriaCampo(driver);
						driver.close();
						esperar(tempoMin);

					} catch (Exception e) {
						System.out.println("Não foi possível confirmar melhoria");
						driver.close();
						esperar(4000);

					}

				}
			}
		}.start();

	}

	public static WebDriver configWebDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\helio\\Documents\\Desenvolvimento/chromedriver.exe");

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
