package uF4.practicas._01_10_10_2018._03;

import java.util.Scanner;

import processing.core.PApplet;


public abstract class Shape{ 
	
	private Color color;
	public Point point;
	private static PApplet papplet;
	protected static final int TAMANYO_PANTALLA = 800;  // tamaño de la pantalla
	
	
	
	//CONSTRUCTORES
	protected Shape(Color color, Point point) {  // Constructor con modificado protegido, funciona con normalidad
		this.color = color;
		this.point = point;
	}
	
	
	//GETTERS
	public Color getColor() {
		return color;
	}

	public Point getPoint() {
		return point;
	}

	public static PApplet getPapplet() {
		return papplet;
	}


	//SETTERS
	public void setColor(Color color) {
		this.color = color;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	
	public static void setPapplet(PApplet papplet) {
		Shape.papplet = papplet;
	}
	
	
	//METODOS DE CALCULOS PARA IMPLEMEMTAR
	public abstract double area();  // area de la figura
	
	public abstract double perimetro();  // perimetro de la figura
	
	public abstract void dibujarFigura();  // dibuja la figura en la pantalla.
	
	public abstract void movimientoFigura();  //genera el movimiento de la figura
	
	
	//DETALLES 
	public void detalleCoordenada() {
		System.out.println("Posicion X: " + point.getX() + 
						   "\nPosicion Y: " + point.getY());
	} 
		

	public void detalleColor() {
		System.out.println("Rojo: " + color.getRed() + 
						   "\nVerde: " + color.getGreen() + 
						   "\nAzul: " + color.getBlue());
	}
	
	

	//----------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------
	
	// Esto es para mostrar el menú de opciones.
	public static int menu() {
		
		System.out.println("\n\n\n");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("||||||||||||||||||||FIGURAS|||||||||||||||||||");
		System.out.println();
		System.out.println("1. Crear un circulo.\n" + 
						   "2. Crear un rectangulo.\n" + 
						   "0. Salir.");
			
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int dato;
		do {
			System.out.print("\nElija un opción: ");  // Controla que solo se ingrese una de las 4 opciones.
			dato = sc.nextInt();
		}while(dato > 2);
		
		return dato;	
	}
	


	
	
	
	
	
}  