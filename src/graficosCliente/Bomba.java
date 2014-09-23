package graficosCliente;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import jogo.Jogador;
import socket.Cliente;

public class Bomba {

	private static final long serialVersionUID = 1L;
	static int intervalo;
	static Timer tempo;
	private JPanel cards;
	private JPanel contentPane;
	private JPanel tabuleiroInimigo;
	private JPanel animacao;
	private int tipo = 1;	
	private TelaTabuleiro telaTabuleiro;

	/**
	 * Create the frame.
	 */
	public Bomba(TelaTabuleiro telaTabuleiro) {
		this.cards = telaTabuleiro.getCards();
		this.telaTabuleiro = telaTabuleiro;
	}
	
	public JPanel ativa(int tipo) {
		this.tipo = tipo;
		int delay = 1000;
		int periodo = 1000;
		tempo = new Timer();
		String imagem = null;

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setVisible(true);
		
		JLabel Label = new JLabel("");
		Label.setHorizontalAlignment(SwingConstants.CENTER);

		switch (this.tipo) {
		case 1:
			intervalo = 8;
			imagem = "nuclear";
			break;
		case 2:
			intervalo = 8;
			imagem = "bigexplosao";
			break;			
		case 3:
			intervalo = 8;
			imagem = "giphy3";
			break;
		
		case 4:
			intervalo = 8;
			imagem = "giphy2";
			break;
		case 5:
			intervalo = 8;
			imagem = "agua";
			break;
			
		default:
			break;
		}
		System.out.println(imagem +"e "+  this.tipo );
		Label.setIcon(new ImageIcon("img/bomba/"+ imagem + ".gif"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.CENTER).addComponent(Label, Alignment.CENTER,
				GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.CENTER).addComponent(Label, Alignment.CENTER,
				GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE));
		contentPane.setLayout(gl_contentPane);

		tempo.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				setintervalo();
			}
		}, delay, periodo);
		telaTabuleiro.setAnimacao(this.getContentPane());
		return contentPane;
	}
	

	private int setintervalo() {
		if (intervalo == 1) {
			tempo.cancel();
			CardLayout cl = (CardLayout)(cards.getLayout());
		    cl.show(cards, "1");
		}
		return --intervalo;
	}

	public static int getIntervalo() {
		return intervalo;
	}

	public static void setIntervalo(int intervalo) {
		Bomba.intervalo = intervalo;
	}

	public static Timer getTempo() {
		return tempo;
	}

	public static void setTempo(Timer tempo) {
		Bomba.tempo = tempo;
	}

	public JPanel getCards() {
		return cards;
	}

	public void setCards(JPanel cards) {
		this.cards = cards;
	}

	public JPanel getTabuleiroInimigo() {
		return tabuleiroInimigo;
	}

	public void setTabuleiroInimigo(JPanel tabuleiroInimigo) {
		this.tabuleiroInimigo = tabuleiroInimigo;
	}

	public JPanel getAnimacao() {
		return animacao;
	}

	public void setAnimacao(JPanel animacao) {
		this.animacao = animacao;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}
}
