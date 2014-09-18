	package Socket;

import java.util.Scanner;
import java.io.InputStream;

import Jogo.Jogador;
/**
 * Batalha Naval (Ultimate Battle) - Versao 2.0
 * (Thread Server Input)
 * Essa classe (thread) � responsavel pelo recebimento dos dados de cada cliente para nao ocupar a 
 * a classe "Pai".
 */


public class ThreadServInput implements Runnable {
	 
	   private InputStream cliente;
	   private Servidor servidor;
	   private int numeroConexao;
	   private boolean login;
	   private boolean enviaDados;
	   private Jogador player1;
	   private Jogador player2;
	   
	   //
	   public ThreadServInput(InputStream cliente, Servidor servidor, int numeroConexao, Jogador player1,  Jogador player2) {
	     this.cliente = cliente;
	     this.servidor = servidor;
	     this.numeroConexao = numeroConexao;
	     this.player1 = player1;
	     this.player2 = player2;
	   }
	   // Servidor recebe msgs de um cliente e envia a todos clientes;
	   public void run() {
		   		int contador = 0;
		   		Scanner s = new Scanner(this.cliente);
		   		String nome = "teste", senha = "123456", pais="";
		   		int imagem = 0, medalhas = 0, vitorias = 0, derrotas = 0, totalJogos = 0;
		   		int numeroMsg = 0;		
		   		char[] protocolo;
		   		try{
		   			while (s.hasNextLine()) {
		   					/** A primeira letra da String sera o identificador
		   				 	* # - Escolha de Pecas/Posicoes (Enquanto < numero De Navio)
		   				 	* @ - Ataque ao oponente
		   				 	* & - Ataque
		   				 	* $ - Sistema (Quit, etc)
		   				 	* Demais seram identificadas como Chat...
		   				 	*/
   							String msg = s.nextLine();
   							numeroMsg++;
   							protocolo = msg.toString().toCharArray();
   	   						// Msg do sistema (Comunicacao do Jogo - começa, game over e parabens)
   							if (protocolo[0] == '$') {
   									if (this.numeroConexao == 1){
   	   										// Informa jogador e  do player 1
   	   										// protocolo[1]; � o valor da posicao
   	   										if (leStringProtocolo(protocolo).equals(nome)){
   	   												login = true;
   	   												servidor.uniCast(0,"$OK1");
   	   										}
   	   										else if (leStringProtocolo(protocolo).equals(senha)){
   	   											if (login){
   	   												servidor.uniCast(0,"$OK2");
   	   												login = false;
   	   												enviaDados = true;
   	   											}
   	   										}
   	   										else if (leStringProtocolo(protocolo).equals("OK") && enviaDados){
   	   											imagem = 5;
												pais="eua";
												medalhas = 5; 
												vitorias = 29; 
												derrotas = 5; 
												totalJogos = 34;
  												if (contador == 0){
  													Thread.sleep(1000);
  													servidor.uniCast(0,"$"+imagem);
  												}
  												else if (contador == 1){
  													Thread.sleep(1000);
  													servidor.uniCast(0,"$"+pais);
  												}
  												else if (contador == 2){
  													Thread.sleep(1000);
  													servidor.uniCast(0,"$"+medalhas);
  												}
  												else if (contador == 3){
  													Thread.sleep(1000);
  													servidor.uniCast(0,"$"+vitorias);
  												}
  												else if (contador == 4){
  													Thread.sleep(1000);
  													servidor.uniCast(0,"$"+derrotas);
  												}	
  												else if (contador == 5){
  													Thread.sleep(1000);
  													servidor.uniCast(0,"$"+totalJogos);
  												}
  												else{
  													Thread.sleep(1000);
  													servidor.uniCast(0,"$OK3");
  													enviaDados = false;
  												}
  												contador++;
   	   										}
   	   										else{
	   											servidor.uniCast(0,"$Dado incorreto");
	   										}
   	   										System.out.println("Informacao do cliente 1: " + msg);
   									}	
   									if (this.numeroConexao == 2){		
   										// Marca local da pe�a/navio no tabuleiro/mapa do player 2
   										// protocolo[1]; � o valor da posicao
   										if (leStringProtocolo(protocolo).equals(nome)){
   	   											login = true;
   	   											servidor.uniCast(1,"$OK1");
   										}
   										else if (leStringProtocolo(protocolo).equals(senha)){
   	   											if (login){
   	   												servidor.uniCast(1,"$OK2");
	   												login = false;
	   												enviaDados = true;
   	   											}
   										}
   										else if (leStringProtocolo(protocolo).equals("OK") && enviaDados){
	   										imagem = 2;
											pais="japao";
											medalhas = 1; 
											vitorias = 5; 
											derrotas = 15; 
											totalJogos = 20;
												if (contador == 0){
													Thread.sleep(1000);
													servidor.uniCast(1,"$"+imagem);
												}
												else if (contador == 1){
													Thread.sleep(1000);
													servidor.uniCast(1,"$"+pais);
												}
												else if (contador == 2){
													Thread.sleep(1000);
													servidor.uniCast(1,"$"+medalhas);
												}
												else if (contador == 3){
													Thread.sleep(1000);
													servidor.uniCast(1,"$"+vitorias);
												}
												else if (contador == 4){
													Thread.sleep(1000);
													servidor.uniCast(1,"$"+derrotas);
												}	
												else if (contador == 5){
													Thread.sleep(1000);
													servidor.uniCast(1,"$"+totalJogos);
												}
												else{
													Thread.sleep(1000);
													servidor.uniCast(1,"$OK3");
													enviaDados = false;
												}
												contador++;
	   									}
   										else {
   	   										servidor.uniCast(1,"$Dado incorreto");
   										}
   										System.out.println("Informacao do cliente 2: " + msg);
   									}	
   									if (msg.equals("$QUIT")){ // Mensagens do Sistema (Quit)
   		   								// Desconecta
   		   								servidor.desconecta();
   									}
   							}
   	   						// Msg do Banco de Dados (Jogador, Ranking)
   	   						else if (protocolo[0] == '%') {
   	   								if (this.numeroConexao == 1){
   	   										// Informa jogador e  do player 1
   	   										// protocolo[1]; � o valor da posicao
   	   										if (leStringProtocolo(protocolo).equals("H")){
   	   											servidor.uniCast(0,"Fulano - 0.59");
   	   											Thread.sleep(500);
   	   											servidor.uniCast(0,"Beltrano - 1.29");
   	   											Thread.sleep(500);
   	   											servidor.uniCast(0,"Ciclano - 2.49");
   	   											Thread.sleep(500);
   	   											servidor.uniCast(0,"Deltrano - 3.29");
   	   											Thread.sleep(500);
   	   											servidor.uniCast(0,"Teste - 4.00");
   	   										}
   	   										System.out.println("Solicitacao de Consulta ao BD do cliente 1: " + msg);
   	   								}	
   	   								if (this.numeroConexao == 2){		
   	   										if (leStringProtocolo(protocolo).equals("H")){
   	   											servidor.uniCast(1,"Fulano - 0.59");
   	   											Thread.sleep(500);
   	   											servidor.uniCast(1,"Beltrano - 1.29");
   	   											Thread.sleep(500);
   	   											servidor.uniCast(1,"Ciclano - 2.49");
   	   											Thread.sleep(500);
   	   											servidor.uniCast(1,"Deltrano - 3.29");
   	   											Thread.sleep(500);
   	   											servidor.uniCast(1,"Teste - 4.00");
	   										}
   	   										System.out.println("Solicitacao de Consulta ao BD do cliente 2: " + msg);
   	   								}		
   	   						}
   							//Marcando as escolhas seu Mapa .
   	   						else if (protocolo[0] == '#') {
   	   									if (this.numeroConexao == 1){
   	   											// Marca local da pe�a/navio no tabuleiro/mapa do player
   	   											// protocolo[1]; � o valor da posicao
   	   											player1.addHeroi(leIntProtocolo(protocolo));
   	   											System.out.println("Servidor: Player 1 - Navio " + player1.getContadorHeroi() + 
   	   																		" na posicao " + player1.getContadorPosicao()+ " "
   	   																				+ "recebeu "+ leIntProtocolo(protocolo));
   	   									}	
   	   									if (this.numeroConexao == 2){		
   	   											// Marca local da pe�a/navio no tabuleiro/mapa do player
   	   											// protocolo[1]; � o valor da posicao
   	   											player2.addHeroi(leIntProtocolo(protocolo));
   	   												System.out.println("Servidor: Player 2 - Navio " + player2.getContadorHeroi() + 
   	   																		" na posicao " + player2.getContadorPosicao()+ " "
   	   																				+ "recebeu "+ leIntProtocolo(protocolo));
   	   									}
   	   									if (player1.isVez() && player2.isVez()){
   	   											servidor.uniCast(0,"$ini");
   	   									}
   	   						}
   							// Ataca adversario (Mapa adversario).
   	   						else if (protocolo[0] == '@') {
   	   								if (this.numeroConexao == 1){
   	   										// Marca local da pe�a/navio no tabuleiro/mapa do player 1
   	   										// protocolo[1]; � o valor da posicao
   	   										if (player1.verificaPosicao(leIntProtocolo(protocolo))){
   	   											servidor.uniCast(0,"&"+leIntProtocolo(protocolo));
   	   											servidor.uniCast(1,msg);
   	   											player2.setVida(player2.getVida()-1);
   	   											Thread.sleep(500);
   	   											// Escreve no Chat
   	   											servidor.broadCast("Servidor: Ataque do Player 1 na Posicao (" + leIntProtocolo(protocolo) + ") bem sucedido. " +
																	player2.getHerois().get(player2.verificaHeroi(leIntProtocolo(protocolo))).getNome()  + 
																								" sofreu danos. Vida atual (" + player2.getVida() + ")");
   	   										}
   	   										else{
   	   											servidor.uniCast(0,"*"+leIntProtocolo(protocolo));
   	   											servidor.uniCast(1,msg);
   	   											Thread.sleep(500);
   	   											servidor.broadCast("Servidor: Ataque do Player 1 na Posicao (" + leIntProtocolo(protocolo) + ") falhou. Player 2 sua vez...");
   	   										}
   	   								}	 
   	   								if (this.numeroConexao == 2){		
   	   										// Marca local da pe�a/navio no tabuleiro/mapa do player 2
   	   										// protocolo[1]; � o valor da posicao
   	   										if (player2.verificaPosicao(leIntProtocolo(protocolo))){
   	   											servidor.uniCast(1,"&"+leIntProtocolo(protocolo));
   	   											servidor.uniCast(0,msg);
   	   											Thread.sleep(500);
   	   											player1.setVida(player1.getVida()-1);
   	   											// Escreve no Chat
   	   											servidor.broadCast("Servidor: Ataque do Player 2 na Posicao (" + leIntProtocolo(protocolo) + ") bem sucedido. " +
																player1.getHerois().get(player1.verificaHeroi(leIntProtocolo(protocolo))).getNome()  + 
																							" sofreu um dano. Vida atual (" + player1.getVida() + ")");
   	   										}
   	   										else {
   	   											servidor.uniCast(1,"*"+leIntProtocolo(protocolo));
   	   											servidor.uniCast(0,msg);
   	   											// Escreve no Chat
   	   											Thread.sleep(1500);
   	   											servidor.broadCast("Servidor: Ataque do Player 2 na Posicao (" + leIntProtocolo(protocolo) + ") falhou. Player 1 sua vez...");
   	   										}
   	   									}
   	   						}
   	   						else {
   	   								servidor.broadCast("Chat: " + msg);
   	   						}
							if (player1.getVida() == 0){
										Thread.sleep(500);
										// Atualiza informacoes de quem ganhou
										servidor.uniCast(1,"$win");
										player2.setVitorias(player2.getVitorias()+1);
										player2.setTotalJogos(player2.getTotalJogos()+1);
										// Atualiza informacoes de quem perdeu
										servidor.uniCast(0,"$over");
										player1.setDerrotas(player1.getDerrotas()+1);
										player1.setTotalJogos(player1.getTotalJogos()+1);
										// Atualiza Medalhas de quem ganhou
										if (player2.getVitorias()%5==0)
											player2.setMedalhas(player2.getVitorias()/5);
							}
							if (player2.getVida() == 0) {
										Thread.sleep(500);
										// Atualiza informacoes de quem ganhou
										servidor.uniCast(0,"$win");
										player1.setVitorias(player1.getVitorias()+1);
										player1.setTotalJogos(player1.getTotalJogos()+1);
										// Atualiza informacoes de quem perdeu
										servidor.uniCast(1,"$over");
										player2.setDerrotas(player2.getDerrotas()+1);
										player2.setTotalJogos(player2.getTotalJogos()+1);
										// Atualiza Medalhas de quem ganhou
										if (player2.getVitorias()%5==0)
											player2.setMedalhas(player2.getVitorias()/5);
							}
		   				}
		   		}catch (Exception e) {
		   			s.close();
		   			e.printStackTrace();
		   		}
	   }
	public Jogador getPlayer1() {
		return player1;
	}
	public void setPlayer1(Jogador player1) {
		this.player1 = player1;
	}
	public Jogador getPlayer2() {
		return player2;
	}
	public void setPlayer2(Jogador player2) {
		this.player2 = player2;
	}
	public int leIntProtocolo(char[] protocolo){
		String palavra;
		if  (protocolo.length==3)
			palavra = String.valueOf(protocolo[1]) + String.valueOf(protocolo[2]);
		else 
			palavra = String.valueOf(protocolo[1]);
		
		return Integer.parseInt(palavra);
	}
	
	public String leStringProtocolo(char[] protocolo){
		char[] palavra = new char[protocolo.length-1];
		for (int i=0; i < palavra.length; i++){
			palavra[i] = protocolo[i+1];
		}
		return  String.valueOf(palavra);
	}
	public InputStream getCliente() {
		return cliente;
	}
	public void setCliente(InputStream cliente) {
		this.cliente = cliente;
	}
	public Servidor getServidor() {
		return servidor;
	}
	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	public int getNumeroConexao() {
		return numeroConexao;
	}
	public void setNumeroConexao(int numeroConexao) {
		this.numeroConexao = numeroConexao;
	}  
}