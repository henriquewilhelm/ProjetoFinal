package Graficos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.*;

import Jogo.Jogador;
import Socket.Cliente;

/**
 * Batalha Naval (Ultimate Battle) v1.0 
 * Versao 3.0 de Interface Grafica
 * (telaPlayer)
 * Essa classe eh responsavel pela Tela do Jogador, apresenta todos
 * os dados do jogador como imgem, nome, barra de vida, quantidade de barcos,
 * quantidade de medalhas, numero de vitorias, derrotas e total de jogos
 * Autor: Henrique W.
 */

public class TelaPlayer {

	private JPanel panelPlayer;
	
	private JPanel telaImg;
	private JPanel telaMenu;
	
	private JPanel telaVida;
	private JPanel telaNavios;
	private JPanel telaMedalhas;
	private JPanel telaOutros;
	
	private JPanel caixaVida;
	private JPanel caixaNavios;
	private JPanel caixaMedalhas;
	
	private JLabel vidaText;
	private JLabel naviosText;
	private JLabel medalhasText;
	private JLabel vitoriasText;
	private JLabel derrotasText;
	private JLabel totalText;
	
	private JLabel img;
	private Icon imagem = new ImageIcon(getClass().getResource("img/capitao/capitao.jpg"));
	private JLabel nome;
	
	private JLabel vida[];
	private JLabel navios[];
	private JLabel vitorias[];
	private JLabel derrotas;
	private JLabel total;
	
		
	public TelaPlayer(Jogador player) {
	
		this.panelPlayer = new JPanel(new BorderLayout());
		
		this.telaImg = new JPanel();
		this.telaMenu = new JPanel(new GridLayout(2,4));
		
		this.telaImg.setLayout(new BoxLayout(telaImg,BoxLayout.Y_AXIS));
		
		this.telaVida = new JPanel(new FlowLayout());
		this.telaNavios = new JPanel(new BorderLayout());
		this.telaMedalhas = new JPanel(new BorderLayout());
		this.telaOutros = new JPanel(new BorderLayout());

		this.caixaVida = new JPanel(new GridLayout(1,15));
		this.caixaNavios = new JPanel(new GridLayout(1,5));
		this.caixaMedalhas = new JPanel(new GridLayout(1,player.getVitorias()));
		
		this.img = new JLabel(imagem);
		this.nome = new JLabel("nome");
		this.nome.setFont(new Font("Verdana", Font.BOLD, 14));
		this.nome.setForeground(Color.RED);
		this.vidaText = new JLabel("Vida");
		this.vidaText.setFont(new Font("Verdana", Font.BOLD, 22));
		this.naviosText = new JLabel("Navios");
		this.naviosText.setFont(new Font("Verdana", Font.BOLD, 22));
		this.medalhasText = new JLabel("Medalhas");
		this.medalhasText.setFont(new Font("Verdana", Font.BOLD, 22));
		this.vitoriasText = new JLabel("Vitorias");
		this.vitoriasText.setFont(new Font("Verdana", Font.BOLD, 12));
		this.derrotasText = new JLabel("Derrotas");
		this.derrotasText.setFont(new Font("Verdana", Font.BOLD, 12));
		this.totalText = new JLabel("Total");
		this.totalText.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.vida = new JLabel[15];
		for (int i =0; i<vida.length; i++){
			this.vida[i] = new JLabel(new ImageIcon(this.getClass().getResource("img/vida/vida1.jpg")));
			this.caixaVida.add(this.vida[i]);
		}
		this.navios = new JLabel[5];
		for (int i =0; i<navios.length; i++){
			this.navios[i] = new JLabel();
			this.caixaNavios.add(this.navios[i]);
		}
		
		this.vitorias = new JLabel[player.getMedalhas()];
		for (int i =0; i<vitorias.length; i++){
			String dirMedalha = "img/medalhas/medalha" + Integer.toString((i+1)) + ".jpg";
			this.vitorias[i] = new JLabel(new ImageIcon(this.getClass().getResource(dirMedalha)));
			this.caixaMedalhas.add(this.vitorias[i]);
		}

		this.getNome().setText(player.getNome());
		this.getVitoriasText().setText("Vitorias: " + Integer.toString(player.getVitorias()));
		this.getDerrotasText().setText("Derrotas: " + Integer.toString(player.getDerrotas()));
		this.getTotalText().setText("Numero de Partidas: " + Integer.toString(player.getTotalJogos()));
		
		this.telaVida.add(this.vidaText);
		this.telaVida.add(this.caixaVida);
		this.telaMedalhas.add(this.medalhasText, BorderLayout.WEST);
		this.telaMedalhas.add(this.caixaMedalhas, BorderLayout.CENTER);
		this.telaNavios.add(this.naviosText, BorderLayout.WEST);
		this.telaNavios.add(this.caixaNavios, BorderLayout.CENTER);
		this.telaOutros.add(this.vitoriasText, BorderLayout.NORTH);
		this.telaOutros.add(this.derrotasText, BorderLayout.CENTER);
		this.telaOutros.add(this.totalText, BorderLayout.SOUTH);
		
		this.telaMenu.add(this.telaVida);
		this.telaMenu.add(this.telaMedalhas);
		this.telaMenu.add(this.telaNavios);
		this.telaMenu.add(this.telaOutros);
		
		this.panelPlayer.add(telaMenu, BorderLayout.CENTER);
		
		this.telaImg.add(this.img);
		this.telaImg.add(this.nome);
		
		this.panelPlayer.add(telaImg, BorderLayout.WEST);
	}

