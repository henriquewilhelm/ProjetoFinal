package Graficos;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import javax.swing.*;

import Jogo.Jogador;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.1 de Interface Grafica 
 * (telaTabuleiros)
 * Essa classe eh responsavel pela Tela dos Tabuleiros (Player 1 e 2)
 * Cada tabuleiro tem 100 Botoes: 0-99, a interface eh preparada para
 * marcar os 5 primeiros "clicks" no primeiro tabuleiro que representa a escolha
 * da posicao das Pecas/Navios, depois soh aceita "clicks" no segundo tabuleiro
 * (Player 2).
 * Autor: Henrique W.
 */

public class TelaTabuleiro implements ActionListener {

	// Declara Telas (JPanel`s)
	private JPanel tabuleiro1;
	private JPanel tabuleiro2;
	private ImageIcon imagem = new ImageIcon(this.getClass().getResource("img/mar2.jpg"));  

	// Declara Componente - Botoes dos Tabulerios 1 e 2
	private Botao buttonsTab1[] = new Botao[100]; // = new JButton[100]; // Botoes 0 - 99
	private Botao buttonsTab2[] = new Botao[100]; // = new JButton[100]; // Botoes 0 - 99
	// Vetor para guardar as 5 Escolhas do Jogador 
	private int[] posicaoEscolhida = new int[5];
	// Nossa classe (Importada de Jogo.Jogador)
	private Jogador jogador;
	// Contrutor da Tela
	public TelaTabuleiro(Jogador jogador) { // Nossa classe (Importada de Jogo.Jogador)
		this.jogador = jogador;
		// Cria novas Telas (JPanel`s)
		setTabuleiro1(new JPanel(new GridLayout(10, 1, 0, 0)));
		setTabuleiro2(new JPanel(new GridLayout(10, 1, 0, 0)));
		// Adiciona os botoes (JButton) no vetor de Botoes
		for (int contador = 0; contador < 100; contador++) {
			// Criando Tabuleiro1 (Player 1)
			getButtonsTab1()[contador] = new Botao();
			
			// Criando Tabuleiro2 (Player 2)			
			getButtonsTab2()[contador] = new Botao();
		
			getButtonsTab1()[contador].addActionListener(this);
			getButtonsTab2()[contador].addActionListener(this);
			
			// Adiciona objetos (Botoes) na Tela (JPanel)
			getTabuleiro1().add(getButtonsTab1()[contador]);
			getTabuleiro2().add(getButtonsTab2()[contador]);
			
		
		}
		// Visivel , Invisivel
		getTabuleiro2().setVisible(false);
		getTabuleiro1().setVisible(true);
	}

	// Manipulador de Acoes - Botoes (0-99)
	public void actionPerformed(ActionEvent e) {
		try {
			for (int contador = 0; contador < 100; contador++) {
				// PRIMEIRA ETAPA - Escolhendo a Posicao para cada Peca/Navio
				if (jogador.getNumRodadas() < 5) {
					// Escolhas (Navios/Posicao)
					if (e.getSource() == getButtonsTab1()[contador]) {
						// Coloca em um vetor as Posicoes Escolhidas
						getPosicaoEscolhida()[jogador.getNumRodadas()] = contador;
						// Incrementa numero de rodadas
						jogador.setNumRodadas(jogador.getNumRodadas() + 1);
						// Desabilita Botao (Marca Peca/Navio)
						System.out.println(contador);
						 getButtonsTab1()[contador].setEnabled(false);
						 getButtonsTab1()[contador].setFundo(1);
					}
				} else {

					if (jogador.getNumRodadas() == 5) {
						// Depois de escolher as Posicao das Pecas/Navios mostra
						// o tabuleiro do Player 2
						getTabuleiro2().setVisible(true);
						 
						// Aqui ENVIAMOS a Posicao Escolhida (posicaoEscolhida[5])
					}
					if (e.getSource() == getButtonsTab1()[contador]) {JOptionPane
								.showMessageDialog(null,"O jogo ja comecou, escolha a Posicao "
												+ "que deseja atacar no tabuleiro do Player 2");
					}
					if (e.getSource() == getButtonsTab2()[contador]) {
						getButtonsTab2()[contador].setEnabled(false);
						 getButtonsTab2()[contador].setFundo(1);
						
						// para SocketServer (Pacote Socket) ja implementado
					}

				}
			}
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto");
			System.out.println(exception);
			exception.printStackTrace();
		}
	}

	public JPanel getTabuleiro1() {
		return tabuleiro1;
	}

	public void setTabuleiro1(JPanel tabuleiro1) {
		this.tabuleiro1 = tabuleiro1;
	}

	public JPanel getTabuleiro2() {
		return tabuleiro2;
	}

	public void setTabuleiro2(JPanel tabuleiro2) {
		this.tabuleiro2 = tabuleiro2;
	}
	
	public ImageIcon getImagem() {
		return imagem;
	}

	public void setImagem(ImageIcon imagem) {
		this.imagem = imagem;
	}
	public Botao[] getButtonsTab1() {
		return buttonsTab1;
	}

	public void setButtonsTab1(Botao[] buttonsTab1) {
		this.buttonsTab1 = buttonsTab1;
	}

	public Botao[] getButtonsTab2() {
		return buttonsTab2;
	}

	public void setButtonsTab2(Botao[] buttonsTab2) {
		this.buttonsTab2 = buttonsTab2;
	}

	public int[] getPosicaoEscolhida() {
		return posicaoEscolhida;
	}

	public void setPosicaoEscolhida(int[] posicaoEscolhida) {
		this.posicaoEscolhida = posicaoEscolhida;
	}
}