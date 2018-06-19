package discrete.math;

import java.util.ArrayList;


public class BellmanFordAlgorithm {

    private static final int infinity = 9999;
    private static int distance[], predecessor[];
    private static int edges[][];
    private static ArrayList<Edge> edgesArray;

    public void setEdges(int[][] matrix){
        edges = matrix;
        distance = new int[matrix.length];
        predecessor = new int[matrix.length];
        edgesArray = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                if (edges[i][j] > 0 && edges[i][j] < infinity) {
                    edgesArray.add(new Edge(i,j,edges[i][j]));
                }
            }
        }
    }

    public void execute() {

        for (int i = 0; i < edges.length; i++) {
            distance[i] = infinity;
            predecessor[i] = -1;
        }

        distance[0] = 0;

        for (int g = 1; g <= edges.length - 1; g++){
            for (int h = 0; h < edgesArray.size(); h++){
                int u = edgesArray.get(h).getSource();
                int v = edgesArray.get(h).getDestination();
                int weight = edgesArray.get(h).getDistance();
                if (distance[u] != infinity && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    predecessor[v] = u;
                }
            }
        }
    }
}
