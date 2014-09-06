package Graficos;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Socket.Cliente;

public class Main {
	
	
	// Main (Executavel)
	public static void main(String args[]) throws UnknownHostException,
			IOException {
		JPanel cards;
		Cliente cliente = new Cliente();

		JFrame frame = new JFrame("Batalha Naval 1.0");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
		
		cards = new JPanel(new CardLayout());
		
		TelaInicio telaInicio = new TelaInicio(new JPanel(new BorderLayout()), cliente, cards);
		TelaOpcoes telaOpcoes = new TelaOpcoes(new JPanel(),cards);
		TelaCliente telaCliente = new TelaCliente(new JPanel(new BorderLayout()), cliente);
		
        cards.add(telaInicio.getTelaInicio(), "1");
        cards.add(telaOpcoes.getTelaOpcoes(), "2");
        cards.add(telaCliente.getTelaPrincipal(), "3");
        
		Container cont = frame.getContentPane();
		cont.add(cards);
		frame.setSize(1000, 660);
		frame.setVisible(true);
	}
}
