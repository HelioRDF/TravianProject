package Aldeias;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Config.Selenium;
import Config.Tempo;

public class Campo {

	private String nomeCampo;
	private String nivelCampo;
	private Integer nivelCampoAux;
	private String linkCampo;
	private int arvoreCusto;
	private int barroCusto;
	private int ferroCusto;
	private int cerealCusto;
	private int prioridade;
	private Long tempo;

	public int getArvoreCusto() {
		return arvoreCusto;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
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

	public static Long confirmarMelhoriaCampo(String idAldeia, String linkCampo) {
		//Selenium.driver.get(Links.getLinkaldeia() + idAldeia);
		Selenium.driver.get(linkCampo);
		Long tempoMelhoria = 3000l;
		List<WebElement> elementosBt = Selenium.driver.findElements(By.tagName("button"));
		for (WebElement obj : elementosBt) {

			if (obj.getText().contains("Melhorar para nível")) {

				tempoMelhoria = DadosAldeia.tempoDeMelhoria(idAldeia);
				WebElement btMelhorar = Selenium.driver.findElement(By.id(obj.getAttribute("id")));
				System.out.println("\n\n\nAchou" + btMelhorar);
				btMelhorar.click();
				Tempo.esperar(3000, "Confirmando Melhoria de Campo aldeia "+ idAldeia);
				break;

			}
		}
		return tempoMelhoria;
	}

	@Override
	public String toString() {
		return "Campo [nomeCampo=" + nomeCampo + ", nivelCampo=" + nivelCampo + ", nivelCampoAux=" + nivelCampoAux
				+ ", linkCampo=" + linkCampo + ", arvoreCusto=" + arvoreCusto + ", barroCusto=" + barroCusto
				+ ", ferroCusto=" + ferroCusto + ", cerealCusto=" + cerealCusto + ", prioridade=" + prioridade
				+ ", tempo=" + tempo + "]";
	}

}
