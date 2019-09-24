/**
 *
 * Klasa POINT przechowuje punkt pobrany z danych wejsciowych i
 * odpowiednio zinterpretowany.
 * NAME: reprezentuje nazwe używaną do identyfikacji punktu.
 * X reprezentuje wartość położenia punktu na osi X
 * Y reprezentuje wartość położenia punktu na osi Y
 */
public class Point {
	protected final String name;
	protected  double x;
	protected  double y;
	
	
	public Point(String name ,double x, double y){
		this.name = name;
		this.x = x;
		this.y = y;
	}
}
