package Config;

public class Tempo {
	
	
	public static void esperar(long tempo, String mensagem) {
		try {
			System.out.println("\n ---------------------------------------------\n");
			System.out.println("\n\nEsperando-"+mensagem+" \tTempo:" + tempo);
			System.out.println("\n ---------------------------------------------\n");
			Thread.sleep(tempo);

		} catch (InterruptedException e) {
			System.out.println("Problemas na Classe Tempo método esperar");
		}
	}

}
