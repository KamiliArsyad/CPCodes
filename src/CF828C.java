import java.util.Scanner;

public class CF828C {

    // Return the max distance from any light start to any green light
    public static int soln(char start, char[] lights) {
        int max = 0;
        int distance = 0;

        for (int i = 0; i < lights.length * 2; i++) {
            if (lights[i % lights.length] == 'g') {
                if (distance > max) {
                    max = distance;
                }
                distance = 0;
            } else if (lights[i % lights.length] == start || distance != 0) {
                distance++;
            }
        }
        System.out.println(max);
        return max;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] soln = new int[n];
        int count = 0;
        while (count < n) {
            int size = in.nextInt();
            // Read the next character
            char c = in.next().charAt(0);
            char[] a;
            String s;

            // Scan the input string s
            s = in.next();
            // Convert the string to a char array
            a = s.toCharArray();
            soln(c, a);

            count++;
        }
    }
}


