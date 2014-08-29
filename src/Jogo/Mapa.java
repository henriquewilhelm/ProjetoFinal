package Jogo;
import java.util.Random;

/** Autor: Henrique Wilhelm
 * O Mapa possui 2 atributos:
 * - Dimensão é uma matriz de tamanho definido durante a construção da 
 *  classe
 * - Obstáculos, são array contendo, ? posições [x,y] definidas pelo 
 *   construtor, tendo como limite as dimensões do mapa.
 * 
 * Os obstáculos devem ser gerados assim que o mapa for construído. 
 * Não podendo haver um obstáculo do lado do outro.
 */

public class Mapa {
	private int[][] dimensao;
	private int[][] obstaculos;
	// Construtor da classe Mapa com valores padrao
	public Mapa(){
			
			this.dimensao = new int[10][20];
			this.obstaculos = new int[20][2];
			criaObstaculos(5);
	}
	// Construtor da classe Mapa com valores passados por parametro
	public Mapa(int largura, int comprimento, int numObstaculos){
			this.dimensao = new int[largura][comprimento];
			this.obstaculos = new int[comprimento][2];
			criaObstaculos(numObstaculos);
	}
	// Metodo que cria os obstaculos (Registra a posicao (X,Y) de cada obstaculo no Mapa) 
	private void criaObstaculos(int numObstaculos){
		Random gerador = new Random();  // Gerador de numeros aleatorios
		int randomX; 					// Variavel auxiliar para manipular valor X Sorteado
		int randomY;					// Variavel auxiliar para manipular valor Y Sorteado
		int totalObstaculos = 0; 		// Contador de obstaculos
		while (totalObstaculos < this.obstaculos.length){
				randomX = gerador.nextInt(this.dimensao.length); 
				randomY = gerador.nextInt(this.dimensao[0].length);
				if (obstaculoValido(randomX,randomY)){
						obstaculos[totalObstaculos][0] = randomX;
						obstaculos[totalObstaculos][1] = randomY;
						totalObstaculos++;
				}
		}
	}
	// Verifica se obstaculo eh valido
	private boolean obstaculoValido(int x, int y){
		int[][] comparacoes = {{-1,-1},{-1,1},{1,-1},{1,1},{0,0},{0,-1},{0,1},{1,0},{-1,0}};
		for (int i=0; i < this.obstaculos.length; i++){
			for (int j=0; j < comparacoes.length; j++){
				if (j == 0 && x == 0 && y == 0){
						continue;
				}
				if (obstaculos[i][0] == (comparacoes[j][0] + x) &&
							obstaculos[i][1] == (comparacoes[j][1] + y)) {
						return false;
				}
			}
		}
		return true;
	}
	// Metodo toString
	public String toString(){
			String str = "Obstaculos\n\n";
			for (int[] o : this.obstaculos){
					str += ("[ " + o[0] + " , " + o[1] + " ]\n");
					int x = o[0]; 			
					int y = o[1];
					dimensao[x][y] = 1;
			}
			str += "\nMAPA\n";
			for (int i =0; i< dimensao.length; i++){
				for (int j =0; j< dimensao[i].length; j++){
					if (dimensao[i][j] == 1)
						str += ".";
					else 
						str += "0";
				}
				str += "\n";
			}
			return str;
	}
	public int[][] getDimesao() {
		return dimensao;
	}

	public void setDimesao(int[][] dimensao) {
		this.dimensao = dimensao;
	}

	public int[][] getObstaculos() {
		return obstaculos;
	}

	public void setObstaculos(int[][] obstaculos) {
		this.obstaculos = obstaculos;
	}
}
