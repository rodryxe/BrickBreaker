import java.util.Random;

public class Configuracion {

	// Ventana

	public static int ANCHO = 700;
	public static int ALTO = 600;
	public static String TITULO = "Block Breaker";

	// Bloques

	public static int ANCHO_BLOQUE;
	public static int ALTO_BLOQUE;
	public static int NUM_BLOQUES = 21;

	// jugador

	public static int INICIO_JUGADOR = 310;
	public static int POSICION_Y_JUG = 550;
	public static int TAM_JUG_X = 100;
	public static int TAM_JUG_Y = 8;
	public static int VEL_JUG = 20;

	// pelota

	public static int INICIO_PELOTA_X = ((int) new Random().nextInt(600)) + 50;
	public static int INICIO_PELOTA_Y = ((int) new Random().nextInt(250)) + 330;
	public static int VEL_PEL_X = -1;
	public static int VEL_PEL_Y = -2;
	public static int TAM_PEL_X = 20;
	public static int TAM_PEL_Y = 20;

	// timer

	public static int DELAY = 8;

	// puntos

	public static int PUNTOS_BLOQUE = 5;
	public static int POS_PUNTOS_X = 590;
	public static int POS_PUNTOS_Y = 30;

}
