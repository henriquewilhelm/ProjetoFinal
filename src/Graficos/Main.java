package Graficos;

import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Container;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Socket.Cliente;
/**
 * Batalha Naval (Projeto GeracaoTec) - Versao 2.2 de Interface Grafica 
 * - Classe: main
 * O main eh responsavel por criar a Janela (JFrame), ele agrupa as Telas (Inicio, Opcoes
 * e Principal) dentro Card de Telas (agrupador de JPanel) para trocar a exibicao de cada 
 * componente com mais facilidade.
 * Autor: Henrique W.
 */

public class Main {
	// Main (Executavel)
	public static void main(String args[]) throws UnknownHostException,	IOException {
		// Cria Janelas
		JFrame frame = new JFrame("Batalha Naval 1.0");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
		// Cria e instancia Classe de Comunicacao de Dados 
		Cliente cliente = new Cliente();
		// Cria o Card de Telas
		JPanel cards;
		cards = new JPanel(new CardLayout());		
		// Cria Telas do Jogo
		TelaInicio telaInicio = new TelaInicio(cliente, cards);
		TelaOpcoes telaOpcoes = new TelaOpcoes(cards);
		TelaPrincipal telaPrincipal = new TelaPrincipal(cliente);		
		// Adiciona Telas do Jogo ao Card De Telas
        cards.add(telaInicio.getTelaInicio(), "1");
        cards.add(telaOpcoes.getTelaOpcoes(), "2");
        cards.add(telaPrincipal.getTelaPrincipal(), "3");        
        // Criando e instanciando container e adicionando Card de Telas ao Container
		Container cont = frame.getContentPane();
		cont.add(cards);
		// Tamanho e Visivel
		frame.setSize(1000, 660);
		frame.setVisible(true);
	}
}
