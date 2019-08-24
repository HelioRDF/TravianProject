package coletaInfos;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import InfosTravian.AldeiasFarme;
import InfosTravian.JogadoresFarme;

public class RankJogadores {

	public static void listaDeJogadores(String link, WebDriver driver) {

		try {
			driver.get(link);
			System.out.println("\n\n\n\n\n\n---------------------------------------");
			System.out.println("Link:" + link);
			System.out.println("---------------------------------------\\n\\n\\n\\n\\n\\n");
			List<WebElement> jogadores = driver.findElements(By.className("pla"));
			ArrayList<JogadoresFarme> listaJogadores = new ArrayList<JogadoresFarme>();

			for (WebElement obj : jogadores) {
				JogadoresFarme objJogadores = new JogadoresFarme();
				String linkJogador = obj.findElement(By.tagName("a")).getAttribute("href");
				String nomeJogador = obj.getText();
				objJogadores.setNomeJogador(nomeJogador);
				objJogadores.setLinkJogador(linkJogador);
				listaJogadores.add(objJogadores);

			}

			for (JogadoresFarme jogadoresFarme : listaJogadores) {

				identificaAldeias(jogadoresFarme.getNomeJogador(), jogadoresFarme.getLinkJogador(), driver);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro lista de Jogadores");
		}

	}

	public static ArrayList<AldeiasFarme> identificaAldeias(String jogador, String linkJogador, WebDriver driver) {
		ArrayList<AldeiasFarme> listaDeAldeias = new ArrayList<>();
		try {
			driver.get(linkJogador);
			List<WebElement> aldeias = driver.findElements(By.className("name"));
			String linkAldeia = "";
			for (WebElement obj : aldeias) {

				List<WebElement> aldeiasLinks = obj.findElements(By.tagName("a"));

				for (WebElement objLinks : aldeiasLinks) {
					AldeiasFarme aldeia = new AldeiasFarme();
					aldeia.setLinkAldeia(objLinks.getAttribute("href"));
					listaDeAldeias.add(aldeia);
					linkAldeia = objLinks.getAttribute("href").toString();

				}

				for (AldeiasFarme aldeia : listaDeAldeias) {
					driver.get(aldeia.getLinkAldeia());

					List<WebElement> td = driver.findElements(By.tagName("td"));

					Double distanciaLimite = 80d;
					String distanciaencontrada = "null";
					boolean distanciaAceitavel = false;
					boolean alianca = false;

					for (WebElement objTd : td) {

						if (objTd.getText().contains("CF HELL") || objTd.getText().contains("CF")
								|| objTd.getText().contains("CF TNT")) {
							// Adicionar à lista de farm
							alianca = true;
						}
						if (objTd.getText().contains("Campos:")) {
							String distancia[] = objTd.getText().split("Campos");
							Double distanciaDouble = Double.parseDouble(distancia[0]);
							if (distanciaDouble < distanciaLimite) {
								distanciaAceitavel = true;
								distanciaencontrada = objTd.getText();

							}

						}

					}

					if (alianca == false && distanciaAceitavel) {

						System.out.println("\n-----------Inicio --------------");
						System.out.println("\nLink aldeia:\t " + linkAldeia);
						System.out.println("\nInfo:\t " + distanciaencontrada);

						addFarme(driver);
					}
				}
			}

		} catch (Exception e) {
			// System.out.println("\nErro lista de identificaAldeias");
		}
		return listaDeAldeias;
	}

	public static void addFarme(WebDriver driver) {
		List<WebElement> elementosBt = driver.findElements(By.tagName("a"));
		for (WebElement obj : elementosBt) {
			if (obj.getText().contains("Adicionar à lista de farm")) {
				WebElement btMelhorar = driver.findElement(By.id(obj.getAttribute("id")));

				btMelhorar.click();
				System.out.println("Clicou ...");
				System.out.println("\n----------- --------------\n");

				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				WebElement tropas = driver.findElement(By.name("t1"));
				tropas.sendKeys("15");

				WebElement btSalvar = driver.findElement(By.name("save"));
				btSalvar.click();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

}
