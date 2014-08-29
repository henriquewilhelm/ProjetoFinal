package Jogo;

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
