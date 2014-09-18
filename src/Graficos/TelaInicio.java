package Graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Jogo.Jogador;
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
	private JPanel telaBuffer;
	private Container telaInformacoes;
	private JLabel buffer;
	private JLabel autores;
	private JLabel alexandreTextLabel;
	private JLabel carlosTextLabel;
	private JLabel henriqueTextLabel;
	private JLabel jaisonTextLabel;
	private JLabel otavioTextLabel;
	private JLabel tituloTextLabel;
	// Declara Classe de Comunicacao de Dados
	private Cliente cliente;
	private Jogador jogador;
	// Construtor, recebe a tela Principal, Cliente (Pacote de Comunicacao e o Card de delas)
	public TelaInicio(Cliente cliente){
		this.cliente = cliente;
		this.telaInicio = new JPanel();
		this.buffer = new JLabel();
		this.buffer.setIcon(new ImageIcon("img/buffer/buffer.gif"));
		this.buffer.setVisible(false);
		
		this.telaBuffer = new JPanel(new FlowLayout());
		
		this.telaConexao = new TelaConexao(telaInicio, cliente);
		
		this.telaInformacoes = new Container();
		this.telaInformacoes.setLayout(new BoxLayout(telaInformacoes, BoxLayout.PAGE_AXIS));
		
		this.tituloTextLabel = new JLabel("BATALHA NAVAL - PROJETO DO CURSO GERAÇÃO TEC");
		this.tituloTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    this.tituloTextLabel.setFont(new Font("Verdana", Font.BOLD, 24));
	    this.tituloTextLabel.setForeground(Color.BLACK);
	    this.autores =  new JLabel("Autores");
	    this.autores.setAlignmentX(Component.CENTER_ALIGNMENT);
	    this.autores.setFont(new Font("Verdana", Font.BOLD, 18));
	    this.autores.setForeground(Color.GRAY);
	    this.alexandreTextLabel = new JLabel("Alexandre Santos Souza <alexandreess@gmail.com>");
	    this.alexandreTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    this.alexandreTextLabel.setForeground(Color.gray);
	    this.carlosTextLabel = new JLabel ("Carlos Eduardo <carloseduardosousa@gmail.com>");
	    this.carlosTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    this.carlosTextLabel.setForeground(Color.gray);
	    this.henriqueTextLabel = new JLabel ("Henrique Wilhelm <henrique.wilhelm@gmail.com");
	    this.henriqueTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    this.henriqueTextLabel.setForeground(Color.gray);
	    this.jaisonTextLabel = new JLabel ("Jaison dos santos <jaison1906@gmail.com>");
	    this.jaisonTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    this.jaisonTextLabel.setForeground(Color.gray);
	    this.otavioTextLabel = new JLabel("Otavio Ribeiro <otavioribeiro@capflorianopolis.org.br>");
	    this.otavioTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    this.otavioTextLabel.setForeground(Color.gray);
	    
		this.telaInicio.add(this.telaConexao.getPanelConexao());
		
		this.telaBuffer.add(this.buffer);
		this.telaInformacoes.add(telaBuffer, Component.CENTER_ALIGNMENT);
		this.telaInformacoes.add(this.tituloTextLabel, Component.CENTER_ALIGNMENT);
		this.telaInformacoes.add(this.autores, Component.CENTER_ALIGNMENT);
		this.telaInformacoes.add(this.alexandreTextLabel, Component.CENTER_ALIGNMENT);
	    this.telaInformacoes.add(this.carlosTextLabel, Component.CENTER_ALIGNMENT);
	    this.telaInformacoes.add(this.henriqueTextLabel, Component.CENTER_ALIGNMENT);
	    this.telaInformacoes.add(this.jaisonTextLabel, Component.CENTER_ALIGNMENT);
	    this.telaInformacoes.add(this.otavioTextLabel, Component.CENTER_ALIGNMENT);
	    this.telaInicio.add(telaInformacoes);
	}
	// Getter and Setters
	public JPanel getTelaInicio() {
		return telaInicio;
	}
	public void setTelaInicio(JPanel telaInicio) {
		this.telaInicio = telaInicio;
	}
	// Getter and Setters
	public TelaConexao getTelaConexao() {
		return telaConexao;
	}
	public void setTelaConexao(TelaConexao telaConexao) {
		this.telaConexao = telaConexao;
	}
	public JLabel getBuffer() {
		return buffer;
	}
	public void setBuffer(JLabel buffer) {
		this.buffer = buffer;
	}
	
}
