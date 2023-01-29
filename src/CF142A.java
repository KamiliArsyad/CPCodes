import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class CF142A {
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

    public static int findMinCost(int[] arr) {
        Arrays.sort(arr);

        int minCost = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) continue;
            if (arr[i] == 1) {
                minCost++;
                arr[i + 1] = arr[i + 1] - 1;
            } else {
                minCost++;
            }
        }

        System.out.println(minCost);

        return minCost;
    }

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        int n = r.nextInt();

        for (int i = 0; i < n; i++) {
            // Read an array
            int size = r.nextInt();
            int[] arr = new int[size];
            for (int j = 0; j < size; j++) {
                arr[j] = r.nextInt();
            }

            // Solve the problem
            findMinCost(arr);
        }
    }
}
