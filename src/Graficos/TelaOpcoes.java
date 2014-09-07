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
public class TelaOpcoes {
	// Declara Telas
	private JPanel telaPrincipal;
	private Container telaOpcoes;
	private JPanel cards;
	// Declara Componentes
	private JButton player;
	private JButton multiplayer;
	private JButton ranking;
	// Construtor, recebe tela Principal e 
	public TelaOpcoes(JPanel cards){
		// Recebe Cards
		this.cards = cards;
		// Instancia Tela Principal
		this.telaPrincipal = new JPanel();
		this.telaPrincipal.setLayout(new GridLayout(3,1));
		// Instancia Container
		this.telaOpcoes = new Container();
		this.telaOpcoes.setLayout(new BoxLayout(telaOpcoes, BoxLayout.PAGE_AXIS));
		// Instancia Componentes - Botes (Player One, Multiplayer e Ranking)
		this.player = new JButton("Player One");
		this.player.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.multiplayer = new JButton("Multiplayer");
		this.multiplayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.ranking = new JButton("Ranking");
		this.ranking.setAlignmentX(Component.CENTER_ALIGNMENT);
		// Cria e instancia Manipulador de Acoes
		ButtonHandler handler = new ButtonHandler();
		this.player.addActionListener(handler);
		this.multiplayer.addActionListener(handler);
		this.ranking.addActionListener(handler);
		// Adiciona Componentes a Tela
		this.telaOpcoes.add(this.player);
		this.telaOpcoes.add(this.multiplayer);
		this.telaOpcoes.add(this.ranking);
		// Adiciona Sub-tela a Tela
		this.telaPrincipal.add(telaOpcoes);
	}
	// Manipulador de Acoes Botoes 
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
				if (event.getSource() == multiplayer){
					CardLayout cl = (CardLayout)(cards.getLayout());
			        cl.show(cards, "3");
				}
		}
	}
	// Getters and Setters
	public Container getTelaOpcoes() {
		return telaOpcoes;
	}
	public void setTelaOpcoes(Container telaInicio) {
		this.telaOpcoes = telaInicio;
	}
	public JPanel getTelaPrincipal() {
		return telaPrincipal;
	}
	public void setTelaPrincipal(JPanel telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
	}
}
