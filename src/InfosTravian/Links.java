package InfosTravian;

public class Links {

	protected static final String linkPaginaLogin = "https://tx3.lusobrasileiro.travian.com/";
	protected static final String linkRankPaginaJogadores = linkPaginaLogin + "statistiken.php?id=0&page=";
	protected static final String linkEdificio = linkPaginaLogin + "build.php?newdid=17507&id=19&gid=10";
	protected static final String linkCampo = linkPaginaLogin + "build.php?newdid=22789&id=2";
	protected static final String linkAldeia = linkPaginaLogin + "dorf1.php?newdid=";
	protected static final String linkAldeiaHome = "https://tx3.lusobrasileiro.travian.com/dorf1.php";

	/**
	 * 
	 * @return Retorna o Link de login
	 */
	public static String getLinkpaginalogin() {
		return linkPaginaLogin;
	}

	public static String getLinkrankpaginajogadores() {
		return linkRankPaginaJogadores;
	}

	public static String getLinkaldeia() {
		return linkAldeia;
	}

	public static String getLinkaldeiahome() {
		return linkAldeiaHome;
	}

}
