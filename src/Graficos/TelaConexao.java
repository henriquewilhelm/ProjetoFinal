package Graficos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.*;

import Socket.Cliente;

/**
 * Batalha Naval (Ultimate Battle) v1.0 Versao 2.1 de Interface Grafica
 * (telaConexao)
 * Essa classe eh responsavel pela Tela de Conexao, possui um Textfiled: 
 * Servidor/Porta e Botoes: Conecta/Desconceta, etc... Ela importa o
 * pacote Socket ja desenvolvido neste projeto (Servidor/Cliente) e realiza 
 * a conexao ClientSocket com ServerSocket pela sua interface, possui uma
 * relacao com todas as telas pois eh responsavel por toda comunicacao... 
 * Autor: Henrique W.
 */

public class TelaConexao {
	// Declara Tela (JPanel)
	private JPanel panelMenuConexao;
	// Componentes do Menu de Conexao com Servidor
	private JLabel server;
	private JButton conectaServer;
	private JButton closeServer;
	private JButton opUm;
	private JButton opDois;
	private JButton opTres;
	private JTextField textFieldHost;
	private JTextField textFieldPorta;
	// Nossa classe (Importada de Socket)
	private Cliente cliente;
	// Variaveis auxiliares
	private String host;
	private int porta;
	private PrintStream saida;
	private String comandoSaida = "";
	private boolean status = false;
	// Construtor da Tela
	public TelaConexao() {
		// Cria nova Tela (JPanel)
		setPanelMenuConexao(new JPanel(new GridLayout(1, 1)));
		// Cria os Componentes - Campos (TextField) e botoes (JButton)
		setServer(new JLabel("Servidor:"));
		setTextFieldHost(new JTextField(30));
		getTextFieldHost().setText("localhost");
		setTextFieldPorta(new JTextField(5));
		setConectaServer(new JButton("Conecta no servidor"));
		setCloseServer(new JButton("Desconecta"));
		setOpUm(new JButton("Op Um"));
		setOpDois(new JButton("Op Dois"));
		setOpTres(new JButton("Op Tres"));

		// Adiciona objetos na Tela (JPanel)
		getPanelMenuConexao().add(getServer());
		getPanelMenuConexao().add(getTextFieldHost());
		getPanelMenuConexao().add(getTextFieldPorta());
		getPanelMenuConexao().add(getConectaServer());
		getPanelMenuConexao().add(getCloseServer());
		getPanelMenuConexao().add(getOpUm());
		getPanelMenuConexao().add(getOpDois());
		getPanelMenuConexao().add(getOpTres());

		// Cria handler (Manipulador de Botoes - Acoes)
		ButtonHandler handler = new ButtonHandler();
		getOpUm().addActionListener(handler);
		getOpDois().addActionListener(handler);
		getOpTres().addActionListener(handler);

		// Cria handlerCon (Manipulador de Botoes - Acoes)
		ButtonConexaoHandler handlerCon = new ButtonConexaoHandler();
		getCloseServer().addActionListener(handlerCon);
		getConectaServer().addActionListener(handlerCon);

		// Desabilita botao
		getCloseServer().setEnabled(false);
	}

	// Manipulador de Acoes - Botoes (1, 2, 3)
	private class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == getOpUm()) {
				setComandoSaida("#");
			} else if (event.getSource() == getOpDois()) {
				setComandoSaida("@");
			} else if (event.getSource() == getOpTres()) {
				setComandoSaida("$");
			}
			//saida.println(comandoSaida);
			//System.out.println(comandoSaida);
		}
	}

	// Manipulador de Acoes - Botoes (Conecta, Desconecta)
	private class ButtonConexaoHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				if (event.getSource() == getConectaServer()) {
					host = getTextFieldHost().getText();
					porta = Integer.parseInt(getTextFieldPorta().getText());
					// Cliente (Import Socket)
					cliente = new Cliente(host, porta);
					saida = cliente.conecta(); // PrintStream do socket
					getConectaServer().setEnabled(false); // Desabilita botao
					getCloseServer().setEnabled(true); // Habilita botao
					setStatus(true);
				} else if (event.getSource() == getCloseServer()) {
					cliente.desconecta();
					getConectaServer().setEnabled(true); // Habilita botao
					getCloseServer().setEnabled(false); // Desabilita botao
					setStatus(false);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// Getters and Setters
	public JPanel getPanelMenuConexao() {
		return panelMenuConexao;
	}

	public void setPanelMenuConexao(JPanel panelMenuConexao) {
		this.panelMenuConexao = panelMenuConexao;
	}

	public JLabel getServer() {
		return server;
	}

	public void setServer(JLabel server) {
		this.server = server;
	}

	public JButton getConectaServer() {
		return conectaServer;
	}

	public void setConectaServer(JButton conectaServer) {
		this.conectaServer = conectaServer;
	}

	public JButton getCloseServer() {
		return closeServer;
	}

	public void setCloseServer(JButton closeServer) {
		this.closeServer = closeServer;
	}

	public JButton getOpUm() {
		return opUm;
	}

	public void setOpUm(JButton opUm) {
		this.opUm = opUm;
	}

	public JButton getOpDois() {
		return opDois;
	}

	public void setOpDois(JButton opDois) {
		this.opDois = opDois;
	}

	public JButton getOpTres() {
		return opTres;
	}

	public void setOpTres(JButton opTres) {
		this.opTres = opTres;
	}

	public JTextField getTextFieldHost() {
		return textFieldHost;
	}

	public void setTextFieldHost(JTextField textFieldHost) {
		this.textFieldHost = textFieldHost;
	}

	public JTextField getTextFieldPorta() {
		return textFieldPorta;
	}

	public void setTextFieldPorta(JTextField textFieldPorta) {
		this.textFieldPorta = textFieldPorta;
	}

	public Cliente getCli() {
		return cliente;
	}

	public void setCli(Cliente cli) {
		this.cliente = cli;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public PrintStream getSaida() {
		return saida;
	}

	public void setSaida(PrintStream saida) {
		this.saida = saida;
	}

	public String getComandoSaida() {
		return comandoSaida;
	}

	public void setComandoSaida(String comandoSaida) {
		this.comandoSaida = comandoSaida;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
