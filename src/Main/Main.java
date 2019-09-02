package Main;

import Aldeias.Aldeia;
import Aldeias.Campo;
import Aldeias.DadosAldeia;
import Config.Entrar;
import InfosTravian.Links;

public class Main {

	public static void main(String[] args) {

		iniciarTareCampo();
	}
	
	public static void iniciarTareCampo(){
		int repetir=0;
		 Aldeia l = new Aldeia("Aldeia L", "22371");
		 Aldeia m = new Aldeia("Aldeia M", "22789");
		 
		 
		Entrar.login();
		DadosAldeia.verificaCampos(l);
		DadosAldeia.verificaCampos(m);
		
		while(repetir<=18) {
			
			Campo.confirmarMelhoriaCampo(l.getIdAldeia(), l.getCampos().get(repetir).getLinkCampo());
			Campo.confirmarMelhoriaCampo(l.getIdAldeia(), l.getCampos().get(repetir).getLinkCampo());
			
			if(repetir==18) {
				repetir=0;
			}
			repetir++;
			
		}
		
	}
}
