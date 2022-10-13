import java.util.Scanner;

public class CF799E {
  public static int soln(int[] input, int target) {
    return 0;
  }

  public static int countOne(int[] input) {
    int count = 0;

    for (int i = 0; i < input.length; i++) {
      count = input[i] == 1 ? count + 1 : count;
    }

    return count;
  }

  public static void main(String[] args) {
    
  }  
}
