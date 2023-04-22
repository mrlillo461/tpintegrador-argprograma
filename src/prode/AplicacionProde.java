package prode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javax.naming.directory.SearchResult;

public class AplicacionProde {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String archResultados = "C:\\Users\\TU\\OneDrive\\Documentos\\ARGENTINA PROGRAMA\\workspace-Eclipse\\ProDe_Integrador\\recursos\\resultados.csv";
		String archPronostico = "C:\\Users\\TU\\OneDrive\\Documentos\\ARGENTINA PROGRAMA\\workspace-Eclipse\\ProDe_Integrador\\recursos\\pronostico.csv";

		List<Ronda> rondas = inicializarRondas(archResultados);
//		for (Ronda ronda : rondas) {
//			System.out.println(ronda);
//		}
	}

	///// MÉTODO PARA INICIALIZAR CADA UNA DE LAS RONDAS /////
	public static List<Ronda> inicializarRondas(String archResultados) {
		List<String> listaResultados = new ArrayList<>(); // LISTA QUE CONTENDRÁ LOS DATOS LEÍDOS DESDE EL ARCHIVO "resultados.csv"
		List<Ronda> rondas = new ArrayList<>(); // LISTA QUE CONTENDRÁ CADA UNA DE LAS RONDAS

		// SE CAPTURA LA EXCEPCIÓN DE ENTRADA/SALIDA AL LEER EL ARCHIVO
		try {
			listaResultados = Files.readAllLines(Paths.get(archResultados)); // SE LEE EL ARCHIVO Y SE GUARDA CADA LÍNEA EN LA LISTA
			listaResultados.remove(0); // QUITAMOS EL PRIMER ELEMENTO DE LA LISTA YA QUE CONTIENE LOS TÍTULOS DE CADA COLUMNA
		} catch (IOException e) {
			System.out.println("No se pudo leer el archivo: " + e.getMessage());
		}

		int nroRondaActual = 0; // GUARDA EL NÚMERO DE RONDA QUE ESTAMOS CARGANDO
		List<Partido> partidosRondaActual = new ArrayList<>(); // LISTA CON LOS PARTIDOS PERTENECIENTES A LA RONDA QUE ESTAMOS CARGANDO

		// RECORREMOS LÍNEA POR LÍNEA LOS DATOS LEÍDOS DEL ARCHIVO
		for (int i = 0; i < listaResultados.size(); i++) {
			String[] partidos = listaResultados.get(i).split(";"); // ALMACENAMOS EN UN ARRAY CADA DATO SEPARADO POR ";"
			int nroRonda = Integer.parseInt(partidos[0]); // NÚMERO DE RONDA AL QUE PERTENECE EL PARTIDO ACTUAL

			// COMENZAMOS A ARMAR EL PARTIDO QUE CONTENDRÁ LOS EQUIPOS QUE SE ENFRENTAN Y LOS GOLES CONVERTIDOS POR CADA UNO
			Equipo equipo1 = new Equipo();
			equipo1.setNombre(partidos[1]); // SETEAMOS EL NOMBRE DEL PRIMER EQUIPO
			Equipo equipo2 = new Equipo();
			equipo2.setNombre(partidos[4]); // SETEAMOS EL NOMBRE DEL SEGUNDO EQUIPO
			int golesEquipo1 = Integer.parseInt(partidos[2]); // CONVERTIMOS A ENTERO LOS GOLES DEL PRIMER EQUIPO
			int golesEquipo2 = Integer.parseInt(partidos[3]); // CONVERTIMOS A ENTERO LOS GOLES DEL SEGUNDO EQUIPO

			// EVALUAMOS SI CAMBIÓ LA RONDA
			if (nroRonda != nroRondaActual) {
				// ANTES DE CREAR UN OBJETO RONDA VERIFICAMOS SI YA EXISTEN LOS DATOS PARA PODER CARGARLA
				if (nroRondaActual > 0) {
					// INICIALIZAMOS LA RONDA Y SE LA AGREGA A LA LISTA DE RONDAS
					Ronda ronda = new Ronda(nroRondaActual, partidosRondaActual);
					rondas.add(ronda);
				}
				// PREPARAMOS LAS VARIABLES PARA LA PRÓXIMA RONDA
				nroRondaActual = nroRonda;
				partidosRondaActual = new ArrayList<>();
			}

			// INICIALIZAMOS EL PARTIDO MEDIANTE SU CONSTRUCTOR Y SE AGREGA A LA LISTA DE PARTIDOS DE LA RONDA
			Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);
			partidosRondaActual.add(partido);
		}

		// AGREGAMOS LA ÚLTIMA RONDA PROCESADA
		if (nroRondaActual > 0) {
			Ronda ronda = new Ronda(nroRondaActual, partidosRondaActual);
			rondas.add(ronda);
		}
		
		return rondas;
	}


//	
//		List<String> lPronosticos = new ArrayList<>();
//
//		try {
//			lPronosticos = Files.readAllLines(Paths.get(aPronostico));
//			lPronosticos.remove(0);
//		} catch (IOException e) {
//			System.out.println("No se pudo leer el archivo: " + e.getMessage());
//		}
//
//		int cantPartidosRonda = ronda.getPartidos().length;
//
//		Pronostico[] unPronostico = new Pronostico[cantPartidosRonda];
//		Partido[] partidosRonda = ronda.getPartidos();
//		int aciertos = 0;
//
//		for (int i = 0; i < cantPartidosRonda; i++) {
//
//			ResultadoEnum prediccion = null;
//
//			if (lPronosticos.get(i).split(";")[2].toLowerCase().matches("x")) {
//				prediccion = ResultadoEnum.GANADOR;
//			} else {
//				if (lPronosticos.get(i).split(";")[3].toLowerCase().matches("x")) {
//					prediccion = ResultadoEnum.EMPATE;
//				} else {
//					if (lPronosticos.get(i).split(";")[4].toLowerCase().matches("x")) {
//						prediccion = ResultadoEnum.PERDEDOR;				
//					} else {
//						System.out.println("\u001B[31m"+"Por favor, marque con una \"X\" su pronóstico"+"\u001B[0m");
//					}
//					
//				}
//			}
//
//			if (prediccion != null) {
//				Pronostico pronostico = new Pronostico(partidosRonda[i], partidosRonda[i].getEquipo1(), prediccion);
////				pronostico.setPartido(partidosRonda[i]);
////				pronostico.setEquipo(partidosRonda[i].getEquipo1());
////				pronostico.setResultado(prediccion);
//				unPronostico[i] = pronostico;
//			}
//			
//			aciertos += unPronostico[i].puntos();
//		}
//
//		System.out.println("\n");
//		System.out.println("\u001B[32m" + "		 	▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀ ▀");
//		System.out.println("		█      El puntaje obtenido es: " + aciertos + "	█");
//		System.out.println("		▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄ ▄" + "\u001B[0m");
//	
//	}
}