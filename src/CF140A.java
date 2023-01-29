import java.io.DataInputStream;
import java.io.IOException;

public class CF140A {
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

    // Given a non-degenerate triangle's coordinate, check if one side is vertical and another is horizontal
    public static boolean checker(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (x1 == x2 && y1 == y2) return false;
        if (x1 == x3 && y1 == y3) return false;
        if (x2 == x3 && y2 == y3) return false;

        if (x1 == x2) {
            if (y1 == y3 || y2 == y3) return true;
        } else if (x1 == x3) {
            if (y1 == y2 || y2 == y3) return true;
        } else if (x2 == x3) {
            if (y1 == y2 || y1 == y3) return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
       Reader s = new Reader();
       int n = s.nextInt();
       boolean[] sol = new boolean[n];
       int count = 0;

       while (count < n) {
           // Read four lines. First line empty, next ith contains xith and yith of the triangle
           double x1 = s.nextInt();
           double y1 = s.nextInt();
           double x2 = s.nextInt();
           double y2 = s.nextInt();
           double x3 = s.nextInt();
           double y3 = s.nextInt();

           sol[count] = !checker(x1, y1, x2, y2, x3, y3);
           count++;
       }

         for (int i = 0; i < n; i++) {
              if (sol[i]) System.out.println("YES");
              else System.out.println("NO");
         }
    }
}
