import big.data.DataSource;

import java.io.Reader;
import java.util.*;
public class RoadCalculator {
    private static HashMap<String, Node> graph;
    private static LinkedList<Edge> mst = new LinkedList<>();
    Reader fileReader = null;

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);
        System.out.println("Please enter graph URL: ");
        String a = stdin.nextLine();
        graph = buildGraph(a);

        System.out.println("MST:");
        buildMST(graph).forEach(System.out::println);
        System.out.println("Enter a starting point for shortest path or Q to quit: ");
        String x = stdin.nextLine();
        boolean check = true;
        while (!x.equals("Q")){
        System.out.println("Please enter the destination: ");
        String y = stdin.nextLine();
        System.out.println(Djikstra(graph, x, y));
        if(x.equals("Q")) {
            System.exit(0);
        }

        }



    }

    /**
     * @param a This takes in the url link
     * @return This returns the completed map
     */
    public static HashMap<String, Node> buildGraph(String a) {
        HashMap<String, Node> graph = new HashMap<>();

        DataSource ds = DataSource.connectXML(a).load();
        String cityNamesStr = ds.fetchString("cities");
        String[] cityNames = cityNamesStr.substring(1, cityNamesStr.length() - 1).replace("\"", "").split(",");
        String roadNamesStr = ds.fetchString("roads");
        String[] roadNames = roadNamesStr.substring(1, roadNamesStr.length() - 1).split("\",\"");

        roadNames[0] = roadNames[0].substring(1);
        roadNames[roadNames.length - 1] = roadNames[roadNames.length - 1].substring(0, roadNames[roadNames.length - 1].length() - 1);

        for (int i = 0; i < cityNames.length; i++) {
            graph.put(cityNames[i], new Node(cityNames[i]));
        }
        for (String roadName : roadNames) {
            String[] roadNameSplit = roadName.split(",", 3);

            Node A = graph.get(roadNameSplit[0]);
            Node B = graph.get(roadNameSplit[1]);
            int distance = Integer.parseInt(roadNameSplit[2]);

            Edge startToEnd = new Edge(A, B, distance);
            Edge endToStart = new Edge(B, A, distance);

            A.getEdges().add(startToEnd);
            B.getEdges().add(endToStart);
        }

        System.out.println("Cities: ");
        for (int c = 0; c < cityNames.length; c++) {
            System.out.println((Arrays.toString(new String[]{cityNames[c]})));
        }


        System.out.println("Roads: ");
        for (int i = 0; i < roadNames.length; i++) {
            System.out.println(Arrays.toString(new String[]{roadNames[i]}));
        }
        System.out.println("System.out.println(Enter a starting point for shortest path or Q to quit: ");




        return graph;
    }


    /**
     * @param graph  This takes in the HashMap
     * @return This returns the minimum spanning tree
     */
    public static LinkedList<Edge> buildMST(HashMap<String, Node> graph) {
        LinkedList<Edge> mst = new LinkedList<>();

        for (Node newNode : graph.values()) {
            newNode.setDistance(Integer.MAX_VALUE);
            newNode.setVisited(false);
        }
        Map.Entry<String, Node> entry = graph.entrySet().iterator().next();
        Node value = entry.getValue();
        value.setDistance(0);

        for(int i = 0; i < graph.size(); i++) {
            Node iterator = null;
            int smallest = Integer.MAX_VALUE;

            for (Node vertex : graph.values()) {
                if (vertex.getVisited() == false && vertex.getDistance() < smallest) {
                    iterator = vertex;
                    smallest = vertex.getDistance();
                }
            }

            for(Edge e: iterator.getEdges()) {
                if(e.getB().getVisited()) {
                    continue;
                }

                int newDistance = e.getCost();

                if(newDistance < e.getB().getDistance()) {
                    e.getB().setDistance(newDistance);
                    e.getB().setMinimumEdge(e);
                }
            }

            // if we added this node via an edge
            if(iterator.getMinimumEdge() != null) {
                mst.add(iterator.getMinimumEdge());
            }

            iterator.setVisited(true);
        }

        return mst;
    }


    /**
     * @param graph This takes in the HashMap
     * @param source This takes in the starting point from userinput
     * @param dest This takes in the destination from userinput
     * @return This returns the minimum amount of weight to get to the destination
     */
    public static int Djikstra(HashMap<String, Node> graph, String source, String dest) {

        for (Node newNode : graph.values()) {
            newNode.setDistance(Integer.MAX_VALUE);
            newNode.setVisited(false);
        }

        Node startingNode = graph.get(source);
        startingNode.setDistance(0);
        startingNode.setPath(new LinkedList<>(Arrays.asList(startingNode.getCity())));

        for(int i = 0; i < graph.size(); i++) {
            Node iterator = null;
            int smallest = Integer.MAX_VALUE;

            for (Node newNodes : graph.values()) {
                if (newNodes.getVisited() == false && newNodes.getDistance() < smallest) {
                    iterator = newNodes;
                    smallest = newNodes.getDistance();
                }
            }

            for (Edge edges : iterator.getEdges()) {
                Node nextNode = edges.getB();
                int compare = iterator.getDistance() + edges.getCost();
                LinkedList<String> path = (LinkedList<String>) iterator.getPath().clone();
                path.add(nextNode.getCity());

                if (compare < nextNode.getDistance()) {
                    nextNode.setDistance(compare);
                    nextNode.setPath(path);
                }
            }

            iterator.setVisited(true);
        }


        System.out.println("Shortest path:");
        System.out.println(graph.get(dest).getPath());
        return graph.get(dest).getDistance();
    }

    //https://www3.cs.stonybrook.edu/~cse214/hw/hw7-images/hw7.xml
}





