package graficosCliente;

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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TelaRanking {

	private JPanel telaRanking;
	private Container panelNome;
	private JPanel panelTipos;
	private JPanel panelPorTempo;
	private JPanel panelPorMedalhas;
	private JPanel panelPorMedia;
	private JPanel cards;
	
	private JLabel ranking;
	private JLabel rankingPorTempo;
	private JLabel rankingPorMedalhas;
	private JLabel rankingPorMedia;
	
	private JTextArea textAreaPorTempo;
	private JTextArea textAreaPorMedalhas;
	private JTextArea textAreaPorMedia;
	
	private JButton voltar;
	
	private boolean status;
	
	public TelaRanking(JPanel cards){
		this.cards = cards;
		this.telaRanking = new JPanel(new BorderLayout());
		this.panelNome = new Container();
		this.panelNome.setLayout(new BoxLayout(panelNome, BoxLayout.PAGE_AXIS));
		this.panelTipos = new JPanel(new GridLayout(1,3));
		
		this.panelPorTempo = new JPanel(new FlowLayout());
		this.panelPorMedalhas = new JPanel(new FlowLayout());
		this.panelPorMedia = new JPanel(new FlowLayout());
		
		this.ranking = new JLabel("BATALHA NAVAL - RANKING");
		this.ranking.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.ranking.setFont(new Font("Verdana", Font.BOLD, 20));
		this.ranking.setForeground(Color.RED);
		
		this.rankingPorTempo = new JLabel("Top 5 -Por Tempo");
		this.rankingPorTempo.setFont(new Font("Verdana", Font.BOLD, 18));
		this.textAreaPorTempo = new JTextArea(5,20);
		this.textAreaPorTempo.setFont(new Font("Verdana", Font.BOLD, 16));
		this.textAreaPorTempo.setEditable(false);
		this.panelPorTempo.add(rankingPorTempo);
		this.panelPorTempo.add(textAreaPorTempo);
		
		this.rankingPorMedalhas = new JLabel("Top 5 -Por Medalhas");
		this.rankingPorMedalhas.setFont(new Font("Verdana", Font.BOLD, 18));
		this.textAreaPorMedalhas = new JTextArea(5,20);
		this.textAreaPorMedalhas.setFont(new Font("Verdana", Font.BOLD, 16));
		this.textAreaPorMedalhas.setEditable(false);	
		this.panelPorMedalhas.add(rankingPorMedalhas);
		this.panelPorMedalhas.add(textAreaPorMedalhas);
		
		
		this.rankingPorMedia = new JLabel("Por Media");
		this.rankingPorMedia.setFont(new Font("Verdana", Font.BOLD, 18));
		this.textAreaPorMedia = new JTextArea(5,20);
		this.textAreaPorMedia.setEditable(false);
		this.textAreaPorMedia.setFont(new Font("Top 5 -Verdana", Font.BOLD, 16));
		this.panelPorMedia.add(rankingPorMedia);
		this.panelPorMedia.add(textAreaPorMedia);	
		
		ButtonHandler handlerCon = new ButtonHandler();
		voltar = new JButton("Voltar");
	    voltar.addActionListener(handlerCon);
	      
		
		this.panelTipos.add(panelPorTempo);
		this.panelTipos.add(panelPorMedalhas);
		this.panelTipos.add(panelPorMedia);
		
		this.panelNome.add(ranking);
		
		this.telaRanking.add(panelNome, BorderLayout.NORTH);
		this.telaRanking.add(panelTipos, BorderLayout.CENTER);
		this.telaRanking.add(voltar, BorderLayout.SOUTH);
	}

	private class ButtonHandler implements ActionListener {
 		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == voltar) {
				CardLayout cl = (CardLayout)(cards.getLayout());
			    cl.show(cards, "2");
			}
 		}
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public JPanel getTelaRanking() {
		return telaRanking;
	}

	public void setTelaRanking(JPanel telaRanking) {
		this.telaRanking = telaRanking;
	}

	public Container getPanelNome() {
		return panelNome;
	}

	public void setPanelNome(Container panelNome) {
		this.panelNome = panelNome;
	}

	public JPanel getPanelTipos() {
		return panelTipos;
	}

	public void setPanelTipos(JPanel panelTipos) {
		this.panelTipos = panelTipos;
	}

	public JPanel getPanelPorTempo() {
		return panelPorTempo;
	}

	public void setPanelPorTempo(JPanel panelPorTempo) {
		this.panelPorTempo = panelPorTempo;
	}

	public JPanel getPanelPorMedalhas() {
		return panelPorMedalhas;
	}

	public void setPanelPorMedalhas(JPanel panelPorMedalhas) {
		this.panelPorMedalhas = panelPorMedalhas;
	}

	public JPanel getPanelPorMedia() {
		return panelPorMedia;
	}

	public void setPanelPorMedia(JPanel panelPorMedia) {
		this.panelPorMedia = panelPorMedia;
	}

	public JLabel getRanking() {
		return ranking;
	}

	public void setRanking(JLabel ranking) {
		this.ranking = ranking;
	}

	public JLabel getRankingPorTempo() {
		return rankingPorTempo;
	}

	public void setRankingPorTempo(JLabel rankingPorTempo) {
		this.rankingPorTempo = rankingPorTempo;
	}

	public JLabel getRankingPorMedalhas() {
		return rankingPorMedalhas;
	}

	public void setRankingPorMedalhas(JLabel rankingPorMedalhas) {
		this.rankingPorMedalhas = rankingPorMedalhas;
	}

	public JLabel getRankingPorMedia() {
		return rankingPorMedia;
	}

	public void setRankingPorMedia(JLabel rankingPorMedia) {
		this.rankingPorMedia = rankingPorMedia;
	}

	public JTextArea getTextAreaPorTempo() {
		return textAreaPorTempo;
	}

	public void setTextAreaPorTempo(JTextArea textAreaPorTempo) {
		this.textAreaPorTempo = textAreaPorTempo;
	}

	public JTextArea getTextAreaPorMedalhas() {
		return textAreaPorMedalhas;
	}

	public void setTextAreaPorMedalhas(JTextArea textAreaPorMedalhas) {
		this.textAreaPorMedalhas = textAreaPorMedalhas;
	}

	public JTextArea getTextAreaPorMedia() {
		return textAreaPorMedia;
	}

	public void setTextAreaPorMedia(JTextArea textAreaPorMedia) {
		this.textAreaPorMedia = textAreaPorMedia;
	}
	
}
