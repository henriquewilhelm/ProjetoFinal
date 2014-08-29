package Graficos;

import java.awt.*;   
import java.awt.event.*;   

import javax.swing.*;   
  
/**
 * Prototipo de Interface Grafica para o projeto do curso Geração Tec
 * Batalha Naval - Multiplayer
 * Autor: Henrique Wilhelm
 */
public class PrototipoGrafico extends JFrame implements ActionListener {   
   
   private Container cont;   
   private JPanel tabuleiro1;   
   private JPanel tabuleiro2;   
   private JPanel panelOp;   
   private JPanel panelMenu;
      
   private JButton buttonsTab1[]; // Botoes 0 - 99
   private JButton buttonsTab2[]; // Botoes 0 - 99
   private JButton buttonsMenuConexao[]; // Botoes 0 - 5
  
   private JTextField textFieldResultados;
   private JTextField textFieldChat;
   private char op;
   private double resultado;
   private double x = 0;
   private double y = 0;

   private boolean posicaoInicial;
   
   private int contaEscolha = 0;
   int[] posicaoEscolhida= new int[5];
   private String val1;
   private String val2;
   private Icon icons[] = {
		      new ImageIcon( getClass().getResource( "img/img1.jpg" ) ),
		      new ImageIcon( getClass().getResource( "img/img2.jpg" ) ),
		      new ImageIcon( getClass().getResource( "img/img3.jpg" ) ),
		      new ImageIcon( getClass().getResource( "img/img4.jpg" ) ),
		      new ImageIcon( getClass().getResource( "img/img5.jpg" ) ),
		      new ImageIcon( getClass().getResource( "img/img6.jpg" ) )
   };
   private JLabel label[] = { new JLabel(icons[0]),  new JLabel(icons[1]), new JLabel(icons[2]), new JLabel(icons[3]), new JLabel(icons[4]) }; // label para mostrar o item selecionado   
   public PrototipoGrafico() {   
	  
      super ("Batalha Naval");   
      cont = getContentPane();   
      cont.setLayout(new BorderLayout());    
      
      tabuleiro1 = new JPanel(new GridLayout(10,1,0,0));  
      tabuleiro2 = new JPanel(new GridLayout(10,1,0,0));
      panelOp = new JPanel(new GridLayout(6,1,1,1));   
      panelMenu = new JPanel(new GridLayout(1,5,1,1));   
      
      textFieldResultados = new JTextField( new Double(resultado).toString(), 40 );
      textFieldResultados.setEditable( false ); // disable editing
      
      textFieldChat = new JTextField( "Comece o chat...", 250 );
      textFieldChat.setEditable( true ); // disable editing
           
      buttonsTab1 = new JButton[ 100 ]; // set size of array
      buttonsTab2 = new JButton[ 100 ]; // set size of array
      
      buttonsMenuConexao = new JButton[ 5 ]; // set size of array
      
      for ( int contador = 0 ; contador < 100; contador++ )
      {
    	  		buttonsTab1[ contador ] = new JButton( new Integer(contador).toString() );
    	  		buttonsTab1[ contador ].addActionListener( this );

    	  		buttonsTab2[ contador ] = new JButton( new Integer(contador).toString() );
    	  		buttonsTab2[ contador ].addActionListener( this );
    	
    		 	tabuleiro1.add(buttonsTab1[ contador ]);
    	 		tabuleiro2.add(buttonsTab2[ contador ]);
      }
      
      for ( int contador = 0 ; contador < 5; contador++ )
      {

    	  		buttonsMenuConexao[ contador ] = new JButton( "Menu " + new Integer(contador).toString() );
    	  		buttonsMenuConexao[ contador ].addActionListener( this );
    	
    	 		panelMenu.add(buttonsMenuConexao[ contador ]);
    	 		
    	 		panelOp.add( label[ contador] );
      }
      // Primeiras 5 rodadas
      textFieldResultados.addActionListener(this);
      panelOp.add( textFieldResultados );
      tabuleiro2.setVisible(false);      
      
      
      
      //cont.add(textField, BorderLayout.SOUTH);
      cont.add(panelMenu, BorderLayout.NORTH);     
      cont.add(tabuleiro1, BorderLayout.WEST);
      cont.add(tabuleiro2, BorderLayout.EAST);
      cont.add(panelOp, BorderLayout.CENTER);
      
      op = ' ';
   }   
   public void actionPerformed(ActionEvent e) {   
     try{  
    	for ( int contador = 0 ; contador < 100; contador++ ){
    		
    			if (contaEscolha<5){
 				
    						if (e.getSource() == buttonsTab1[ contador ] ){
    									// Textfield
    									if (op == ' '){
    											if (val1 == null)
    													val1 = new Integer(contador).toString();
    											else if (val1.length() < 80)
    													val1 = val1 + new Integer(contador).toString();
    											textFieldResultados.setText(val1);
    									}
    									else{
    											if (val2 == null)
    													val2 = new Integer(contador).toString();
    											else if (val2.length() < 80)
    													val2 = val2 + new Integer(contador).toString();
    											textFieldResultados.setText(val2);
    									}
    									// Escolhas (Navios/Posicao)
    									if (contaEscolha == 0){
    										label[0].setIcon( icons[ 5 ] ); // Mostra que foi escolhido (Muda imagem)
    										
    									}
    									if (contaEscolha == 1){
    										label[1].setIcon( icons[ 5 ] ); // Mostra que foi escolhido (Muda imagem)
       									}
    									if (contaEscolha == 2){
    										label[2].setIcon( icons[ 5 ] ); // Mostra que foi escolhido (Muda imagem)
    									}
    									if (contaEscolha == 3){
    										label[3].setIcon( icons[ 5 ] ); // Mostra que foi escolhido (Muda imagem)
    									}
    									if (contaEscolha == 4){
    										label[4].setIcon( icons[ 5 ] ); // Mostra que foi escolhido (Muda imagem)
    									}
    									
    									posicaoEscolhida[contaEscolha] = contador;
    									contaEscolha++;
    									buttonsTab1[ contador ].setEnabled(false);
    									
    						
    						}
    			}
    			else {	
    						// Apaga Imagem de Navio
    						panelOp.setVisible(false);
    						if (contaEscolha == 5){
    									tabuleiro2.setVisible(true);
    									//posicaoEscolhida[contadorPosicaoEscolhida]);
    						}
    						if (e.getSource() == buttonsTab2[ contador ]){
    		 					buttonsTab2[ contador ].setEnabled(false);
    						}
    		 	}
    	 }  
    	
   
    		
    	
     }
     catch (Exception exception) {   
         JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto");
         System.out.println(exception); 
     }
   }      public static void main(String args[]) {   
       
	   PrototipoGrafico test = new PrototipoGrafico();
	   	test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   
	   	test.setBackground(new Color(0,100,50));
	    test.setSize(1000,500);   
	    test.setVisible(true);  
   }
}