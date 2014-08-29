package Graficos;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.0 de Interface Grafica (TelaChat) 
 * Esta classe eh responsavel pela Tela do Chat (Bate-Papo). Ela
 * possui dois Campos de Texto (TextField) e um botão enviar (JButton)!  
 * Autor: Henrique W.
 */

public class TelaChat {
	// Declara Tela (JPanel)
	private JPanel panelChat; 
	// Declara Componentes
	private JTextArea textAreaConversas;
	private JTextField textFieldChat;
	private JButton buttonChat; // Enviar
	
	private TelaConexao telaConexao;
	// Construtor da Tela
	public TelaChat(TelaConexao telaConexao){
		this.telaConexao = telaConexao;
		// Cria nova Tela (JPanel)
		setPanelChat(new JPanel(new GridLayout(3, 1, 1, 1)));
		setTextAreaConversas(new JTextArea(1,1));
		setTextFieldChat(new JTextField("Comece o chat...", 250));
		setButtonChat(new JButton("Enviar"));
		// Habilita / Disabilita edicao
		getTextFieldChat().setEditable(true);
		getTextAreaConversas().setEditable(false);
		getTextAreaConversas().setSize(50, 50);
		// Adicionando Componentes na nova Tela (JPanel)
		getPanelChat().add(getTextAreaConversas());
		getPanelChat().add(getTextFieldChat());
		getPanelChat().add(getButtonChat());
		// Manipulador de Eventos (Click, Entrada do Mouse/Teclado, etc)
		ButtonHandler handler = new ButtonHandler();
		//getTextFieldChat().addActionListener(handler);
		getButtonChat().addActionListener(handler);
	}
	private class ButtonHandler implements ActionListener {
		// Manipulador de Acoes - Botoes (BottonsChat)
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == getButtonChat()) {
					System.out.println(getTextFieldChat().getText());
					//Printa na saida
					telaConexao.getSaida().println(getTextFieldChat().getText());
				}
				String cmd = e.getActionCommand();
				if (cmd.equals("atualiza"))
						setTextAreaConversas(getTextAreaConversas().getText() + "\n" + telaConexao.getCli().getComandoEntrada());
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto");
				exception.printStackTrace();
			}
		}
		
	}
	public JTextArea getTextAreaConversas() {
		return textAreaConversas;
	}
	public void setTextAreaConversas(JTextArea textAreaConversas) {
		this.textAreaConversas = textAreaConversas;
	}
	public void setTextAreaConversas(String texto) {
		this.textAreaConversas.setText(texto);
	}
	public JTextField getTextFieldChat() {
		return textFieldChat;
	}
	public void setTextFieldChat(JTextField textFieldChat) {
		this.textFieldChat = textFieldChat;
	}
	public JButton getButtonChat() {
		return buttonChat;
	}
	public void setButtonChat(JButton buttonChat) {
		this.buttonChat = buttonChat;
	}
	public JPanel getPanelChat() {
		return panelChat;
	}
	public void setPanelChat(JPanel panelChat) {
		this.panelChat = panelChat;
	}
}
