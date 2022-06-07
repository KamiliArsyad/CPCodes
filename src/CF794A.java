import java.util.Arrays;
import java.util.Scanner;

public class CF794A {  
  public static String soln(int[] in) {
    // get the total mean
    double mean = 0;
    for (int i = 0; i < in.length; i++) mean += in[i];
    mean = mean / in.length;
    
    for (int i = 0; i < in.length; i++) {
      // a b c d
      if (in[i] == afterRemoved(in.length, mean, in[i])) return "YES";
    }

    return "NO";
  }

  public static double afterRemoved(double length, double mean, double removed) {
    return ((mean * length) - removed) / (length - 1);
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    int count = 0;
    String[] results = new String[n];
    while (n-- > 0) {
      int size = s.nextInt();
      int[] input = new int[size];

      for (int i = 0; i < size; i++) {
          input[i] = s.nextInt();
      }
      results[count] = soln(input);
      count = count + 1;
    }
    s.close();
    Arrays.stream(results).forEach(System.out::println);
  } 
}