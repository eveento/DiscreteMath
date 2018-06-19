package Projekt1;

import java.util.Arrays;

public class Project1 {

    public static void main(String[] args) {

        boolean check = false;

        double num1 = 2.4;
        double num2 = -2.7;


        System.out.println("Numbers:");
        System.out.println(num1);
        System.out.println(num2);
        System.out.println("Floor:");
        System.out.println(Double.toString(floor(num1)));
        System.out.println(Double.toString(floor(num2)));
        System.out.println("Ceiling:");
        System.out.println(Double.toString(ceiling(num1)));
        System.out.println(Double.toString(ceiling(num2)));
        System.out.println("Fractional part v1:");
        System.out.println(Double.toString(frac(num1)));
        System.out.println(Double.toString(frac(num2)));
        System.out.println("Fractional part v2:");
        System.out.println(Double.toString(frac2(num1)));
        System.out.println(Double.toString(frac2(num2)));
        System.out.println("Modulo:");
        System.out.println(Double.toString(modulo(num1, num2)));
        System.out.println(Double.toString(modulo(num2, num1)));

        Eratosthenes(10);


       // int[] s = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] s = {1, 2, 3};
        System.out.println("Given sequence is permutation: " + isPermutation(s));

        if(isPermutation(s)==true)
            permutation(new int[s.length], 0, s);
    }

    private static double floor(double number) {

        int x = 0;
        if (number > 0) {
            while (x < number) {
                x++;
            }
            x--;
            return (number - (number - x));
        } else {
            while (x > number) {
                x--;
            }
            x++;
            return (number - (number - x)) - 1;
        }
    }

    private static double ceiling(double number) {

        int x = 0;
        if (number > 0) {
            while (x < number) {
                x++;
            }
            x--;
            return (number - (number - x)) + 1;
        } else {
            while (x > number) {
                x--;
            }
            x++;
            return (number - (number - x));
        }
    }

    private static double frac(double number) {

        if (number > 0)
            return number - floor(number);
        else
            return number + floor(-number);
    }

    private static double frac2(double number) {

        return modulo(number, 1);
    }

    private static double modulo(double a, double b) {

        if (b < 0)
            b = -b;
        if (a < 0) {
            a = -a;
            return -(a - floor(a / b) * b);
        } else
            return a - floor(a / b) * b;
    }


    private static void Eratosthenes(int n) {

        boolean[] isPrimeArray = new boolean[n];
        Arrays.fill(isPrimeArray, true);

        for (int factor = 2; factor * factor < n; factor++) {
            for (int j = factor; factor * j < n; j++) {
                isPrimeArray[factor * j] = false;
            }
        }
        for (int i = 2; i < n; i++) {
            if (isPrimeArray[i])
                System.out.println(i);
        }

    }

    private static boolean isPermutation(int[] numbers) {

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i; j < numbers.length - 1; j++) {
                if (numbers[i] == numbers[j + 1])
                    return false;
            }
        }
        return true;
    }

    private static long counter = 0;

    private static void permutation(int[] perm, int pos, int[] array) { 
        if (pos == perm.length) {
            if (isPermutation(perm)) {
                counter++;
                System.out.println(Arrays.toString(perm));
            }
        } else {
            for (int i = 0; i < array.length; i++) {
                perm[pos] = array[i];
                permutation(perm, pos + 1, array);
            }
        }
    }
}
