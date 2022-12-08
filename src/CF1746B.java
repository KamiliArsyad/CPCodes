import java.io.DataInputStream;
import java.io.IOException;

public class CF1746B {
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

    /*You have an array a of size n consisting only of zeroes and ones. You can do the following operation:

    choose two indices 1≤i,j≤n, i≠j,
    add ai to aj,
    remove ai from a.
    Note that elements of a can become bigger than 1 after performing some operations. Also note that n becomes 1 less after the operation.

    What is the minimum number of operations needed to make a non-decreasing, i. e. that each element is not less than the previous element?*/
    public static int solution(int[] a) {
       int count = 0;
       int left = 0;
       int right = a.length - 1;

       while (left < right) {
           if (a[left] > a[right]) {
               count++;
               right--;
               left++;
           } else if (a[left] == 0 && a[right] == 0) {
               left++;
           } else if (a[left] == 0 && a[right] == 1) {
               left++;
               right--;
           } else if (a[left] == 1 && a[right] == 1) {
               right--;
           }
       }

       return count;
    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        int n = in.nextInt();
        int[] soln = new int[n];
        int count = 0;
        while (count < n) {
            int size = in.nextInt();
            int[] a = new int[size];

            for (int i = 0; i < size; i++) {
                a[i] = in.nextInt();
            }

            soln[count] = solution(a);

            count++;
        }

        for (int i = 0; i < soln.length; i++) {
            System.out.println(soln[i]);
        }
    }
}