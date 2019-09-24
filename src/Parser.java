
import java.io.*;
import java.util.*;


/**
 * Ta klasa pobiera dane wejsciowe jakie podajemy w pliku o typie input nazwanym file.txt.
 * dla każdej lini w pliku wejściowym tworzy punkt reprezentowany przez jego nazwe, wspolrzedna Xi oraz
 * współrzedną Yi. Dodaje punkt do listy typu <Point> nazwanej POINTS. Nastepnie wywołuje funckję CREATEMAP, która z kolei
 * tworzy hasz mape dla naszsego problemu oraz zapisuje ja w zmiennej statycznej GRAPH
 * GRAPH mapuje każdy węzeł o kluczu zmiennej typu string do listy węzłow w której każdy węzęł zawiera
 * wartośc odległości w lini prostej od klucza SDISTANCE.
 */
class Parser{

	/**
	 * Ścieżka do pliku z danymi wejsciowymi
	 *
	 */
	private String FilePath;
	
	
	
	
	List<Node> value =null;
	List<Point> points = new ArrayList<Point>();
	
	/**
	 * Ta metoda bierze nazwe pliku jako dane wejściowe
	 * i analizuje plik po każedej lini.
	 *
	 */
	public boolean parseFile(String path){


		
		String[] str = null;
		FilePath = path;
		
		try{
			
			FileReader file = new FileReader(FilePath);
			Scanner scanner = new Scanner(file);														
			
			while(scanner.hasNextLine()){

				String line = scanner.nextLine();
				str = line.split(",");
				
				
				Point p = new Point(str[0],Double.parseDouble(str[1]),Double.parseDouble(str[2]));
				
				//dodajemy punkt do listy punktów
				points.add(p);
			}
			
			tsp.numNodes = points.size();
			scanner.close();
			createMap(points);
			}catch(IOException e){
				
				return false;
				
			}
		
		
		return true;
	}
	
	public void createMap(List<Point> list){
		
		double sdistance = 0;
		
		for(Point p:list){
			
			for(Point q:list){
				
				/**
				 *  powtarzamy dla kazdego punktu w liscie GRAPH za wyjatkiem siebie samego.
				 * */
				if(!p.name.equals(q.name)){
					
					/**
					 * obliczamy odleglosc w lini prostej poniedzy punktami
					 * */
					sdistance = Math.sqrt(Math.pow(p.x - q.x, 2.0) + Math.pow(p.y - q.y, 2.0));
					
					/**
					 * tworzymy węzeł który jest sąsiadujący z aktualnym węzłem P
					 */
					Node n = new Node(q.name,sdistance);
					
					value = tsp.graph.get(p.name);
					
					/**
					 * sprawdzamy czy klucz został już utworzony w naszej hashMap'ie czy nie
					 * */
					if (value == null){
						value = new ArrayList<Node>();
						value.add(n);
						tsp.graph.put(p.name,value);
					}else{
						value.add(n);
					}
					}
				}
			}
		}
	}