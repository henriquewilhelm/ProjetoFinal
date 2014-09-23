// (c)2014|carlosedusousa.

package arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConsultaDescNavio {
	public static void main(String[] args) {	
		// Consulta dados.
		ConsultaDescNavio a = new ConsultaDescNavio();
		// Todos
		a.ConsultaNavio('1', "eua"); 
		a.ConsultaNavio('2',"japao"); 
		a.ConsultaNavio('3',"inglaterra"); 
		a.ConsultaNavio('4',"franca"); 
		a.ConsultaNavio('5',"italia");
		
	}
	// Classe consulta todos os dados e imprime.
	public void ConsultaNavio(char p, String pais) {

		File arquivo = new File(consultaTipo(p, pais));

		// verifica se o arquivo exite ou não.
		if (arquivo.exists()) {

			try {
				// le o arquivo e imprime cada linha do mesmo no console
				FileReader fr = new FileReader(arquivo);
				BufferedReader br = new BufferedReader(fr);
				while (br.ready()) {
					System.out.println(br.readLine());
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Arquivo não encontrado");
		}

	}

	public String ConsultaNavio(int i ,String text, String pais){

		File arquivo = new File(consultaTipo(i, pais));

		// verifica se o arquivo exite ou não.
		if (arquivo.exists()) {

			try {
				// le o arquivo e imprime cada linha do mesmo no console
				FileReader fr = new FileReader(arquivo);
				BufferedReader br = new BufferedReader(fr);
				while (br.ready()) {
					text = text + "\n" + br.readLine();
				}
				br.close();
				return text;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Arquivo não encontrado");
		}
		return text;
	}
	
	
	public String consultaTipo(int tipo, String pais) {

		String documento = null;
		String[] eua = { "arquivos/eua/nimitz.txt", "arquivos/eua/uss_saratoga.txt","arquivos/eua/uss-forrestal.txt","arquivos/eua/uss-enterprise.txt","arquivos/eua/uss_gerald_ford.txt"};
		String[] italia = { "arquivos/italia/andrea-doria.txt", "arquivos/italia/caio-duilio.txt", "arquivos/italia/giuseppe-garibaldi.txt", "arquivos/italia/vittorio-veneto.txt", "arquivos/italia/sauro-bubmarine.txt"};
		String[] franca = { "arquivos/franca/jean=bart-1.txt", "arquivos/franca/jean-bart-2.txt", "arquivos/franca/richelieu.txt", "arquivos/franca/uss-john-stennis.txt", "arquivos/franca/hms-illustrious.txt" };
		String[] alemanha = { "arquivos/alemnaha/bismarck.txt", "arquivos/alemanha/tirpitz.txt", "arquivos/alemanha/gneisenag.txt", "arquivos/alemanha/hms hood.txt", "arquivos/alemanha/scharnhorst.txt" };
		String[] inglaterra = { "arquivos/inglaterra/admiral-graf.txt", "arquivos/inglaterra/uss_saratoga.txt", "arquivos/inglaterra/uss-enterprise.txt", "arquivos/inglaterra/uss-forrestal.txt", "arquivos/inglaterra/uss-hornet.txt", };
		String[] japao = { "arquivos/japao/akagi.txt", "arquivos/japao/ijn-musashi.txt", "arquivos/japao/shinano.txt", "arquivos/japao/shokaku.txt", "arquivos/japao/yamato.txt", };
		
		switch (tipo) {
		case 0:
			if (pais.equals("eua"))	
				documento = eua[0];
			else if (pais.equals("italia"))	
				documento = italia[0];
			else if (pais.equals("franca"))	
				documento = franca[0];
			else if (pais.equals("alemanha"))	
				documento = alemanha[0];
			else if (pais.equals("inglaterra"))	
				documento = inglaterra[0];
			else if (pais.equals("japao"))	
				documento = japao[0];
			break;
		
		case 1:
			if (pais.equals("eua"))	
				documento = eua[1];
			else if (pais.equals("italia"))	
				documento = italia[1];
			else if (pais.equals("franca"))	
				documento = franca[1];
			else if (pais.equals("alemanha"))	
				documento = alemanha[1];
			else if (pais.equals("inglaterra"))	
				documento = inglaterra[1];
			else if (pais.equals("japao"))	
				documento = japao[1];
			break;

		case 2:
			if (pais.equals("eua"))	
				documento = eua[2];
			else if (pais.equals("italia"))	
				documento = italia[2];
			else if (pais.equals("franca"))	
				documento = franca[2];
			else if (pais.equals("alemanha"))	
				documento = alemanha[2];
			else if (pais.equals("inglaterra"))	
				documento = inglaterra[2];
			else if (pais.equals("japao"))	
				documento = japao[2];
			break;
			
		case 3:
			if (pais.equals("eua"))	
				documento = eua[3];
			else if (pais.equals("italia"))	
				documento = italia[3];
			else if (pais.equals("franca"))	
				documento = franca[3];
			else if (pais.equals("alemanha"))	
				documento = alemanha[3];
			else if (pais.equals("inglaterra"))	
				documento = inglaterra[3];
			else if (pais.equals("japao"))	
				documento = japao[3];
			break;
			
		case 4:
			if (pais.equals("eua"))	
				documento = eua[4];
			else if (pais.equals("italia"))	
				documento = italia[4];
			else if (pais.equals("franca"))	
				documento = franca[4];
			else if (pais.equals("alemanha"))	
				documento = alemanha[4];
			else if (pais.equals("inglaterra"))	
				documento = inglaterra[4];
			else if (pais.equals("japao"))	
				documento = japao[4];
			break;
		}	
		return documento;
	}

}
