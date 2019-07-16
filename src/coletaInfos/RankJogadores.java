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
				System.out.println("\n--------------------\n");
				System.out.println("Jogador: " + jogadoresFarme.getNomeJogador());
				System.out.println("Link: " + jogadoresFarme.getLinkJogador());

				identificaAldeias(jogadoresFarme.getLinkJogador(), driver);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro lista de Jogadores");
		}

	}

	public static ArrayList<AldeiasFarme> identificaAldeias(String linkAldeia, WebDriver driver) {
		ArrayList<AldeiasFarme> listaDeAldeias = new ArrayList<>();
		try {
			driver.get(linkAldeia);
			List<WebElement> aldeias = driver.findElements(By.className("name"));

			for (WebElement obj : aldeias) {

				List<WebElement> aldeiasLinks = obj.findElements(By.tagName("a"));

				for (WebElement objLinks : aldeiasLinks) {
					// System.out.println("Obj:"+aldeiasLinks);
					// System.out.println("Qtd:"+objLinks.getSize());
					AldeiasFarme aldeia = new AldeiasFarme();
					aldeia.setLinkAldeia(objLinks.getAttribute("href"));
					listaDeAldeias.add(aldeia);
					System.out.println("\nLink:\n" + objLinks.getAttribute("href"));
				}

				for (AldeiasFarme aldeia : listaDeAldeias) {
					driver.get(aldeia.getLinkAldeia());
					List<WebElement> td = driver.findElements(By.tagName("td"));

					for (WebElement objTd : td) {
						System.out.println(objTd.getText());

					}

				}
			}

		} catch (Exception e) {
			System.out.println("Erro lista de identificaAldeias");
		}
		return listaDeAldeias;
	}

}
