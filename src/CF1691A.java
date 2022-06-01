import java.util.Arrays;
import java.util.Scanner;

public class CF1691A {
    public static int solnB(int[] in) {
      int countEven = 0;
      int countOdd = 0;

      for (int i = 0; i < in.length; i++) {
        if (isEven(in[i])) countEven++;
        else countOdd++;        
      }

      return Math.min(countEven, countOdd);
    }

    public static boolean isEven(int i) {return i % 2 == 0;}

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int count = 0;
        int[] results = new int[n];
        while (n-- > 0) {
            int size = s.nextInt();
            int[] input = new int[size];

            for (int i = 0; i < size; i++) {
                input[i] = s.nextInt();
            }
            results[count] = solnB(input);
            count = count + 1;
        }
        s.close();
        Arrays.stream(results).forEach(System.out::println);
    }
}
