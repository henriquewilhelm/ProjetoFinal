package audio;

import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;

public class Trilha  {	
	final String arquivo1 = "mp3/skyrim.mp3";
	final String arquivo2 = "mp3/mar.mp3";
	private Som mp3;
	
	public Trilha (int valor) throws FileNotFoundException, JavaLayerException{
		if (valor == 1){
			this.mp3 = new Som(arquivo1);
			this.mp3.play();
		}
		else{
			this.mp3 = new Som(arquivo2);
			this.mp3.play();
		}
	}
	public void play() throws FileNotFoundException, JavaLayerException{
		this.mp3.play();
	}
	public void stop(){
		this.mp3.close();
	}

	public static void main(String[] args) throws FileNotFoundException, JavaLayerException {
		Trilha trilha = new Trilha(1);
	}
}
