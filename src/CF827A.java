import java.io.DataInputStream;
import java.io.IOException;


public class CF827A {
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

    public static void main(String[] args) throws IOException {
        Reader s = new Reader();
        int n = s.nextInt();
        int[] soln = new int[n + 1];
        int count = 0;
        while (count < n) {
            int a = s.nextInt();
            int b = s.nextInt();
            int c = s.nextInt();

            if (a + b == c || a + c == b || b + c == a) {
                soln[count] = 1;
            } else {
                soln[count] = 0;
            }

            count++;
        }

        for (int i = 0; i < n; i++) {
            if (soln[i] == 1) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
