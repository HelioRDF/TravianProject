package Aldeias;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Config.Selenium;
import InfosTravian.Links;
import InfosTravian.Log;

public class DadosAldeia {

	// Dentro de Recursos
	public static void quantidadeDeRecursos(Aldeia aldeia) {
		try {
			WebElement madeira = Selenium.driver.findElement(By.id("l1"));
			WebElement barro = Selenium.driver.findElement(By.id("l2"));
			WebElement ferro = Selenium.driver.findElement(By.id("l3"));
			WebElement cereal = Selenium.driver.findElement(By.id("l4"));
			aldeia.setMadeira(Integer.parseInt(madeira.getText().replace(".", "")));
			aldeia.setBarro(Integer.parseInt(barro.getText().replace(".", "")));
			aldeia.setFerro(Integer.parseInt(ferro.getText().replace(".", "")));
			aldeia.setCereal(Integer.parseInt(cereal.getText().replace(".", "")));
			System.out.println(
					"\n------------------------------------------------------------------------------------------------------------------------------------------------\n ");
			System.out.println("\tRecursos na Aldeia \n " + aldeia.getNomeAldeia());
			System.out.println("\tMadeira: " + aldeia.getMadeira());
			System.out.println("\tBarro: " + aldeia.getBarro());
			System.out.println("\tFerro: " + aldeia.getFerro());
			System.out.println("\tCereal: " + aldeia.getFerro());
			System.out.println("\n------------------------------------\n ");
		} catch (Exception e) {
			System.out.println("Erro:" + e);
		}
	}

	public static Campo custoMelhorarCampo(Campo campo) {
		List<WebElement> elementosDiv = Selenium.driver.findElements(By.tagName("div"));
		// System.out.println(" \n----- Custo Melhoria----- \n");
		int num = 1;
		for (WebElement obj : elementosDiv) {
			String classe = obj.getAttribute("class").toString();
			if (classe.contains("inlineIcon resource")) {
				// System.out.println(num + "-" + obj.getText());
				if (num == 1) {
					campo.setArvoreCusto(Integer.parseInt(obj.getText()));
				} else if (num == 2) {
					campo.setBarroCusto(Integer.parseInt(obj.getText()));
				} else if (num == 3) {
					campo.setFerroCusto(Integer.parseInt(obj.getText()));
				} else if (num == 4) {
					campo.setCerealCusto(Integer.parseInt(obj.getText()));
				}
				num++;
			}
		}
		return campo;
	}

	// Dentro de Recursos
	public static void verificaCampos(Aldeia aldeia) {
		Selenium.driver.get(Links.getLinkaldeia() + aldeia.getIdAldeia());
		List<WebElement> camposRecursos = Selenium.driver.findElements(By.tagName("area"));

		ArrayList<Campo> camposList = new ArrayList<>();
		for (WebElement obj : camposRecursos) {
			if (obj.getAttribute("alt").contains("Centro da Aldeia")) {
				continue;
			}
			Campo objCampo = new Campo();
			String nivelCampoInfo = obj.getAttribute("alt");
			String array[] = new String[2];
			array = nivelCampoInfo.split("Nível");
			Integer numNivel = Integer.parseInt(array[1].trim());
			String campo = array[0].trim();

			// Diminui a prioridade para campo de cereais
			if (campo.toString().equalsIgnoreCase("Campo de cereais") && numNivel > 2) {
				numNivel = numNivel + 3;
			}
			objCampo.setNivelCampo(nivelCampoInfo);
			objCampo.setLinkCampo(obj.getAttribute("href"));
			objCampo.setNivelCampoAux(numNivel);
			objCampo.setNomeCampo(campo);
			if (numNivel == 10) {
				continue;
			}

			orderCamposNvl(camposList);
			camposList.add(objCampo);
		}

		aldeia.setCampos(camposList);
		for (Campo obj2 : aldeia.getCampos()) {

			Selenium.driver.get(obj2.getLinkCampo());
			obj2.setTempo(tempoDeMelhoria( obj2.getLinkCampo(), aldeia.getNomeAldeia()));
			obj2 = custoMelhorarCampo(obj2);

			System.out.println("\n---------------------------------------------------:\n");
			System.out.println("Campo:" + obj2.getNomeCampo());
			System.out.println("Prioridade:" + obj2.getPrioridade());
			System.out.println("Nivel:" + obj2.getNivelCampo());
			System.out.println("Tempo:" + obj2.getTempo());
			System.out.println("Link:" + obj2.getLinkCampo());
			System.out.println("Madeira:" + obj2.getArvoreCusto());
			System.out.println("Barro:" + obj2.getBarroCusto());
			System.out.println("Ferro:" + obj2.getFerroCusto());
			System.out.println("Cereal:" + obj2.getCerealCusto());
			System.out.println("\n---------------------------------------------------:\n");
		}
	}

		public static Long tempoDeMelhoria( String linkCampo, String nomeAldeia) {

		List<WebElement> duracaoSpan = Selenium.driver.findElements(By.tagName("span"));
		long minutosEspera = 0l;

		WebElement tipoCampo = Selenium.driver.findElement(By.className("titleInHeader"));
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\nCampo: "
				+ tipoCampo.getText() + "\n");

		for (WebElement obj : duracaoSpan) {
			String classeNoObj = obj.getAttribute("class").toString();
			String tempoEspera = obj.getText();

			if (obj.getText().length() == 7) {

				try {
					System.out.println("\n------------------------------------\nTempo(Valor): " + tempoEspera);
					System.out.println("Class: " + classeNoObj + "\n------------------------------------\n");
					if (classeNoObj.contains("value")) {
						String encontraMinutosList[] = new String[3];
						encontraMinutosList = tempoEspera.split(":");
						minutosEspera = Long.parseLong(encontraMinutosList[1]);
						minutosEspera = (minutosEspera * 60 * 1000) + 60000;
						LocalTime horainicio = LocalTime.now();

						System.out.println("Hora: " + horainicio);
						System.out.println("Tempo de espera milisegundos: " + minutosEspera);
						System.out.println("Tempo de espera minutos: " + minutosEspera / 1000 / 60 + " Minutos");
						String infos = "Hora: " + horainicio + "\t --- Min de espera:" + (minutosEspera / 1000 / 60)
								+ " Minutos";
						Log.geraLog(nomeAldeia + ".txt", infos);

						break;
					}
				} catch (Exception e) {
					System.out.println("Problemas no tempoDeMelhoria");
					minutosEspera = 990000;

				}
			}
		}
		return minutosEspera;
	}

	public static String orderCamposNvl(ArrayList<Campo> camposList) {
		camposList.sort((h1, h2) -> h1.getNivelCampoAux().compareTo(h2.getNivelCampoAux()));
		String link = "";
		int prioridade = 1;
		for (Campo obj : camposList) {
			obj.setPrioridade(prioridade);
			if (prioridade == 1) {
				link = obj.getLinkCampo();
			}
			prioridade++;
		}
		return link;
	
	}

}
