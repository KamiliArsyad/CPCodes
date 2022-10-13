import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class CF827D {
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
    // Given an array of n positive integers a1,a2,…,an (1≤ai≤1000). Find the maximum value of i+j such that ai and aj are coprime, or −1 if no such i, j exist.
    public static int solution(int[] a) {
        int max = -1;

        // find the max value of i+j
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (gcd(a[i], a[j]) == 1) {
                    max = Math.max(max, i + j);
                }
            }
        }

        return max;
    }

    // Given two integers a and b, find the greatest common divisor of a and b
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        Reader s = new Reader();
        int n = s.nextInt();
        int[] soln = new int[n];
        int count = 0;
        while (count < n) {
            int length = s.nextInt();
            int[] a = new int[length];

            for (int i = 0; i < length; i++) {
                a[i] = s.nextInt();
            }

            soln[count] = solution(a);

            count++;
        }

        for (int i = 0; i < soln.length; i++) {
            System.out.println(soln[i]);
        }
    }
}
