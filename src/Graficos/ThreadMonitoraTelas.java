package Graficos;

import Jogo.Jogador;

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
	
	private TelaConexao telaConexao;
	private TelaTabuleiro telaTabuleiro;
	private TelaConsole telaConsole;
	private TelaChat telaChat;
	private Jogador player;

	public ThreadMonitoraTelas(Jogador player, TelaConexao telaConexao, TelaTabuleiro telaTabuleiro, 
			TelaConsole telaConsole, TelaChat telaChat) {
		
		this.telaTabuleiro = telaTabuleiro;
		this.telaConsole = telaConsole;
		this.telaConexao = telaConexao;
		this.telaChat = telaChat;
		this.player = player;
	}
	// Metodo que executa start() a Thread (Processo Filho)
	public void run() {
		String aux = "";
		int numRodadas = 0;
		// Enquanto Numero de Jogadas for menor que 100 (Numero de posicao de um tabuleiro)
		while (player.getNumRodadas() < 100) {
					// Comente a parte de baixo para nao ver o numero de Rodadas (repetindo)
					//System.out.println("ThreadMonitoraRodadas - " + telaMenuImagens.getJogador().getNumRodadas());
					numRodadas = player.getNumRodadas();
					if (numRodadas == 2) {
						for (int i=0; i<player.getHerois().get(1).getPosicao().length; i++){
							int posicao= player.getHerois().get(0).getPosicao()[i].getX();
							telaTabuleiro.getButtonsTab1()[posicao].setEnabled(false);
							telaTabuleiro.getButtonsTab1()[posicao].setFundo(telaTabuleiro.getPosicao(), 1);
							telaTabuleiro.getButtonsTab1()[posicao].setFundo(i);
						}
					}
					if (numRodadas == 3) {		
							for (int i=0; i<player.getHerois().get(1).getPosicao().length; i++){
								int posicao =player.getHerois().get(1).getPosicao()[i].getX();
								telaTabuleiro.getButtonsTab1()[posicao].setEnabled(false);
								telaTabuleiro.getButtonsTab1()[posicao].setFundo(telaTabuleiro.getPosicao(), 2);
								telaTabuleiro.getButtonsTab1()[posicao].setFundo(i);
							}
					}
					if (numRodadas == 4) {		
						for (int i=0; i<player.getHerois().get(2).getPosicao().length; i++){
							int posicao = player.getHerois().get(2).getPosicao()[i].getX();
							telaTabuleiro.getButtonsTab1()[posicao].setEnabled(false);
							telaTabuleiro.getButtonsTab1()[posicao].setFundo(telaTabuleiro.getPosicao(), 3);
							telaTabuleiro.getButtonsTab1()[posicao].setFundo(i);
						}
					}
					if (numRodadas == 5) {		
						for (int i=0; i<player.getHerois().get(3).getPosicao().length; i++){
							int posicao = player.getHerois().get(3).getPosicao()[i].getX();
							telaTabuleiro.getButtonsTab1()[posicao].setEnabled(false);
							telaTabuleiro.getButtonsTab1()[posicao].setFundo(telaTabuleiro.getPosicao(), 4);
							telaTabuleiro.getButtonsTab1()[posicao].setFundo(i);
						}
					}
					if (numRodadas == 6) {		
						for (int i=0; i<player.getHerois().get(4).getPosicao().length; i++){
							int posicao = player.getHerois().get(4).getPosicao()[i].getX();
							telaTabuleiro.getButtonsTab1()[posicao].setEnabled(false);
							telaTabuleiro.getButtonsTab1()[posicao].setFundo(telaTabuleiro.getPosicao(), 5);
							telaTabuleiro.getButtonsTab1()[posicao].setFundo(i);
						}	
						telaConsole.getPanelConsole().setVisible(false);
						telaTabuleiro.getTabuleiro2().setVisible(true);
					}		
				// Atualiza tela do chat (se conexao ok)
				if (telaConexao.isStatus()){
					String comandoEntrada = telaConexao.getCli().getComandoEntrada();
					// Verifica se mensagem nao é igual a ultima...
					if (!comandoEntrada.equals(aux)){
						telaChat.setTextAreaConversas(comandoEntrada + "\n"+ telaChat.getTextAreaConversas().getText());
						aux = comandoEntrada;
					}
				}
		}
	}
}
	
	
	