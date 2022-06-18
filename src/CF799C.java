import java.util.Scanner;

public class CF799C {
  public static int[] soln(int[] input, String[] in) {
    int locate = 0;

    for (int i = 1; i < input.length - 1; i++) {
      if (input[i - 1] == 2 && input[i] == 1 && input[i + 1] == 2) {
        locate = i;
      }  
    }
    
    return new int[]{locate + 1, findOne(in[locate]) + 1};
  }

  public static int findOne(String in) {
    for (int i = 0; i < in.length(); i++) {
      if (in.charAt(i) == '#') return i;
    }

    return -1;
  }

  public static int count(String in) {
    int count = 0;
    for (int i = 0; i < in.length(); i++) {
      if (in.charAt(i) == '#') count++;
    }

    return count;
  }
  
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    int[][] results = new int[n][2];
    int count = 0;
    s.nextLine();
    while (n-- > 0) {
      String[] in = new String[8];
      int[] input = new int[8];
      s.nextLine();
      
      for (int i = 0; i < 8; i++) {
        in[i] = s.nextLine();
        input[i] = count(in[i]);
      }
      
      results[count] = soln(input, in);
      
      count = count + 1;
    }
    s.close();
    for (int i = 0; i < results.length; i++) {
      System.out.println(results[i][0] + " " + results[i][1]);
    };
  }
}
