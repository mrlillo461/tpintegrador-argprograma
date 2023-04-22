package prode;

import java.util.Arrays;
import java.util.List;

public class Ronda {
	private int nro;
	private List<Partido> partidos;
	
	public Ronda() {
		
	}

	public Ronda(int nro, List<Partido> partidos) {
		this.nro = nro;
		this.partidos = partidos;
	}
	

	@Override
	public String toString() {
		return "Ronda [nro=" + nro + ", partidos=" + partidos + "]";
	}

	public int getNro() {
		return nro;
	}
	
	public void setNro(int nro) {
		this.nro = nro;
	}
	
	public List<Partido> getPartidos() {
		return partidos;
	}
	
	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}
}