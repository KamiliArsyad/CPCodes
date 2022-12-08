import java.util.Scanner;

public class CF828A {

    public static int soln(int[] a, String s) {
        char[] rep = s.toCharArray();
        char[] indices = new char[51];

        for (int i = 0; i < rep.length; i++) {
            // Check if indices a[i] is empty, if it is, add rep[i] to it
            if (indices[a[i]] == 0) {
                indices[a[i]] = rep[i];
            } else {
                // If it is not empty, check if it is the same as rep[i]
                if (indices[a[i]] != rep[i]) {
                    // If it is not the same, return 0
                    return 0;
                }
            }
        }

        return 1;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] soln = new int[n];
        int count = 0;
        while (count < n) {
            int size = in.nextInt();
            int[] a = new int[size];
            String s;

            // Scan the input array a
            for (int i = 0; i < size; i++) {
                a[i] = in.nextInt();
            }

            // Scan the input string s
            s = in.next();
            soln[count] = soln(a, s);

            count++;
        }

        for (int i = 0; i < soln.length; i++) {
            System.out.println(soln[i] == 1 ? "YES" : "NO");
        }
    }
}
