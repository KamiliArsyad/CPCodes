import java.util.Scanner;

public class CF799A {
  public static int soln(int t, int b, int c, int d) {
    int count = 0;
    count = t < b ? count + 1 : count;
    count = t < c ? count + 1 : count;;
    count = t < d ? count + 1 : count;
    
    return count;
  }
  
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    int[] results = new int[n];
    int count = 0;
    while (n-- > 0) {
      int a = s.nextInt();
      int b = s.nextInt();
      int c = s.nextInt();
      int d = s.nextInt();

      results[count] = soln(a, b, c, d);
      count = count + 1;
    }
    s.close();
    for (int i = 0; i < results.length; i++) {
      System.out.println(results[i]);
    };
  }

}
