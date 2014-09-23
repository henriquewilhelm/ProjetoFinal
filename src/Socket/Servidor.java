package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import arquivos.EscreveArquivo;
import jogo.Jogador;
/**
 * Batalha Naval (Ultimate Battle) - Versao 2.0
 * (Servidor)
 * Essa classe � responsavel por cria um serversocket, ela espera pela conexao de dois clientes,
 * uma thread fica responsavel pelo recebimento dos dados de cada cliente para nao ocupar a 
 * a classe enquanto ela espera por uma saida, que conta com ajuda de um ArrayList de PrintStream,
 * alem deste atributo ela possui uma porta.
 * Obs: A ideia � criar um banco de dados para cadastro e login dos jogadores, alem de gerar um 
 * log em arquivo... 
 */

public class Servidor {
	 
	   private int porta;	
	   private List<PrintStream> clientes;  // Arraylist de PrintStream (Saida Padrao do Server/Socket)
	   private PrintStream saida;			// Saida Padrao do Server/Socket
	   private ServerSocket servidor; 
	   private int numeroConexao = 1;
	   private int totalConexoes= 0;
	   private Jogador player1;
	   private Jogador player2;
	   
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
		   			EscreveArquivo log = new EscreveArquivo();
		   			// Escreve no Console
		   			System.out.println("* Servidor do Projeto do GeracaoTEC (Batalha Naval) - Porta " + this.porta + " aberta...");
		   			log.escreve("* Servidor do Projeto do GeracaoTEC (Batalha Naval) - Porta " + this.porta + " aberta..."); 
		   			this.player1 = new Jogador();
		   			this.player2 = new Jogador();
		   			// Esperando clientes	
		   			while (true) {
		   				if (numeroConexao<=2){
		   					// Aceita um cliente
		   					Socket cliente = servidor.accept();
		   					System.out.println("- Handshake realizado - Conexao numero: " + numeroConexao + " Endereco do Cliente: " + cliente.getInetAddress().getHostAddress());
		   					log.escreve("- Handshake realizado - Conexao numero: " + numeroConexao + " Endereco do Cliente: " + cliente.getInetAddress().getHostAddress());
		   					// Le msgs vindas do cliente e adiconando ao ArrayList de Saida Padrao do Server/Socket
		   					saida = new PrintStream(cliente.getOutputStream());
		   					this.clientes.add(numeroConexao-1, saida);
	       
		   					// Thread para receber mensagens do cliente (InputStream e Servidor)
		   					ThreadServInput ThreadServ = new ThreadServInput(cliente.getInputStream(), this, numeroConexao, this.player1, this.player2, log);
		   					new Thread(ThreadServ).start();
		   				}
		   			
		   				if (numeroConexao==3){
		   					//broadCast("PRONTO");
		   				}
		   				if (numeroConexao>=3){
		   					numeroConexao=1;
		   				}
		   				else{
		   					numeroConexao++;
		   				}
		   			}
		   	}catch (Exception e) {
		   			// TODO: handle exception
		   			e.printStackTrace();
		   			servidor.close();
		   	}	 
	   
	   }
	   public void desconecta() throws IOException{
		   		broadCast("$QUIT");
		   		servidor.close();
	   }
	   // Envia mensagem a todos clientes conectados;
	   public void broadCast(String msg) {
		   			//System.out.println("servidor broadcast: "+msg);
		   			// Envia mensagem para todos
		   			for (PrintStream cliente : this.clientes) {
		   							cliente.println(msg);
		   			}
	   }
	   // Envia mensagem para cliente especifico
	   public void uniCast(int index, String msg) {
		   			// Envia mensagem para cliente especifico
		   			clientes.get(index).println(msg);
		   			//System.out.println("servidor unicast para cliente "+index+": "+msg);
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
			new Servidor(22222).conecta();
	   }
}
	