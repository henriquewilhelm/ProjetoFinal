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

public class TelaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	// Classes do projeto
	private JPanel telaPrincipal;
	private TelaJogador telaJogador;
	private TelaTabuleiro telaTabuleiro;
	private TelaNavios telaNavios;
	private TelaChat telaChat;
	private Cliente cliente;
	private Jogador jogador;
	// Construtor da Tela Principal (Tabuleiros, Chat, Jogador, Navios), recebe cliente conectado.
	public TelaPrincipal(Cliente cliente, Jogador jogador) {
		
		try {			
			this.telaPrincipal = new JPanel(new BorderLayout());
			// Cria Jogador - Nossa classe (Importada de Jogo.Jogador)
			//this.player = player;
			// Cria Cliente Socket - Nossa classe (Importada de Socket.Cliente)			
			this.cliente = cliente;
			// Cria Sub-Telas 
			telaJogador = new TelaJogador(jogador);
			telaTabuleiro = new TelaTabuleiro(this.cliente, jogador);
			telaNavios = new TelaNavios(jogador);
			telaChat = new TelaChat(this.cliente);
			// Adiciona Sub-Telas a Tela Principal, passado o Layout escolhido
			telaPrincipal.add(telaJogador.getPanelPlayer(), BorderLayout.PAGE_START);
			telaPrincipal.add(telaTabuleiro.getTabuleiro1(), BorderLayout.LINE_START);
			telaPrincipal.add(telaTabuleiro.getTabuleiro2(), BorderLayout.LINE_END);
			telaPrincipal.add(telaNavios.getCards(), BorderLayout.CENTER);
			telaPrincipal.add(telaChat.getPanelChat(), BorderLayout.SOUTH);
			
		} catch (Exception exception) {
			System.out.println(exception);
			exception.printStackTrace();
		}
	}
	// Getter and Setters
	public JPanel getTelaPrincipal() {
		return telaPrincipal;
	}
	public void setTelaPrincipal(JPanel telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
	}
	public TelaJogador getTelaJogador() {
		return telaJogador;
	}
	public void setTelaJogador(TelaJogador telaPlayer) {
		this.telaJogador = telaPlayer;
	}
	public TelaTabuleiro getTelaTabuleiro() {
		return telaTabuleiro;
	}
	public void setTelaTabuleiro(TelaTabuleiro telaTabuleiro) {
		this.telaTabuleiro = telaTabuleiro;
	}
	public TelaNavios getTelaNavios() {
		return telaNavios;
	}
	public void setTelaNavios(TelaNavios telaNavios) {
		this.telaNavios = telaNavios;
	}
	public TelaChat getTelaChat() {
		return telaChat;
	}
	public void setTelaChat(TelaChat telaChat) {
		this.telaChat = telaChat;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador player) {
		this.jogador = player;
	}
	
}