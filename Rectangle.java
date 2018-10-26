package uF4.practicas._01_10_10_2018._03;

import processing.core.PApplet;

public class Rectangle extends Shape { // Resctangulo

	private double alto, ancho;
	private double ubicacionPaletaX;
	private static PApplet pappletPaleta;

	protected static final int ANCHO_PALETA = 200;
	protected static final int ALTO_PALETA = 30;
	protected static final int UBICACION_PALETA_X = -100;
	protected static final int UBICACION_PALETA_Y = Shape.TAMANYO_PANTALLA - 100;
	protected static final int UBICACION_PALETA_CENTRO = Shape.TAMANYO_PANTALLA + 400;

	// CONSTRUCTOR
	public Rectangle(double alto, double ancho, Point point, Color color) {
		super(color, point);
		this.alto = alto;
		this.ancho = ancho;
		this.ubicacionPaletaX = point.getX();
	}

	// GETTERS
	public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public static PApplet getPappletPaleta() {
		return pappletPaleta;
	}

	public double getUbicacionPaletaX() {
		return ubicacionPaletaX;
	}

	// SETTER
	public void setHW(double alto, double ancho) {
		this.alto = alto;
		this.ancho = ancho;
	}

	public static void setPappletPaleta(PApplet pappletPaleta) {
		Rectangle.pappletPaleta = pappletPaleta;
	}

	public void setUbicacionPaletaX(double ubicacionPaletaX) {
		this.ubicacionPaletaX = ubicacionPaletaX;
	}

	// METODOS SOBREESCRITOS
	@Override
	public double area() {
		return alto * ancho;
	}

	@Override
	public double perimetro() {
		return 2 * (alto + ancho);
	}

	// --------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------

	@Override
	public void movimientoFigura() {

		if (pappletPaleta.keyPressed) { // detectara cuando se presiona una tecla
			if (pappletPaleta.keyCode == 37) { // Detecta el tipo de tecla presionada, en este caso 37 = IZQUIERDA
				ubicacionPaletaX = getUbicacionPaletaX() - 10;  // se mueve direccion izquierda sin limites
			} else if (pappletPaleta.keyCode == 39) { // Detecta el tipo de tecla presionada, en este caso 39 = DERECHA
				ubicacionPaletaX = getUbicacionPaletaX() + 10;  // se mueve direccion derecha sin limites
			}
		}

		// Esto es el movimiento para la izquierda con un limite para que no salga de la pantalla.
		if ((getUbicacionPaletaX() + Shape.TAMANYO_PANTALLA / 2) <= 0) {
			ubicacionPaletaX = getUbicacionPaletaX() + 10;
		}

		// Esto es el movimiento para la derecha con un limite para que no salga de la pantalla.
		if ((getUbicacionPaletaX() + UBICACION_PALETA_CENTRO / 2) >= Shape.TAMANYO_PANTALLA) {
			ubicacionPaletaX = getUbicacionPaletaX() - 10;
		}
	}
	
	// Esta funcion devolvera el valor de 'ubicacionPaletaX' como resultado de la funcion 'movimientoFigura()'
	// para poder hacer que la pelota rebote en la paleta.
	public float barraMovimiento() {
		return (float) ubicacionPaletaX ;
	}
	

	@Override
	public void dibujarFigura() {
		// Aqui se obtendra el color de relleno del rectangulo
		getPappletPaleta().fill(getColor().getRed(), getColor().getGreen(), getColor().getBlue());
		getPappletPaleta().stroke(getColor().getRed(), getColor().getGreen(), getColor().getBlue());
		// Aqui se dara la posicion en la pantalla.
		getPappletPaleta().rect((float) ((Shape.TAMANYO_PANTALLA / 2) + getUbicacionPaletaX()), UBICACION_PALETA_Y, (float) ancho,(float) alto);

	}

}
