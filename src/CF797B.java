import java.util.Scanner;

public class CF797B {
  public static boolean soln(int[] a, int[] b) {
    // find index of max element in b
    int maxB = 0;
    for (int i = 0; i < b.length; i++) {
      maxB = a[i] - b[i] > a[maxB] - b[maxB] ? i : maxB;
    }

    int diff = a[maxB] - b[maxB];

    if (diff < 0) return false;

    // trace a
    boolean result = true;
    for (int i = 0; i < a.length; i++) {
      a[i] = a[i] <= diff ? 0 : a[i] - diff; 
      result = result && a[i] == b[i];
      if (!result) return false;
    }

    return true;
  }

  public static void printer(boolean res) {
    System.out.println(res ? "YES" : "NO");
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    int count = 0;
    Boolean[] results = new Boolean[n];

    while (n-- > 0) {
      int size = s.nextInt();
      int[] inputA = new int[size];
      int[] inputB = new int[size];

      for (int i = 0; i < size; i++) {
        inputA[i] = s.nextInt();
      }

      for (int i = 0; i < size; i++) {
        inputB[i] = s.nextInt();
      }

      results[count] = soln(inputA, inputB);
      count = count + 1;
    }
    s.close();
    
    for(int i = 0; i < results.length; i++) {
      System.out.println(results[i] ? "YES" : "NO");
    }
  }  
}