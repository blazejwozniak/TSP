
import java.util.*;

/**
 * Ta Klasa implementuje Algorytm A*, którego celem jest wyznaczenie naszemu agentowi najoptymalniejszej trasy.
 * Wykorzystaną w tej implementacji jest heurystyka polegająca na wyznaczaniu kosztu resztkowego na zasadzie odleglosci w lini prostej.
 * Rówinież w celach statystycznych zaimplementowałem publikowania rozważań oraz wyborów algorytmu w logu. Dzięki temu,
 * możemy szczegółowo zobaczyć jakie węzły są rozwijane.
 * 
 *  
 * */

class Astar {

	/**
	 * FRONTIER jest to priotytetowa kolejka
	 * CHILD jest to węzeł, który jest usuwany z listy listaDzieci rodzica.
	 * z kolei CHILDLIST jest listą węzłów powiązanych z rodzicem
	 * ostatecznie ENDTOUR jest ścieżką z ostatnio odwiedzonego węzła do naszego punktu końcowego, a w naszym wypadku
	 * jest to również punkt startowy (problem "Komiwojażera")
	 * 
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
	Double gcost, hcost;
	

	
	
	
	
	/**
	 * HEURISTICCOST bierze jako parametr w formacie String wartość naszego dziecka (CHILD) i
	 * wylicza odległość do celowego węzła
	 *
	 * Sam parametr CHILD jest zmienną typu String która reprezentuje nazwe węzła.
	 * funkcja HEURISTICSEARCH zwraca nam ostatecznie odległość w lini prostej CHILD ( dziecka)
	 * od węzła który jest naszym celem
	 */
	private double heuristicCost(String child) {
		
		double h = 0.0;
		for (Node c : tsp.graph.get(tsp.initialState)) {
			if (c.name.equals(child)) {
				h = c.sld;
				break;
			}
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
					
					hcost = heuristicCost(child.name);

					/**
					 * teraz zbieramy listę wszystkich przodków i wstawiamy je do
					 * dziecka (CHILD)
					 */

					childData = new NodeInfo(child.name, node.path, gcost,
							hcost);
					childData.path.add(node.nodeName);
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

		node = new NodeInfo(tsp.initialState); 

		frontier.add(node);

		while (true) {


			if (frontier.peek() == null) {
				return;
			}

			/**
			 * Zdejmujemy przód kolejki FRONTIER
			 */
			node = frontier.remove();

			if (node.path.size() == (tsp.numNodes - 1)) {

				/**
				 * Dodajemy węzeł do logu w celach statytstycznych
				 */
				LOWriter.log(node);

				childList = tsp.graph.get(node.nodeName);
				addChildren(childList, true);
				/**
				 * Wypisujemy optymalną ścieżkę do logu oraz nadpisujemy nasze pliki
				 * wyjściowe.
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