import java.io.DataInputStream;
import java.io.IOException;

public class CF140B {
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

    // For each element of the array that is greater than the first element,
    // add the difference between the first element and the current element to the answer
    // also add the difference between the first element and the current element to the first element
    public static long soln(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[0]) {
                // binary search the number of integer that can be moved from a[i] to a[0] without making a[0] greater than a[i]
                int l = 0;
                int r = a[i] - a[0];

                while (l < r) {
                    int mid = (l + r) / 2;
                    if (a[0] + mid <= a[i] - mid) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }

                a[0] += l;
                a[i] -= l;
            }
        }

        return a[0];
    }

    public static void main(String[] args) throws IOException {
        Reader s = new Reader();
        int n = s.nextInt();
        long[] soln = new long[n];
        int count = 0;

        while (count < n) {
            int a = s.nextInt();
            // Array of size a
            int[] arr = new int[a];
            for (int i = 0; i < a; i++) {
                arr[i] = s.nextInt();
            }

            soln[count] = soln(arr);
            count++;
        }

        for (int i = 0; i < soln.length; i++) {
            System.out.println(soln[i]);
        }
    }
}
