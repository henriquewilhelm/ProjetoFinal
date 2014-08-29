package Graficos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.1 de Interface Grafica 
 * (TelaChat) 
 * Esta classe eh responsavel pela Tela do Chat (Bate-Papo). Ela
 * possui dois Campos de Texto (TextField e TextArea com ScrollPane)
 * e um botao enviar (JButton)!  
 * Autor: Henrique W.
 */

public class TelaChat {
	// Declara Tela (JPanel)
	private JPanel panelChat; 
	// Declara Componentes
	private JTextArea textAreaConversas;
	private JScrollPane scrollPane;
	private JTextField textFieldChat;
	private JButton buttonChat; // Enviar
	
	private TelaConexao telaConexao;
	// Construtor da Tela
	public TelaChat(TelaConexao telaConexao){
		this.telaConexao = telaConexao;
		// Cria nova Tela (JPanel)
		setPanelChat(new JPanel(new GridBagLayout()));
        ButtonHandler handler = new ButtonHandler();
        
        setTextFieldChat(new JTextField(20));
        getTextFieldChat().addActionListener(handler);
        
        setTextAreaConversas(new JTextArea(5, 20));
        getTextAreaConversas().setEditable(false);
        scrollPane = new JScrollPane(textAreaConversas);
        
        setButtonChat(new JButton("Enviar"));
        getButtonChat().addActionListener(handler);
        
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
 
        c.fill = GridBagConstraints.HORIZONTAL;
        getPanelChat().add(scrollPane, c);
 
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        getPanelChat().add(textFieldChat, c);
        getPanelChat().add(buttonChat);
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
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
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
