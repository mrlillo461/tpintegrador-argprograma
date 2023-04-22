package prode;

import java.util.Arrays;

public class Participante {
	private String nombre;
	private Pronostico[] pronosticos;
	
	public Participante() {
		
	}
	
	public Participante(String nombre, Pronostico[] pronosticos) {
		this.nombre = nombre;
		this.pronosticos = pronosticos;
	}
	
	@Override
	public String toString() {
		return "Participante [" + nombre + ", pronosticos=" + Arrays.toString(pronosticos) + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pronostico[] getPronosticos() {
		return pronosticos;
	}

	public void setPronosticos(Pronostico[] pronosticos) {
		this.pronosticos = pronosticos;
	}
}