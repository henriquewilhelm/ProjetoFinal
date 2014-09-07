package Graficos;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

import Jogo.Jogador;
import Socket.Cliente;

/**
 * Batalha Naval (Projeto GeracaoTec) - Versao 2.2 de Interface Grafica 
 * Classe: telaPrincipal
 * Essa classe responsavel por agrupar todas as Sub-Telas (JPanel) dentro da Tela Principal
 * para organizar a disposicao (lugar) de cada componente com mais facilidade.  
 * Possui as seguintes telas ate aqui: TelaPlayer, TelaTabuleiros, TelaConsole e TelaChat,
 * recebe o cliente ja conectado.
 * Autor: Henrique W.
 */

public class TelaPrincipal extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	// Classes do projeto
	private JPanel telaPrincipal;
	private TelaPlayer telaPlayer;
	private TelaTabuleiro telaTabuleiro;
	private TelaConsole telaConsole;
	private TelaChat telaChat;
	private Cliente cliente;
	// Construtor da Tela Principal (Tabuleiros, Chat, Jogador, Navios), recebe cliente conectado.
	public TelaPrincipal(Cliente cliente) {
				//stest.pack();
		try {
			this.telaPrincipal = new JPanel(new BorderLayout());
			// Cria Jogador - Nossa classe (Importada de Jogo.Jogador)
			Jogador player = new Jogador("Capitao Jack", 10);			
			// Cria Cliente Socket - Nossa classe (Importada de Socket.Cliente)			
			this.cliente = cliente;
			// Cria Sub-Telas 
			telaPlayer = new TelaPlayer(player);
			telaTabuleiro = new TelaTabuleiro(this.cliente, player);
			telaConsole = new TelaConsole(player);
			telaChat = new TelaChat(this.cliente);
			// Aponta o processador para thread.
			ExecutorService service = Executors.newFixedThreadPool(1);
			// Inicializa Thread responsavel por verificar Rodadas e controlar as Sub-Telas quando necessario.
			service.submit(new ThreadMonitoraTelas(player, cliente, telaPlayer, telaTabuleiro, telaConsole, telaChat));
			// Adiciona Sub-Telas a Tela Principal, passado o Layout escolhido
			telaPrincipal.add(telaPlayer.getPanelPlayer(), BorderLayout.PAGE_START);
			telaPrincipal.add(telaTabuleiro.getTabuleiro1(), BorderLayout.LINE_START);
			telaPrincipal.add(telaTabuleiro.getTabuleiro2(), BorderLayout.LINE_END);
			telaPrincipal.add(telaConsole.getCards(), BorderLayout.CENTER);
			telaPrincipal.add(telaChat.getPanelChat(), BorderLayout.SOUTH);
		} catch (Exception exception) {
			System.out.println(exception);
			exception.printStackTrace();
		}
	}
	// Manipulador de Acoes
	public void actionPerformed(ActionEvent e) {
			
	}
	// Getter and Setters
	public JPanel getTelaPrincipal() {
		return telaPrincipal;
	}
	public void setTelaPrincipal(JPanel telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
	}
}