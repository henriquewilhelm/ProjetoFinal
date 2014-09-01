package Graficos;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Jogo.Jogador;
import Jogo.Posicao;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.2 de Interface Grafica 
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
	// Declara Componente - Imagem  
	// Declara Componente - Botoes dos Tabulerios 1 e 2
	private Botao buttonsTab1[] = new Botao[100]; // = new JButton[100]; // Botoes 0 - 99
	private Botao buttonsTab2[] = new Botao[100]; // = new JButton[100]; // Botoes 0 - 99
	// Declara variavel auxiliar
	private String posicao = "vertical";
	// Declara a nossa classe Jogador (Importada de Jogo.Jogador)
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
			// Adicionando ao Manipulador de Eventos
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
				// Escolhas (Navios/Posicao)
				if (e.getSource() == getButtonsTab1()[contador]) {
					int aux;
					if (posicao.equals("horizontal"))
						 aux = 1;
					else
						 aux = 10;
					// Cria posicao do Heroi 
					if (jogador.getNumRodadas() == 1) {
						jogador.getHerois().get(0).getPosicao()[0] = new Posicao(contador);
						jogador.getHerois().get(0).getPosicao()[1] = new Posicao(contador+aux);
					}
					if (jogador.getNumRodadas() == 2) {
						jogador.getHerois().get(1).getPosicao()[0] = new Posicao(contador);
						jogador.getHerois().get(1).getPosicao()[1] = new Posicao(contador+aux);
					}
					if (jogador.getNumRodadas() == 3) {
						jogador.getHerois().get(2).getPosicao()[0] = new Posicao(contador-aux);
						jogador.getHerois().get(2).getPosicao()[1] = new Posicao(contador);
						jogador.getHerois().get(2).getPosicao()[2] = new Posicao(contador+aux);
					}
					if (jogador.getNumRodadas() == 4) {
						jogador.getHerois().get(3).getPosicao()[0] = new Posicao(contador-aux);
						jogador.getHerois().get(3).getPosicao()[1] = new Posicao(contador);
						jogador.getHerois().get(3).getPosicao()[2] = new Posicao(contador+aux);
						jogador.getHerois().get(3).getPosicao()[3] = new Posicao(contador+aux*2);
					}
					if (jogador.getNumRodadas() == 5) {
						jogador.getHerois().get(4).getPosicao()[0] = new Posicao(contador-aux);
						jogador.getHerois().get(4).getPosicao()[1] = new Posicao(contador);
						jogador.getHerois().get(4).getPosicao()[2] = new Posicao(contador+aux);
						jogador.getHerois().get(4).getPosicao()[3] = new Posicao(contador+aux*2);
					}
				} 
				else {
					if (jogador.getNumRodadas() == 5) {
					//
					}
					if (e.getSource() == getButtonsTab1()[contador]) {
							JOptionPane.showMessageDialog(null,"O jogo ja comecou, escolha a Posicao "
												+ "que deseja atacar no tabuleiro do Player 2");
					}
					if (e.getSource() == getButtonsTab2()[contador]) {
							getButtonsTab2()[contador].setEnabled(false);
							getButtonsTab2()[contador].setFundo(1);
					}
				}
			}
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto");
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

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
}