package Main;

import Aldeias.Aldeia;
import Aldeias.Campo;
import Aldeias.DadosAldeia;
import Aldeias.MelhoraInfraestrutura;
import Config.Entrar;
import Config.Tempo;

public class Main {

	public static void main(String[] args) {
		// iniciarTarefa();

		Entrar.login();
		MelhoraInfraestrutura.fazerTropas("24069", "32", "t1", "2");

	}

	public static void iniciarTarefa() {
		int repetir = 0;
		Aldeia one = new Aldeia("Aldeia 01", "24069");
		Aldeia two = new Aldeia("Aldeia 02", "24398");
		Entrar.login();
		DadosAldeia.verificaCampos(one);
		DadosAldeia.verificaCampos(two);
		Long proximoPronto = 400000l;

		while (repetir <= 10) {
			Tempo.esperar(2000, "Aguarndo para reiniciar for em iniciarTarefa");

			if (one.isMelhorar()) {

				System.out.println("\n\n\n---------- Aldeia A");
				one.setTempoEspera(
						Campo.confirmarMelhoriaCampo(one.getIdAldeia(), one.getCampos().get(repetir).getLinkCampo()));
				one.setMelhorar(false);
				aguardandoParaUpar(one.getTempoEspera(), one);

				System.out.println("----------GetTempo" + one.getTempoEspera());

				if (repetir <= 3) {
					MelhoraInfraestrutura.melhorarInfra(one.getIdAldeia(), "19");
					System.out.println("Caso 01 - " + repetir);
				} else if (repetir <= 6) {
					MelhoraInfraestrutura.melhorarInfra(one.getIdAldeia(), "20");
					System.out.println("Caso 02 - " + repetir);
				} else if (repetir <= 10) {
					MelhoraInfraestrutura.melhorarInfra(one.getIdAldeia(), "27");
					System.out.println("Caso 03 - " + repetir);
				}

				if (one.getTempoEspera() < proximoPronto) {
					proximoPronto = one.getTempoEspera();
				}
			}

			Tempo.esperar(10000, "Mudando de Aldeia ...");
			if (two.isMelhorar()) {
				System.out.println("\n\n\n---------- Aldeia B");
				two.setTempoEspera(
						Campo.confirmarMelhoriaCampo(two.getIdAldeia(), two.getCampos().get(repetir).getLinkCampo()));
				two.setMelhorar(false);
				aguardandoParaUpar(two.getTempoEspera(), two);

				System.out.println("----------GetTempo" + two.getTempoEspera());

				if (repetir <= 3) {
					MelhoraInfraestrutura.melhorarInfra(two.getIdAldeia(), "19");
					System.out.println("Caso 01 - " + repetir);
				} else if (repetir <= 6) {
					MelhoraInfraestrutura.melhorarInfra(two.getIdAldeia(), "20");
					System.out.println("Caso 02 - " + repetir);
				} else if (repetir <= 10) {
					MelhoraInfraestrutura.melhorarInfra(two.getIdAldeia(), "26");
					System.out.println("Caso 03 - " + repetir);
				}

				if (two.getTempoEspera() < proximoPronto) {
					proximoPronto = two.getTempoEspera();
				}
			}

			System.out.println("----------- Repetir" + repetir);
			if (repetir == 10) {
				repetir = 0;
				DadosAldeia.verificaCampos(one);
				DadosAldeia.verificaCampos(two);
				Entrar.login();
			}
			repetir++;
			Tempo.esperar(proximoPronto, "Aguarndando proximo pronto");
		}
	}

	public static void aguardandoParaUpar(long tempo, Aldeia aldeia) {
		new Thread(new Runnable() {
			public void run() {
				Tempo.esperar(tempo, "aguardando para Upar");
				aldeia.setMelhorar(true);
				System.out.println("\n -------------Aguardando..." + tempo);
			}
		}).start();
	}
}
