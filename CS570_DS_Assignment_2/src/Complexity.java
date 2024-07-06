/* Name - Yash Deshpande
    cwid - 20025089
    Assignment No 2 - Complexity
 */

import static java.lang.Math.*;
public class Complexity {

    /* Time Complexity O(n^2)    */
    public static void method1(int n) {
        if (n < 0) {
            System.out.println("Invalid value to measure time complexity");
        } else {
            int i, j;
            int counter = 1;

            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    System.out.println("Counter of Operation Method 1 - " + counter);
                    counter = counter + 1;

                }
            }
            System.out.println("Final count of counter = "+(counter-1));
            System.out.println("\n");
        }
    }
    /* Time Complexity O(n^3)    */
    public static void method2(int n) {
        if (n < 0) {
            System.out.println("Invalid value to measure time complexity");
        } else {
            int i, j, k;
            int counter = 1;

            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    for (k = 0; k < n; k++) {
                        System.out.println("Counter of Operation Method 2 -" + counter);
                        counter += 1;

                    }
                }
            }
            System.out.println("Final count of counter = "+(counter-1));
            System.out.println("\n");
        }
    }
    /* Time Complexity O(log n)    */
    public static void method3(int n) {
        if (n < 0) {
            System.out.println("Invalid value to measure time complexity");
        } else {
            int i;
            int counter = 1;
            for (i = 1; i <= n; i = i * 2) {
                System.out.println("Counter of Operation Method 3- " + counter);
                counter += 1;
            }
            System.out.println("Final count of counter = "+(counter-1));
            System.out.println("\n");
        }
    }
    /* Time Complexity O(n (log n))    */
    public static void method4(int n) {
        if (n < 0) {
            System.out.println("Invalid value to measure time complexity");
        }
        else {
            int i, j;
            int counter = 1;

            for (i = 1; i <= n; i++) {
                for (j = 1; j <= n; j = j * 2) {
                    System.out.println("Counter of Operation Method 4- " + counter);
                    counter += 1;
                }
            }
            System.out.println("Final count of counter = "+(counter-1));
            System.out.println("\n");
        }
    }
    /* Time Complexity O(log(log(n))    */
    public static void method5(int n) {
        if (n < 0) {
            System.out.println("Invalid value to measure time complexity");
        } else {
            int k;
            int counter = 1;
            for (k = n; k > 2; k = (int) Math.sqrt(k)) {
                System.out.println("Counter of Operation Method 5- " + counter);
                counter += 1;

            }
            System.out.println("Final count of counter = "+(counter-1));
            System.out.println("\n");
        }
    }

}

