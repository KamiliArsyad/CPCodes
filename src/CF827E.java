import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CF827E {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    // Given an array qn and an array a, find the number of steps achievable for each qn
    public static void stepsAchievable(int[] qn, int[] a) {
        long[] sum = new long[a.length];
        int[] solnIndex = new int[qn.length];
        int j = 0;

        // Fill sum with the sum of its previous elements
        sum[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            sum[i] = sum[i - 1] + a[i];
        }

        // Transform a
        transformer(a);

        // For each qn, binary search for the index of the element in a that is greater than qn
        for (int i = 0; i < qn.length; i++) {
            if (qn[i] < a[0]) {
                solnIndex[i] = -1;
            } else {
                solnIndex[i] = binarySearch(a, qn[i]);
            }
        }

        for (int i = 0; i < solnIndex.length; i++) {
            if (solnIndex[i] == -1) {
                System.out.print(0);
                // Add space if it is not the last element
            } else if (solnIndex[i] >= a.length) {
                System.out.print(sum[a.length - 1]);
            } else {
                System.out.print(sum[solnIndex[i]]);
            }

            if (i != solnIndex.length - 1) {
                System.out.print(" ");
            }
        }
    }

    // Find the smallest index of the element in a that is greater than x. There can be duplicates
    public static int binarySearch(int a[], int x) {
        int low = 0;
        int high = a.length - 1;
        int mid = low + (high - low) / 2;

        while (low <= high) {
            if (mid < a.length - 1 && a[mid] <= x && a[mid + 1] > x) {
                break;
            } else if (a[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            mid = low + (high - low) / 2;
        }

        return low;
    }

    // transform a into the maximum element of a before each element
    public static void transformer(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            a[i] = max;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader s = new Reader();
        int n = s.nextInt();
        int count = 0;

        while (count < n) {
            int steps = s.nextInt();
            int qns = s.nextInt();

            int[] a = new int[steps];
            int[] q = new int[qns];

            for (int i = 0; i < steps; i++) {
                a[i] = s.nextInt();
            }

            for (int i = 0; i < qns; i++) {
                q[i] = s.nextInt();
            }

            // get the solution stepsAchieveable(x, a) for all x in q
            stepsAchievable(q, a);

            // Print a new line
            System.out.println();
            count++;
        }
    }
}
