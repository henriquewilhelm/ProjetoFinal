package Graficos;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Jogo.Jogador;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.0 de Interface Grafica (TelaChat) 
 * Tread até aqui com a telaMenuOpcoes, trocando as
 * imagens (Label`s) quando receber um "click" no tabuleiro (troca imagem com
 * Peca/Navio por imagem com Mensagem OK).
 */

public class ThreadMonitoraTelas extends Thread {
	

	private TelaMenuImagens telaMenuImagens;

	public ThreadMonitoraTelas(TelaMenuImagens telaMenuOpcoes) {
		this.telaMenuImagens = telaMenuOpcoes;
	}
	// Metodo que executa start() a Thread (Processo Filho)
	public void run() {
		// Enquanto Numero de Jogadas for menor que 100 (Numero de posicao de um tabuleiro)
		while (telaMenuImagens.getJogador().getNumRodadas() < 100) {
					// Comente a parte de baixo para nao ver o numero de Rodadas 
					//System.out.println("ThreadMonitoraRodadas - " + telaMenuImagens.getJogador().getNumRodadas());
					int numRodadas = telaMenuImagens.getJogador().getNumRodadas();
					// Troca Imagem (JLabel) mostrando que Peca/Navio foi escolhido
					if (telaMenuImagens.getJogador().getNumRodadas() == 1) {
								telaMenuImagens.getLabel()[0]
										.setIcon(telaMenuImagens.getIcons()[5]);
					}
					if (telaMenuImagens.getJogador().getNumRodadas() == 2) {
								telaMenuImagens.getLabel()[1]
										.setIcon(telaMenuImagens.getIcons()[5]);
					}
					if (telaMenuImagens.getJogador().getNumRodadas() == 3) {
							telaMenuImagens.getLabel()[2]
									.setIcon(telaMenuImagens.getIcons()[5]);
					}
					if (telaMenuImagens.getJogador().getNumRodadas() == 4) {
							telaMenuImagens.getLabel()[3]
									.setIcon(telaMenuImagens.getIcons()[5]);
					}
					if (telaMenuImagens.getJogador().getNumRodadas() == 5) {
							telaMenuImagens.getLabel()[4]
									.setIcon(telaMenuImagens.getIcons()[5]);
				
							// para SocketClient (Importado do Pacote Socket) ja implementado
							for (int contLabel = 0; contLabel < 5; contLabel++) {
									telaMenuImagens.getLabel()[contLabel].setVisible(false);
							}
							// Desabilita TelaMenuImagens ao final das 5 rodadas
							telaMenuImagens.getPanelOpcoes().setEnabled(false);
					}
		}
	}
}