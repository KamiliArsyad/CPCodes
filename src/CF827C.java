import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class CF827C {
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

    // Given an 8x8 array of R, B, or ., check if there's a vertical R
    public static int checker(char[][] a) {
        boolean status = true;

        for (int i = 0; i < 8; i++) {
            status = true;
            for (int j = 0; j < 8; j++) {
                if (a[j][i] == 'R') {
                    status = false;
                    break;
                }
            }
            if (status) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        //Reader in = new Reader();
        // Initiate a scanner
        java.util.Scanner in = new java.util.Scanner(System.in);
        int n = in.nextInt();
        int count = 0;
        int[] soln = new int[n];
        in.nextLine();

        while (count < n) {
            char[][] a = new char[8][8];
            // skip the empty line before the input
            String ss = in.nextLine();

            // Read in the 8x8 array using a nested for loop,
            for (int i = 0; i < 8; i++) {
                String line = in.nextLine();
                for (int j = 0; j < 8; j++) {
                    a[i][j] = line.charAt(j);
                }
            }

            // Solve the problem
            soln[count] = checker(a);
            count++;
        }

        // Print R if there's a vertical R, B if there's a vertical B
        for (int i = 0; i < n; i++) {
            if (soln[i] == 1) {
                System.out.println("B");
            } else {
                System.out.println("R");
            }
        }
    }
}
