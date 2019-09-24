
import java.util.*;

/**
 * Jest to kolejna klasa implemetująca algorytm A*
 * Tym razem implementujemy zgodznie z poleceniem kolejną heurystykę. Osobiście wybrałem heurystykę o nazwie
 * Minimalne drzewo rozpinające (ang. Minimal Spanning Tree) na podstawie HashMap'y tworznej przez
 * klasę PARSER, która również zapewnia nam informację o odwiedzonych węzłach.
 * */

class mAstar {

	int count = 0;

	/**
	 * FRONTIER jest to kolejka priorytetowa z zasadą FIFO
	 * EXPLORED jest to Lista powiązana (ang LinkedList) wszystkich odwiedzonych węzłów
	 * CURRENT jest to również lista powiązana wsyzstkich aktualnie odwiedzonych węzłów, które musimy przejrzeć
	 * aby znaleźć najkrótszą drogę
	 * CHILD jest to węzeł który jest usunięty z listy rodzica (childList)
	 * CHILDLIST jest to lista węzłów, które są powiązane z rodzicem.
	 * ENDTOUR jest to droga z ostatnio odwiedzonego węzła do źródłowego węzła który w naszym wypadku jest również
	 * naszym stanem docelowym.
	 */
	
	
	PriorityQueue<NodeInfo> frontier = new PriorityQueue<NodeInfo>(1,new Compare() {
        @Override
        public int compare(NodeInfo o1, NodeInfo o2) {
        return Double.valueOf(o1.f).compareTo(o2.f);
        }
    });
	
	Node child;
	List<Node> childList;
	NodeInfo childData, endTour;
	NodeInfo node;
	double gcost, hcost;
	List<String> explored = new ArrayList<String>();
	List<String> current = new ArrayList<String>();

	/**
	 * MHEURISTICCOST jako parametr pobiera objekt NODEINFO, który reprezentuje dziecko (CHILD) i szuka
	 * minimalnego drzewa rozpinającego (MTS) kosztu heurystycznego.
	 *
	 * CHILD to zmienna typu NODEINFO która reprezentuje dziecko, posiada również wskażnik przodka oraz koszt heurystyki SLD od rodzica.
	 * funkcja zwraca dystans w lini prostej po krawedziach grafu of dziecka do stanu końcowego
	 */
	
	private double mheuristicCost(NodeInfo child) {
		double h = 0.0;
		explored.clear();
		current.clear();
		List<Node> pNodes;
		double min = 0.0;
		String minNode = null;
		
		for(String s: child.path){
			if(!s.equals(tsp.initialState)){
				explored.add(s);
			}
		}
		
		current.add(child.nodeName);
		explored.add(child.nodeName);
		
		
		while(explored.size() <= (tsp.numNodes - 1)){
			
			/**
			 * Przechodzimy przez aktualne węzły
			 */
			min = 99999999.99999;
			for(String s:current){
				pNodes = tsp.graph.get(s);
				for(Node p: pNodes){
					if(!explored.contains(p.name)){
						if(p.sld < min){
							min = p.sld;
							minNode = p.name;
						}
					}
					
				}
				
			}
			
			/**
			 * Dodajemy aktualnie najelpszy węzeł do kolejki CURRENT
			 */
			
			explored.add(minNode);
			current.add(minNode);
			h += min;
		}
		
		return h;
	}

	private void addChildren(List<Node> children, boolean last) {
		int i = 0;
		if (last == false) {
			while (i < children.size()) {

				child = children.get(i);


				if (!(node.path.contains(child.name))) {
					

					gcost = child.sld + node.g;
					hcost = 0.0;
					
					
					/**
					 * bierzemy listę przodków parenta i dodajemy je do naszego dziecka (child'a)
					 */
					
					childData = new NodeInfo(child.name, node.path, gcost,hcost);
					childData.path.add(node.nodeName);
					
					/**
					 * Bierzemy minimalny koszt heurystyki MST i dodajemy ten koszt do objektu CHILDDATA
					 */
					hcost = mheuristicCost(childData);
					childData.h = hcost;
					childData.f = gcost + hcost;
					frontier.add(childData);
				}
				i++;
			}
		} else {

			int j = 0;
			while (j < children.size()) {
				double gcost = 0.0;
				child = children.get(j);
				if (child.name.equals(tsp.initialState)) {
					gcost = child.sld + node.g;
					endTour = new NodeInfo(child.name, node.path, gcost);
					endTour.path.add(node.nodeName);
					endTour.path.add(child.name);
					endTour.f = endTour.g + endTour.h;
					break;
				}
				j++;
			}
		}
	}

	public void search() {

		LOWriter.init();


		/**
		 * FRONTIER - nasza kolejka węzłów
		 */
		node = new NodeInfo(tsp.initialState);

		frontier.add(node);
		node.h = mheuristicCost(node);
		node.f = node.g + node.h;


		while (true) {


			if (frontier.peek() == null) {
				return;
			}

			/**
			 * usuwamy przód kolejki FRONITER
			 */
			node = frontier.remove();
		
			if (node.path.size() == (tsp.numNodes - 1)) {

				/**
				 * dodajemy węzeł do logu w celach statystycznych
				 */
				LOWriter.log(node);

				childList = tsp.graph.get(node.nodeName);
				addChildren(childList, true);
				/**
				 * Dopisujemy ścieżkę optymalną do logu oraz do anszego outputu
				 */

				LOWriter.output(endTour);
				return;
			} else {
				
				LOWriter.log(node);
			}

			childList = tsp.graph.get(node.nodeName);
			addChildren(childList, false);

		}

	}
}