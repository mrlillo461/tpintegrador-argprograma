package prode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AplicacionProde {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String aResultados = "C:\\Users\\TU\\OneDrive\\Documentos\\ARGENTINA PROGRAMA\\workspace-Eclipse\\ProDe_Integrador\\recursos\\resultados.csv";
		String aPronostico = "C:\\Users\\TU\\OneDrive\\Documentos\\ARGENTINA PROGRAMA\\workspace-Eclipse\\ProDe_Integrador\\recursos\\pronostico.csv";
		
		List<String> lResultados = new ArrayList<>();
		
		try {
			lResultados = Files.readAllLines(Paths.get(aResultados));
			lResultados.remove(0);
			//System.out.println(lResultados);
		}
		catch (IOException e) {
			System.out.println("No se encuentra el archivo");
		}
		
		Partido[] unaRonda = new Partido[lResultados.size()];
		
		/*for (String linea : lResultados){
			String[] partidos = linea.split(";");*/
		
		for (int i = 0; i < lResultados.size(); i++) {
			String[] partidos = lResultados.get(i).split(";");
			/*System.out.println(partidos[0]);
			break;*/
			
			Equipo equipo1 = new Equipo();
			equipo1.setNombre(partidos[0]);
			Equipo equipo2 = new Equipo();
			equipo2.setNombre(partidos[3]);
			
			int golesEquipo1 = Integer.parseInt(partidos[1]);
			int golesEquipo2 = Integer.parseInt(partidos[2]);
			
			Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);
			//System.out.println(partido);
			
			//Partido[] unaRonda = new Partido[lResultados.size()];
			unaRonda[i] = partido;
		}
		
		Ronda ronda = new Ronda();
		ronda.setPartidos(unaRonda);
		System.out.println(ronda);
	}
}
