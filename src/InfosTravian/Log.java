package InfosTravian;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {

	public static void geraLog(String arquivo, String dados) {
		FileWriter arq;
		try {
			arq = new FileWriter("C:\\Users\\helio\\Desktop\\"+arquivo);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf("+--Dados--+%n");
			gravarArq.printf("+-------------+%n");
			gravarArq.printf(dados);
			
			arq.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}



 
    