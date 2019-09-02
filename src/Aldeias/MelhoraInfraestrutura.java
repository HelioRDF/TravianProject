package Aldeias;

import org.openqa.selenium.WebDriver;

import Config.Entrar;
import Config.Selenium;
import Config.Tempo;

public class MelhoraInfraestrutura {

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
					driver = Selenium.configWebDriver();
					try {
						Entrar.login();
						Tempo.esperar(1000);
						driver.get(linkEdificio);
						Long tempoMin = Edificio.tempoDeMelhoria(driver, linkEdificio, idAldeia);
						Edificio.confirmarMelhoriaInfra(driver);
						driver.close();
						Tempo.esperar(tempoMin);
						num--;

					} catch (Exception e) {
						System.out.println("Não foi possível confirmar melhoria");
						driver.close();
						Tempo.esperar(120000);

					}

				}
			}
		}.start();

	}
}
