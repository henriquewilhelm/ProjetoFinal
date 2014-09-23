package graficosServidor;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import bancoDados.JDBCConnect;
import bancoDados.JogadorDAO;
import net.proteanit.sql.DbUtils;

public class LogJogadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTable table;
	private JLabel lblPesquisarPor;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogJogadores frame = new LogJogadores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LogJogadores() {
		setResizable(false);
		setTitle("Log de Ranking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				consultaDados(e);				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setViewportBorder(null);
		scrollPane.setEnabled(false);
		
		lblPesquisarPor = new JLabel("Pesquisar por");
		lblPesquisarPor.setFont(new Font("Arial", Font.PLAIN, 14));
		
		textField = new JTextField();

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaDados(e);
			}
		});
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(lblPesquisarPor, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnConsultar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPesquisarPor)
						.addComponent(btnConsultar)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void consultaDados(ActionEvent e){
		try {
			JDBCConnect conexao = new JDBCConnect();
			Connection con = conexao.criarConexao();
			JogadorDAO logando = new JogadorDAO();
			ResultSet rs = logando.log(con, textField.getText());
			//DbUtils monta a grid de exibção. 
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
