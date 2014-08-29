// (c)2014|carlosedusousa.

package Arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConsultaDescNavy {

	// Classe consulta todos os dados e imprime.
	public void consultaNavy(char p) {

		File arquivo = new File(consultaTipo(p));

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

	public String consultaTipo(char p) {

		String documento = null;

		switch (p) {
		case 'n':
			documento = "doc/perola.dat";
			break;
				
		case 'd':
			documento = "doc/destroyer.dat";
			break;
		
		case 's':
			documento = "doc/submarino.dat";
			break;

		case 'c':
			documento = "doc/costa.dat";
			break;
			
		case 't':
			documento = "doc/titanic.dat";
			break;
			
		case 'p':
			documento = "doc/plataforma.dat";
			break;
			
		case 'b':
			documento = "doc/bote.dat";
			break;
			
		case 'k':
			documento = "doc/canoa.dat";
			break;
			
		}	
		return documento;

	}

}
