package Graficos;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

import Jogo.Jogador;
import Socket.Cliente;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.1 de Interface Grafica 
 * (telaCliente)
 * Essa classe alem de conter o main (executavel) eh responsavel por agrupar
 * todas as Telas (JPanel) dentro de um Conteiner (agrupador de JPanel) para 
 * organizar a disposicao (lugar) de cada componente com mais facilidade. 
 * O main cria a Janela (JFrame), a classe adiciona as Telas (JPanel) ao Conteiner 
 * de Telas e as organiza... 
 * Possui as seguintes telas ate aqui: TelaTabuleiros, TelaConexao, TelaImagens e TelaChat 
 * Autor: Henrique W.
 */

public class TelaClienteMain extends JFrame implements ActionListener {
	
	private Container cont;
	// Classes do projeto
	private TelaConexao telaConexao;
	private TelaTabuleiro telaTabuleiro;
	private TelaConsole telaConsole;
	private TelaChat telaChat;
	private Cliente cliente;

	public TelaClienteMain() {
		super("Batalha Naval 1.0");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1000, 580);
		//stest.pack();
		try {
			// Cria Jogador - Nossa classe (Importada de Jogo.Jogador)
			Jogador player = new Jogador("Capitao Jack", 10);			
			// Cria Cliente Socket - Nossa classe (Importada de Socket.Cliente)			
			cliente = new Cliente();
			// Cria conteiner, facilita a organizacao e adicao de nova Telas (JPanel)
			cont = getContentPane();
			
			// Cria Telas
			telaConexao = new TelaConexao(cliente);
			telaTabuleiro = new TelaTabuleiro(cliente, player);
			telaConsole = new TelaConsole(telaTabuleiro, player);
			telaChat = new TelaChat(cliente);
			
			ExecutorService service = Executors.newFixedThreadPool(1);
			// Inicializa Thread responsavel por verificar Rodadas de cada Jogador
			// e controla as Telas quando necessario.
			service.submit(new ThreadMonitoraTelas(player,telaConexao, telaTabuleiro, telaConsole, telaChat));
		
			cont.add(telaConexao.getPanelMenuConexao(), BorderLayout.PAGE_START);
			cont.add(telaTabuleiro.getTabuleiro1(), BorderLayout.LINE_START);
			cont.add(telaTabuleiro.getTabuleiro2(), BorderLayout.LINE_END);
			cont.add(telaConsole.getPanelConsole(), BorderLayout.CENTER);
			cont.add(telaChat.getPanelChat(), BorderLayout.SOUTH);
			
		} catch (Exception exception) {
			System.out.println(exception);
			exception.printStackTrace();
		}
	}

	// Manipulador de Acoes
	public void actionPerformed(ActionEvent e) {
		
	}
}