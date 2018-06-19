package  discrete.math;

import java.util.Stack;

class Matrix {

    private final int MAX = 10;
    private final int NIESKONCZONOSC = 99;

    private int[][] macierz;
    private boolean[] odwiedzone;
    private int krawedzie, wierzcholki;


    Matrix(int n, int m) {
        krawedzie = m;
        wierzcholki = n;
        macierz = new int[wierzcholki][wierzcholki];
        odwiedzone = new boolean[wierzcholki];
        for (int i = 0; i < wierzcholki; i++) {
            odwiedzone[i] = false;
        }
        wypelnij_zerami();
    }

    private void wypelnij_zerami() {
        for (int i = 0; i < wierzcholki; i++)
            for (int j = 0; j < wierzcholki; j++)
                macierz[i][j] = 0;
    }

    void wypelnij_macierz(int n, int m) {
        if (m >= n - 1 && m < n * n - 1) {
            krawedzie = m;
            wierzcholki = n;
            wypelnij_zerami();

            for (int j = 0; j < krawedzie; j++) {
                n = (int) (Math.random() * wierzcholki);
                m = (int) (Math.random() * wierzcholki);
                if (n != m && macierz[n][m] == 0)
                    macierz[n][m] = (1 + (int) (Math.random() * MAX) * (int) Math.pow(-1.0d, Math.random()));
                else j--;
            }

        }
    }

    int fordfulkerson_wszerz(int start, int koniec) {
        System.out.println("Prezentowanie wynikow Forda-Fulkersona metoda przeszukiwania w szerz");
        int maks_przeplyw = 0;
        int minimalny = NIESKONCZONOSC;
        Matrix pomocnicza = new Matrix(wierzcholki, krawedzie);
        for (int i = 0; i < wierzcholki; i++) //tworzenie pomocniczej, z inf w miejscach zer
            for (int j = 0; j < wierzcholki; j++) {
                if (macierz[i][j] == 0 && i != j)
                    pomocnicza.macierz[i][j] = NIESKONCZONOSC;
                else
                    pomocnicza.macierz[i][j] = macierz[i][j];
            }

        Stack<Integer> kolejka = new Stack<>();
        Stack<Integer> droga = new Stack<>();
        Stack<Integer> tmp = new Stack<>();
        int poprzedni;

        boolean znalazlodroge;

        do {
            for (int i = 0; i < wierzcholki; i++) {
                odwiedzone[i] = false;
            }
            minimalny = NIESKONCZONOSC;
            while (!kolejka.empty())
                kolejka.pop();

            kolejka.push(start);
            odwiedzone[start] = true;

            int sasiad;
            znalazlodroge = false;
            while (!kolejka.empty()) {

                sasiad = kolejka.peek();
                droga.push(sasiad);
                kolejka.pop();
                if (sasiad == koniec) { //jezeli wierzcholek jest koncowym
                    while (!droga.empty()) { //przeliczanie najmniejszej drogi
                        poprzedni = droga.peek();
                        tmp.push(poprzedni);
                        droga.pop();
                        if (!droga.empty()) {
                            if (pomocnicza.macierz[droga.peek()][poprzedni] < minimalny)
                                minimalny = pomocnicza.macierz[droga.peek()][poprzedni];
                            while (pomocnicza.macierz[droga.peek()][poprzedni] == NIESKONCZONOSC) { //pozbycie sie bledu kiedy
                                droga.pop();  // miedzy dwoma wierzcholkami nie ma polaczenia
                                if (pomocnicza.macierz[droga.peek()][poprzedni] < minimalny)
                                    minimalny = pomocnicza.macierz[droga.peek()][poprzedni];
                            }
                        }
                    }


                    while (!tmp.empty()) { //zmniejszanie przeplywu
                        poprzedni = tmp.peek();
                        tmp.pop();
                        if (!tmp.empty()) {
                            pomocnicza.macierz[poprzedni][tmp.peek()] -= minimalny;
                        }

                    }

                    maks_przeplyw += minimalny;

                    for (int i = 0; i < wierzcholki; i++) // inf w miejscach zer
                        for (int j = 0; j < wierzcholki; j++) {
                            if (pomocnicza.macierz[i][j] == 0 && i != j)
                                pomocnicza.macierz[i][j] = NIESKONCZONOSC;
                        }

                    znalazlodroge = true;
                    break;
                } else { //jezeli znaleziony wierzcholek nie jest koncowym
                    for (int i = 0; i < wierzcholki; i++) {
                        if (!odwiedzone[i] && pomocnicza.macierz[sasiad][i] != 0 && pomocnicza.macierz[sasiad][i] != NIESKONCZONOSC) {
                            odwiedzone[i] = true;
                            kolejka.push(i);
                        }
                    }
                }
            }

        } while (znalazlodroge);

        System.out.println("Maksymalny przeplyw z " + start + " do " + koniec + " wynosi " + maks_przeplyw);
        return maks_przeplyw;
    }

