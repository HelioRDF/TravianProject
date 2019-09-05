package Main;

import Aldeias.Aldeia;
import Aldeias.Campo;
import Aldeias.DadosAldeia;
import Config.Entrar;
import Config.Tempo;

public class Main {

	public static void main(String[] args) {
		iniciarTareCampo();
	}

	public static void iniciarTareCampo() {
		int repetir = 0;
		Aldeia m = new Aldeia("Aldeia M", "22789");
		Entrar.login();
	
		DadosAldeia.verificaCampos(m);
		while (repetir <= 10) {

			if (m.isMelhorar()) {
				m.setTempoEspera(
						Campo.confirmarMelhoriaCampo(m.getIdAldeia(), m.getCampos().get(repetir).getLinkCampo()));
				m.setMelhorar(false);
				aguardandoParaUpar(m.getTempoEspera(), m);
				Tempo.esperar(2000);

			}

			if (repetir == 10) {
				repetir = 0;
				DadosAldeia.verificaCampos(m);
				Entrar.login();
			}
			repetir++;
			Tempo.esperar(2000);
			
		}
	}
	


	public static void aguardandoParaUpar(long tempo, Aldeia aldeia) {
		new Thread(new Runnable() {
			public void run() {
				Tempo.esperar(tempo);
				aldeia.setMelhorar(true);
			}
		}).start();
	}
}
