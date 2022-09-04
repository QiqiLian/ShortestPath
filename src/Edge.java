/**
 *This class implements comparable Comparable to compare the edges of the graph
 */
public class Edge implements Comparable {
    private Node A;
    private Node B;
    private int cost;

    /**
     * @param a This is the starting point
     * @param b This is the final destination of the chosen point
     * @param c This is the weight of each vertices
     */
    public Edge (Node a, Node b, int c){
        this.A = a;
        this.B = b;
        this.cost = c;


    }


    /**
     * @param otherEdge
     * @return This compare method returns -1
     */
    public int compareTo(Object otherEdge){


        return -1;

    }


    /** This method gets the starting point
     * @return
     */
    public Node getA() {
        return A;
    }

    /** This method gets the final destination
     * @return
     */
    public Node getB() {
        return B;
    }

    /**
     * @return This method gets the weight of the vertices
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param a This method sets the starting point
     */
    public void setA(Node a) {
        A = a;
    }

    /**
     * @param b This method sets the ending point
     */
    public void setB(Node b) {
        B = b;
    }

    /**
     * @param cost This method sets the weight of each vertices
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * @return This method return the string of each parameters of the graph
     */
    @Override
    public String
    toString() {
        return "Edge{" +
                 A.getCity() + B.getCity()+
                ", cost=" + cost +
                '}';
    }

}
