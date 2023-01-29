import java.io.DataInputStream;
import java.io.IOException;

public class CF847B {
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

    // Given that solution is always possible, outputs the sequence of dice rolls that will sum to s given that
    // The maximum dice roll is maxDice and the sum after removing maxDice is r
    public static int soln(int n, int s, int r) {
        int maxDice = s - r;
        int count = 1;
        int sum = s - maxDice;
        System.out.print(maxDice + " ");

        while (count < n) {
            for (int i = 1; i <= maxDice; i++) {
                if (isPossible(n - count -1, maxDice, sum - i)) {
                    System.out.print(i + (count == n - 1 ? "" : " "));
                    sum -= i;
                    count++;
                    break;
                }
            }
        }

        return sum;
    }

    public static boolean isPossible(int n, int max, int targetSum) {
        // If the target sum is less than the number of dice, then it is impossible
        if (targetSum < n) {
            return false;
        }

        // If the target sum is greater than the number of dice * max, then it is impossible
        if (targetSum > n * max) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Reader read = new Reader();
        int c = read.nextInt();

        for (int i = 0; i < c; i++) {
            // Read three integers
            int n = read.nextInt();
            int s = read.nextInt();
            int r = read.nextInt();

            soln(n, s, r);
            // Print a new line
            System.out.println();
        }
    }
}
