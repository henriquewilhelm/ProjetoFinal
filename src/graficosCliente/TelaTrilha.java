package graficosCliente;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class TelaTrilha {
	   private boolean audio  = false;
	   private JCheckBox audioJCheckBox; 
	   private JPanel telaTrilha;
	   public TelaTrilha(){
		  telaTrilha = new JPanel(new FlowLayout());
	      audioJCheckBox = new JCheckBox( "Audio / Mudo" ); // create bold checkbox
	      audioJCheckBox.setSelected(true);
	      telaTrilha.add( audioJCheckBox ); // add bold checkbox to JFrame
    
	      CheckBoxHandler handler = new CheckBoxHandler();
	      audioJCheckBox.addItemListener( handler );            
	   } 

	   private class CheckBoxHandler implements ItemListener
	   {
               
	      public void itemStateChanged( ItemEvent event )
	      {
	         if ( event.getSource() == audioJCheckBox ){
	        	 		setAudio(true);
	         }
	      }     	  
	   }

	public boolean isAudio() {
		return audio;
	}

	public void setAudio(boolean audio) {
		this.audio = audio;
	}

	public JPanel getTelaTrilha() {
		return telaTrilha;
	}

	public void setTelaTrilha(JPanel telaTrilha) {
		this.telaTrilha = telaTrilha;
	} 
	
}	
