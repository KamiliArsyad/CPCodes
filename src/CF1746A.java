import java.io.DataInputStream;
import java.io.IOException;

public class CF1746A {
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

    // Determine if it's possible to turn an array a composed of 0 and 1 into [1]
    public static boolean solution(int[] a, int k) {
        int n = a.length;

        if (n == 1) {
            return a[0] == 1;
        }

        for (int i = 0; i < n; i++) {
            if (a[i] == 1) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        int n = in.nextInt();
        int[] soln = new int[n];
        int count = 0;
        while (count < n) {
            int size = in.nextInt();
            int k = in.nextInt();
            int[] a = new int[size];

            for (int i = 0; i < size; i++) {
                a[i] = in.nextInt();
            }

            soln[count] = solution(a, k) ? 1 : 0;

            count++;
        }

        for (int i = 0; i < soln.length; i++) {
            System.out.println(soln[i] == 1 ? "YES" : "NO");
        }
    }
}
