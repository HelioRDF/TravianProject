package coletaInfos;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Recursos {

	static int arvoreDisponivel;
	static int barroDisponivel;
	static int ferroDisponivel;
	static int cerealDisponivel;

	// Dentro de Recursos
	public static void quantidadeDeRecursos(WebDriver driver) {
		WebElement madeira = driver.findElement(By.id("l1"));
		WebElement barro = driver.findElement(By.id("l2"));
		WebElement ferro = driver.findElement(By.id("l3"));
		WebElement cereal = driver.findElement(By.id("l4"));

		setArvoreDisponivel(Integer.parseInt(madeira.getText()));
		setBarroDisponivel(Integer.parseInt(barro.getText()));
		setFerroDisponivel(Integer.parseInt(ferro.getText()));
		setCerealDisponivel(Integer.parseInt(cereal.getText()));

		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------\n ");
		System.out.println("\tRecursos na Aldeia \n ");
		System.out.println("\tMadeira: " + getArvoreDisponivel());
		System.out.println("\tBarro: " + getBarroDisponivel());
		System.out.println("\tFerro: " + getFerroDisponivel());
		System.out.println("\tCereal: " + getCerealDisponivel());
		System.out.println("\n------------------------------------\n ");

	}

	// Dentro de Recursos
	public static ArrayList<Campo> verificaNVLCampos(WebDriver driver) {

		List<WebElement> camposRecursos = driver.findElements(By.tagName("area"));
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

			objCampo.setNivelCampo(nivelCampoInfo);
			objCampo.setLinkCampo(obj.getAttribute("href"));
			objCampo.setNivelCampoAux(numNivel);
			camposList.add(objCampo);

		}
		return camposList;
	}

	public static String orderCamposNvl(ArrayList<Campo> camposList) {
		camposList.sort((h1, h2) -> h1.getNivelCampoAux().compareTo(h2.getNivelCampoAux()));
		String link = "";
		int prioridade = 1;
		System.out.println("Informações dos Campos\n");
		for (Campo obj : camposList) {
			obj.setPrioridade(prioridade);
			System.out.println("Nível: " + obj.getNivelCampoAux());
			System.out.println("Prioridade: " + obj.getPrioridade());
			System.out.println("Link: " + obj.getLinkCampo() + "\n");

			if (prioridade == 1) {
				link = obj.getLinkCampo();
			}
			prioridade++;

		}

		return link;

	}

	public static int getArvoreDisponivel() {
		return arvoreDisponivel;
	}

	public static void setArvoreDisponivel(int arvoreDisponivel) {
		Recursos.arvoreDisponivel = arvoreDisponivel;
	}

	public static int getBarroDisponivel() {
		return barroDisponivel;
	}

	public static void setBarroDisponivel(int barroDisponivel) {
		Recursos.barroDisponivel = barroDisponivel;
	}

	public static int getFerroDisponivel() {
		return ferroDisponivel;
	}

	public static void setFerroDisponivel(int ferroDisponivel) {
		Recursos.ferroDisponivel = ferroDisponivel;
	}

	public static int getCerealDisponivel() {
		return cerealDisponivel;
	}

	public static void setCerealDisponivel(int cerealDisponivel) {
		Recursos.cerealDisponivel = cerealDisponivel;
	}

}
