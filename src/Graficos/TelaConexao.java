package Graficos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.*;
import Socket.Cliente;

/**
 * Batalha Naval (Projeto GeracaoTec) v1.0 Versao 2.2 de Interface Grafica
 * Classe: telaConexao
 * Essa classe eh responsavel pela Tela de Conexao, possui quatro Textfileds 
 * Servidor, Porta, Login e Senha, dois Botoes: Conecta/Desconceta... Ela importa o
 * pacote Socket ja desenvolvido neste projeto (Servidor/Cliente), realiza a 
 * conexao do ClientSocket com o ServerSocket pela sua interface, possui uma 
 * relacao com todas as telas pois  eh responsavel inicializar a classe de 
 * comunicacao... 
 * Autor: Henrique W.
 */

public class TelaConexao {
	// Declara Telas (JPanel)
	private JPanel panelConexao;
	private JPanel cont;
	private JPanel cards;
	// Componentes do Menu de Conexao com Servidor
	private JLabel serverLabel;
	private JLabel portaLabel;
	private JLabel loginLabel;
	private JLabel senhaLabel;
	private JButton conectaServer;
	private JButton closeServer;
	private JTextField textFieldHost;
	private JTextField textFieldPorta;
	private JTextField textFieldLogin;
	private JTextField textFieldSenha;
	// Cria e instancia Classe de Comunicacao de Dados
	private Cliente cliente;
	// Variaveis auxiliares
	private String host;
	private int porta;
	private PrintStream saida;
	private String comandoSaida = "";
	private boolean status = false;
	
	// Construtor da Tela
	public TelaConexao(JPanel cont, Cliente cliente, JPanel cards) {
		// Recebe dados por parametro
		this.cont = cont;
		this.cards = cards;
		setCliente(cliente);
		// Cria nova Tela (JPanel)
		setPanelConexao(new JPanel(new GridLayout(5, 2)));
		// Cria os Componentes - Campos (TextField) e botoes (JButton)
		setServerLabel(new JLabel("Servidor"));
		setTextFieldHost(new JTextField(30));
		getTextFieldHost().setText("localhost");
		setPortaLabel(new JLabel("Porta"));
		setTextFieldPorta(new JTextField(5));
		getTextFieldPorta().setText("22222");
		setLoginLabel(new JLabel("Login"));
		setTextFieldLogin(new JTextField());
		setSenhaLabel(new JLabel("Senha"));
		setTextFieldSenha(new JTextField());
		setConectaServer(new JButton("Conecta no servidor"));
		setCloseServer(new JButton("Desconecta"));
		// Adiciona objetos na Tela (JPanel)
		getPanelConexao().add(getServerLabel());
		getPanelConexao().add(getTextFieldHost());
		getPanelConexao().add(getPortaLabel());
		getPanelConexao().add(getTextFieldPorta());
		getPanelConexao().add(getLoginLabel());
		getPanelConexao().add(getTextFieldLogin());
		getPanelConexao().add(getSenhaLabel());
		getPanelConexao().add(getTextFieldSenha());
		getPanelConexao().add(getConectaServer());
		getPanelConexao().add(getCloseServer());
		// Cria handlerCon (Manipulador de Botoes - Acoes)
		ButtonConexaoHandler handlerCon = new ButtonConexaoHandler();
		getCloseServer().addActionListener(handlerCon);
		getConectaServer().addActionListener(handlerCon);
		// Desabilita botao
		getCloseServer().setEnabled(false);
	}
	// Manipulador de Acoes - Botoes (Conecta, Desconecta)
	private class ButtonConexaoHandler implements ActionListener {
 		public void actionPerformed(ActionEvent event) {
			try {
				// Se botao Conecta for "apertado" 
				if (event.getSource() == getConectaServer()) {
					host = getTextFieldHost().getText();
					porta = Integer.parseInt(getTextFieldPorta().getText());
					// Adiciona Host de Porta na Classe de Comunicacao de Dados
					getCliente().setPorta(porta);
					getCliente().setHost(host);
					// Chama metodo conecta da Classe de Comunicacao de Dados 
					setStatus(getCliente().conecta()); // Retorna TRUE e conectar
					getConectaServer().setEnabled(false); // Desabilita botao Conecta
					getCloseServer().setEnabled(true); // Habilita botao Desconecta
					// Muda Tela, passa para Tela de Opcoes (Player, Multiplayer e Ranking)
					CardLayout cl = (CardLayout)(cards.getLayout());
			        cl.show(cards, "2");
				} 
				// Se botao Desconta for "apertado"
				else if (event.getSource() == getCloseServer()) {
					// Chama metodo desconecta da Classe de Comunicacao de Dados 
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
	public JPanel getPanelConexao() {
		return panelConexao;
	}
	public void setPanelConexao(JPanel panelConexao) {
		this.panelConexao = panelConexao;
	}
	public JLabel getPortaLabel() {
		return portaLabel;
	}
	public void setPortaLabel(JLabel portaLabel) {
		this.portaLabel = portaLabel;
	}
	public JLabel getServerLabel() {
		return serverLabel;
	}
	public void setServerLabel(JLabel server) {
		this.serverLabel = server;
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cli) {
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
	public JLabel getLoginLabel() {
		return loginLabel;
	}
	public void setLoginLabel(JLabel loginLabel) {
		this.loginLabel = loginLabel;
	}
	public JLabel getSenhaLabel() {
		return senhaLabel;
	}
	public void setSenhaLabel(JLabel senhaLabel) {
		this.senhaLabel = senhaLabel;
	}
	public JTextField getTextFieldLogin() {
		return textFieldLogin;
	}
	public void setTextFieldLogin(JTextField textFieldLogin) {
		this.textFieldLogin = textFieldLogin;
	}
	public JTextField getTextFieldSenha() {
		return textFieldSenha;
	}
	public void setTextFieldSenha(JTextField textFieldSenha) {
		this.textFieldSenha = textFieldSenha;
	}
	public JPanel getCont() {
		return cont;
	}
	public void setCont(JPanel cont) {
		this.cont = cont;
	}
	public JPanel getCards() {
		return cards;
	}
	public void setCards(JPanel cards) {
		this.cards = cards;
	}
}
