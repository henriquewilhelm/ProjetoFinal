package Socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.0
 * (Cliente)
 * Essa classe é responsavel por cria um socket, ela realiza a conexao com o host e porta passados por
 * parametro, uma thread fica responsavel pelo recebimento dos dados do servidor para nao ocupar a 
 * a classe enquanto ela espera por uma saida.
 */


public class Cliente {
	     
	   private String host;
	   private int porta;
	   private boolean status = false;
	   private Socket cliente;
	   private PrintStream saida; 
	   private String comandoEntrada = "";  
	  
	   // Construtor (Recebe nome do host e porta)
	   public Cliente (String host, int porta) throws UnknownHostException, IOException {
		   this.host = host;
		   this.porta = porta;
	   }
	   // Metodo conecta na host e porta e retorna o PrintStream (Saida padrao do socket)
	   public PrintStream conecta () throws UnknownHostException, IOException{
			 			// Constroi e instancia Socket
			 			cliente = new Socket(this.host, this.porta);
		        		        		  
		        		// Thread para receber mensagens do servidor (InputStream e Cliente)
		       		   	ThreadClientInput ThreadCli = new ThreadClientInput(cliente.getInputStream(), this);
		       		   	new Thread(ThreadCli).start();
		       		   
		       		   	// lê msgs vinda do servidor
		       		   	saida = new PrintStream(cliente.getOutputStream());
		       		   	status = true;		// true == conectado
		       		   	return saida;	
		   }
	   	// Metodo desconecta do socket
		public void desconecta() throws IOException{
		        		  cliente.close();  // Fecha socket 
		        		  status = false;	// false == desconectado
		        		  saida.close();	// Fecha o PrintStream (Saida padrao)
		}	
		// Getters and Setters
		public String getComandoEntrada() {
			return comandoEntrada;
		}
		public void setComandoEntrada(String comandoEntrada) {
			this.comandoEntrada = comandoEntrada;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
}
	 
	 