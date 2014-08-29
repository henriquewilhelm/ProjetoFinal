package Socket;

import java.util.Scanner;
import java.io.InputStream;
/**
 * Batalha Naval (Ultimate Battle) - Versao 2.0
 * (Thread Server Input)
 * Essa classe (thread) é responsavel pelo recebimento dos dados de cada cliente para nao ocupar a 
 * a classe "Pai".
 */


public class ThreadServInput implements Runnable {
	 
	   private InputStream cliente;
	   private Servidor servidor;
	   private int numeroConexao;

	 
	   public ThreadServInput(InputStream cliente, Servidor servidor, int numeroConexao) {
	     this.cliente = cliente;
	     this.servidor = servidor;
	     this.numeroConexao = numeroConexao;
	   }
	   
	   // Servidor recebe msgs de um cliente e envia a todos clientes;
	   public void run() {
		   		Scanner s = new Scanner(this.cliente);
		   		int numeroEscolhas = 0;
		   		int numeroJogadas = 0;
		   		int numeroMsg = 0;
		   		int numeroNavio = 5;
		   		int numeroNavioAbatidos=0;
		   		
		   		char[] protocolo;
		   		try{
		   			while (s.hasNextLine()) {
		 
		   				// Protocolo de Comunicação
		   				/** A primeira letra da String sera o identificador
		   				 * # - Escolha de Pecas/Posicoes (Enquanto < numero De Navio)
		   				 * @ - Ataque ao oponente
		   				 * $ - Sistema (Quit, etc)
		   				 * Demais seram identificadas como Chat...
		   				 */
   							String msg = s.nextLine();
   							numeroMsg++;
   							// Mensagens do Sistema (Quit)
   							
   								protocolo = msg.toString().toCharArray();
   								// Marcando as escolhas seu Mapa .
   								if (protocolo[0] == '#' && numeroEscolhas < numeroNavio) {
   									if (this.numeroConexao == 1){
   										// Marca local da peça/navio no tabuleiro/mapa do player
   										// protocolo[1]; é o valor da posicao
   										System.out.println("Servidor Anota Escolha de posicao " + msg + " para cliente 1");
   									}	
   									if (this.numeroConexao == 2){		
   										// Marca local da peça/navio no tabuleiro/mapa do player
   										// protocolo[1]; é o valor da posicao
   										System.out.println("Servidor Anota Escolha de posicao " + msg + " para cliente 2");
   									}	
   									numeroEscolhas++;
   								}
   								//telnet localhost 33333	
   								// Ataca adversario (Mapa adversario).
   								else if (protocolo[0] == '@' && numeroEscolhas >= numeroNavio) {
									if (this.numeroConexao == 1){
										// Marca local da peça/navio no tabuleiro/mapa do player 1
										// protocolo[1]; é o valor da posicao
										servidor.uniCast(1,msg);
										System.out.println("Ataque do cliente 1 na Posicao: " + msg + " do Mapa do cliente 2");
									}	 
									if (this.numeroConexao == 2){		
										// Marca local da peça/navio no tabuleiro/mapa do player 2
										// protocolo[1]; é o valor da posicao
										servidor.uniCast(0,msg);
										System.out.println("Ataque do cliente 2 na Posicao: " + msg + " do Mapa do cliente 1");
									}	
									
   								}
   								// Feedback (Informa quando destroi um navio)
   								else if (protocolo[0] == '$') {
   									if (this.numeroConexao == 1){
   										// Informa jogador e  do player 1
   										// protocolo[1]; é o valor da posicao
   										servidor.uniCast(1,msg);
   										System.out.println("Informacao do cliente 1: " + msg);
   									}	
   									if (this.numeroConexao == 2){		
   										// Marca local da peça/navio no tabuleiro/mapa do player 2
   										// protocolo[1]; é o valor da posicao
   										servidor.uniCast(0,msg);
   										System.out.println("Informacao do cliente 2: " + msg);
   									}	
   								}
   								else if (msg.equals("QUIT")){ 
   									// Desconecta
   									servidor.desconecta();
   								}
   								else {
   										servidor.broadCast("Chat: " + msg);
   								}
   									
   							
		   			}
		   		}catch (Exception e) {
		   			s.close();
		   			e.printStackTrace();
		   		}
	   }
	 }