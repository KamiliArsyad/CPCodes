import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class CF827B {
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

    // Given an array a, check if all the elements inside are distinct
    public static int checker(int[] a) {
        Arrays.sort(a);

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == a[i + 1]) {
                return 0;
            }
        }

        return 1;
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

            soln[count] = checker(a);
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
