package Jogo;

/** 
 * Fiquei estudando mais sobre Interface Grafica e acabei deixando de lado a parte 
 * do Mapa, Peças/Navios e Jogador, ainda falta pensar nesses detalhes...
 * Aqui no Pacote "Jogo" vão as regras e as classes que eu citei acima, se ele funcionar
 * aqui funciona em qualquer interface grafica!! 
 */

public class Jogador {

	private String nome;
	private int numRodadas =0;
	
	public Jogador(String nome){
		this.nome= nome;
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
}
