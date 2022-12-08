import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class CF828D {
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


    public static int solution(int[] a) {
        // Choose any 0 <= i < a.length and multiply a[i] by product of all other elements of a
        // If the product is not divisible by 2^n, choose another 0 <= j < a.length, j != i,
        // and multiply a[j] by the product of all other elements of a
        // Repeat until the product is divisible by 2^n
        int product = 1;

        for (int i = 0; i < a.length; i++) {
            product *= a[i];
        }

        for (int i = 0; i < a.length; i++) {
            if ((product % 2) == 0) {
                product = product >> 1;
            }
        }

        return 0;
    }


    public static void main(String[] args) throws IOException {
        Reader s = new Reader();
        int n = s.nextInt();
        int[] soln = new int[n];
        int count = 0;

        while (count < n) {
            int length = s.nextInt();
            int[] a = new int[length];

            count++;
        }

        for (int i = 0; i < soln.length; i++) {
            if (soln[i] == 1) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}