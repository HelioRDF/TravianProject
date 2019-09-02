package Aldeias;

import java.time.LocalTime;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import InfosTravian.Log;

public class Edificio {

	private String nivelCampo;
	private Integer nivelCampoAux;
	private String linkCampo;
	private int arvoreCusto;
	private int barroCusto;
	private int ferroCusto;
	private int cerealCusto;
	private int prioridade;
	private Long tempo;

	public void custoMelhoria(WebDriver driver) {
		List<WebElement> elementosDiv = driver.findElements(By.tagName("div"));

		System.out.println(" \n----- Custo Melhoria----- \n");
		for (WebElement obj : elementosDiv) {
			String classe = obj.getAttribute("class").toString();
			if (classe.contains("resources")) {
				System.out.println(classe + ": " + obj.getText());
				if (classe.contains("resources r1")) {
					setArvoreCusto(Integer.parseInt(obj.getText()));
				} else if (classe.contains("resources r2")) {
					setBarroCusto(Integer.parseInt(obj.getText()));
				} else if (classe.contains("resources r3")) {
					setFerroCusto(Integer.parseInt(obj.getText()));
				} else if (classe.contains("resources r4")) {
					setCerealCusto(Integer.parseInt(obj.getText()));
				}
			}

		}

	}

	public static void confirmarMelhoriaInfra(WebDriver driver) {
		List<WebElement> elementosBt = driver.findElements(By.tagName("button"));
		for (WebElement obj : elementosBt) {
			if (obj.getText().contains("Melhorar para nível")) {
				WebElement btMelhorar = driver.findElement(By.id(obj.getAttribute("id")));

				// System.out.println("Aqui:"+ obj.getAttribute("id"));
				btMelhorar.click();
			}
		}
	}

	public static Long tempoDeMelhoria(WebDriver driver, String estrutura, String idAldeia) {

		List<WebElement> duracaoSpan = driver.findElements(By.tagName("span"));
		long minutosEspera = 0l;

		WebElement tipoCampo = driver.findElement(By.className("titleInHeader"));
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
		
						if (minutosEspera < 400000) {
							minutosEspera = 400000;
						}
						System.out.println("Hora: " + horainicio);
						System.out.println("Tempo de espera milisegundos: " + minutosEspera);
						System.out.println("Tempo de espera minutos: " + minutosEspera / 1000 / 60 + " Minutos");
						String infos = "Hora: " + horainicio + "\t --- Min de espera:" + (minutosEspera / 1000 / 60)
								+ " Minutos";

						Log.geraLog(idAldeia + "-Infra.txt", infos);

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

	public int getArvoreCusto() {
		return arvoreCusto;
	}

	public void setArvoreCusto(int arvoreCusto) {
		this.arvoreCusto = arvoreCusto;
	}

	public int getBarroCusto() {
		return barroCusto;
	}

	public void setBarroCusto(int barroCusto) {
		this.barroCusto = barroCusto;
	}

	public int getFerroCusto() {
		return ferroCusto;
	}

	public void setFerroCusto(int ferroCusto) {
		this.ferroCusto = ferroCusto;
	}

	public int getCerealCusto() {
		return cerealCusto;
	}

	public void setCerealCusto(int cerealCusto) {
		this.cerealCusto = cerealCusto;
	}

	public String getNivelCampo() {
		return nivelCampo;
	}

	public void setNivelCampo(String nivelCampo) {
		this.nivelCampo = nivelCampo;
	}

	public String getLinkCampo() {
		return linkCampo;
	}

	public void setLinkCampo(String linkCampo) {
		this.linkCampo = linkCampo;
	}

	public Integer getNivelCampoAux() {
		return nivelCampoAux;
	}

	public void setNivelCampoAux(Integer nivelCampoAux) {
		this.nivelCampoAux = nivelCampoAux;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public Long getTempo() {
		return tempo;
	}

	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}

}
