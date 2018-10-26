package uF4.practicas._01_10_10_2018._03;


import processing.core.PApplet;

public class Circle extends Shape { // Circulo

	private double radio; // el radio de la esfera
	private int x, y; // x, y seran las coordenadas de ubicacion de la pelota.
	private int velocX, velocY; // Esto se encargara el movimiento de la pelota.
	private static PApplet pappletPelota; // Sirve para que la pelota sea puesta en la pantalla. ---> A

	protected static final int TAMANYO_PELOTA = 40; // Tamaño de la pelota definido.
	protected static final int COLOR_PRECISO = 255; // numero para un color preciso.
	protected static final int COLOR_NEUTRO = 0; // numero para un color preciso.
	protected static final int VELOCIDAD_X = 7; // Velocidad de la coordenada X
	protected static final int VELOCIDAD_Y = 5; // Velocidad de la coordenada Y
	protected static final int INICIO_X = Shape.TAMANYO_PANTALLA - 400; // Inicio de la coordenada X 400
	protected static final int INICIO_Y = Shape.TAMANYO_PANTALLA - 120; // Inicio de la coordenada Y 680
	protected static final int NUMERO_PELOTAS = 3;  // representa la cantidad de pelotas por partida
	protected static final int PUNTOS_REBOTE_PALETA = 10;  // representa la puntuacion por cada rebote en la paleta

	// CONSTRUCTORES
	public Circle(Point point, Color color, double radio) {
		super(color, point);
		this.radio = radio;
	}

	public Circle(int velocX, int velocY, Point point, Color color) {
		super(color, point);
		this.x = (int) point.getX(); // Inicio de la coordenada X para la pelota
		this.y = (int) point.getY(); // Inicio de la coordenada Y para la pelota
		this.velocX = velocX;
		this.velocY = velocY;
	}

