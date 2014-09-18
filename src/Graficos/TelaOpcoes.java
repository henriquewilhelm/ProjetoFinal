package Graficos;


	import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Jogo.Jogador;
	
	public class TelaOpcoes {
	
		private TelaJogador telaJogador;
		private JPanel panelOpcoes;
		
		private JPanel subPanelOpcoes;
		private JPanel panelPais;
		private JPanel panelImagem;
		private Container panelInfo;
		private JPanel panelBotoes;

		private JPanel cards;
		
		private JLabel informacaoTextLabel;
		private JComboBox capitaoJComboBox; // combobox que vai ter o nome dos icones
		private JComboBox paisJComboBox; // combobox que vai ter o nome dos icones
		private JLabel paisLabel; // label para mostrar o item selecionado
		private JButton voltar;
		
		private String nomeCapitao[] = { "capitao0.jpg", "capitao1.jpg",  "capitao2.jpg", "capitao3.jpg",
				"capitao4.jpg",  "capitao5.jpg", "capitao6.jpg" };
		private String nomePais[] = { "alemanha", "eua",  "franca", "inglaterra", "italia", "japao" };
		private int jogadorImagem = 0;
		private String jogadorPais = "";
		private boolean status;
		
		private Jogador jogador;
   
	    // construtor 
	    public TelaOpcoes(Jogador jogador, JPanel cards)
	    {
		  this.jogador = jogador;
		  this.cards = cards;
		  this.panelOpcoes = new JPanel(new BorderLayout());
		  
		  this.telaJogador = new TelaJogador(jogador);
		  this.telaJogador.getTelaMenu().setLayout(new GridLayout(4,1));
		  
		  
		  this.panelInfo = new Container();
		  this.panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.PAGE_AXIS));
		  
		  this.subPanelOpcoes = new JPanel(new GridLayout(1,2));
		  this.panelPais = new JPanel(new FlowLayout());
		  this.panelBotoes = new JPanel(new FlowLayout());		  
		  
		  this.informacaoTextLabel = new JLabel("ESCOLHA SUA IMAGEM E NAÇÃO");
		  this.informacaoTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	      this.informacaoTextLabel.setFont(new Font("Verdana", Font.BOLD, 24));
	      this.informacaoTextLabel.setForeground(Color.BLACK);
		  
	      
	      this.panelImagem = new JPanel(new FlowLayout());
	      capitaoJComboBox = new JComboBox( nomeCapitao ); // Cria uma nova combobox e passa o o vetor de strings como parametro
	      capitaoJComboBox.setMaximumRowCount( 7 ); // mostra 3 linhas

	      capitaoJComboBox.addItemListener(                            
	         new ItemListener() // Classe interna anonima 
	         {                                                        
	            // handle JComboBox event                             
	            public void itemStateChanged( ItemEvent event )       
	            {                                                     
	               // determina qual item da lista foi selecionado
	               if ( event.getStateChange() == ItemEvent.SELECTED ){
	            	   setJogadorImagem ( capitaoJComboBox.getSelectedIndex() );
	            	  // capitaoLabel.setIcon( new ImageIcon("img/capitao/capitao"+getJogadorImagem()+".jpg"));     
	                  setStatus(true);
	                }
	            }                  
	         }  
	      );
	      
	      this.panelImagem.add(capitaoJComboBox);
	      
	      paisJComboBox = new JComboBox( nomePais ); 
	      paisJComboBox.setMaximumRowCount( 6 ); 

	      paisJComboBox.addItemListener(                            
	         new ItemListener() // Classe interna anonima 
	         {                                                        
	            // handle JComboBox event                             
	            public void itemStateChanged( ItemEvent event )       
	            {                                                     
	               // determina qual item da lista foi selecionado
	               if ( event.getStateChange() == ItemEvent.SELECTED ){
	                  paisLabel.setIcon( new ImageIcon("img/pais/"+nomePais[ paisJComboBox.getSelectedIndex() ]+".jpg") );
	                  setJogadorPais( nomePais[paisJComboBox.getSelectedIndex()] );
	               }
	            }                     
	         } 
	      );                   

	      this.panelPais.add( paisJComboBox ); // adiciona combobox no JFrame
	      paisLabel = new JLabel( new ImageIcon("img/pais/"+nomePais[ getPais(jogador.getPais()) ]+".jpg")); // no inicio, mostra o primeiro icone
	      this.panelPais.add( paisLabel ); // adiciona label ao frame
	      
	      ButtonHandler handlerCon = new ButtonHandler();
	      
	      voltar = new JButton("Voltar");
	      voltar.addActionListener(handlerCon);
	      this.panelBotoes.add(voltar);
	      
	      this.panelInfo.add(informacaoTextLabel);
	      this.panelOpcoes.add( panelImagem, BorderLayout.WEST);
	      this.panelOpcoes.add(panelInfo, BorderLayout.NORTH);
	      
	      this.panelOpcoes.add(telaJogador.getPanelPlayer(), BorderLayout.CENTER);
	      
	      this.subPanelOpcoes.add(panelPais);
	      this.panelOpcoes.add(subPanelOpcoes, BorderLayout.EAST);
	      this.panelOpcoes.add(panelBotoes, BorderLayout.SOUTH);
	}
	private class ButtonHandler implements ActionListener {
	 		public void actionPerformed(ActionEvent event) {
				if (event.getSource() == voltar) {
					CardLayout cl = (CardLayout)(cards.getLayout());
				    cl.show(cards, "2");
				}
	 		}
	}
	public int getPais(String pais) {
			for (int i=0; i< nomePais.length; i++){
				if (nomePais[i].equals(pais))
					return i;	
			}
			return 0;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public JPanel getTelaOpcoes() {
		return panelOpcoes;
	}

	public void getTelaOpcoes(JPanel panelOpcoes) {
		this.panelOpcoes = panelOpcoes;
	}
	public int getJogadorImagem() {
		return jogadorImagem;
	}
	public void setJogadorImagem(int jogadorImagem) {
		this.jogadorImagem = jogadorImagem;
	}
	public String getJogadorPais() {
		return jogadorPais;
	}
	public void setJogadorPais(String jogadorPais) {
		this.jogadorPais = jogadorPais;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public TelaJogador getTelaJogador() {
		return telaJogador;
	}
	public void setTelaJogador(TelaJogador telaJogador) {
		this.telaJogador = telaJogador;
	}
	public JLabel getPaisLabel() {
		return paisLabel;
	}
	public void setPaisLabel(JLabel paisLabel) {
		this.paisLabel = paisLabel;
	}
	public JComboBox getCapitaoJComboBox() {
		return capitaoJComboBox;
	}
	public void setCapitaoJComboBox(JComboBox capitaoJComboBox) {
		this.capitaoJComboBox = capitaoJComboBox;
	}
	public JComboBox getPaisJComboBox() {
		return paisJComboBox;
	}
	public void setPaisJComboBox(JComboBox paisJComboBox) {
		this.paisJComboBox = paisJComboBox;
	}
	
	
}

