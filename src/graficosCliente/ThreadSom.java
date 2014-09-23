package graficosCliente;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import audio.*;
import socket.Cliente;
import jogo.Jogador;

/**
 * Batalha Naval (Projeto GeracaoTec) - Versao 2.2 de Interface Grafica 
 * Classe: Thread Monitora Telas 
 * Thread (Processo Filho) que monitora o objeto Jogador e todas Sub-Telas da Tela Principal 
 * (JPanel), se orienta principalmente pelo o numero de rodadas para alterar a condicao dos 
 * componentes fazendo a iteracao entre eles, mas tambem utiliza outras FLAGs. Assim as Telas
 * podem se relacionar com mais facilidade sem interromper nenhuma funcionamento enquanto 
 * aguardam por alguma condicao...
 * Autor: Henrique Wilhelm
 */

public class ThreadSom extends Thread {
		
	private int valor = 0;
	// Construtor, recebe todas classes construidas para jogo e realiza a iteracao entre elas
	public ThreadSom(int valor) {
		this.valor = valor;
	}
	// Metodo que executa start() a Thread (Processo Filho)
	public void run() {
		try {
				new Explosao(valor);
				stop();
		} catch (Exception e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}