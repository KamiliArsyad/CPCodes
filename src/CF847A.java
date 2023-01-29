import java.io.IOException;
import java.util.Scanner;

public class CF847A {
    public static void main(String[] args) throws IOException {
        String pi = "314159265358979323846264338327";
        Scanner r = new Scanner(System.in);
        int n = r.nextInt();

        for (int i = 0; i < n; i++) {
            // Read each string
            String s = r.next();
            // For every element in the string s, check until it is different than the pi on the ith position
            int count = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) != pi.charAt(j)) {
                    break;
                }
                count++;
            }

            System.out.println(count);
        }
    }
}
