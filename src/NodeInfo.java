
import java.util.*;


/**
 * Klasa NODEINFO tworzy objekt dla każdego węzła,
 * przechowuje ich nazwę, koszt ściezki aby do nich dotrzeć oraz liste (ArrayList) ich przodków
 *  
 */
public class NodeInfo {
	String nodeName;
	
	/**
	 * PATH jest to ścieżka przodków w formie Listy ze źródłowego węzła
	 */
	ArrayList<String> path = new ArrayList<String>();
	
	/**
	 * zmienna G jest to koszt ścieżki z węzła inicjalnego do węzła aktualnego.
	 */
	double g;
	
	/**
	 * H jest to koszt heurytstyczny który w przypadku algorytmu A* może być odległością w lini prostej lub koszt wag minimalnie rozwijanego drzewa
	 */
	double h;
	
	double f;
	
	String parentName;
	
	/**
	 * Poniższy konstruktor jest przywoływany kiedy koszt heurystyki nie jest używany
	 * paramtert NODENAME to nazwa węzła
	 * PATH to lista przodków węzła
	 * G to koszt ścieżki węzła
	 */
	public NodeInfo(String nodeName, ArrayList<String>path,double g){
		this.nodeName = nodeName;
		this.g = g;
		for(String s:path){
			this.path.add(s);
		}
		this.h = 0.0;
	}
	
	/**
	 * Ten konstruktor wołamy z kolei kiedy koszt heurystyczny jest podany
	 *
	 * H  koszt heurystyczny
	 */
	public NodeInfo(String nodeName, ArrayList<String>path,double g,double h){

		this.nodeName = nodeName;
		this.g = g;
		for(String s:path){
			this.path.add(s);
		}
		this.h = h;
	}
	
	/**
	 * Kontruktor wykorzystany do stworzenia stanu inicjalnego naszego problemu
	 * This constructor is used to create the initialState of the problem
	 */
	public NodeInfo(String nodeName){
		this.nodeName = nodeName;
		this.g = 0.0;
		this.h = 0.0;
	}
	
	/**
	 * Konstruktor dedykowany na cele tworzenia objektów typu NODEINFO,
	 * które będą urzywane do heurystyki MST.
	 *
	 * parmaetr G - odleglosc w lini prostej od węzła do rodzica
	 * parametr S - jest prawdziwy tylko wtedy kiedy ta funkcja jest uzyta do wyliczenia MST
	 */
	public NodeInfo(String nodeName,double g){
		this.nodeName = nodeName;
		this.g = g;
		this.h = 0.0;
		this.f= 0.0;
		
	}
	
}
