import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class CF847D {
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

    public static void solution(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 1;
        Arrays.sort(arr);
        map.put(arr[arr.length - 1], 1);

        if (arr.length == 1) {
            System.out.println(1);
            return;
        }

        for (int i = arr.length - 2; i >= 0; i--) {
            if (map.containsKey(arr[i] + 1)) {
                if (map.get(arr[i] + 1) == 1) {
                    map.remove(arr[i] + 1);
                } else {
                    map.replace(arr[i] + 1, map.get(arr[i] + 1) - 1);
                }
                if (map.containsKey(arr[i])) {
                    map.replace(arr[i], map.get(arr[i]) + 1);
                } else {
                    map.put(arr[i], 1);
                }
            } else {
                if (map.containsKey(arr[i])) {
                    map.replace(arr[i], map.get(arr[i]) + 1);
                } else {
                    map.put(arr[i], 1);
                }
                count++;
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        int n = r.nextInt();

        for (int i = 0; i < n; i++) {
            int size = r.nextInt();
            int[] arr = new int[size];
            for (int j = 0; j < size; j++) {
                arr[j] = r.nextInt();
            }

            solution(arr);
        }
    }
}
