package Aldeias;

import Config.Selenium;
import Config.Tempo;

public class MelhoraInfraestrutura {

	// Melhora a infraestrutura da Aldeia
	public static Long melhorarInfra(String idAldeia, String idEdificio) {
		String linkEdificio = "https://tx3.lusobrasileiro.travian.com/build.php?newdid=" + idAldeia + "&id="
				+ idEdificio;
		Long tempoMin = 3000l;

		try {

			Selenium.driver.get(linkEdificio);
			System.out.println("Link edificio" + linkEdificio);
			tempoMin = Edificio.tempoDeMelhoria(Selenium.driver, linkEdificio, idAldeia);
			Edificio.confirmarMelhoriaInfra();
			Tempo.esperar(5000, "Aguardando confirma��o de melhorarInfra");

		} catch (Exception e) {
			System.out.println("N�o foi poss�vel confirmar melhoria");
			Tempo.esperar(120000, "Exception em  MelhoraInfraestrutura m�todo melhorarInfra");

		}
		return tempoMin;
	}

	// Melhora a infraestrutura da Aldeia
	public static Long fazerTropas(String idAldeia, String idEdificio, String tipoTropa, String quantidade) {
		String linkEdificio = "https://tx3.lusobrasileiro.travian.com/build.php?newdid=" + idAldeia + "&id="
				+ idEdificio;
		Long tempoMin = 3000l;

		try {

			Selenium.driver.get(linkEdificio);
			System.out.println("Link edificio" + linkEdificio);

			Edificio.confirmarTreinamento(tipoTropa, quantidade);
			Tempo.esperar(5000, "Aguardando confirma��o de fazerTropas");

		} catch (Exception e) {
			System.out.println("N�o foi poss�vel fazer Tropas");
			Tempo.esperar(120000, "Exception em  MelhoraInfraestrutura m�todo fazerTropas");

		}
		return tempoMin;
	}
}
