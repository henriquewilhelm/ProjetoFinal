package Graficos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Jogo.Jogador;

/**
 * Batalha Naval (Ultimate Battle) v1.0 - Versao 2.1 de Interface Grafica 
 * (telaMenuImagens)
 * Esta classe eh responsavel pela Tela do Menu de Imagens. Ela  possui 5 Label`s
 * com Imagens (5 imagens com Pecas/Navios e 1 com Mensagem de OK).
 * 
 * Obs: Ainda vou adicionar o nome dos navios e uma descricao perto da imagem de
 * cada um, mas essa eh basicamente a tela da primeira etapa (Escolha das Posicoes).
 * Falta criar a tela da segunda etapa (quando o jogo comeca), onde vamos ter os niveis
 * de vida do jogador e de cada navio do Jogador assim como os pontos/acertos no inimigo...
 * Autor: Henrique W.
 */

public class TelaImagens implements ActionListener {
	// Declara Tela (JPanel)
	private JPanel panelImagens;
	private JButton auxiliar;
	// Declarando e adicionando as Imagens ao vetor de Icon (Diretorio img) 
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
	public TelaImagens(Jogador jogador) {
		this.jogador = jogador;
		// Cria nova Tela (JPanel)
		setPanelImagens(new JPanel(new GridLayout(6, 1, 1, 1)));
		// setPanelOpcoes(new JPanel(new FlowLayout()));
		// Adiciona as JLabel (Imagens), o Campos de Texto (JTextfield) e o
		// botoao (JButton)
		for (int contador = 0; contador < 5; contador++) {
			getPanelImagens().add(getLabel()[contador]);
		}
		this.auxiliar = (new JButton("Op Um"));
		this.panelImagens.add(this.auxiliar);
		this.auxiliar.addActionListener(this);
		//this.auxiliar.setVisible(false);
	}
	// Manipulador de Acoes (Por enquanto vazio)
	public void actionPerformed(ActionEvent event) {
	
	}

	public JButton getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(JButton auxiliar) {
		this.auxiliar = auxiliar;
	}	
	
	public JPanel getPanelImagens() {
		return panelImagens;
	}

	public void setPanelImagens(JPanel panelOpcoes) {
		this.panelImagens = panelOpcoes;
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
