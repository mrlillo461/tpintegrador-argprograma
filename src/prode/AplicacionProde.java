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
			// System.out.println(lResultados);
		} catch (IOException e) {
			System.out.println("No se pudo leer el archivo: " + e.getMessage());
		}

		Partido[] unaRonda = new Partido[lResultados.size()];

		/*
		 * for (String linea : lResultados){ String[] partidos = linea.split(";");
		 */

		for (int i = 0; i < lResultados.size(); i++) {
			String[] partidos = lResultados.get(i).split(";");
			/*
			 * System.out.println(partidos[0]); break;
			 */

			Equipo equipo1 = new Equipo();
			equipo1.setNombre(partidos[0]);
			Equipo equipo2 = new Equipo();
			equipo2.setNombre(partidos[3]);

			int golesEquipo1 = Integer.parseInt(partidos[1]);
			int golesEquipo2 = Integer.parseInt(partidos[2]);

			Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);
			// System.out.println(partido);

			unaRonda[i] = partido;
		}

		Ronda ronda = new Ronda();
		ronda.setPartidos(unaRonda);
		// System.out.println(ronda);

		List<String> lPronosticos = new ArrayList<>();

		try {
			lPronosticos = Files.readAllLines(Paths.get(aPronostico));
			lPronosticos.remove(0);
		} catch (IOException e) {
			System.out.println("No se pudo leer el archivo: " + e.getMessage());
		}

		int cantPartidosRonda = ronda.getPartidos().length;

		Pronostico[] unPronostico = new Pronostico[cantPartidosRonda];
		Partido[] partidosRonda = ronda.getPartidos();
		Pronostico pronostico = new Pronostico(/*partidosRonda[i], partidosRonda[i].getEquipo1(), prediccion*/);
		int aciertos = 0;

		for (int i = 0; i < cantPartidosRonda; i++) {

			ResultadoEnum prediccion = null;

			if (lPronosticos.get(i).split(";")[2].toLowerCase().matches("x")) {
				prediccion = ResultadoEnum.GANADOR;
			} else {
				if (lPronosticos.get(i).split(";")[3].toLowerCase().matches("x")) {
					prediccion = ResultadoEnum.EMPATE;
				} else {
					if (lPronosticos.get(i).split(";")[4].toLowerCase().matches("x")) {
						prediccion = ResultadoEnum.PERDEDOR;				
					} else {
						System.out.println("\u001B[31m"+"Por favor, marque con una \"X\" su pronóstico"+"\u001B[0m");
					}
					
				}
			}

			if (prediccion != null) {
				pronostico.setPartido(partidosRonda[i]);
				pronostico.setEquipo(partidosRonda[i].getEquipo1());
				pronostico.setResultado(prediccion);
				unPronostico[i] = pronostico;
			}
			
			aciertos += unPronostico[i].puntos();
		}

		System.out.println("\n");
		System.out.println("\u001B[32m" + "		 	▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀");
		System.out.println("		█      El puntaje obtenido es: " + aciertos + "	█");
		System.out.println("		▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄" + "\u001B[0m");
	
	}
}