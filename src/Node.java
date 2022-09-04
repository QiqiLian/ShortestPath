import java.util.HashSet;
import java.util.LinkedList;

/**
 * This is the Node class for the graph
 */
public class Node {
    String city;
    private HashSet<Edge> edges;
    private boolean visited;
    private LinkedList<String> path;
    private int distance = Integer.MIN_VALUE;
    // The minimum edge that adds this node to MST
    private Edge minimumEdge;


    /**
     * @param city This sets the paramaters for each node
     */
    public Node(String city){
        this.city = city;
        this.edges = new HashSet<>();
        this.visited = false;
        this.path = new LinkedList<>();
        this.minimumEdge = null;
    }


    public Edge getMinimumEdge() {
        return minimumEdge;
    }

    public void setMinimumEdge(Edge minimumEdge) {
        this.minimumEdge = minimumEdge;
    }

    /**
     * @return This gets the edges of the nodes
     */
    public HashSet<Edge> getEdges() {
        return edges;
    }

    /**
     * @return This gets the path for the node
     */
    public LinkedList<String> getPath() {
        return path;
    }

    /**
     * @return This path gets the distance inbetween the nodes
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @return This gets the city of the graph
     */
    public String getCity() {
        return city;
    }

    /**
     * @return This returns if the place is visited or not
     */
    public boolean getVisited (){
        return visited;
    }

    /**
     * @param edges This sets the edges of the graph
     */
    public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }

    /**
     * @param city This sets the city of the graph
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @param distance This sets the distance between the nodes
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @param path This sets the path between the nodes
     */
    public void setPath(LinkedList<String> path) {
        this.path = path;
    }

    /**
     * @param visited This sets wether the node is visited or not
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
