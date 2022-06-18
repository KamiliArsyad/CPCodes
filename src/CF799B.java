import java.util.Scanner;
import java.util.HashSet;

public class CF799B {
  public static int soln(int[] input) {
    HashSet<Integer> track = new HashSet<>();
    int count = 0;

    for (int i = 0; i < input.length; i++) {
      if (track.contains(input[i])) {
        count++;
      } else track.add(input[i]);
    }
   
    return (count % 2) == 0 ? input.length - count : input.length - count - 1;
  }
  
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    int[] results = new int[n];
    int count = 0;
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
    for (int i = 0; i < results.length; i++) {
      System.out.println(results[i]);
    };
  }
}

