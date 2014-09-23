	package socket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.InputStream;

import jogo.Jogador;
import arquivos.EscreveArquivo;
import bancoDados.JDBCConnect;
import bancoDados.JogadorDAO;
/**
 * Batalha Naval (Ultimate Battle) - Versao 2.0
 * (Thread Server Input)
 * Essa classe (thread) ï¿½ responsavel pelo recebimento dos dados de cada cliente para nao ocupar a 
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
	   private EscreveArquivo log;
	   //
	   public ThreadServInput(InputStream cliente, Servidor servidor, int numeroConexao, Jogador player1, Jogador player2, EscreveArquivo log) {
			 this.cliente = cliente;
		     this.servidor = servidor;
		     this.numeroConexao = numeroConexao;
		     this.player1 = player1;
		   	 this.player2 = player2;
		   	 this.log = new EscreveArquivo();
	}
		   // Servidor recebe msgs de um cliente e envia a todos clientes;
	   public void run() {
			 int contador = 0;
			 Scanner s = new Scanner(this.cliente);   	
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
   							// Msg do sistema (Comunicacao do Jogo - comeÃ§a, game over e parabens)
   							if (protocolo[0] == '$') {
   									if (this.numeroConexao == 1){
   											log.escreve("<- Comunicacao - Protocolo (Login/Consulta BD) recebendo informacao do Jogador 1: " + msg);
   											System.out.println("<- Comunicacao - Protocolo (Login/Consulta BD) recebendo informacao do Jogador 1: " + msg);
   											if (!login){ //CONSULTA BD
   	   												player1.setNome(leStringProtocolo(protocolo));
   	   												login = validaNome(player1.getNome());
   	   												player1.setId(verificaId(player1.getNome()));
   	   												System.out.println("--> Comunicacao - Jogador 1 Consulta no BD por Nome: "+player1.getNome()+" e envia $OK1");
   	   												System.out.println("-> Comunicacao - Jogador 1 Consulta no BD por Id: "+player1.getId());
   	   												servidor.uniCast(0,"$OK1");
   	   										}
   	   										else if (!enviaDados){ //CONSULTA BD
   	   											if (login){
   	   												enviaDados = validaSenha(leStringProtocolo(protocolo), player1.getId());
   	   												System.out.println("--> Comunicacao - Jogador 1 Consulta no BD por Senha ****** e envia $OK2");
   	   												servidor.uniCast(0,"$OK2");
   	   											}
   	   											else 
   	   											System.out.println("-> Comunicacao - Jogador 1 Erro na senha...");
   	   										}
   	   										else if (leStringProtocolo(protocolo).equals("OK") && enviaDados){
  												if (contador == 0){
  													player1.setImagem(verificaImg(player1.getId()));
  													servidor.uniCast(0,"$"+this.player1.getImagem()); //CONSULTA BD
  													System.out.println("-> Comunicacao - Jogador 1 Consulta no BD por Imagem e envia $"+player1.getImagem());
  												}
  												else if (contador == 1){
  													player1.setPais(verificaPais(player1.getId()));
  													servidor.uniCast(0,"$"+this.player1.getPais()); //CONSULTA BD
  													System.out.println("-> Comunicacao - Jogador 1 Consulta no BD por Pais e envia $"+player1.getPais());
  												}
  												else if (contador == 2){
  													player1.setMedalhas(verificaMedalhas(player1.getId()));
  													servidor.uniCast(0,"$"+this.player1.getMedalhas()); //CONSULTA BD
  													System.out.println("-> Comunicacao - Jogador 1 Consulta no BD por Medalhas e envia $"+player1.getMedalhas());
  												}
  												else if (contador == 3){
  													player1.setVitorias(verificaVitorias(player1.getId()));
  													servidor.uniCast(0,"$"+this.player1.getVitorias()); //CONSULTA BD
  													System.out.println("-> Comunicacao - Jogador 1 Consulta no BD por Vitorias e envia $"+player1.getVitorias());
  												}
  												else if (contador == 4){
  													player1.setDerrotas(verificaDerrotas(player1.getId()));
  													servidor.uniCast(0,"$"+this.player1.getDerrotas()); //CONSULTA BD
  													System.out.println("-> Comunicacao - Jogador 1 Consulta no BD por Derrotas e envia $"+player1.getDerrotas());
  												}	
  												else if (contador == 5){
  													player1.setTotalJogos(verificaTotalJogos(player1.getId()));
  													servidor.uniCast(0,"$"+this.player1.getTotalJogos()); //CONSULTA BD
  													System.out.println("-> Comunicacao - Jogador 1 Consulta no BD por Total Jogos e envia $"+player1.getTotalJogos());
  												}
  												else{
  													System.out.println("--> Comunicacao - Jogador 1 Consulta no BD por Total Jogos e envia $OK3");
  													servidor.uniCast(0,"$OK3");
  													enviaDados = false;
  												}
  												contador++;
   	   										}
   	   										else{
	   											servidor.uniCast(0,"$Dado incorreto");
	   										}
   									}	
   									if (this.numeroConexao == 2){		
   										log.escreve("<- Comunicacao - Protocolo (Login/Consulta BD) recebendo informacao do Jogador 2 envia: " + msg);
   										System.out.println("<- Comunicacao - Protocolo (Login/Consulta BD) recebendo informacao do Jogador 2 envia: " + msg);
   										if (!login){ //CONSULTA BD
  												player2.setNome(leStringProtocolo(protocolo));
  												login = validaNome(player2.getNome());
  												player2.setId(verificaId(player2.getNome()));
  												System.out.println("--> Comunicacao - Jogador 2 Consulta no BD por Nome: "+player2.getNome()+" e envia $OK1");
  												System.out.println("-> Comunicacao - Jogador 2 Consulta no BD por Id: "+player2.getId());
  												servidor.uniCast(1,"$OK1");
  										}
  										else if (!enviaDados){ //CONSULTA BD
  											if (login){
  												enviaDados = validaSenha(leStringProtocolo(protocolo), player2.getId());
  												servidor.uniCast(1,"$OK2");
  												System.out.println("--> Comunicacao - Jogador 2 Consulta no BD por Senha: ****** e envia $OK2");
  											}
  										}
  										else if (leStringProtocolo(protocolo).equals("OK") && enviaDados){
  									
											if (contador == 0){
												player2.setImagem(verificaImg(player2.getId()));
												servidor.uniCast(1,"$"+this.player2.getImagem()); //CONSULTA BD
												System.out.println("-> Comunicacao - Jogador 2 Consulta no BD por Imagem e envia $"+player2.getImagem());
											}
											else if (contador == 1){
												player2.setPais(verificaPais(player2.getId()));
												servidor.uniCast(1,"$"+this.player2.getPais()); //CONSULTA BD
												System.out.println("-> Comunicacao - Jogador 2 Consulta no BD por Pais  e envia $"+player2.getPais());
											}
											else if (contador == 2){
												player2.setMedalhas(verificaMedalhas(player2.getId()));
												servidor.uniCast(1,"$"+this.player2.getMedalhas()); //CONSULTA BD
												System.out.println("-> Comunicacao - Jogador 2 Consulta no BD por Medalhas e envia $"+player2.getMedalhas());
											}
											else if (contador == 3){
												player2.setVitorias(verificaVitorias(player2.getId()));
												servidor.uniCast(1,"$"+this.player2.getVitorias()); //CONSULTA BD
												System.out.println("-> Comunicacao - Jogador 2 Consulta no BD por Vitorias  e envia $"+player2.getVitorias());
											}
											else if (contador == 4){
												player2.setDerrotas(verificaDerrotas(player2.getId()));
												servidor.uniCast(1,"$"+this.player2.getDerrotas()); //CONSULTA BD
												System.out.println("-> Comunicacao - Jogador 2 Consulta no BD por Derrotas e envia $"+player2.getDerrotas());
											}	
											else if (contador == 5){
												player2.setTotalJogos(verificaTotalJogos(player2.getId()));
												servidor.uniCast(1,"$"+this.player2.getTotalJogos()); //CONSULTA BD
												System.out.println("-> Comunicacao - Jogador 2 Consulta no BD por Total Jogos e envia $"+player2.getTotalJogos());
											}
											else{
												System.out.println("--> Comunicacao - Jogador 2 Consulta no BD por Total Jogos e envia $OK3");
												servidor.uniCast(1,"$OK3");
												enviaDados = false;
											}
											contador++;
	   									}
   										else {
   	   										servidor.uniCast(1,"$Dado incorreto");
   										}
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
   	   										// protocolo[1]; ï¿½ o valor da posicao
   	   										if (leStringProtocolo(protocolo).equals("H")){ 
   	   											servidor.uniCast(0,"teste"); //CONSULTA BD
   	   											Thread.sleep(500);
   	   											servidor.uniCast(0,"henrique"); //CONSULTA BD
   	   											Thread.sleep(500);
   	   											servidor.uniCast(0,"carlos"); //CONSULTA BD
   	   											Thread.sleep(500);
   	   											servidor.uniCast(0,"otavio"); //CONSULTA BD
   	   											Thread.sleep(500);
   	   											servidor.uniCast(0,"usuario"); //CONSULTA BD
   	   										}
   	   										System.out.println("Solicitacao de Consulta ao BD do cliente 1: " + msg);
   	   								}	
   	   								if (this.numeroConexao == 2){		
   	   										if (leStringProtocolo(protocolo).equals("H")){
   	   											servidor.uniCast(1,"alexandre"); //CONSULTA BD
   	   											Thread.sleep(500); 
   	   											servidor.uniCast(1,"henrique"); //CONSULTA BD
   	   											Thread.sleep(500);
   	   											servidor.uniCast(1,"carlos"); //CONSULTA BD
   	   											Thread.sleep(500);
   	   											servidor.uniCast(1,"jaison"); //CONSULTA BD
   	   											Thread.sleep(500);
   	   											servidor.uniCast(1,"otavio"); //CONSULTA BD
	   										}
   	   										System.out.println("Solicitacao de Consulta ao BD do cliente 2: " + msg);
   	   								}		
   	   						}
   							//Marcando as escolhas seu Mapa .
   	   						else if (protocolo[0] == '#') {
   	   									if (this.numeroConexao == 1){
   	   											System.out.println("<- Comunicacao - Protocolo (Jogo/Escolhas) recebendo informacao do Jogador 1: " + msg);
   	   											log.escreve("<- Comunicacao - Protocolo (Jogo/Escolhas) recebendo informacao do Jogador 1: " + msg);
   	   											// Marca local da peï¿½a/navio no tabuleiro/mapa do player
   	   											// protocolo[1]; ï¿½ o valor da posicao
   	   											player1.addHeroi(leIntProtocolo(protocolo));
   	   									}	
   	   									if (this.numeroConexao == 2){		
   	   											System.out.println("<- Comunicacao - Protocolo (Jogo/Escolhas) recebendo informacao do Jogador 2: " + msg);
   	   											log.escreve("<- Comunicacao - Protocolo (Jogo/Escolhas) recebendo informacao do Jogador 2: " + msg);
   	   											// Marca local da peï¿½a/navio no tabuleiro/mapa do player
   	   											// protocolo[1]; ï¿½ o valor da posicao
   	   											player2.addHeroi(leIntProtocolo(protocolo));
   	   										}
   	   									if (player1.isVez() && player2.isVez()){
   	   											servidor.uniCast(0,"$ini");
   	   											//System.out.println("COMECOU");
   	   									}
   	   						}
   							// Ataca adversario (Mapa adversario).
   	   						else if (protocolo[0] == '@') {
   	   								if (this.numeroConexao == 1){
   	   										// Marca local da peï¿½a/navio no tabuleiro/mapa do player 1
   	   										// protocolo[1]; ï¿½ o valor da posicao
   	   										if (player2.verificaPosicao(leIntProtocolo(protocolo))){
   	   											servidor.uniCast(0,"&"+leIntProtocolo(protocolo));
   	   											servidor.uniCast(1,msg);
   	   											player2.setVida(player2.getVida()-1);
   	   											Thread.sleep(500);
   	   											// Escreve no Chat
   	   											servidor.broadCast("Servidor: Ataque do Jogador "+player1.getNome()+" na Posicao (" + leIntProtocolo(protocolo) + ") bem sucedido. " +
																	player2.getHerois().get(player2.verificaHeroi(leIntProtocolo(protocolo))).getNome()  + 
																								" sofreu danos. Vida atual (" + player2.getVida() + ")");
   	   											log.escreve("<- Comunicacao - Protocolo (Jogo/Ataque) Jogador 1: Ataque "+player1.getNome()+" "
   	   													+ "na Posicao (" + leIntProtocolo(protocolo) + ") bem sucedido. Embarcação sofreu danos. Vida atual (" + player2.getVida() + ")");
   	   										}
   	   										else{
   	   											servidor.uniCast(0,"*"+leIntProtocolo(protocolo));
   	   											servidor.uniCast(1,msg);
   	   											Thread.sleep(500);
   	   											servidor.broadCast("Servidor: Ataque do Jogador "+player1.getNome()+" na Posicao (" + leIntProtocolo(protocolo) + ") falhou. Player 2 sua vez...");
   	   											log.escreve("<- Comunicacao - Protocolo (Jogo/Ataque) Jogador 1: Ataque do "+player1.getNome()+" na Posicao (" + leIntProtocolo(protocolo) + ") falhou. Passando a vez...");
   	   										}
   	   								}	 
   	   								if (this.numeroConexao == 2){		
   	   										// Marca local da peï¿½a/navio no tabuleiro/mapa do player 2
   	   										// protocolo[1]; ï¿½ o valor da posicao
   	   										if (player1.verificaPosicao(leIntProtocolo(protocolo))){
   	   											servidor.uniCast(1,"&"+leIntProtocolo(protocolo));
   	   											servidor.uniCast(0,msg);
   	   											Thread.sleep(500);
   	   											player1.setVida(player1.getVida()-1);
   	   											// Escreve no Chat
   	   											servidor.broadCast("Servidor: Ataque do Jogador "+player2.getNome()+" na Posicao (" + leIntProtocolo(protocolo) + ") bem sucedido. " +
																player1.getHerois().get(player1.verificaHeroi(leIntProtocolo(protocolo))).getNome()  + 
																							" sofreu um dano. Vida atual (" + player1.getVida() + ")");
   	   											log.escreve("<- Comunicacao - Protocolo (Jogo/Ataque) Jogador 1: Ataque do "+player2.getNome()+" "
   	   													+ "na Posicao (" + leIntProtocolo(protocolo) + ") bem sucedido. Embarcação sofreu danos. Vida atual (" + player1.getVida() + ")");
   	   										}
   	   										else {
   	   											servidor.uniCast(1,"*"+leIntProtocolo(protocolo));
   	   											servidor.uniCast(0,msg);
   	   											// Escreve no Chat
   	   											Thread.sleep(1500);
   	   											servidor.broadCast("Servidor: Ataque do Jogador "+player2.getNome()+" na Posicao (" + leIntProtocolo(protocolo) + ") falhou. Player 1 sua vez...");
   	   											log.escreve("<- Comunicacao - Protocolo (Jogo/Ataque) Jogador 2: Ataque do "+player2.getNome()+" na Posicao (" + leIntProtocolo(protocolo) + ") falhou. Passando a vez...");
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
										update(player1); //UPDATE BD
										update(player2); //UPDATE BD
										System.out.println("<- Comunicacao - Protocolo (Jogo/Ataque) Jogador 2 Vence...");
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
										update(player1); //UPDATE BD
										update(player2); //UPDATE BD
										System.out.println("<- Comunicacao - Protocolo (Jogo/Ataque) Jogador 1 Vence...");
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
	
	public boolean validaNome(String nome){
		try {
				JDBCConnect conexao = new JDBCConnect();
				java.sql.Connection con = conexao.criarConexao();
				JogadorDAO logando = new JogadorDAO();
				if (logando.verificaNome(con, nome)) {
					return true;	
				} else {
					System.out.println("! Erro na Consulta ao BD - Nome não localizado...");
					log.escreve("! Erro na Consulta ao BD - Nome não localizado...");
					con.close();
					return false;
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validaSenha(String senha, int id){
		try {
				JDBCConnect conexao = new JDBCConnect();
				java.sql.Connection con = conexao.criarConexao();
				JogadorDAO logando = new JogadorDAO();
				
				if (logando.verificaSenha(con, senha, id)) {
					return true;
				
				} else {
					System.out.println("! Erro na Consulta ao BD - Nome não localizado...");
					log.escreve("! Erro na Consulta ao BD - Nome não localizado...");
					con.close();
					return false;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public int verificaId(String nome){
		try {
				JDBCConnect conexao = new JDBCConnect();
				java.sql.Connection con = conexao.criarConexao();
				JogadorDAO logando = new JogadorDAO();
				return logando.buscaId(con, nome);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int verificaImg(int id){
		try {
				JDBCConnect conexao = new JDBCConnect();
				java.sql.Connection con = conexao.criarConexao();
				JogadorDAO logando = new JogadorDAO();
				return logando.buscaImg(con, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public String verificaPais(int id){
		try {
				JDBCConnect conexao = new JDBCConnect();
				java.sql.Connection con = conexao.criarConexao();
				JogadorDAO logando = new JogadorDAO();
				return logando.buscaPais(con, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public int verificaMedalhas(int id){
		try {
				JDBCConnect conexao = new JDBCConnect();
				java.sql.Connection con = conexao.criarConexao();
				JogadorDAO logando = new JogadorDAO();
				return logando.buscaMedalhas(con, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int verificaVitorias(int id){
		try {
				JDBCConnect conexao = new JDBCConnect();
				java.sql.Connection con = conexao.criarConexao();
				JogadorDAO logando = new JogadorDAO();
				return logando.buscaVitorias(con, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int verificaDerrotas(int id){
		try {
				JDBCConnect conexao = new JDBCConnect();
				java.sql.Connection con = conexao.criarConexao();
				JogadorDAO logando = new JogadorDAO();
				return logando.buscaDerrotas(con, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int verificaTotalJogos(int id){
		try {
				JDBCConnect conexao = new JDBCConnect();
				java.sql.Connection con = conexao.criarConexao();
				JogadorDAO logando = new JogadorDAO();
				return logando.buscaTotalJogos(con, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public void update(Jogador jogador){
		try {
				JDBCConnect conexao = new JDBCConnect();
				java.sql.Connection con = conexao.criarConexao();
				JogadorDAO logando = new JogadorDAO();
				logando.save(con, jogador);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}