	// Getters and Setters
	public JPanel getPanelPlayer() {
		return panelPlayer;
	}
	public void setPanelPlayer(JPanel panelPlayer) {
		this.panelPlayer = panelPlayer;
	}
	public JPanel getTelaImg() {
		return telaImg;
	}
	public void setTelaImg(JPanel telaImg) {
		this.telaImg = telaImg;
	}
	public JPanel getTelaMenu() {
		return telaMenu;
	}
	public void setTelaMenu(JPanel telaMenu) {
		this.telaMenu = telaMenu;
	}
	public JPanel getTelaVida() {
		return telaVida;
	}
	public void setTelaVida(JPanel telaVida) {
		this.telaVida = telaVida;
	}
	public JPanel getTelaNavios() {
		return telaNavios;
	}
	public void setTelaNavios(JPanel telaNavios) {
		this.telaNavios = telaNavios;
	}
	public JPanel getTelaVitorias() {
		return telaMedalhas;
	}
	public void setTelaVitorias(JPanel telaVitorias) {
		this.telaMedalhas = telaVitorias;
	}
	public JPanel getTelaOutros() {
		return telaOutros;
	}
	public void setTelaOutros(JPanel telaOutros) {
		this.telaOutros = telaOutros;
	}
	public JPanel getCaixaVida() {
		return caixaVida;
	}
	public void setCaixaVida(JPanel caixaVida) {
		this.caixaVida = caixaVida;
	}
	public JPanel getCaixaNavios() {
		return caixaNavios;
	}
	public void setCaixaNavios(JPanel caixaNavios) {
		this.caixaNavios = caixaNavios;
	}
	public JPanel getCaixaMedalhas() {
		return caixaMedalhas;
	}
	public void setCaixaMedalhas(JPanel caixaVitorias) {
		this.caixaMedalhas = caixaVitorias;
	}
	public JLabel getVidaText() {
		return vidaText;
	}
	public void setVidaText(JLabel vidaText) {
		this.vidaText = vidaText;
	}
	public JLabel getNaviosText() {
		return naviosText;
	}
	public void setNaviosText(JLabel naviosText) {
		this.naviosText = naviosText;
	}
	public JLabel getVitoriasText() {
		return vitoriasText;
	}
	public void setVitoriasText(JLabel vitoriasText) {
		this.vitoriasText = vitoriasText;
	}
	public JLabel getDerrotasText() {
		return derrotasText;
	}
	public void setDerrotasText(JLabel derrotasText) {
		this.derrotasText = derrotasText;
	}
	public JLabel getTotalText() {
		return totalText;
	}
	public void setTotalText(JLabel totalText) {
		this.totalText = totalText;
	}
	public JLabel getImg() {
		return img;
	}
	public void setImg(JLabel img) {
		this.img = img;
	}
	public Icon getImagem() {
		return imagem;
	}
	public void setImagem(Icon imagem) {
		this.imagem = imagem;
	}
	public JLabel getNome() {
		return nome;
	}
	public void setNome(JLabel nome) {
		this.nome = nome;
	}
	public JLabel[] getVida() {
		return vida;
	}
	public void setVida(JLabel[] vida) {
		this.vida = vida;
	}
	public JLabel[] getNavios() {
		return navios;
	}
	public void setNavios(JLabel[] navios) {
		this.navios = navios;
	}
	public JLabel[] getVitorias() {
		return vitorias;
	}
	public void setVitorias(JLabel[] vitorias) {
		this.vitorias = vitorias;
	}
	public JLabel getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(JLabel derrotas) {
		this.derrotas = derrotas;
	}
	public JLabel getTotal() {
		return total;
	}
	public void setTotal(JLabel total) {
		this.total = total;
	}
}
