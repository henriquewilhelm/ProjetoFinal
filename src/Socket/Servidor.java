package Socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Servidor {
	 
	   private int porta;	
	   private List<PrintStream> clientes;  // Arraylist de PrintStream (Saida Padrao do Server/Socket)
	   private PrintStream saida;			// Saida Padrao do Server/Socket
	   private ServerSocket servidor; 
	   
	   // Construtor (Recebe porta)
	   public Servidor (int porta) {
	     this.porta = porta;
	     this.clientes = new ArrayList<PrintStream>();
	   }
	   // Metodo conecta na host e porta e retorna o PrintStream (Saida padrao do socket)
	   public void conecta () throws IOException {
		   	try{
		   			// Cria e instancia ServerSocket (Passando a Porta)
		   			servidor = new ServerSocket(this.porta);
		   			System.out.println("Porta " + this.porta + " aberta!");
		   
		   			// Esperando clientes
		   			while (true) {
		   						// Aceita um cliente
		   					Socket cliente = servidor.accept();
		   					System.out.println("Nova conexão com o cliente " +   
		   							cliente.getInetAddress().getHostAddress());
	       
		   					// Lê msgs vinda do cliente e adiconando ao ArrayList de Saida Padrao do Server/Socket
		   					saida = new PrintStream(cliente.getOutputStream());
		   					this.clientes.add(saida);
	       
		   					// Thread para receber mensagens do cliente (InputStream e Servidor)
		   					ThreadServInput ThreadServ = new ThreadServInput(cliente.getInputStream(), this);
		   					new Thread(ThreadServ).start();
		   			}
		   	}catch (Exception e) {
		   			// TODO: handle exception
		   			e.printStackTrace();
		   			servidor.close();
		   	}
	   
	   }
	   // Envia mensagem a todos clientes conectados;
	   public void broadCast(String msg) {
		   			// Envia mensagem para todos
		   			for (PrintStream cliente : this.clientes) {
		   							cliente.println(msg);
		   			}
	   }
	   public static void main(String[] args) throws  UnknownHostException, IOException {
			// inicia o servidor
			new Servidor(44444).conecta();
	   }
}
	