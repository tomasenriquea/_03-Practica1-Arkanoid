package uF4.practicas._01_10_10_2018._03;

import processing.core.PApplet;
import processing.core.PGraphics;


public class Main extends PApplet {

	Circle pelota = null; // crea la pelota pero no se le asigna espacio de memoria.
	Rectangle paleta = null; // Crea la paleta pero no se le asigna espacio de memoria.
	float rebote = 0.0f; // Sera el valor devuelto que controlara el rebote de la pelota con la paleta.
	
	Rectangle bloques[];  //Array de bloques.
	int valorBloque;
	
	
	
	boolean tocaSuelo = false;  // boleano que nos servira para saber cuando la pelota toca el suelo.
	int contadorTocaSuelo;  // Contador de veces que la pelota toca el suelo.
	
	boolean vecesRebotes = false; // boleano que nos servira para saber cuando la pelota toca la paleta.
	int rebotesPuntos;  // cantidad de veces que rebota la pelota con la paleta o la puntuacion generada por ello.

	
	
	
	public static void main(String[] args) {
		String[] mainArgs = { "Fronton" };
		Main main = new Main(); // se llama a la clase principal Main
		PApplet.runSketch(mainArgs, main);
	}

	
	@Override
	public void settings() {
		size(Shape.TAMANYO_PANTALLA, Shape.TAMANYO_PANTALLA); // aqui se pondra el tamaño de la pantalla.
		Circle.setPappletPelota(this); // sirve para que las pelota se dibuje en la pantalla.
		Rectangle.setPappletPaleta(this); // sirve para que las paleta se dibuje en la pantalla.
		Shape.setPapplet(this);  // Servira para poner el texto en pantalla.
	}

	
	@Override
	public void setup() {
		// Color y ubicación de inicio de la pelota, mas su implementación con su velocidad de movimiento.
		Color color = new Color(Circle.COLOR_NEUTRO, Circle.COLOR_NEUTRO, Circle.COLOR_PRECISO);
		Point point = new Point(Circle.INICIO_X, Circle.INICIO_Y);
		pelota = new Circle(Circle.VELOCIDAD_X, Circle.VELOCIDAD_Y, point, color);

		// Esto son el color y ubicacion de la paleta, mas su implementación con movimiento y ubicación
		Color color2 = new Color(123, 104, 238);  // Color  Medium SlateBlue
		Point point2 = new Point(Rectangle.UBICACION_PALETA_X, Rectangle.UBICACION_PALETA_Y);
		paleta = new Rectangle(Rectangle.ALTO_PALETA, Rectangle.ANCHO_PALETA, point2, color2);

		contadorTocaSuelo = 0;  // Inicializado el contador de veces que la pelota toca el suelo.
		rebotesPuntos = 0;  // Inicializado el contador de puntuacion por rebote de la pelota con la paleta.
		
		valorBloque = 10;  
		
		
		//-----------------------------------------------------------------------
		//Codigo de los bloques
		Color color3 = new Color(173, 255, 47);  // color de bloques GreenYellow
		Point point3[] = new Point[Rectangle.CANTODAD_FILAS_BLOQUES];  // Array de coordenadas de ubicación de bloques
		bloques = new Rectangle[Rectangle.CANTODAD_FILAS_BLOQUES];  // Array de objetos de bloques a crear
		
		/** Aqui se asigna la ubicacion de cada bloque a crear donde: 
		 * @param indice, sera la cantidad de objetos point a crear
		 * @param ubicacionY, sera la ubicacion Y de las filas de bloques a crear y
		 * @param Rectangle.ALTO_BLOQUE, en este caso sera la ubicacion X del bloque que sera fija.
		 */
		for(int indice = 0, ubicacionY = Rectangle.ALTO_BLOQUE; indice < Rectangle.CANTODAD_FILAS_BLOQUES && ubicacionY <= Rectangle.LIMITE_Y_ULTIMO_BLOQUE; indice++, ubicacionY += 40) {
			point3[indice] = new Point(Rectangle.ALTO_BLOQUE, ubicacionY);
		}
		
		
		//Aqui se crean las filas de bloques
		for(int i = 0; i < Rectangle.CANTODAD_FILAS_BLOQUES; i++) {
			bloques[i] = new Rectangle(Rectangle.ALTO_BLOQUE, Rectangle.ANCHO_BLOQUE, point3[i], color3, Rectangle.RADIO_BLOQUE);
		}
	
	}

	
	@Override
	public void draw() {
		background(211,211,211); // el color de fondo lightGrey
		pelota.dibujarFigura(); // se dibuja la pelota.
		pelota.movimientoFigura(); // se llama a su metodo de movimiento.

		paleta.dibujarFigura(); // se dibuja la paleta
		paleta.movimientoFigura(); // se le aplica el movimiento horizontal

		
		rebote = paleta.barraMovimiento(); // devuelve un valor que se usara para el rebote de la pelota y la paleta
		// genera el rebote entre la paleta y la pelota y devuelve un valor 'true' cada vez que lo hace.
		vecesRebotes = pelota.rebotePaletaPelota(rebote); 
		tocaSuelo = pelota.salidaPelota(); // Devolvera 'true' cada vez que la pelota toque el suelo.
		
		
		if(tocaSuelo == true && contadorTocaSuelo < Circle.NUMERO_PELOTAS) {  // Limita la cantidad de cambios para que no halla numero negativo.
			contadorTocaSuelo++;  // contara los 'true' devueltos, que seran las veces que la pelota cae al suelo.
		}
		pelota.cantidadPartidas(contadorTocaSuelo);  // Texto en pantalla que indicara los puntos y la cantidad de pelotas restantes.
		
		
		if(vecesRebotes == true) {  // verifica los rebotes de la pelota con la paleta para la puntuacion
			rebotesPuntos+= Circle.PUNTOS_REBOTE_PALETA;  // aumenta la puntiacion en 10.
		}
		pelota.cantidadPuntosObtenidos(rebotesPuntos);  // muestra los puntos obtenidos por rebote de la paleta con la pelota.
		
		//-----------------------------------------------------------------------
		//Codigo de los bloques
		for(int i = 0; i < Rectangle.CANTODAD_FILAS_BLOQUES; i++) {
			bloques[i].mostrarVariosBloques();
		}
		valorBloque = pelota.choque();		
		paleta.borrarBloque(valorBloque);
		
	}

}

/*
2.3. arkanoid
Aprovechando el ejercicio anterior, introduzca bloques en la parte opuesta del lado abierto de modo que, el objetivo del juego sea destruir todos 
los bloques. Un bloque se destruye si toca el balón..

Indicaciones:
[x] Los bloques son rectángulos.

Extra:
Diseñar una partida de forma que:
[x]1) Haya tres pelotas por partida.
[x]2) Cada vez que la pala toca el balón se ganan puntos.
[]3) El balón se va acelerando.
[x]4) Un marcador muestra en todo momento el estado de la partida
[]5) Y todo lo que os apetezca.
*/

