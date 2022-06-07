import java.util.Scanner;

public class CF797A {
  public static int soln(int blocks) {
    int mid;
    int left;
    int right;

    int begin = 0;
    int end = blocks;

    while (begin < end) {
      mid = (begin + end) / 2;
      left = mid - 1;
      right = blocks - mid - left;
      if (possible(left, mid, right)) {
        end = mid;
      } else if (possible(begin - 2, begin, blocks - 2*begin + 2)) {
        end = mid;
      } else begin = mid + 1;
    }
    
    return begin;
  }

  public static boolean possible(int left, int mid, int right) {
    return (right > 0 && left > 0 && mid > 0) && mid > left && left > right;
  }

  public static void printer(int sol, int in) {
    int left = sol - 1;
    int right = in - sol - left;
    System.out.println(
      possible(left, sol, right)
        ? left + " " + sol + " " + right
        : (left - 1) + " " + sol + " " + (right + 1));
  }


  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    int[] inputs = new int[n];
    int[] results = new int[n];
    
    for (int i = 0; i < n; i++) {
      inputs[i] = s.nextInt();
      results[i] = soln(inputs[i]);
    } 
    s.close();

    for (int i = 0; i < n; i++) {
      printer(results[i], inputs[i]);
    }
  }
}
