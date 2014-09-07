package Graficos;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Socket.Cliente;

/**
 * Batalha Naval (Projeto GeracaoTec) v1.0 - Versao 2.2 de Interface Grafica 
 * Classe: telaInicio
 * Tela inicial responsavel por chamar e organizar a TelaConexao, junto a imagem 
 * de apresentacao do jogo.
 * Autor: Henrique Wilhelm 
 */

public class TelaInicio {
	// Declara Classe Grafica
 	private TelaConexao telaConexao;
 	// Declara Telas
	private JPanel telaInicio;
	// Declara Classe de Comunicacao de Dados
	// Construtor, recebe a tela Principal, Cliente (Pacote de Comunicacao e o Card de delas)
	public TelaInicio(Cliente cliente, JPanel cards){
		this.telaInicio = new JPanel();
		this.telaConexao = new TelaConexao(telaInicio, cliente, cards);
		this.telaInicio.add(this.telaConexao.getPanelConexao());
	}
	// Getter and Setters
	public JPanel getTelaInicio() {
		return telaInicio;
	}
	public void setTelaInicio(JPanel telaInicio) {
		this.telaInicio = telaInicio;
	}
}
