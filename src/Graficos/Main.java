package Graficos;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Jogo.Jogador;
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
		boolean status = true;
		// Cria Janelas
		JFrame frame = new JFrame("Batalha Naval 1.0");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
		// Cria e instancia Classe de Comunicacao de Dados 
		Cliente cliente = new Cliente();
		// Cria o Card de Telas
		JPanel cards;
		cards = new JPanel(new CardLayout());		
		// Cria Jogador
		Jogador jogador = new Jogador();			
		// Cria Telas do Jogo
		TelaInicio telaInicio = new TelaInicio(cliente);		
		TelaPrincipal telaPrincipal = new TelaPrincipal(cliente, jogador);	
		TelaOpcoes telaOpcoes = new TelaOpcoes(jogador, cards);
		TelaRanking telaRanking = new TelaRanking(cards);
		TelaConsole telaConsole = new TelaConsole(cards, telaOpcoes, telaRanking, telaPrincipal);
		// Adiciona Telas do Jogo ao Card De Telas
		cards.add(telaInicio.getTelaInicio(), "1");
        cards.add(telaConsole.getTelaConsole(), "2");
        cards.add(telaPrincipal.getTelaPrincipal(), "3");        
		cards.add(telaOpcoes.getTelaOpcoes(), "4");        
		cards.add(telaRanking.getTelaRanking(), "5");        
	    // Criando e instanciando container e adicionando Card de Telas ao Container   
	    Container cont = frame.getContentPane();
		cont.add(cards);
	 	// Tamanho e Visivel
		frame.setSize(1000, 660);
    	frame.setVisible(true);
    	
    	// Aponta o processador para thread.
    	ExecutorService service = Executors.newFixedThreadPool(1);
    	// Inicializa Thread responsavel por verificar Rodadas e controlar as Sub-Telas quando necessario.
    	service.submit(new ThreadMonitoraTelas(jogador, cliente, cards, telaPrincipal.getTelaJogador(),
    			telaPrincipal.getTelaTabuleiro(), telaPrincipal.getTelaNavios(), telaPrincipal.getTelaChat(),
    			telaInicio.getTelaConexao(), telaOpcoes, telaRanking, telaInicio));
			
	}
}
