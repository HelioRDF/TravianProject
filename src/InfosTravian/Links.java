package InfosTravian;

public class Links {

	protected static int idPagina=130;
	protected static final String linkPaginaLogin = "https://ts29.lusobrasileiro.travian.com/";
	protected static final String linkRankPaginaJogadores = "https://ts29.lusobrasileiro.travian.com/statistiken.php?id=0&page="+idPagina;


	/**
	 * 
	 * @return Retorna o Link de login
	 */
	public static String getLinkpaginalogin() {
		return linkPaginaLogin;
	}


	public static int getIdPagina() {
		return idPagina;
	}


	public static void setIdPagina(int idPagina) {
		Links.idPagina = idPagina;
	}


	public static String getLinkrankpaginajogadores() {
		return linkRankPaginaJogadores;
	}

	

}
