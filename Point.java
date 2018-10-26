package uF4.practicas._01_10_10_2018._03;

public class Point {
	private float x, y;
	
	//CONSTRUCTOR
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	//GETTERS
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	
	//SETTERS
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
	@Override
	public String toString() {
		return "X: " + x +
			   "\nY: " + y;
	}
	
	
	
	

}
