package Graficos;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Socket.Cliente;


public class TelaInicio {

	private TelaConexao telaConexao;
	private JPanel telaInicio;
	private Cliente cliente;
	private JPanel cards;
	public TelaInicio(JPanel telaPrincipal, Cliente cliente, JPanel cards){
		
		this.telaInicio = new JPanel();
		this.telaConexao = new TelaConexao(telaPrincipal, cliente, cards);
		this.telaInicio.add(this.telaConexao.getPanelConexao());
		this.cards = cards;
	}
	public JPanel getTelaInicio() {
		return telaInicio;
	}
	public void setTelaInicio(JPanel telaInicio) {
		this.telaInicio = telaInicio;
	}
	
	
	
}
