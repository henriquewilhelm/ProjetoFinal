package Graficos;

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.1 de Interface Grafica 
 * (Botao) 
 * Esta classe eh responsavel pela criacao do nosso botao personalizado, que sera usado 
 * dentro dos tabuleiros.  
 * Ela extende a classe JButton ou seja, herda todos metodos da classe JButton apenas reescreve
 * o metodo o paintComponent que é responsavel pela aparencia padrao dos Botoes em Java. Alem de 
 * reescrever este metodo, criei novo metodo (setFundo(int i)) que passa um inteiro para o objeto
 * que muda o valor que controla o vetor de imagem usado pelo o paintComponent, mudando o fundo...
 * Obs: Por enquanto só coloquei um botao azul e um vermelho, mas é facinho de adicionar outros,
 * dessa mesma forma iremos fazer os botoes dos barcos, cada botao é um barco/imagem ou pedaco de
 * um(a)!
 * Autor: Henrique W.
 */

public class Botao extends javax.swing.JButton {  //Herda todas caracteristicas de JButton  
	private int i=0;
    private JLabel label ;  
    private ImageIcon imagem[] = { new ImageIcon(this.getClass().getResource("img/mar1.jpg")),
    			new ImageIcon(this.getClass().getResource("img/mar2.jpg")) };
    // Construtor de Botao com nome
    public Botao(String text){  
        this(); 
        label.setText(text);  
    }  
    // Construtor de Botao sem nome  
    public Botao(){  
        label = new JLabel();  
        this.add(label);  
    }
    // Set nome (String) no botao
    public void setText(String nome){  
        label.setText(nome);  
    }  
    // REESCREVE METODO DA CLASSE JBUTTON 
    // Aplica a imagem (image[0]) como fundo do botao (Obs: A imagem determina o tamanho do botao)
    public void paintComponent(Graphics g) {    
 
       ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);    
           
       final Image backgroundImage = imagem[i].getImage();    
       // Pega tamanho da imagem
       double scaleX = getWidth() / (double) backgroundImage.getWidth(null);    
       double scaleY = getHeight() / (double) backgroundImage.getHeight(null);
       AffineTransform xform = AffineTransform.getScaleInstance(scaleX, scaleY);
       // Desenha Imagem
       ((Graphics2D) g).drawImage(backgroundImage, xform, this);          
   }   
   // Muda a imagem (image[int i]) do fundo do botao  (Obs: A imagem determina o tamanho do botao)
   public void setFundo(int i){
	   this.i = i;
   }
}