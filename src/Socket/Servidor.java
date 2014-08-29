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
	   private int numeroConexao = 1;
	   
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
		   				if (numeroConexao<=2){
		   						// Aceita um cliente
		   					Socket cliente = servidor.accept();
		   					System.out.println("Conexão numero: " + numeroConexao + " Endereço do Cliente: " +   
		   							cliente.getInetAddress().getHostAddress());
		   					
		   					// Lê msgs vinda do cliente e adiconando ao ArrayList de Saida Padrao do Server/Socket
		   					saida = new PrintStream(cliente.getOutputStream());
		   					this.clientes.add(saida);
	       
		   					// Thread para receber mensagens do cliente (InputStream e Servidor)
		   					ThreadServInput ThreadServ = new ThreadServInput(cliente.getInputStream(), this, numeroConexao);
		   					new Thread(ThreadServ).start();
		   				}
		   				if (numeroConexao==3){
		   					broadCast("PRONTO");
		   				}
		   				if (numeroConexao>=3){
		   					numeroConexao=0;
		   				}
		   				numeroConexao++;
		   			}
		   			
		   	}catch (Exception e) {
		   			// TODO: handle exception
		   			e.printStackTrace();
		   	}	 
	   
	   }
	   public void desconecta() throws IOException{
		   		broadCast("QUIT");
		   		servidor.close();
	   }
	   // Envia mensagem a todos clientes conectados;
	   public void broadCast(String msg) {
		   			// Envia mensagem para todos
		   			for (PrintStream cliente : this.clientes) {
		   							cliente.println(msg);
		   			}
	   }
	   // Envia mensagem para cliente especifico
	   public void uniCast(int index, String msg) {
		   			// Envia mensagem para cliente especifico
		   			clientes.get(index).println(msg);
	   }
	   // Getters and Setters
	   public int getNumeroConexao() {
			return numeroConexao;
	   }
	   public void setNumeroConexao(int numeroConexao) {
			this.numeroConexao = numeroConexao;
	   }
	   public static void main(String[] args) throws  UnknownHostException, IOException {
			// inicia o servidor
			new Servidor(33333).conecta();
	   }
}
	