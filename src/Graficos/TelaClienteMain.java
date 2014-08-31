package Graficos;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.swing.*;

import Jogo.Jogador;

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

	public TelaClienteMain() {
		super("Batalha Naval 1.0");
		try {
			// Cria Jogador - Nossa classe (Importada de Jogo.Jogador)
			Jogador player = new Jogador("Capitao Jack");
			// Cria conteiner, facilita a organizacao e adicao de nova Telas (JPanel)
			cont = getContentPane();
		
			// Cria Telas
			telaConexao = new TelaConexao();
			telaConsole = new TelaConsole(player);
			telaTabuleiro = new TelaTabuleiro(player);
			telaChat = new TelaChat(telaConexao);
			
		
			// Inicializa Thread responsavel por verificar Rodadas de cada Jogador
			// e controla as Telas quando necessario.
			new ThreadMonitoraTelas(telaConexao, telaTabuleiro, telaConsole, telaChat).start();
			/**
			 * MAIOR DIFICULDADE ATE AQUI:
			 * 
			 * Criei esta Thread (Processo filho) pois ainda nao descobri uma
			 * maneira de fazer as Telas (JFrames) se comunicar entre elas,
			 * todas sempre ficam "ocupadas" aguardando algum evento (click,
			 * entrada do teclado, etc)... Estava com dificuldade de
			 * compartilhar o Numero de Rodadas, tentei de varias maneiras mas
			 * n�o consegui de outro jeito! Os botoes da TelaTabuleiro nao se
			 * relacionam com os botoes da TelaMenuConexao, nem com nenhuma
			 * outra Tela, sao objetos diferentes reunidos dentro do conteiner
			 * que "monta" a Janela, ate onde entendi a classe JFrame fica
			 * eternamente presa aos eventos, por isso eu criei essa Thread,
			 * porque a "comunicacao" soh existia apos um evento. Como se trata
			 * de um jogo e um botao influencia diretamente em outro, como no
			 * nosso caso por exemplo, na primeira etapa do Batalha Naval as 5
			 * primeiras Rodadas s�o referentes a escolha da Posicao de cada uma
			 * das 5 Pecas/Navios, o TelaTabuleiro conta quantos botoes foram
			 * selecionados e a TelaMenuImagens precisa retirar/mudar a Imagem
			 * do Menu, a Thread verifica o numero de Rodadas e controla as
			 * Telas. Henrique Wilhelm.
			 */

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

	// Main (Executavel)
	public static void main(String args[]) throws UnknownHostException,
			IOException {

		TelaClienteMain test = new TelaClienteMain();
		test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		test.setBackground(new Color(0, 100, 50));
		test.setSize(1000, 500);
		//stest.pack();
		test.setVisible(true);
	}
}