

/**
 * Klasa NODE tworzy inicjalne węzły które wpisujemy w nasz plik typu input o nazwie file.txt
 * oraz przechowuje ich odległośc w lini prostej od innych węzłów
 */
class Node{

	protected String name;

	//Dystans od węzła do węzła w kluczu hasz Mapy
	protected double sld = 0;

	public Node(String name,double sld){
		this.name = name;
		this.sld = sld;
	}
}