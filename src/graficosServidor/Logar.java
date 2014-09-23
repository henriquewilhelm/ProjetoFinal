package graficosServidor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JSeparator;

import bancoDados.JDBCConnect;
import bancoDados.JogadorDAO;

import java.awt.Component;

import jogo.*;

/**
 * 
 * @author cadu
 *
 */
public class Logar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSenha;
    private Vector<JCheckBox> testaChks;  
    private Vector<JLabel> titulo;  

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logar frame = new Logar();
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
	public Logar() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JCheckBox chckbxCriarJogador = new JCheckBox("Criar Jogador");
		 testaChks = new Vector<JCheckBox>();  
		 testaChks.add(chckbxCriarJogador);
		chckbxCriarJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxCriarJogadorEvento(e);
			}
		});
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 14));
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				btnOkEvento(e);				
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLimparEvento(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
									.addComponent(txtSenha, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)))))
					.addGap(79))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnLimpar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_titulo = new JPanel();
		
		JSeparator separator_2 = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_titulo, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(190, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(10, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)))
					.addGap(36))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxCriarJogador)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_titulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxCriarJogador)
					.addGap(16))
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {panel, separator_2, separator_1});
		
		JLabel lblTituloJogo = new JLabel("Entrar no Jogo");
		 titulo = new Vector<JLabel>();  
		 titulo.add(lblTituloJogo);
		lblTituloJogo.setFont(new Font("Arial", Font.ITALIC, 14));
		lblTituloJogo.setVerticalAlignment(SwingConstants.TOP);
		lblTituloJogo.setHorizontalAlignment(SwingConstants.LEFT);
		panel_titulo.add(lblTituloJogo);
		contentPane.setLayout(gl_contentPane);
		
	}

	public void btnOkEvento(ActionEvent e){
		
		if(chckbxCriarJogadorEvento(e)){
			try {
				JDBCConnect conexao = new JDBCConnect();
				Connection con = conexao.criarConexao();
				Jogador jogador = new Jogador(txtNome.getText(), txtSenha.getText());
				JogadorDAO logando = new JogadorDAO();

				if(logando.addLogin(con, jogador.getNome(), jogador.getSenha()))
					JOptionPane.showMessageDialog(null, "O Usuário " 
							+ txtNome.getText().toUpperCase().replaceAll("\\s+","") + " criado com sucesso" );
				else{
					JOptionPane.showMessageDialog(null, "Ja existe este usuario "
							+ txtNome.getText().toUpperCase().replaceAll("\\s+","") );
					con.close();
					System.out.println("conexao finalizada.");
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (Exception ex) {
				
			}

		}else{
	    	try {
				JDBCConnect conexao = new JDBCConnect();
				Connection con = conexao.criarConexao();
				Jogador jogador = new Jogador(txtNome.getText(), txtSenha.getText());
				JogadorDAO logando = new JogadorDAO();

				if(logando.login(con, jogador.getNome(), jogador.getSenha()))
					JOptionPane.showMessageDialog(null, "O Usuario " 
							+ txtNome.getText().toUpperCase() + " se Logou" );
				else{
					JOptionPane.showMessageDialog(null, "Usuario ou senha não cadastrados" );
					con.close();
					System.out.println("conexao finalizada.");
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (Exception ex) {
				
			}
		}
	}
	
	public void btnLimparEvento(ActionEvent e){		
		txtNome.setText("");
		txtSenha.setText("");
		testaChks.get(0).setSelected(false);
		titulo.get(0).setText("Entrar no Jogo");
	}
	
	public boolean chckbxCriarJogadorEvento(ActionEvent e){
		 JCheckBox temp = testaChks.get(0);  
		 JLabel texto = titulo.get(0);  
		 
		 if(temp.isSelected()){
			texto.setText("Criar Jogador");
            return true;
         }else{
        	 texto.setText("Entrar no Jogo");
         }
		return false;		
	}
}