	// GETTER
	public double getRadio() {
		return radio;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getVelocX() {
		return velocX;
	}

	public int getVelocY() {
		return velocY;
	}

	public static PApplet getPappletPelota() {
		return pappletPelota;
	}

	// SETTER
	public void setRadio(double radio) {
		this.radio = radio;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVelocX(int velocX) {
		this.velocX = velocX;
	}

	public void setVelocY(int velocY) {
		this.velocY = velocY;
	}

	public static void setPappletPelota(PApplet pappletPelota) {
		// Sirve para que la pelota sea puesta en la pantalla. ---> A
		Circle.pappletPelota = pappletPelota;
	}

	// METODOS SOBRESCRITOS
	@Override
	public double area() {
		return Math.PI * (radio * radio);
	}

	@Override
	public double perimetro() {
		return 2 * Math.PI * radio;
	}

	@Override
	public void movimientoFigura() {
		// Generara el movimiento de X e Y para la pelota.
		x = getX() + getVelocX();
		y = getY() - getVelocY();

		if ((getX() > getPappletPelota().width - 10) || (getX() < 10)) { // Controla el movimiento de la coordenada X
			velocX = getVelocX() * -1;
		}
		if ((getY() > getPappletPelota().height - 10) || (getY() < 10)) { // Controla el movimiento de la coordenada Y
			velocY = getVelocY() * -1;
		}
	}

	// --------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------

	// Con esto verificamos cuando la pelota caiga en el suelo, devolviendo 'true' cada vez que lo haga.
	public boolean salidaPelota() {
		boolean salida = false;
		if ((getY() > Shape.TAMANYO_PANTALLA - 10)) { // esto representa la parte de 'Y' que es el suelo
			salida = true;
		}
		return salida;
	}

	/** Sirve para que se muestre el texto en la pantalla, cantidad de pelotas por partida y fin de juego.
	 *  @param pelotasAquitar 
	 *  sera el dato que restara la cantidad de pelotas que tenemos por partida.
	 *  @variable 'cantidadPelotas' se encargara de mostrar el mensaje de fin de juego y de activar el reinicio o salida
	 *  del juego.
	 */
	public void cantidadPartidas(int pelotasAquitar) {
		String pelotas = "Pelotas: ";
		String finPartida = "Game Over";
		String mensajeReinicioFin = "Reinicia el juego\n      Si --> 's'\n     No --> 'n'";
		int cantidadPelotas = (NUMERO_PELOTAS - pelotasAquitar);   // es la cantidad de pelotas por partida
		
		// para la cantidad de pelotas
		getPapplet().textSize(20);
		getPapplet().fill(0);
		getPapplet().text(pelotas + cantidadPelotas, 650, 790);
		
		if(cantidadPelotas < 1) {
			// Para el mensaje de fin de partida
			getPapplet().textSize(100);
			getPapplet().fill(COLOR_PRECISO, COLOR_NEUTRO, COLOR_NEUTRO);
			getPapplet().text(finPartida, 140, 400);
			
			// Mensaje de reinicio o finalizar.
			getPapplet().textSize(20);
			getPapplet().fill(0, 0, 200);
			getPapplet().text(mensajeReinicioFin, 330, 450);
			reinicioJuego();  // Llamara a la funcion para reiniciar el juego o salir de el.	
			detenerPelota();  // FALTA TERMINAR ESTA FUNCION
		}	
	}
	
	// Funcion para reiniciar el juego o salir de el.
	private void reinicioJuego() {
		char si = 's';
		char no = 'n';
		if (pappletPelota.keyPressed) { // detectara cuando se presiona una tecla
			if (pappletPelota.key == si) {
				getPapplet().frameCount = -1;  // Con esto se reinicia el juego
			} else if (pappletPelota.key == no) { 
				getPapplet().exit();  // esto es para salir del juego
			}
		}  		
	} 
	
	private void detenerPelota() {  //FALTA TERMINAR
		//getPappletPelota().noLoop();
		getPappletPelota();
	}
	
	

	/**
	 * Muestra la cantidad de puntos obtenidos en la partida al rebotar la pelota contra la pala.
	 * @param puntos
	 */
	public void cantidadPuntosObtenidos(int puntos) {
		String puntuacion = "Puntuación: ";
		// Para la puntuacion
		getPapplet().textSize(20);
		getPapplet().fill(0);
		getPapplet().text(puntuacion + puntos, 10, Shape.TAMANYO_PANTALLA - 10);	
	}
	
	// --------------------------------------------------------------------------------------------------------

	
	// --------------------------------------------------------------------------------------------------------	
	
	
	@Override
	public void dibujarFigura() {
		// Color de relleno.
		getPappletPelota().fill(getColor().getRed(), getColor().getGreen(), getColor().getBlue());
		// Color de perimetro.
		getPappletPelota().stroke(getColor().getRed(), getColor().getGreen(), getColor().getBlue());
		// genera el movimiento y dibuja la pelota.
		getPappletPelota().ellipse(getX(), getY(), TAMANYO_PELOTA, TAMANYO_PELOTA);
	}

	/**
	 * Esta funcion recibira un dato que varia, que es muy importante para que la pelota rebote en la paleta
	 * el dato se recibida del metodo 'barraMovimiento()' de la clase 'Rectangle', tambien devolvera 'true' la 
	 * veces que rebota la pelota con la paleta. 
	 * @param rebote
	 */
	public boolean rebotePaletaPelota(float rebote) {
		boolean vecesRebotes = false;
		float ubicacionExactaX = Shape.TAMANYO_PANTALLA / 2 + rebote;  // esto es la ubicacion de la paleta

		if (getY() > (Rectangle.TAMANYO_PANTALLA - 120) && getX() > ubicacionExactaX && x < ubicacionExactaX + Rectangle.ANCHO_PALETA) {
			velocX = getVelocX() * -1;
			velocY = getVelocY() * -1;
			vecesRebotes = true;
		}
		return vecesRebotes;  //Aqui devolvera 'true' cada vez que rebote la pelota
	}
	/* EXPLICACION PARA EL REBOTE DE LA PELOTA 680
	  
	 - Distancia entre la paleta y el suelo = 100 
	 - ancho de la paleta = 30 
	 - alto y ancho de la pantalla = 800 
	 - ancho de la paleta = 200
	  
	  cuando el valor de 'y' sea mayor 680 (varia segun el gusto, puede ser 670) --> y < 800-(30 + 100) 
	  cuando el valor de 'x' sea mayor 'ubicacionExactaX' ---> (400 + ubicacionX)
	 */

}
