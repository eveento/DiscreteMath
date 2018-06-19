package discrete.math;

import java.io.PrintWriter;

public class Main {

    public static final int MAX_N = 2001;
    public static final int MIN_N = 100;
    public static final int STEP = 100;

    public static void main(String[] args) {

        System.out.println("START");

        try (
                PrintWriter out = new PrintWriter("./results/times" + MIN_N + "-" + MAX_N + ".csv");
                PrintWriter out1 = new PrintWriter("./results/times" + MIN_N + "-" + MAX_N + "-Bellman");
                PrintWriter out2 = new PrintWriter("./results/times" + MIN_N + "-" + MAX_N + "-Dijkstra");
        ) {
//            out.println("n;bellmanFord;dijkstra");

            for (int n = MIN_N; n < MAX_N; n += STEP) {

                String data = "\rProgress: " + (int) (((float) n / MAX_N) * 100) + "%";
                System.out.write(data.getBytes());

                int[][] matrix = generateMatrix(n);

                /** Bellman-Ford **/
                BellmanFordAlgorithm bellmanFordAlgorithm2 = new BellmanFordAlgorithm();
                bellmanFordAlgorithm2.setEdges(matrix);
                long startBF2 = System.nanoTime();
                bellmanFordAlgorithm2.execute();
                long stopBF2 = System.nanoTime();

                /** Dijkstra **/
                DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
                dijkstraAlgorithm.setEdges(matrix);
                long startD2 = System.nanoTime();
                dijkstraAlgorithm.execute();
                long stopD2 = System.nanoTime();

                /** Save time results **/
                out.println(n + ";" + (float) (stopBF2 - startBF2) / 1000000 + ";" + (float) (stopD2 - startD2) / 1000000);
                out1.println((float) (stopBF2 - startBF2) / 1000000);
                out2.println((float) (stopD2 - startD2) / 1000000);

            }

            String data = "\rProgress: 100%";
            System.out.write(data.getBytes());
            System.out.println("\nFINISH");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[][] generateMatrix(int n) {

        int matrix[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * 300);
            }
            matrix[i][i] = 0;
        }
        return matrix;
    }
}
