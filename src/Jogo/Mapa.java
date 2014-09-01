package Jogo;

import java.util.ArrayList;

public class Mapa {
	private int[] dimensao;
	private ArrayList<Heroi> herois;
	private ArrayList<Posicao> posicoes;
	
	public Mapa(int tamanho) {
		this.dimensao = new int[tamanho];
		this.posicoes = new ArrayList<Posicao>();
		this.herois = new ArrayList<Heroi>();
	}
	
	public void adicionaPosicoes(ArrayList<Heroi> herois){// .... gerar
	
		this.herois = herois;
		int percorridos = 0;
		while(percorridos < this.herois.size()) {
			for (int i=0; i<this.herois.get(percorridos).getPosicao().length; i++){
				int x = this.herois.get(percorridos).getPosicao()[i].getX();
				if(obstaculoValido(x)) {
					this.posicoes.add(this.herois.get(percorridos).getPosicao()[i]);
				}
			}
			percorridos++;
		}
	}
		
	
	public void setPosicao(Posicao[] posicoes){
		for (int i=0; i<posicoes.length; i++)
			this.posicoes.add(posicoes[i]);
	}
	
	public Posicao getPosicao(int index){
		return 	this.posicoes.get(index); 
	}
	
	
	private boolean obstaculoValido(int x) {
		//int[][] comparacoes = {{0, 0}, {-1,-1}, {-1, 1}, {1, -1}, {1,1},
				              // {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		if (x >= 0 || x < 100){
			
		for(int i = 0; i < this.posicoes.size(); i++) {
			if (this.posicoes.get(i).getX() != x){
					continue;
			}
			else
					return false;
			}
		}
		return true;
	}
	
	public String toString() {
		String str = "";
		int controlador = 0;
			for(int j = 0; j < this.dimensao.length; j++) {
				boolean obstaculo = false;
				
				for(int w = 0; w < this.posicoes.size(); w++) {
					if(this.posicoes.get(w).getX() == j) {
						obstaculo = true;
					}
				}
				
				if(obstaculo){	
					str += "0";
				} else {
					str += ".";
				}
				controlador++;		
				if (controlador==10){
					controlador=0;
					str += "\n";
				}
			}		
			return str;
	}
}