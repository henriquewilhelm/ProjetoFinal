package Socket;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/** Batalha Naval (Ultimate Battle) - Versao 1.0 da Interface Grafica
 * Tela de Servidor
 */

public class InterfaceCliente {
	     
		
	   private String host;
	   private int porta;
	   private JButton conectaServer, closeServer, um, dois, tres;
	   private JFrame frame;
	   private JTextField textField1, textField2;    
	   private PrintStream saida; 
	   private String comandoSaida = "";
	   private Cliente cli;
	   
	   public InterfaceCliente () {
	   }
	   
	   public InterfaceCliente (String host, int porta) {
	     this.host = host;
	     this.porta = porta;
	   }
	   
	   public void constroiFrame() {
		   		// Controi Frame
		   		frame = new JFrame("Ultimate Battle - Cliente");
		   		// set frame layout
		        frame.setLayout( new FlowLayout() ); 
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(300,300);
		       
		        // Campos (textField) e botoes (Button)
				textField1 = new JTextField( 30 );    
				textField1.setText("localhost");
				textField2 = new JTextField( 10 );    
				conectaServer = new JButton( "Conecta no servidor" );
				closeServer = new JButton( "Desconecta" );
				um = new JButton( "Escolhe Posicao X para Navio" );
				dois = new JButton( "Ataca em posicao X do mapa do inimigo" );
				tres = new JButton( "Informa que foi atingido" );
				
				// Adiciona objetos no frame
				frame.add( textField1 );
				frame.add( textField2 );
				frame.add( conectaServer );
				frame.add( closeServer );
				frame.add( um );
				frame.add( dois );
				frame.add( tres );
				// Visivel
				frame.setVisible(true);
				
				// Cria handler (Manipulador de Botoes - Acoes)
				ButtonButtonHandler handler = new ButtonButtonHandler();         
				um.addActionListener( handler );
				dois.addActionListener( handler );
				tres.addActionListener( handler );
				
				// Cria handlerCon (Manipulador de Botoes - Acoes)
				ButtonConexaoHandler handlerCon = new ButtonConexaoHandler();         
				closeServer.addActionListener( handlerCon );
				conectaServer.addActionListener( handlerCon );
				
				// Desabilita botao
				closeServer.setEnabled(false);
		}
	   	// Manipulador de Acoes - Botoes (1, 2, 3)
		private class ButtonButtonHandler implements ActionListener{
			
		      public void actionPerformed( ActionEvent event )
		      {
		    	  if (event.getSource() == um) {
		    		  	comandoSaida = "#";
		    	  }
		    	  else if (event.getSource() == dois) {
		    		  comandoSaida = "@";
		    	  }
		    	  else if (event.getSource() == tres) {
		    		  comandoSaida = "$";
		    	  }
		    	  saida.println(comandoSaida);
		    	  //System.out.println(comandoSaida);
		      } 
		   } 
		// Manipulador de Acoes - Botoes (Conecta, Desconecta)
		private class ButtonConexaoHandler implements ActionListener  {
		      public void actionPerformed( ActionEvent event )
		      {
		          try {
		        	  if (event.getSource() == conectaServer) {
		        		  host = textField1.getText();
		        		  porta = Integer.parseInt(textField2.getText());
		        		  // Instancia cliente
		        		  cli = new Cliente(host,porta);
		        		  saida = cli.conecta();
	        			  conectaServer.setEnabled(false);		// Desabilita botao
	        			  closeServer.setEnabled(true); 		// Habilita botao
		        	  }
		        	  else if (event.getSource() == closeServer) {
		        		  cli.desconecta(); 
		        		  conectaServer.setEnabled(true);
		        		  closeServer.setEnabled(false);

		        	  }
		          }
		          catch (IOException e)
		          {
		             e.printStackTrace();
		          }
		      } 
		 } 
		// Teste Main
		public static void main(String[] args) throws  UnknownHostException, IOException {
		     // inicia o cliente 
		     new InterfaceCliente().constroiFrame();
	}
}
	 
	 
