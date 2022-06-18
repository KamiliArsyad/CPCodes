import java.util.Scanner;
import java.util.HashSet;

public class CF799F {
  public static boolean soln(int[] input) {
    int size = input.length;
    for (int i = 0; i < size - 2; i++) {
      HashSet<Integer> s = new HashSet<Integer>();

      int curr_sum = remainder(input[i]);
      
      for (int j = i + 1; j < size; j++) {
        int temp = curr_sum - (input[j] % 10);
        if (s.contains(temp < 0 ? temp + 10 : temp)) {
            return true;
        }
        s.add(input[j] % 10);
      }
    }
  return false;
  }

  public static int remainder(int number) {
    int result = (3 - number) % 10;
    return result < 0 ? result + 10 : result;
  }
  
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    boolean[] results = new boolean[n];
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
      System.out.println(results[i] ? "YES" : "NO");
    };
  }  
}