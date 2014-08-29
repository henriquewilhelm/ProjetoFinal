package Socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	     
	   private String host;
	   private int porta;
	   private boolean status = false;
	   private Socket cliente;
	   private PrintStream saida; 
	   private String comandoEntrada;  
	  
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
	 
	 