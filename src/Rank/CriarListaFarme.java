package Rank;

import org.openqa.selenium.WebDriver;

import Config.Entrar;
import Config.Selenium;
import Config.Tempo;
import InfosTravian.Links;

public class CriarListaFarme {



	// Visualizar Rank e add farmes
	public static void verRank() {

		WebDriver driver;
		// driver.manage().window().setPosition(new Point(-2000, 0));
		try {
			int idPAgina = 58;
			driver = Selenium.configWebDriver();
			while (idPAgina >= 38) {

				Entrar.login();
				RankJogadores.listaDeJogadores(Links.getLinkrankpaginajogadores() + idPAgina, driver);
				System.out.println("\n\n\n\n-----------------------------------------------------------------\n\n\n\n");
				System.out.println("\n\n\n\nLink=" + Links.getLinkrankpaginajogadores() + idPAgina);
				System.out.println("\n\n\n\n-----------------------------------------------------------------\n\n\n\n");
				idPAgina--;
				Tempo.esperar(2000, "Espera em verRank");
			}
		} catch (Exception e) {
			Tempo.esperar(10000, "Exception em CriarListaFarme");
		}
	}

	
}
