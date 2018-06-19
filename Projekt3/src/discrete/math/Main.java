package discrete.math;

import java.io.PrintWriter;

public class Main {

    private final static int WIERZCHOLEK = 0;

    public static void main(String[] args) {

        int n, m, min, max;
        long start, stop;

        int testStart = 100;
        int testStop = 3000;
        int testStep = 100;
        int[] V = new int[testStop / testStep];

        for (int i = testStart; i < testStop + 1; i += testStep) {
            V[(i / testStep) - 1] = i;
        }

        int[] E = {25, 50, 75};
        double[][] czasy_przeszukiwania_wszerz = new double[V.length][E.length];
        double[][] czasy_przeszukiwania_wglab = new double[V.length][E.length];
        wyzeruj(czasy_przeszukiwania_wszerz);
        wyzeruj(czasy_przeszukiwania_wglab);
        for (int i = 0; i < V.length; i++) {
            for (int j = 0; j < E.length; j++) {

                n = V[i];
                min = V[i] - 1;
                max = (V[i] - 1) * (V[i] / 2);
                m = (max - min) * E[j] / 100 + min;
                Matrix macierz = new Matrix(n, m);
                macierz.wypelnij_macierz(n, m);

                start = System.nanoTime();
                macierz.fordfulkerson_wszerz(WIERZCHOLEK, n - 1);
                stop = System.nanoTime();
                czasy_przeszukiwania_wszerz[i][j] += (double) (stop - start) / 1000000;

                start = System.nanoTime();
                macierz.fordfulkerson_wglab(WIERZCHOLEK, n - 1);
                stop = System.nanoTime();
                czasy_przeszukiwania_wglab[i][j] += (double) (stop - start) / 1000000;

            }
        }

        try (
                PrintWriter out = new PrintWriter("./results/times.csv");
                PrintWriter out25 = new PrintWriter("./results/times-25");
                PrintWriter out50 = new PrintWriter("./results/times-50");
                PrintWriter out75 = new PrintWriter("./results/times-75");
        ) {
            out.println("wglab");
            out25.println("wglab");
            out50.println("wglab");
            out75.println("wglab");
            out.println("n;25%;50%;75%");
            for (int i = 0; i < V.length; i++) {
                out.print(V[i] + ";");
                for (int j = 0; j < E.length; j++) {
                    out.print(czasy_przeszukiwania_wglab[i][j] + ";");
                }
                out25.print(czasy_przeszukiwania_wglab[i][0]);
                out50.print(czasy_przeszukiwania_wglab[i][1]);
                out75.print(czasy_przeszukiwania_wglab[i][2]);
                out.println();
                out25.println();
                out50.println();
                out75.println();
            }
            out.println("wszerz");
            out25.println("wszerz");
            out50.println("wszerz");
            out75.println("wszerz");
            out.println("n;25%;50%;75%");
            for (int i = 0; i < V.length; i++) {
                out.print(V[i] + ";");
                for (int j = 0; j < E.length; j++) {
                    out.print(czasy_przeszukiwania_wszerz[i][j] + ";");
                }
                out25.print(czasy_przeszukiwania_wszerz[i][0]);
                out50.print(czasy_przeszukiwania_wszerz[i][1]);
                out75.print(czasy_przeszukiwania_wszerz[i][2]);
                out.println();
                out25.println();
                out50.println();
                out75.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void wyzeruj(double[][] czasy) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                czasy[i][j] = 0;
            }
        }
    }
}
