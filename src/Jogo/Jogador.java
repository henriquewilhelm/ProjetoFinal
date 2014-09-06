package Jogo;

import java.util.ArrayList;

/**
 * Todos atributos da classe Jogador 
 * - Nome
 * - Herois (5)
 * - Numero Navios
 * - Numero de Medalhas (Uma a cada 5 vitorias)
 * - Numero de Vitorias
 * - Numero de Derrotas
 * - Total de Jogos
 *  
 * Atributos do Jogador para o Banco de Dados
 * - Nome
 * - Numero de Medalhas (Uma a cada 5 vitorias)
 * - Numero de Vitorias 
 * - Numero de Derrotas
 * - Total de Jogos
 */

public class Jogador {

	private String nome;
	private int numRodadas = 1;
	private ArrayList<Heroi> herois;
	private int vida;
	private Mapa mapa;
	private int numeroNavios;
	private int medalhas;
	private int vitorias;
	private int derrotas;
	private int totalJogos;
	
	public Jogador(String nome, int tamanho){
		this.nome= nome;
		this.herois = new ArrayList<Heroi>();
		this.mapa = new Mapa(tamanho);
		this.numeroNavios = 5;
		this.vida = 100;
		this.medalhas = 5;
		this.vitorias = 25;
		this.derrotas = 5;
		this.totalJogos = 30;
		this.criaHerois();
	}

	public void criaHerois(){
		
		Posicao[] posicoesNavio1 = new Posicao[2];
		Heroi Navio1 = new Heroi("Barco", posicoesNavio1, 100, 75, 50);
		
		Posicao[] posicoesNavio2 = new Posicao[2];
		Heroi Navio2 = new Heroi("Costeiro", posicoesNavio2, 100, 75, 50);
		
		Posicao[] posicoesNavio3 = new Posicao[3];
		Heroi Navio3 = new Heroi("Destroyer", posicoesNavio3, 100, 75, 50);
		
		Posicao[] posicoesNavio4 = new Posicao[4];
		Heroi Navio4 = new Heroi("Cargueiro", posicoesNavio4, 100, 75, 50);
		
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
	public int getVida() {
		return this.vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getNumeroNavios() {
		return numeroNavios;
	}
	public void setNumeroNavios(int numeroNavios) {
		this.numeroNavios = numeroNavios;
	}
	public int getMedalhas() {
		return medalhas;
	}
	public void setMedalhas(int medalhas) {
		this.medalhas = medalhas;
	}	
	public int getVitorias() {
		return vitorias;
	}
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}	
	public int getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}
	public int getTotalJogos() {
		return totalJogos;
	}
	public void setTotalJogos(int totalJogos) {
		this.totalJogos = totalJogos;
	}
}
