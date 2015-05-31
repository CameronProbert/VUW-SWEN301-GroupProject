package main.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkStandard {

	// initialise all paths to be cost of infinity:
	// A fringe is initialised

	// Begin at starting node, and check all the paths which go out from the
	// node.
	// As you 'discover' nodes, ensure to record the cost it has taken to reach
	// nodes.
	// if the 'cost' to reach that node is less than the current cost there
	// (initialised
	// all to infinity), then replace it
	// if the 'cost' to reach that node is higher than the current cost then
	// don't replace it.
	// at each point when a node is reached, as well as the cost there, the
	// origin node is also recorded

	// remember that costs are weighted from the START node.

	// at some point, if a node has reached a point where all the possible
	// incoming nodes
	// have been checked, then we can say that the lowest cost of them is the
	// lowest cost path - ie: the best that you can get. It is now 'finished'

	private Location origin;
	private Location destination;
	private Set<Location> locations;

	public DijkStandard(Location origin, Location destination) {
		this.origin = origin;
		this.destination = destination;
	}

	public void initialiseGraph(Set<Location> l) {
		locations = l;
		setInfinity();

		PriorityQueue<Location> standardQueue = new PriorityQueue<Location>();

		origin.setMinDistance(0);
		standardQueue.add(origin);

		while ( !standardQueue.isEmpty()) {

			Location node = standardQueue.poll();


			if (node.getVisited()) {
				continue;
			}

			node.setVisited(true);

			if( node.getPrevious()!=null ) {
				System.out.println("\n" + node.getName() + " from "+node.getPrevious().getName());
			}

			// Visit all outbound routes
			for (Route edge : node.getOutbound()) {
				System.out.println(edge.getOrigin().getName() + " - "+edge.getDestination().getName() );
				Location siblingNode = edge.getDestination();

				if( siblingNode==destination ) {
					System.out.println("FOUND!");
					siblingNode.setPrevious(node);
					break; }

				double weight = edge.getPricePerGramTransport();
				double pathSoFar = node.getMinDistance() + weight;

				if (pathSoFar < siblingNode.getMinDistance()) { // if the path
																// we are
																// checking is
																// better than
																// the existing

					standardQueue.remove(siblingNode);

					siblingNode.setMinDistance(pathSoFar);
					siblingNode.setPrevious(node);

					standardQueue.add(siblingNode);
				}
			}
		}

		printGraph(getShortestPathTo(destination));

	}

	public void setInfinity() {

		for (Location l : locations) {
			l.setMinDistance(Double.POSITIVE_INFINITY);
			l.setVisited(false);
		}

	}

	public void printGraph(List<Location> p) {

		System.out.println("\nFINAL PATH: ");

		for (Location loc : p) {
			System.out.println(loc.toString());
		}
	}

	public static List<Location> getShortestPathTo(Location end) {
		List<Location> path = new ArrayList<Location>();
		for (Location node = end; node != null; node = node.getPrevious())
			path.add(node);
		Collections.reverse(path);
		return path;
	}

}
