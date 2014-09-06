package Graficos;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Bomba extends JFrame {

	private static final long serialVersionUID = 1L;
	static int intervalo;
	static Timer tempo;
	static int i = 2;// parametro teste que será passado

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bomba frame = new Bomba(i);// Chama qual imagem desejada.
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bomba(int i) {
		int delay = 1000;
		int periodo = 1000;
		tempo = new Timer();
		String imagem = null;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel Label = new JLabel("");
		Label.setHorizontalAlignment(SwingConstants.CENTER);

		switch (i) {
		case 1:
			setBounds(100, 100, 341, 258);
			intervalo = 3;
			imagem = "bigexplosao";
			break;

		case 2:
			setBounds(100, 100, 271, 215);
			intervalo = 7;
			imagem = "nuclear";
			break;

		case 3:
			setBounds(100, 100, 500, 300);
			intervalo = 1;
			imagem = "jack";
			break;
			
		case 4:
			setBounds(100, 100, 500, 300);
			intervalo = 2;
			imagem = "giphy3";
			break;
		
		case 5:
			setBounds(100, 100, 500, 300);
			intervalo = 2;
			imagem = "giphy2";
			break;
		
		default:
			break;
		}
		Label.setIcon(new ImageIcon(
				"D:\\Workspace\\Projeto\\src\\Graficos\\img\\popup\\" 
						+ imagem + ".gif"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.CENTER).addComponent(Label, Alignment.CENTER,
				GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.CENTER).addComponent(Label, Alignment.CENTER,
				GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE));
		contentPane.setLayout(gl_contentPane);

		tempo.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				setintervalo();
			}
		}, delay, periodo);

	}

	private static final int setintervalo() {
		if (intervalo == 1) {
			tempo.cancel();
			System.exit(0);
		}
		return --intervalo;
	}
}