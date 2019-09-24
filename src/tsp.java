
import java.util.*;


/**
 * TSP to klasa typu MAIN która czyta z lini poleceń niezbędne parametry do działania projektu oraz przechowuje
 * niektóre informacje w odpowiednich zmiennych statycznych, które z kolei są używane w innych klasa projektu.
 * Przekazuje również nazwe pliku do kalsy PARSER a dokaldnie jej metody PARSEFILE.
 * Równiez na pdostawie odpowiednich wartości podawanych w konsoli uruchamiane są dopwoiednie heurystyki algorytmu A*.
 * */
class tsp{
	protected static int numNodes;
	protected static String initialState;
	protected static HashMap<String, List<Node>> graph = new HashMap<String, List<Node>>();
	protected static int option;
	protected static String outputPath,outputLog;
	public static void main(String[] args) {
		
		
		int i;
		

		if(args.length != 10 ){
			System.err.println("Nieprawidlowa ilosc parametrow");
			return;
		}
		
		
			for(i = 0;i < args.length;i++){
				
				if(args[i].equals("-t")){
					option = Integer.parseInt(args[i+1]);
				}else if(args[i].equals("-s")){
					tsp.initialState = args[i+1];	
					tsp.initialState = "a";	

				}else if(args[i].equals("-i")){
					
					/*
					 * Tworzymy objekt typu PARSER który interpretuje plik wejsciowy.
					 * 
					 * */
					Parser p = new Parser();
					if(!(p.parseFile(args[i+1]))){
						System.err.println("Podaj poprawna nazwe pliku wejsciowego");
						return;
					}
					
					
				}else if(args[i].equals("-op")){
					tsp.outputPath = args[i+1];
					
					
				}else if(args[i].equals("-ol")){
					tsp.outputLog = args[i+1];

				}
			}
		
		if	( option == 1){
			
			Astar a = new Astar();
			a.search();
			System.out.println("Ukonczono obliczenia dla heurystyki SLD.");

		
		}else if(option == 2){

			mAstar m = new mAstar();
			m.search();
			System.out.println("Ukonczono obliczenia dla heurystyki MST");

		}
		
	}
}
