package Main;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import InfosTravian.Links;
import Login.Entrar;
import coletaInfos.Campo;
import coletaInfos.Edificio;
import coletaInfos.RankJogadores;
import coletaInfos.Recursos;

public class Main {

	public static void main(String[] args) {
		// verRank();
		// melhorarCampo();
		
		esperar(185000);

		melhorarInfraThread("16755", "27", 5);
		esperar(85000);
	
		melhorarInfraThread("19725", "31", 20);
		esperar(85000);
		
		
		melhorarInfraThread("19944", "21", 9);
		esperar(85000);

		
		melhorarInfraThread("20444", "27", 6);
		esperar(85000);

		melhorarInfraThread("21050", "37", 12);
		esperar(85000);
		
		
		melhorarInfraThread("21946", "30", 12);
		esperar(85000);

		melhorarInfraThread("22371", "20", 8);
		esperar(85000);
		
		
		melhorarInfraThread("22789", "20", 10);
		esperar(85000);

	}

	// Melhora a infraestrutura da Aldeia
	public static void melhorarInfraThread(String idAldeia, String idEdificio, int repetir) {
		String linkEdificio = "https://tx3.lusobrasileiro.travian.com/build.php?newdid=" + idAldeia + "&id="
				+ idEdificio;

		new Thread() {
			@Override
			public void run() {

				int num = repetir;
				WebDriver driver;
				while (num != 0) {
					driver = configWebDriver();
					try {
						Entrar.login(driver);
						esperar(1000);
						driver.get(linkEdificio);
						Long tempoMin = Edificio.tempoDeMelhoria(driver, linkEdificio, idAldeia);
						Edificio.confirmarMelhoriaInfra(driver);
						driver.close();
						esperar(tempoMin);
						num--;

					} catch (Exception e) {
						System.out.println("Não foi possível confirmar melhoria");
						driver.close();
						esperar(120000);

					}

				}
			}
		}.start();

	}

	// Visualizar Rank e add farmes
	public static void verRank() {

		WebDriver driver;
		// driver.manage().window().setPosition(new Point(-2000, 0));
		try {
			int idPAgina = 58;
			driver = configWebDriver();
			while (idPAgina >= 38) {

				Entrar.login(driver);
				RankJogadores.listaDeJogadores(Links.getLinkrankpaginajogadores() + idPAgina, driver);
				System.out.println("\n\n\n\n-----------------------------------------------------------------\n\n\n\n");
				System.out.println("\n\n\n\nLink=" + Links.getLinkrankpaginajogadores() + idPAgina);
				System.out.println("\n\n\n\n-----------------------------------------------------------------\n\n\n\n");
				idPAgina--;
				esperar(2000);
			}
		} catch (Exception e) {
			esperar(10000);
		}
	}

	public static void melhorarCampo() {
		ArrayList<String> aldeias = new ArrayList<String>();

		// aldeias.add("https://tx3.lusobrasileiro.travian.com/dorf1.php?newdid=21946&");
		// // j
		aldeias.add("https://tx3.lusobrasileiro.travian.com/dorf1.php?newdid=22789&");// L

		for (String aldeia : aldeias) {
			String idAldeia[] = new String[2];
			idAldeia = aldeia.split("id=");
			System.out.println("\n\n\n\nLink:" + aldeia);
			iniciaTarefaThread(aldeia, idAldeia[1].toString());
			esperar(165000);

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
						System.out.println("\n\n\n\n tarefa - Link:" + link);
						Campo campo = new Campo();
						campo.custoMelhoria(driver);
						Long tempoMin = Campo.tempoDeMelhoria(driver, idAldeia);
						Campo.confirmarMelhoriaCampo(driver);
						driver.close();
						esperar(tempoMin);

					} catch (Exception e) {
						System.out.println("Não foi possível confirmar melhoria");
						driver.close();
						esperar(120000);

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
