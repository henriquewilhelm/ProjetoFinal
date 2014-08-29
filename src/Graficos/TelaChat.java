package Graficos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private JTextField textFieldConversas;
	private JTextField textFieldChat;
	private JButton buttonChat; // Enviar
	// Construtor da Tela
	public TelaChat(){
		// Cria nova Tela (JPanel)
		setPanelChat(new JPanel(new GridLayout(7, 1, 1, 1)));
		setTextFieldConversas(new JTextField("Aqui o bate-papo entre os jogadores...\nTestanto 1, 2."));
		setTextFieldChat(new JTextField("Comece o chat...", 250));
		setButtonChat(new JButton("Enviar"));
		// Habilita / Disabilita edicao
		getTextFieldChat().setEditable(true);
		getTextFieldConversas().setEditable(false);
		// Adicionando Componentes na nova Tela (JPanel)
		getPanelChat().add(getTextFieldConversas());
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
				}

			} catch (Exception exception) {
				JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto");
				System.out.println(exception);
			}
		}
	}
	public JTextField getTextFieldConversas() {
		return textFieldConversas;
	}
	public void setTextFieldConversas(JTextField textFieldConversas) {
		this.textFieldConversas = textFieldConversas;
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
