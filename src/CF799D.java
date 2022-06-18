
public class CF799D {
  public static int soln(int[] input) {
    return 0;
  }

  public static boolean isPalindrome(int num) {
    int reverse = 0;
    for (int i = num; i > 0; i /= 10) reverse = reverse * 10 + i % 10;
         
    return num == reverse;
  }
  
  
  public static void main(String[] args) {}
}
