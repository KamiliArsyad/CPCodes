import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;

public class PA2CS3230 {
    // fast input library taken from
    // https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
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

    /**
     * Given an array a, find the number of subarrays a[i..j] such that the all elements in the subarray
     * appears in the array an even number of times.
     * @param a
     * @return
     */
    public static long solve(int[] a) {
        HashMap<Long, Integer> prefixStorage = new HashMap<>();
        long prefixSum = 0;
        long count = 0;
        prefixStorage.put(0L, 1);

        // For each element in a, assign a random number from 0 to 2^64
        Random rn = new Random();
        long[] random = new long[a.length];

        for (int i = 0; i < a.length; i++) {
            random[i] = rn.nextLong();
        }

        for (int i = 0; i < random.length; i++) {
            prefixSum ^= random[a[i]];
            count += prefixStorage.getOrDefault(prefixSum, 0);
            prefixStorage.put(prefixSum, prefixStorage.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        Reader s = new Reader();
        int n = s.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for(int i=0; i<n; i++) {
            a[i] = s.nextInt();
        }
        System.out.println(solve(a));
    }
}
