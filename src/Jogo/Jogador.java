package Jogo;

import java.util.ArrayList;

/** 
 * Fiquei estudando mais sobre Interface Grafica e acabei deixando de lado a parte 
 * do Mapa, Pe�as/Navios e Jogador, ainda falta pensar nesses detalhes...
 * Aqui no Pacote "Jogo" v�o as regras e as classes que eu citei acima, se ele funcionar
 * aqui funciona em qualquer interface grafica!! 
 */

public class Jogador {

	private String nome;
	private int numRodadas = 1;
	private ArrayList<Heroi> herois;
	private Mapa mapa;
	
	public Jogador(String nome, int tamanho){
		this.nome= nome;
		this.herois = new ArrayList<Heroi>();
		this.mapa = new Mapa(tamanho);
		this.criaHerois();
	}

	public void criaHerois(){
		
		Posicao[] posicoesNavio1 = new Posicao[2];
		Heroi Navio1 = new Heroi("Barco", posicoesNavio1, 100, 25, 25);
		
		Posicao[] posicoesNavio2 = new Posicao[2];
		Heroi Navio2 = new Heroi("Costeiro", posicoesNavio2, 100, 25, 50);
		
		Posicao[] posicoesNavio3 = new Posicao[3];
		Heroi Navio3 = new Heroi("destroyer", posicoesNavio3, 100, 50, 50);
		
		Posicao[] posicoesNavio4 = new Posicao[4];
		Heroi Navio4 = new Heroi("Cargueiro", posicoesNavio4, 100, 25, 75);
		
		Posicao[] posicoesNavio5 = new Posicao[4];
		Heroi Navio5 = new Heroi("Submarino", posicoesNavio5, 100, 75, 50);
		
		this.herois.add(Navio1);
		this.herois.add(Navio2);
		this.herois.add(Navio3);
		this.herois.add(Navio4);
		this.herois.add(Navio5);
	}
	
	public Heroi pegaHeroi(int index){
		return this.herois.get(index);
	}

	// S and G
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumRodadas() {
		return numRodadas;
	}

	public void setNumRodadas(int numRodadas) {
		this.numRodadas = numRodadas;
	}


	public ArrayList<Heroi> getHerois() {
		return herois;
	}


	public void setHerois(ArrayList<Heroi> herois) {
		this.herois = herois;
	}


	public Mapa getMapa() {
		return mapa;
	}


	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	
	
}
