package discrete.math;

public class DijkstraAlgorithm {

    private static final int infinity = 9999;

    private static int distance[], predecessor[];
    private static int edges[][];
    private static boolean visited[];

    // Dijkstra's Algorithm
    //int[] distance2 = new int[edges.length];
    //boolean[] visited = new boolean[edges.length];
    //int infinity2 = 10000; // Infinity equivalent.

    public void setEdges(int[][] matrix){
        edges = matrix;
        distance = new int[matrix.length];
        predecessor = new int[matrix.length];
        visited = new boolean[matrix.length];
    }

    public int execute()
    {

        for (int i = 0; i < edges.length; i++)
        {
            distance[i] = infinity;
            predecessor[i] = -1;
            visited[i] = false;
        }

        distance[0] = 0; // Changed the 0 to variable start.

        for(int i = 0; i < edges.length; i++)
        {
            int min = infinity;
            int currentNode = 0;
            for (int j = 0; j < edges.length; j++)
            {
                if (!visited[j] && distance[j] < min)
                {
                    currentNode = j;
                    min = distance[j];
                }
            }
            visited[currentNode] = true;
            for (int j = 0; j < edges.length; j++)
            {
                if (edges[currentNode][j] < infinity && distance[currentNode] +   edges[currentNode][j] < distance[j])
                {
                    distance[j] = distance[currentNode] + edges[currentNode][j];
                    predecessor[j] = currentNode;
                }
            }
        }
        return distance[edges.length - 2];
    }
}
