package Graficos;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Jogo.Jogador;
import Socket.Cliente;

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
	private Cliente cliente;
	private TelaPlayer telaPlayer;
	private TelaTabuleiro telaTabuleiro;
	private TelaConsole telaConsole;
	private TelaChat telaChat;
	private Jogador player;
	// Construtor, recebe todas classes construidas para jogo e realiza a iteracao entre elas
	public ThreadMonitoraTelas(Jogador player, Cliente cliente, TelaPlayer telaPlayer, TelaTabuleiro telaTabuleiro, 
			TelaConsole telaConsole, TelaChat telaChat) {
		this.telaTabuleiro = telaTabuleiro;
		this.telaPlayer =  telaPlayer;
		this.telaConsole = telaConsole;
		this.cliente = cliente;
		this.telaChat = telaChat;
		this.player = player;
	}
	// Metodo que executa start() a Thread (Processo Filho)
	public void run() {
		String aux = ""; // String auxiliar do Chat
		// Enquanto Numero de Jogadas for menor que 100 (Numero de posicao de um tabuleiro)
		while (player.getNumRodadas() < 100) {
					//System.out.println("ThreadMonitoraRodadas - " + numRodadas);
					if (telaConsole.isEscolha()) { 	// Se tela Console marcar escolha TRUE
							// Se Heroi estiver vivo
							if (player.getHerois().get(player.getNumRodadas()-1).isVivo()){
								// Percorrendo List de Herois
								for (int i=0; i<player.getHerois().get(player.getNumRodadas()-1).getPosicao().length; i++){
									// Pegando todas posicoes de cada Heroi
									int posicao= player.getHerois().get(player.getNumRodadas()-1).getPosicao()[i].getX();
									if (!telaConsole.isVolta()){
											// Marcando posicoes dos Herois
											telaTabuleiro.getButtonsTab1()[posicao].setEnabled(false);
											telaTabuleiro.getButtonsTab1()[posicao].setFundo(telaTabuleiro.isPosicao(), player.getNumRodadas()-1);
											telaTabuleiro.getButtonsTab1()[posicao].setFundo(i);
											// Adicionando Imagem do Navio a Tela Player
											String dirNavio = "img/navios/navio" + Integer.toString((player.getNumRodadas())) + ".jpg";
											telaPlayer.getNavios()[player.getNumRodadas()-1].setIcon(new ImageIcon(dirNavio));
									}
								}
								if (player.getNumRodadas() < 5){	// Atualiza Descricao do Navio na Tela Console	
										telaConsole.getLabel().setIcon(telaConsole.getImagem()[player.getNumRodadas()]);
										telaConsole.getTextArea().setText("");
										telaConsole.getConsulta().ConsultaNavioTextFiel(player.getNumRodadas(), telaConsole.getTextArea());
								}
								// Incrementa uma rododa.
								player.setNumRodadas(player.getNumRodadas() + 1);
							}
							else { // Apresenta mensagem orientando usuario.
									JOptionPane.showMessageDialog(null,"Ops, primeiro escolha "
											+ "algum lugar no tabuleiro...");
							}
							telaConsole.setEscolha(false); // Marca escolha da Tela Console como FALSE		
					}
					// Atualiza a posicao da Tela Tabuleiro conforme Tela Console 
					telaTabuleiro.setPosicao(telaConsole.isPosicao());
					// Se tela Console marcar volta TRUE
					if (telaConsole.isVolta()){
						// Percorrendo List de Herois
						for (int i=0; i<player.getHerois().get(player.getNumRodadas()-1).getPosicao().length; i++){
								// Pegando todas posicoes de cada Heroi para resetar Fundo (remover img navio)
								int posicao= player.getHerois().get(player.getNumRodadas()-1).getPosicao()[i].getX();
								telaTabuleiro.getButtonsTab1()[posicao].setEnabled(true); 
								telaTabuleiro.getButtonsTab1()[posicao].setFundo(); // (Mar azul) 
						}
						player.getHerois().get(player.getNumRodadas()-1).setVivo(false); // Mata Navio
						telaConsole.setVolta(false); // Marca volta da Tela Console como FALSE
						telaPlayer.getNavios()[player.getNumRodadas()-1].setIcon(new ImageIcon()); // Remove Navio da Tela Player
					}
					if (player.getNumRodadas() == 6) { // Se roda igual a 6 comeca o jogo
						telaTabuleiro.getTabuleiro2().setVisible(true); // Mostra o Tabuleiro do Player 2
						CardLayout cl = (CardLayout)(telaConsole.getCards().getLayout());
				        cl.show(telaConsole.getCards(), "2"); // Muda tela de exibicao da Tela Console
					}
				// Atualiza tela do chat (se conexao)
				if (cliente.isStatus()){
					String comandoEntrada = cliente.getComandoEntrada();
					// Verifica se mensagem nao é igual a ultima...
					if (!comandoEntrada.equals(aux)){
						telaChat.setTextAreaConversas(comandoEntrada + "\n"+ telaChat.getTextAreaConversas().getText());
						aux = comandoEntrada;
					}
				}				
				// Atualiza comunicacao
				//telaPlayer.getVida()[0].setVisible(false);
		}
	}
}