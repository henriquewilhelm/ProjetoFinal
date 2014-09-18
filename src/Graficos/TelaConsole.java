package Graficos;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Batalha Naval (Projeto GeracaoTec) v1.0 - Versao 2.2 de Interface Grafica 
 * Classe: telaOpcoes
 * Tela responsavel por criar e organizar os botoes (Player One, Multiplayer e Ranking)
 * chamando as diferentes opcoes.
 * Autor: Henrique Wilhelm
 */
public class TelaConsole {
	// Declara Telas
	private JPanel panelConsole;
	private Container telaConsole;
	private JPanel cards;
	// Declara Componentes
	private JButton player;
	private JButton multiplayer;
	private JButton opcoes;
	private JButton ranking;
	// Declara Nossa Telas
	private TelaOpcoes telaOpcoes;
	private TelaRanking telaRanking;
	private TelaPrincipal telaPrincipal;
	// Construtor, recebe tela Principal e 
	public TelaConsole(JPanel cards, TelaOpcoes telaOpcoes, TelaRanking telaRanking, TelaPrincipal telaPrincipal){
		this.telaOpcoes = telaOpcoes;
		this.telaRanking = telaRanking;
		this.telaPrincipal = telaPrincipal;
		// Recebe Cards
		this.cards = cards;
		// Instancia Tela Principal
		this.panelConsole = new JPanel();
		this.panelConsole.setLayout(new GridLayout(3,1));
		// Instancia Container
		this.telaConsole = new Container();
		this.telaConsole.setLayout(new BoxLayout(telaConsole, BoxLayout.PAGE_AXIS));
		// Instancia Componentes - Botes (Player One, Multiplayer e Ranking)
		this.player = new JButton("Player One");
		this.player.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.multiplayer = new JButton("Multiplayer");
		this.multiplayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.opcoes = new JButton("Opcoes");
		this.opcoes.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.ranking = new JButton("Ranking");
		this.ranking.setAlignmentX(Component.CENTER_ALIGNMENT);
		// Cria e instancia Manipulador de Acoes
		ButtonHandler handler = new ButtonHandler();
		this.player.addActionListener(handler);
		this.multiplayer.addActionListener(handler);
		this.opcoes.addActionListener(handler);
		this.ranking.addActionListener(handler);
		// Adiciona Componentes a Tela
		this.telaConsole.add(this.player);
		this.telaConsole.add(this.multiplayer);
		this.telaConsole.add(this.opcoes);
		this.telaConsole.add(this.ranking);
		// Adiciona Sub-tela a Tela
		this.panelConsole.add(telaConsole);
	}
	// Manipulador de Acoes Botoes 
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
				if (event.getSource() == player){
					CardLayout cl = (CardLayout)(cards.getLayout());
			        cl.show(cards, "3");
				}
				if (event.getSource() == multiplayer){
					CardLayout cl = (CardLayout)(cards.getLayout());
			        cl.show(cards, "3");
			        telaPrincipal.getTelaJogador().setStatus(true);
				}
				if (event.getSource() == opcoes){
					CardLayout cl = (CardLayout)(cards.getLayout());
		        	cl.show(cards, "4");
		        	telaOpcoes.setStatus(true);
				}
				if (event.getSource() == ranking){
					CardLayout cl = (CardLayout)(cards.getLayout());
		        	cl.show(cards, "5");
		        	telaRanking.setStatus(true);
				}
		}
	}
	// Getters and Setters
	public Container getTelaConsole() {
		return telaConsole;
	}
	public void setTelaConsole(Container telaInicio) {
		this.telaConsole = telaInicio;
	}
	public JPanel getPanelConsole() {
		return panelConsole;
	}
	public void setPanelConsole(JPanel panelConsole) {
		this.panelConsole = panelConsole;
	}
}
