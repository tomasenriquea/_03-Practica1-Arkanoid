package uF4.practicas._01_10_10_2018._03;

import processing.core.PApplet;

public class Main extends PApplet {

	Circle pelota = null; // crea la pelota pero no se le asigna espacio de memoria.
	Rectangle paleta = null; // Crea la paleta pero no se le asigna espacio de memoria.
	float rebote = 0.0f; // Sera el valor devuelto que controlara el rebote de la pelota con la paleta.

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
		Color color2 = new Color(Circle.COLOR_NEUTRO, Circle.COLOR_PRECISO, Circle.COLOR_NEUTRO);
		Point point2 = new Point(Rectangle.UBICACION_PALETA_X, Rectangle.UBICACION_PALETA_Y);
		paleta = new Rectangle(Rectangle.ALTO_PALETA, Rectangle.ANCHO_PALETA, point2, color2);

		contadorTocaSuelo = 0;  // Inicializado el contador de veces que la pelota toca el suelo.
		rebotesPuntos = 0;  // Inicializado el contador de puntuacion por rebote de la pelota con la paleta.
		

	}

	public void draw() {
		background(Circle.COLOR_PRECISO); // el color de fondo
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
		

	}

}

/*
2.3. arkanoid
Aprovechando el ejercicio anterior, introduzca bloques en la parte opuesta del lado abierto de modo que, el objetivo del juego sea destruir todos 
los bloques. Un bloque se destruye si toca el balón..

Indicaciones:
[] Los bloques son rectángulos.

Extra:
Diseñar una partida de forma que:
[x]1) Haya tres pelotas por partida.
[x]2) Cada vez que la pala toca el balón se ganan puntos.
[]3) El balón se va acelerando.
[x]4) Un marcador muestra en todo momento el estado de la partida
[]5) Y todo lo que os apetezca.
*/

