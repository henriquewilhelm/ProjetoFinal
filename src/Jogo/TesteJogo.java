package Jogo;

import java.util.ArrayList;

public class TesteJogo {

	public static void main(String[] args) {
		
		// Cria jogador passando nome e tamanho do mapa
		Jogador player = new Jogador("Teste", 100);
		
		// Imprime Mapa
		System.out.println(player.getMapa().toString());
		// Movimenta Navios
		player.getHerois().get(0).caminha("esquerda");
		player.getHerois().get(1).caminha("baixo");
		
		System.out.println(player.getMapa().toString());
	}
}
