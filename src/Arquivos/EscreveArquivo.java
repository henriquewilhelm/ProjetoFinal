package arquivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscreveArquivo {
	
	public static void main(String[] args) {	
		// Consulta dados.
		EscreveArquivo a = new EscreveArquivo();
		// Todos
		a.escreve("eua"); 
		a.escreve("japao"); 
		a.escreve("inglaterra"); 
		a.escreve("franca"); 
		a.escreve("italia");
	}
	public void escreve(String texto) {
		// Cria um objeto que referencia um arquivo específico
		File arquivo = new File("arquivos/log.txt");

		try {
			// A chamada abaixo método irá sobreescrever o arquivo
			// FileWriter fw = new FileWriter( arquivo );

			// Utilizando o atributo true no construtor o arquivo não será
			// recriado
			FileWriter fw = new FileWriter(arquivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine(); // cria uma nova linha no arquivo
			bw.write(texto);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
