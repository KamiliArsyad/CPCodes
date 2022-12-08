import java.io.DataInputStream;
import java.io.IOException;

public class CF828B {
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

    /*
    You are given n of integers a1,a2,…,an. Process q queries of two types:

    query of the form "0 xj": add the value xj to all even elements of the array a,
    query of the form "1 xj": add the value xj to all odd elements of the array a.
    Note that when processing the query, we look specifically at the odd/even value of ai, not its index.

    After processing each query, print the sum of the elements of the array a.
     */

    public static int happensToOdd(int[] queries, int[] values) {
        int n = queries.length;
        int adder = 1;

        for (int i = 0; i < n; i++) {
            if (adder % 2 == 0) {
                if (queries[i] == 0) {
                    adder += values[i];
                }
            } else {
                if (queries[i] == 1) {
                    adder += values[i];
                }
            }
        }

        return adder - 1;
    }

    public static int happensToEven(int[] queries, int[] values) {
        int n = queries.length;
        int adder = 0;

        for (int i = 0; i < n; i++) {
            if (adder % 2 == 0) {
                if (queries[i] == 0) {
                    adder += values[i];
                }
            } else {
                if (queries[i] == 1) {
                    adder += values[i];
                }
            }
        }

        return adder;
    }


    public static void soln(int[] queries, int[] values, int[] a) {
        long sum = 0;
        int wasOdd = 1;
        int wasEven = 0;
        int numOdd = 0;
        int numEven = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                numEven++;
            } else {
                numOdd++;
            }
        }

        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }

        for (int i = 0; i < queries.length; i++) {
            if (queries[i] == 0) {
                if (wasOdd % 2 == 0 && wasEven % 2 == 0) {
                    sum += numOdd * values[i];
                    sum += numEven * values[i];
                    wasOdd += values[i] % 2;
                    wasEven += values[i] % 2;
                } else if (wasEven % 2 == 0) {
                    sum += numEven * values[i];
                    wasEven += values[i] % 2;
                } else if (wasOdd % 2 == 0) {
                    sum += numOdd * values[i];
                    wasOdd += values[i] % 2;
                }
            } else {
                if (wasOdd % 2 == 1 && wasEven % 2 == 1) {
                    sum += (long) numOdd * values[i];
                    sum += (long) numEven * values[i];
                    wasEven += values[i] % 2;
                    wasOdd += values[i] % 2;
                } else if (wasEven % 2 == 1) {
                    sum += (long) numEven * values[i];
                    wasEven += values[i] % 2;
                } else if (wasOdd % 2 == 1) {
                    sum += (long) numOdd * values[i];
                    wasOdd += values[i] % 2;
                }
            }
            System.out.println(sum);
        }
    }

    public static long sum(int[] a, int query, int value) {
        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            if (query == 0) {
                if (a[i] % 2 == 0) {
                    sum += a[i] + value;
                    a[i] += value;
                } else {
                    sum += a[i];
                }
            } else {
                if (a[i] % 2 == 0) {
                    sum += a[i];
                } else {
                    sum += a[i] + value;
                    a[i] += value;
                }
            }
        }
        return sum;
    }


    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        int n = in.nextInt();
        long[] soln = new long[n];
        int count = 0;
        while (count < n) {
            int size = in.nextInt();
            int queries = in.nextInt();
            int[] a = new int[size];
            int[] queryVals = new int[queries];
            int[] queryTypes = new int[queries];

            // Read a
            for (int i = 0; i < size; i++) {
                a[i] = in.nextInt();
            }

            // Read queries
            for (int i = 0; i < queries; i++) {
                queryTypes[i] = in.nextInt();
                queryVals[i] = in.nextInt();
            }

            soln(queryTypes, queryVals, a);

            count++;
        }
    }
}
