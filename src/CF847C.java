import java.io.DataInputStream;
import java.io.IOException;

public class CF847C {
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

    public static int soln(int[] arr) {
        // Find the missing element between 1 and arr.length() + 1
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int missing = (arr.length + 1) * (arr.length + 2) / 2 - sum;

        return missing;
    }

    public static void prtr(int[] arr, int missing, int pos) {
        for (int i = 0; i <= arr.length; i++) {
            if (i < pos) {
                System.out.print(arr[i] + " ");
            } else if (i == pos) {
                System.out.print(missing + " ");
            } else {
                System.out.print(arr[i - 1] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        int c = r.nextInt();

        for (int i = 0; i < c; i++) {
            int n = r.nextInt();
            int count = n;
            int[] arr = new int[n - 1];
            for (int j = 0; j < n - 1; j++) {
                arr[j] = r.nextInt();
            }
            int pos = 0;
            int missing = soln(arr);
            int prev = 0;
            while (count > 0) {
                int in = r.nextInt();
                if (in != missing) {
                    prev = in;
                    pos = (pos + 1) % n;
                    count--;
                } else {
                    prtr(arr, missing, arr[pos]);
                }
            }
            prtr(arr, missing, pos);
        }
    }
}
