package Graficos;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Socket.Cliente;

public class TelaOpcoes {
	private JPanel telaPrincipal;
	private Container telaOpcoes;
	private JButton player;
	private JButton multiplayer;
	private JButton ranking;
	private JPanel cards;
	public TelaOpcoes(JPanel telaPrincipal, JPanel cards){
		this.cards = cards;
		this.telaPrincipal = telaPrincipal;
		this.telaPrincipal.setLayout(new GridLayout(3,1));
		this.telaOpcoes = new Container();
		telaOpcoes.setLayout(new BoxLayout(telaOpcoes, BoxLayout.PAGE_AXIS));
		
		this.player = new JButton("Player");
		this.player.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.multiplayer = new JButton("Multiplayer");
		this.multiplayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.ranking = new JButton("Ranking");
		this.ranking.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		ButtonHandler handler = new ButtonHandler();
		this.player.addActionListener(handler);
		this.multiplayer.addActionListener(handler);
		this.ranking.addActionListener(handler);
		
		this.telaOpcoes.add(this.player);
		this.telaOpcoes.add(this.multiplayer);
		this.telaOpcoes.add(this.ranking);
		
		this.telaPrincipal.add(telaOpcoes);
	}
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
				if (event.getSource() == multiplayer){
					CardLayout cl = (CardLayout)(cards.getLayout());
			        cl.show(cards, "3");
				}
		}
	}
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
