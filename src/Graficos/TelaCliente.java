package Graficos;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

import Jogo.Jogador;
import Socket.Cliente;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.1 de Interface Grafica 
 * (telaCliente)
 * Essa classe responsavel por agrupar todas as Telas (JPanel) dentro de um Conteiner 
 * (agrupador de JPanel) para organizar a disposicao (lugar) de cada componente com mais 
 * facilidade.  
 * O main cria a Janela (JFrame), a classe adiciona as Telas (JPanel) ao Conteiner 
 * de Telas e as organiza... 
 * Possui as seguintes telas ate aqui: TelaTabuleiros, TelaConexao, TelaImagens e TelaChat 
 * Autor: Henrique W.
 */

public class TelaCliente extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	// Classes do projeto
	private JPanel telaPrincipal;
	private TelaPlayer telaPlayer;
	private TelaConexao telaConexao;
	private TelaTabuleiro telaTabuleiro;
	private TelaConsole telaConsole;
	private TelaChat telaChat;
	private Cliente cliente;

	public TelaCliente(JPanel telaPrincipal, Cliente cliente) {
				//stest.pack();
		try {
			this.telaPrincipal = telaPrincipal;
			// Cria Jogador - Nossa classe (Importada de Jogo.Jogador)
			Jogador player = new Jogador("Capitao Jack", 10);			
			// Cria Cliente Socket - Nossa classe (Importada de Socket.Cliente)			
			this.cliente = cliente;
			// Cria conteiner, facilita a organizacao e adicao de nova Telas (JPanel)
			
			// Cria Telas
			telaPlayer = new TelaPlayer(player);
			//telaConexao = new TelaConexao(cliente);
			telaTabuleiro = new TelaTabuleiro(cliente, player);
			telaConsole = new TelaConsole(player);
			telaChat = new TelaChat(cliente);
			
			ExecutorService service = Executors.newFixedThreadPool(1);
			// Inicializa Thread responsavel por verificar Rodadas de cada Jogador
			// e controla as Telas quando necessario.
			service.submit(new ThreadMonitoraTelas(player, cliente, telaPlayer, telaTabuleiro, telaConsole, telaChat));
		
			telaPrincipal.add(telaPlayer.getPanelPlayer(), BorderLayout.PAGE_START);
			telaPrincipal.add(telaTabuleiro.getTabuleiro1(), BorderLayout.LINE_START);
			telaPrincipal.add(telaTabuleiro.getTabuleiro2(), BorderLayout.LINE_END);
			telaPrincipal.add(telaConsole.getPanelConsole(), BorderLayout.CENTER);
			telaPrincipal.add(telaChat.getPanelChat(), BorderLayout.SOUTH);
			
		} catch (Exception exception) {
			System.out.println(exception);
			exception.printStackTrace();
		}
	}

	public JPanel getTelaPrincipal() {
		return telaPrincipal;
	}

	public void setTelaPrincipal(JPanel telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
	}

	// Manipulador de Acoes
	public void actionPerformed(ActionEvent e) {
		
	}
}