    int fordfulkerson_wglab(int start, int koniec) {
        System.out.println("Prezentowanie wynikow Forda-Fulkersona metoda przeszukiwania w glab");
        int maks_przeplyw = 0;
        int minimalny = NIESKONCZONOSC;
        Matrix pomocnicza = new Matrix(wierzcholki, krawedzie);
        for (int i = 0; i < wierzcholki; i++) //tworzenie pomocniczej, z inf w miejscach zer
            for (int j = 0; j < wierzcholki; j++) {
                if (macierz[i][j] == 0 && i != j)
                    pomocnicza.macierz[i][j] = NIESKONCZONOSC;
                else
                    pomocnicza.macierz[i][j] = macierz[i][j];
            }

        Stack<Integer> stos = new Stack<>();
        Stack<Integer> droga = new Stack<>();
        Stack<Integer> tmp = new Stack<>();
        int poprzedni;

        boolean znalazlodroge;


        do {
            for (int i = 0; i < wierzcholki; i++) {
                odwiedzone[i] = false;
            }
            minimalny = NIESKONCZONOSC;
            while (!stos.empty())
                stos.pop();

            stos.push(start);
            odwiedzone[start] = true;

            int sasiad;
            znalazlodroge = false;
            while (!stos.empty()) {

                sasiad = stos.peek();
                droga.push(sasiad);

                stos.pop();
                if (sasiad == koniec) { //jezeli wierzcholek jest koncowym

                    while (!droga.empty()) { //przeliczanie najmniejszej drogi

                        poprzedni = droga.peek();
                        tmp.push(poprzedni);
                        droga.pop();
                        if (!droga.empty()) {

                            if (pomocnicza.macierz[droga.peek()][poprzedni] < minimalny)
                                minimalny = pomocnicza.macierz[droga.peek()][poprzedni];
                            while (pomocnicza.macierz[droga.peek()][poprzedni] == NIESKONCZONOSC) { //pozbycie sie bledu kiedy
                                droga.pop();  // miedzy dwoma wierzcholkami nie ma polaczenia
                                if (pomocnicza.macierz[droga.peek()][poprzedni] < minimalny)
                                    minimalny = pomocnicza.macierz[droga.peek()][poprzedni];
                            }
                        }
                    }

                    while (!tmp.empty()) { //zmniejszanie przeplywu
                        poprzedni = tmp.peek();
                        tmp.pop();
                        if (!tmp.empty()) {
                            pomocnicza.macierz[poprzedni][tmp.peek()] -= minimalny;
                        }

                    }

                    maks_przeplyw += minimalny;

                    for (int i = 0; i < wierzcholki; i++) // inf w miejscach zer
                        for (int j = 0; j < wierzcholki; j++) {
                            if (pomocnicza.macierz[i][j] == 0 && i != j)
                                pomocnicza.macierz[i][j] = NIESKONCZONOSC;
                        }

                    znalazlodroge = true;
                    break;
                } else { //jezeli znaleziony wierzcholek nie jest koncowym
                    for (int i = wierzcholki - 1; i >= 0; i--) {
                        if (!odwiedzone[i] && pomocnicza.macierz[sasiad][i] != 0 && pomocnicza.macierz[sasiad][i] != NIESKONCZONOSC) {
                            odwiedzone[i] = true;
                            stos.push(i);
                        }
                    }
                }
            }

        } while (znalazlodroge);

        System.out.println("Maksymalny przeplyw z " + start + " do " + koniec + " wynosi " + maks_przeplyw);
        return maks_przeplyw;
    }
}
