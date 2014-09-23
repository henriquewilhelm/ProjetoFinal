package graficosCliente;

import java.awt.CardLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import audio.Trilha;
import socket.Cliente;
import jogo.Jogador;
import jogo.Posicao;

/**
 * Batalha Naval (Projeto GeracaoTec) - Versao 2.2 de Interface Grafica 
 * Classe: Thread Monitora Telas 
 * Thread (Processo Filho) que monitora o objeto Jogador e todas Sub-Telas da Tela Principal 
 * (JPanel), se orienta principalmente pelo o numero de rodadas para alterar a condicao dos 
 * componentes fazendo a iteracao entre eles, mas tambem utiliza outras FLAGs. Assim as Telas
 * podem se relacionar com mais facilidade sem interromper nenhuma funcionamento enquanto 
 * aguardam por alguma condicao...
 * Autor: Henrique Wilhelm
 */

public class ThreadMonitoraTelas extends Thread {
	// Declaracao das Classes construidas para o jogo 	
	private Jogador jogador;
	private Cliente cliente;
	private JPanel cards;
	private TelaJogador telaJogador;
	private TelaTabuleiro telaTabuleiro;
	private TelaNavios telaNavios;
	private TelaChat telaChat;
	private TelaConexao telaConexao;
	private TelaOpcoes telaOpcoes;
	private TelaRanking telaRanking;
	private TelaInicio telaInicio;
	private Trilha trilha;
	private TelaTrilha telaTrilha;
	// Construtor, recebe todas classes construidas para jogo e realiza a iteracao entre elas
	public ThreadMonitoraTelas(Jogador jogador, Cliente cliente,  JPanel cards,
							TelaJogador telaPlayer, TelaTabuleiro telaTabuleiro, 
							TelaNavios telaNavios, TelaChat telaChat, TelaConexao telaConexao, 
							TelaOpcoes telaOpcoes, TelaRanking telaRanking, TelaInicio telaInicio, TelaTrilha telaTrilha) {
		this.jogador = jogador;
		this.cliente = cliente;
		this.cards = cards;
		this.telaTabuleiro = telaTabuleiro;
		this.telaJogador =  telaPlayer;
		this.telaNavios = telaNavios;
		this.telaChat = telaChat;
		this.telaConexao = telaConexao;
		this.telaOpcoes = telaOpcoes;
		this.telaRanking = telaRanking;
		this.telaInicio = telaInicio;
		this.telaTrilha = telaTrilha;
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
	
	// Metodo que executa start() a Thread (Processo Filho)
	public void run() {
		try {
			
			this.trilha = new Trilha(1);
			
			int contador = 0;
			boolean statusLogin = false;
			boolean buscaInfo = false;
			String aux = ""; // String auxiliar do Chat
			// Enquanto Numero de Jogadas for menor que 100 (Numero de posicao de um tabuleiro)
			while (true) {
					System.out.flush();
					if (telaNavios.isEscolha()) { 	// Se tela Console marcar escolha TRUE
						// Se Heroi estiver vivo
						if (jogador.getHerois().get(jogador.getNumEscolhas()-1).isVivo()){
							// Percorrendo List de Herois
							for (int i=0; i<jogador.getHerois().get(jogador.getNumEscolhas()-1).getPosicao().length; i++){
								// Pegando todas posicoes de cada Heroi
								int posicao= jogador.getHerois().get(jogador.getNumEscolhas()-1).getPosicao()[i].getXY();
								if (!telaNavios.isVolta()){
										// Marcando posicoes dos Herois
										telaTabuleiro.getButtonsTab1()[posicao].setEnabled(false);
										telaTabuleiro.getButtonsTab1()[posicao].setFundo(telaTabuleiro.isPosicao(), jogador.getNumEscolhas()-1);
										telaTabuleiro.getButtonsTab1()[posicao].setFundo(i);
										// Adicionando Imagem do Navio a Tela Player
										String dirNavio = "img/navios/navio" + Integer.toString((jogador.getNumEscolhas())) + ".jpg";
										telaJogador.getNavios()[jogador.getNumEscolhas()-1].setIcon(new ImageIcon(dirNavio));
										
								}
								System.out.println("Thread Monitora Telas - Numero de Escolhas: " + jogador.getNumEscolhas() +" posicao: "+posicao);
							}
							if (jogador.getNumEscolhas() < 5){	// Atualiza Descricao do Navio na Tela Console	
									telaNavios.getLabel().setIcon(telaNavios.getImagem()[jogador.getNumEscolhas()]);
									telaNavios.getTextArea().setText("");
									telaNavios.getTextArea().setText(telaNavios.getConsulta().ConsultaNavio(jogador.getNumEscolhas(), telaNavios.getTextArea().getText(), jogador.getPais()));
							}
							// Incrementa uma rododa.
							jogador.setNumEscolhas(jogador.getNumEscolhas() + 1);
						}
						else { // Apresenta mensagem orientando usuario.
								JOptionPane.showMessageDialog(null,"Ops, primeiro escolha "
										+ "algum lugar no tabuleiro...");
						}
						telaNavios.setEscolha(false); // Marca escolha da Tela Console como FALSE		
					}
					// Atualiza a posicao da Tela Tabuleiro conforme Tela Console 
					telaTabuleiro.setPosicao(telaNavios.isPosicao());
					// Se tela Console marcar volta TRUE
					if (telaNavios.isVolta()){
						// Percorrendo List de Herois
						for (int i=0; i<jogador.getHerois().get(jogador.getNumEscolhas()-1).getPosicao().length; i++){
							// Pegando todas posicoes de cada Heroi para resetar Fundo (remover img navio)
							int posicao= jogador.getHerois().get(jogador.getNumEscolhas()-1).getPosicao()[i].getXY();
							telaTabuleiro.getButtonsTab1()[posicao].setEnabled(true); 
							telaTabuleiro.getButtonsTab1()[posicao].setFundo(); // (Mar azul) 
						}
						jogador.getHerois().get(jogador.getNumEscolhas()-1).setVivo(false); // Mata Navio
						telaNavios.setVolta(false); // Marca volta da Tela Console como FALSE
						telaJogador.getNavios()[jogador.getNumEscolhas()-1].setIcon(new ImageIcon()); // Remove Navio da Tela Player
						System.out.println("Thread Monitora Telas - Voltando escolha...");
					}
					if (jogador.getNumEscolhas() == 6) { // Se roda igual a 6 comeca o jogo
						telaTabuleiro.getCards().setVisible(true); // Mostra o Tabuleiro do Player 2
						CardLayout cl = (CardLayout)(telaNavios.getCards().getLayout());
						cl.show(telaNavios.getCards(), "2"); // Muda tela de exibicao da Tela Console
						// Percorre todos Herois e envia todas posicoes para o servidor
						for (int contadorHeroi = 0; contadorHeroi < jogador.getHerois().size(); contadorHeroi++){
							for (int contadorPosicao = 0; contadorPosicao < jogador.getHerois().get(contadorHeroi).getPosicao().length; contadorPosicao++){
								cliente.getSaida().println("#"+jogador.getHerois().get(contadorHeroi).getPosicao()[contadorPosicao].getXY());
							}
						}
						jogador.setNumEscolhas(jogador.getNumEscolhas()+1);
						this.trilha.stop();
						this.trilha = new Trilha(2);
					}

					if (telaInicio.getTelaTrilha().isAudio()){
						trilha.stop();
						telaInicio.getTelaTrilha().setAudio(false);
					}
							
					
					// Atualiza tela do chat (se conexao)
					if (cliente.isStatus()){
						String comandoEntrada = cliente.getComandoEntrada();
						// Verifica se mensagem nao é igual a ultima...
						if (!comandoEntrada.equals(aux)){
								char[] protocolo;
								protocolo = comandoEntrada.toCharArray();
								// Sofrando ataque do inimigo
								if (protocolo[0] == '@') {
									if (jogador.verificaPosicao(leIntProtocolo(protocolo))){
											int posicao = jogador.verificaHeroi(leIntProtocolo(protocolo));
											telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setEnabled(true); 
											telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setFundo();
											telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setFundo(1);
											telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setEnabled(false); 
											// Diminui 1 da vida do Jogador
											jogador.setVida(jogador.getVida()-1);
											// Diminui 1 da vida do Heroi (Navio)
											jogador.getHerois().get(posicao).setVida(jogador.getHerois().get(posicao).getVida()-1);
											// Retira uma ponto de cada barra de vida
											telaJogador.getCaixaVida().getComponent(jogador.getVida()).setVisible(false);
											telaNavios.getCaixaVida()[posicao].getComponent(jogador.getHerois().get(posicao).getVida()).setVisible(false);
											new ThreadSom(1).start();
											telaTabuleiro.setAnimacao(telaTabuleiro.getBomba().ativa(1));
											telaTabuleiro.getCards().add(telaTabuleiro.getAnimacao(), "2");
											CardLayout cl = (CardLayout)(telaTabuleiro.getCards().getLayout());
										    cl.show(telaTabuleiro.getCards(), "2");
										    telaNavios.getBuffer().setVisible(true);
											jogador.setVez(false);
									}
									else {
											telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setEnabled(false);
											telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setFundo(2);
											new ThreadSom(2).start();
											telaTabuleiro.setAnimacao(telaTabuleiro.getBomba().ativa(2));
											telaTabuleiro.getCards().add(telaTabuleiro.getAnimacao(), "2");
											CardLayout cl = (CardLayout)(telaTabuleiro.getCards().getLayout());
										    cl.show(telaTabuleiro.getCards(), "2");
										    telaNavios.getBuffer().setVisible(false);
											jogador.setVez(true);
									}
									
								}
								else if (protocolo[0] == '$'){
									if (leStringProtocolo(protocolo).equals("ini")) {
										jogador.setVez(true);
										JOptionPane.showMessageDialog(null,"VOCE COMECA...");
										telaNavios.getBuffer().setVisible(false);
									}
									else if (leStringProtocolo(protocolo).equals("over")) {
										JOptionPane.showMessageDialog(null,"GAME OVER...");
										jogador.setDerrotas(jogador.getDerrotas()+1);
										jogador.setTotalJogos(jogador.getTotalJogos()+1);
										CardLayout cl = (CardLayout)(cards.getLayout());
										cl.show(cards, "2");
										
									}
									else if (leStringProtocolo(protocolo).equals("win")) {
										JOptionPane.showMessageDialog(null,"PARABENS VOCE VENCEU...");
										jogador.setVitorias(jogador.getVitorias()+1);
										jogador.setTotalJogos(jogador.getTotalJogos()+1);
										if (jogador.getVitorias()%5==0)
											jogador.setMedalhas(jogador.getVitorias()/5);
										CardLayout cl = (CardLayout)(cards.getLayout());
										cl.show(cards, "2");
									}
								}
								// Resposta do Ataque (Acertou)
								else if (protocolo[0] == '&') {
										telaTabuleiro.getButtonsTab2()[leIntProtocolo(protocolo)].setEnabled(false);
										telaTabuleiro.getButtonsTab2()[leIntProtocolo(protocolo)].setFundo(1);
										new ThreadSom(1).start();
										telaTabuleiro.setAnimacao(telaTabuleiro.getBomba().ativa(1));
										telaTabuleiro.getCards().add(telaTabuleiro.getAnimacao(), "2");
										CardLayout cl = (CardLayout)(telaTabuleiro.getCards().getLayout());
									    cl.show(telaTabuleiro.getCards(), "2");
										jogador.setVez(true);
										telaNavios.getBuffer().setVisible(false);
								}
								// Resposta do Ataque (Errou)
								else if (protocolo[0] == '*') {
										telaTabuleiro.getButtonsTab2()[leIntProtocolo(protocolo)].setEnabled(false);
										telaTabuleiro.getButtonsTab2()[leIntProtocolo(protocolo)].setFundo(2);
										new ThreadSom(2).start();
										telaTabuleiro.setAnimacao(telaTabuleiro.getBomba().ativa(2));
										telaTabuleiro.getCards().add(telaTabuleiro.getAnimacao(), "2");
										CardLayout cl = (CardLayout)(telaTabuleiro.getCards().getLayout());
									    cl.show(telaTabuleiro.getCards(), "2");
										jogador.setVez(false);
										telaNavios.getBuffer().setVisible(true);
								}
								else {// Atualiza Chat
										telaChat.setTextAreaConversas(comandoEntrada + "\n"+ telaChat.getTextAreaConversas().getText());
								}
								aux = comandoEntrada;
						}	
						
						if (!statusLogin){
							// Envia Nome/Login
							cliente.getSaida().println("$"+telaConexao.getTextFieldLogin().getText());
							Thread.sleep(500);
							if (cliente.getComandoEntrada().equals("$OK1")){
								//JOptionPane.showMessageDialog(null,"Login Ok");
								jogador.setNome(telaConexao.getTextFieldLogin().getText());
								telaJogador.getNome().setText(jogador.getNome());
							}
							// Envia Senha/Login
							cliente.getSaida().println("$"+telaConexao.getTextFieldSenha().getText());
							Thread.sleep(500);
							if (cliente.getComandoEntrada().equals("$OK2")){
								telaInicio.getBuffer().setVisible(true);
								statusLogin = true;
								buscaInfo = true;
								cliente.getSaida().println("$OK");
							}
						}
						if (buscaInfo){
								Thread.sleep(2000);
								String auxDados = "";
								if (!cliente.getComandoEntrada().equals("$OK3")){
									//JOptionPane.showMessageDialog(null,"Recebeu "+ contador);
									String entradaDados = cliente.getComandoEntrada();
									if (!entradaDados.equals(auxDados)){
										if (contador == 0){
											jogador.setImagem(leIntProtocolo(cliente.getComandoEntrada().toCharArray()));
											telaOpcoes.setJogadorImagem(jogador.getImagem());
											telaOpcoes.getCapitaoJComboBox().setSelectedIndex(jogador.getImagem());
											System.out.println("<- Comunicacao - Protocolo (Sistema/Login) Cliente recebe Imagem: "+jogador.getImagem());
											cliente.getSaida().println("$OK");
										}
										else if (contador == 1){
											jogador.setPais(leStringProtocolo(cliente.getComandoEntrada().toCharArray()));
											telaOpcoes.setJogadorPais(jogador.getPais());
											telaOpcoes.getPaisLabel().setIcon( new ImageIcon("img/pais/"+jogador.getPais()+".jpg") );
											telaOpcoes.getPaisJComboBox().setSelectedItem(jogador.getPais());
											System.out.println("<- Comunicacao - Protocolo (Sistema/Login) Cliente recebe Pais: "+jogador.getPais());
											cliente.getSaida().println("$OK");
											
											telaNavios.atualizaPais(jogador.getPais());
											telaNavios.getJogador().atualizaHerois(telaNavios.getPais());
											telaNavios.atualizaHeroisJogador(jogador.getPais());
											
											telaNavios.getLabel().setIcon(telaNavios.getImagem()[jogador.getNumEscolhas()-1]);
											
										}
										else if (contador == 2){
											jogador.setMedalhas(leIntProtocolo(cliente.getComandoEntrada().toCharArray()));
											System.out.println("<- Comunicacao - Protocolo (Sistema/Login) Cliente recebe Medalhas: "+jogador.getMedalhas());
											cliente.getSaida().println("$OK");
										}
										else  if (contador == 3){
											jogador.setVitorias(leIntProtocolo(cliente.getComandoEntrada().toCharArray()));
											System.out.println("<- Comunicacao - Protocolo (Sistema/Login) Cliente recebe Vitorias: "+jogador.getVitorias());
											cliente.getSaida().println("$OK");
										}
										else  if (contador == 4){
											jogador.setDerrotas(leIntProtocolo(cliente.getComandoEntrada().toCharArray()));
											System.out.println("<- Comunicacao - Protocolo (Sistema/Login) Cliente recebe Derrotas: "+jogador.getDerrotas());
											cliente.getSaida().println("$OK");
										}
										else  if (contador == 5){
											jogador.setTotalJogos(leIntProtocolo(cliente.getComandoEntrada().toCharArray()));
											System.out.println("<- Comunicacao - Protocolo (Sistema/Login) Cliente recebe Total de Jogos: "+jogador.getTotalJogos());
											cliente.getSaida().println("$OK");
											buscaInfo = false;
											// Muda Tela, passa para Tela de Opcoes (Player, Multiplayer e Ranking)
											JOptionPane.showMessageDialog(null,"Login bem sucedido - Seja benvindo ao Batalha Naval");
											CardLayout cl = (CardLayout)(cards.getLayout());
											cl.show(cards, "2");
										}
										contador++;
										auxDados = entradaDados;
									}
							}
						}
						if (telaJogador.isStatus()){ // Atualiza informacoes do Jogador
							telaJogador.getJogador().setImagem(jogador.getImagem());
							telaJogador.getImg().setIcon(new ImageIcon("img/capitao/capitao" + jogador.getImagem() + ".jpg"));
							telaJogador.getCaixaMedalhas().removeAll();
							telaJogador.setMedalhas( new JLabel[jogador.getMedalhas()] );
							for (int i =0; i<telaJogador.getMedalhas().length; i++){
								String dirMedalha = "img/medalhas/medalha" + Integer.toString((i+1)) + ".jpg";
								// Atualiza medalhas na telaJogador da telaConsole
								telaJogador.getMedalhas()[i] = new JLabel(new ImageIcon(dirMedalha));
								telaJogador.getCaixaMedalhas().add(telaJogador.getMedalhas()[i]);
							}
							telaJogador.getVitoriasText().setText("Vitorias: " + Integer.toString(jogador.getVitorias()));
							telaJogador.getDerrotasText().setText("Derrotas: " + Integer.toString(jogador.getDerrotas()));
							telaJogador.getTotalText().setText("Numero de Partidas: " + Integer.toString(jogador.getTotalJogos()));
							telaJogador.setStatus(false);
						}
						if (telaOpcoes.isStatus()){ // Atualiza informacoes do Jogador							
							telaOpcoes.getTelaJogador().getImg().setIcon(new ImageIcon("img/capitao/capitao" + telaOpcoes.getJogadorImagem() + ".jpg"));
							telaOpcoes.getPaisLabel().setIcon( new ImageIcon("img/pais/"+  telaOpcoes.getJogadorPais() +".jpg") );
							telaOpcoes.getTelaJogador().getNome().setText(jogador.getNome());
							telaOpcoes.getTelaJogador().getCaixaMedalhas().removeAll();
							telaOpcoes.getTelaJogador().setMedalhas( new JLabel[jogador.getMedalhas()] );
							for (int i =0; i<telaOpcoes.getTelaJogador().getMedalhas().length; i++){
								String dirMedalha = "img/medalhas/medalha" + Integer.toString((i+1)) + ".jpg";
								// Atualiza medalhas na telaJogador da telaConsole
								telaOpcoes.getTelaJogador().getMedalhas()[i] = new JLabel(new ImageIcon(dirMedalha));
								telaOpcoes.getTelaJogador().getCaixaMedalhas().add(telaOpcoes.getTelaJogador().getMedalhas()[i]);
							}
							telaOpcoes.getTelaJogador().getVitoriasText().setText("Vitorias: " + Integer.toString(jogador.getVitorias()));
							telaOpcoes.getTelaJogador().getDerrotasText().setText("Derrotas: " + Integer.toString(jogador.getDerrotas()));
							telaOpcoes.getTelaJogador().getTotalText().setText("Numero de Partidas: " + Integer.toString(jogador.getTotalJogos()));
							jogador.setImagem(telaOpcoes.getJogadorImagem());
							jogador.setPais(telaOpcoes.getJogadorPais());
							telaOpcoes.setStatus(false);		
						}
						if (telaRanking.isStatus()){ // Atualiza informacoes do Jogador
							// Solicita consulta ao Banco de dados
							String bancoDados = "";  
							cliente.getSaida().println("%H");
							for (int i=0; i<5; i++){
								Thread.sleep(500);
								bancoDados  = bancoDados + cliente.getComandoEntrada() + "\n";
								telaRanking.getTextAreaPorTempo().setText(bancoDados);
								telaRanking.getTextAreaPorMedalhas().setText(bancoDados);
								telaRanking.getTextAreaPorMedia().setText(bancoDados);
							}
							telaRanking.setStatus(false);
						}
						if (telaNavios.isRandom()){ // Atualiza informacoes do Jogador
							for (int i=0; i<5; i++){
								for (int j=0; j<jogador.getHerois().get(i).getPosicao().length; j++){
									// Pegando todas posicoes de cada Heroi
									int posicao= jogador.getHerois().get(i).getPosicao()[j].getXY();
									// Marcando posicoes dos Herois
									telaTabuleiro.getButtonsTab1()[posicao].setEnabled(false);
									telaTabuleiro.getButtonsTab1()[posicao].setFundo(telaTabuleiro.isPosicao(), i);
									telaTabuleiro.getButtonsTab1()[posicao].setFundo(j);
									System.out.println("Thread Monitora Telas - Numero de Escolhas: " + jogador.getNumEscolhas() +" posicao: "+posicao);
								}
								jogador.setNumEscolhas(jogador.getNumEscolhas()+1);
							}
							telaNavios.setRandom(false);
						}
					}
			}
	
		} catch (Exception e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}