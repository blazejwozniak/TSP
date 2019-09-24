import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




public class LOWriter {
	
	
	private static File optimal = new File(tsp.outputPath);
	private static File logFile = new File(tsp.outputLog);
	private static FileWriter log = null;
	private static FileWriter opt = null;

	/**
	 * metoda INIT jest to statyczna metoda klasy LOWriter, która inicjaluzuje nam
	 * plik wyjściowy zawierający ścieżkę optymalną.
	 * Tworzy ona 2 objekty OPT i LOG aby nadpisywaly odpowiednio:
	 * OPT - dokladnie ścieżkę optymalną a LOG - pokazywała rozważania agenta oraz jego wybory.
	 * Jest to swojego rodzaju dziennik agenta.
	 * 
	 */
	
	protected static void init() {

		// tworzenie plików
		try {
			optimal.createNewFile();
			logFile.createNewFile();
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			opt = new FileWriter(optimal);
			log = new FileWriter(logFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	

	/**
	 * Funkcja OUTPUT nadpisuje rozwiązanie już znalezione optymalne do tego pobiera
	 * parametr ENDTOUR jest objektem typu NODEINFO, który zawiera ścieżkę
	 * do naszego celu oraz optymalną ścieżkę o koszcie(g),
	 * heurystycznym koszcie (h) z aktualnego węzła do naszego celu oraz łącznego kosztu (f)
	 */
	protected static void output(NodeInfo endTour) {

		try {
			for (String s : endTour.path) {
				opt.write(s + "\n");
				log.write(s);
			}
			
			/**
			 * Wypisuje optymalną ścieżkę do naszego outputu
			 */
			log.write("," + (endTour.g));
			log.write("," + (endTour.h));
			log.write("," + (endTour.f));
			
			/**
			 * Dopisuje koszt całościowy naszej ścieżki optymalnej
			 */
			opt.write("Laczny koszt sciezki: " + (endTour.f));


			opt.close();
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Ta Metoda bierze parametr typu NODEINFO oraz
	 * nadpisuje nasz output_log wypisując ścieżkę do węzła, koszt
	 * ścieżki, koszt heurystyczny oraz koszt łączny.
	 * Parametr NODE jest objektem typu NODEINFO zawierającym, koszt,
	 * ścieżkę, koszt (g), koszt heurystyczny (h) oraz koszt łączny/całościowy (f)
	 */
	protected static void log(NodeInfo node) {

		try {
			for (String s : node.path) {
				log.write(s);
			}
			log.write(node.nodeName);
			log.write("," + (node.g));
			log.write("," + (node.h));
			log.write("," + (node.f));
			log.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
