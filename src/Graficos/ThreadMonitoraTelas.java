package Graficos;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Jogo.Jogador;
import Socket.Cliente;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.1 de Interface Grafica 
 * (Thread Monitora Telas) 
 * Thread (Processo Filho) que monitora o objeto Jogador e as telas, se orienta principalmente
 * pelo o numero de rodadas para alterar a telaMenusImagens, trocando as imagens (Label`s) quando
 * receber um "click" na telaTabuleiro (troca imagem com Peca/Navio por imagem com Mensagem OK).
 * Assim as Telas (JPanel) podem se relacionar...
 * Autor: Henrique Wilhelm
 */

public class ThreadMonitoraTelas extends Thread {
	
	private Cliente cliente;
	private TelaPlayer telaPlayer;
	private TelaTabuleiro telaTabuleiro;
	private TelaConsole telaConsole;
	private TelaChat telaChat;
	private Jogador player;

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
		String aux = "";
		// Enquanto Numero de Jogadas for menor que 100 (Numero de posicao de um tabuleiro)
		while (player.getNumRodadas() < 100) {
					// Comente a parte de baixo para nao ver o numero de Rodadas (repetindo)
					//System.out.println("ThreadMonitoraRodadas - " + numRodadas);
					if (telaConsole.isEscolha()) {
							if (player.getHerois().get(player.getNumRodadas()-1).isVivo()){
								for (int i=0; i<player.getHerois().get(player.getNumRodadas()-1).getPosicao().length; i++){
									int posicao= player.getHerois().get(player.getNumRodadas()-1).getPosicao()[i].getX();
									if (!telaConsole.isVolta()){
											telaTabuleiro.getButtonsTab1()[posicao].setEnabled(false);
											telaTabuleiro.getButtonsTab1()[posicao].setFundo(telaTabuleiro.isPosicao(), player.getNumRodadas()-1);
											telaTabuleiro.getButtonsTab1()[posicao].setFundo(i);
									
											String dirNavio = "img/navios/navio" + Integer.toString((i+1)) + ".jpg";
											telaPlayer.getNavios()[player.getNumRodadas()-1].setIcon(new ImageIcon(this.getClass().getResource(dirNavio)));
									}
								}
								if (player.getNumRodadas() < 5){		
										telaConsole.getLabel().setIcon(telaConsole.getImagem()[player.getNumRodadas()]);
										telaConsole.getTextArea().setText("");
										telaConsole.getConsulta().ConsultaNavioTextFiel(player.getNumRodadas(), telaConsole.getTextArea());
										// Incrementa numero de rodadas
								}
								else{
									telaConsole.getLabel().setIcon(telaConsole.getImagem()[0]);
									telaConsole.getTextArea().setText("");
								}
								System.out.println("Thread Monitora Rodadas - " + player.getNumRodadas());
								player.setNumRodadas(player.getNumRodadas() + 1);
							}
							else { 
									JOptionPane.showMessageDialog(null,"Ops, primeiro escolha "
											+ "algum lugar no tabuleiro...");
							}
							telaConsole.setEscolha(false);		
					}
					telaTabuleiro.setPosicao(telaConsole.isPosicao());
					
					// Volta escolha
					if (telaConsole.isVolta()){
						for (int i=0; i<player.getHerois().get(player.getNumRodadas()-1).getPosicao().length; i++){
								int posicao= player.getHerois().get(player.getNumRodadas()-1).getPosicao()[i].getX();
								telaTabuleiro.getButtonsTab1()[posicao].setEnabled(true);
								telaTabuleiro.getButtonsTab1()[posicao].setFundo(); // fundo azul 
								telaTabuleiro.getButtonsTab1()[posicao].setFundo(0);
						}
						player.getHerois().get(player.getNumRodadas()-1).setVivo(false);
						telaConsole.setVolta(false);
					}
					if (player.getNumRodadas() == 6) {
						telaTabuleiro.getTabuleiro2().setVisible(true);
						telaConsole.getPanelConsole().setVisible(false);
					}
				// Atualiza tela do chat (se conexao ok)
				if (cliente.isStatus()){
					String comandoEntrada = cliente.getComandoEntrada();
					// Verifica se mensagem nao é igual a ultima...
					if (!comandoEntrada.equals(aux)){
						telaChat.setTextAreaConversas(comandoEntrada + "\n"+ telaChat.getTextAreaConversas().getText());
						aux = comandoEntrada;
					}
				}				
				// TELA PLAYER
				//telaPlayer.getVida()[0].setVisible(false);
		}
	}
}
	
	
	