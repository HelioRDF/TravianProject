package Aldeias;

import java.util.ArrayList;

public class Aldeia {

	private String nomeAldeia;
	private String idAldeia;
	private int madeira;
	private int barro;
	private int ferro;
	private int cereal;
	private ArrayList<Campo> campos;
	private boolean melhorar=false;
	
	
	public Aldeia(String nomeAldeia, String idAldeia) {
		super();
		this.nomeAldeia = nomeAldeia;
		this.idAldeia = idAldeia;
	}

	public String getNomeAldeia() {
		return nomeAldeia;
	}

	public void setNomeAldeia(String nomeAldeia) {
		this.nomeAldeia = nomeAldeia;
	}

	public String getIdAldeia() {
		return idAldeia;
	}

	public void setIdAldeia(String idAldeia) {
		this.idAldeia = idAldeia;
	}

	public int getMadeira() {
		return madeira;
	}

	public void setMadeira(int madeira) {
		this.madeira = madeira;
	}

	public int getBarro() {
		return barro;
	}

	public void setBarro(int barro) {
		this.barro = barro;
	}

	public int getFerro() {
		return ferro;
	}

	public void setFerro(int ferro) {
		this.ferro = ferro;
	}

	public int getCereal() {
		return cereal;
	}

	public void setCereal(int cereal) {
		this.cereal = cereal;
	}

	public ArrayList<Campo> getCampos() {
		return campos;
	}

	public void setCampos(ArrayList<Campo> campos) {
		this.campos = campos;
	}

	public boolean isMelhorar() {
		return melhorar;
	}

	public void setMelhorar(boolean melhorar) {
		this.melhorar = melhorar;
	}
	
	
	

}
