// (c)2014|carlosedusousa.

package Arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConsultaDescNavio {

	// Classe consulta todos os dados e imprime.
	public void ConsultaNavio(char p) {

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

	public void ConsultaNavioTextFiel(int i, JTextArea text){

		File arquivo = new File(consultaTipo(i));

		// verifica se o arquivo exite ou não.
		if (arquivo.exists()) {

			try {
				// le o arquivo e imprime cada linha do mesmo no console
				FileReader fr = new FileReader(arquivo);
				BufferedReader br = new BufferedReader(fr);
				while (br.ready()) {
					text.setText(text.getText() + "\n" + br.readLine());
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Arquivo não encontrado");
		}

	}
	
	
	public String consultaTipo(int i) {

		String documento = null;

		switch (i) {
		case 0:
			documento = "doc/barco.dat";
			break;
		
		case 1:
			documento = "doc/costeiro.dat";
			break;

		case 2:
			documento = "doc/submarino.dat";
			break;
			
		case 3:
			documento ="doc/destroyer.dat";
			break;
			
		case 4:
			documento = "doc/cargueiro.dat";
			break;
		}	
		return documento;
	}

}
