package Graficos;

import java.awt.*;
import javax.swing.*;
import Jogo.Jogador;

/**
 * Batalha Naval (Ultimate Battle) v1.0 - Versao 2.0 de Interface Grafica (telaMenuOpcoes)
 * Esta classe eh responsavel pela Tela do Menu de Opcoes. Ela  possui 5 Label`s
 * com Imagens (5 imagens com Peças/Navios e 1 com Mensagem de OK). Possui uma Thread
 * (Processo Filho) que monitora o obejto Jogador (Nome, numRodada) assim ela pode se 
 * relacionar com outras Telas/JFrames.  
 * Autor: Henrique W.
 */

public class TelaMenuImagens {
	// Declara Tela (JPanel)
	private JPanel panelOpcoes;
	// 	// Declarando e adicionando as Imagens ao vetor de Icon (Diretorio img) 
	private Icon icons[] = {
			new ImageIcon(getClass().getResource("img/img1.jpg")),
			new ImageIcon(getClass().getResource("img/img2.jpg")),
			new ImageIcon(getClass().getResource("img/img3.jpg")),
			new ImageIcon(getClass().getResource("img/img4.jpg")),
			new ImageIcon(getClass().getResource("img/img5.jpg")),
			new ImageIcon(getClass().getResource("img/img6.jpg")) };
	// Adicionando vetor de Icon (Imagens) ao JLabel[]
	private JLabel label[] = { new JLabel(icons[0]), new JLabel(icons[1]),
			new JLabel(icons[2]), new JLabel(icons[3]), new JLabel(icons[4]) };
	// Nossa classe (Classe importada de Jogo.Jogador)
	private Jogador jogador;
	// Construtor da Tela
	public TelaMenuImagens(Jogador jogador) {
		this.jogador = jogador;
		// Cria nova Tela (JPanel)
		setPanelOpcoes(new JPanel(new GridLayout(7, 1, 1, 1)));
		// setPanelOpcoes(new JPanel(new FlowLayout()));
		// Adiciona as JLabel (Imagens), o Campos de Texto (JTextfield) e o
		// botoao (JButton)
		for (int contador = 0; contador < 5; contador++) {
			getPanelOpcoes().add(getLabel()[contador]);
		}
	}

	public JPanel getPanelOpcoes() {
		return panelOpcoes;
	}

	public void setPanelOpcoes(JPanel panelOpcoes) {
		this.panelOpcoes = panelOpcoes;
	}

	public Icon[] getIcons() {
		return icons;
	}

	public void setIcons(Icon[] icons) {
		this.icons = icons;
	}

	public JLabel[] getLabel() {
		return label;
	}

	public void setLabel(JLabel[] label) {
		this.label = label;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

}
