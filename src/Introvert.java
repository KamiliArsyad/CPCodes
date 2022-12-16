import java.util.Scanner;

public class Introvert {
     public static int solution(int[] arr) {
        // Find the longest distance between two ones in the array
        // walk from left
        int max = 0;
        int[] left = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                left[i] = 0;
            } else {
                if (i == 0) {
                    left[i] = 1000000;
                } else {
                    left[i] = left[i - 1] + 1;
                }
            }
        }

        // walk from right
        int[] right = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                right[i] = 0;
            } else {
                if (i == arr.length - 1) {
                    // Big integer
                    right[i] = 10000000;
                } else {
                    right[i] = right[i + 1] + 1;
                }
            }
        }

        // new array that stores the minimum distances between left and right
        int[] min = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            min[i] = Math.min(left[i], right[i]);
        }


        // find the max distance
        for (int i = 0; i < arr.length; i++) {
            if (min[i] > min[max]) {
                max = i;
            }
        }

        return max + 1;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String l = s.nextLine();
        String n = s.nextLine();
        int[] a = new int[n.length()];
        for (int i = 0; i < n.length(); i++) {
            a[i] = n.charAt(i) == '1' ? 1 : 0;
        }
        System.out.println(solution(a));
    }
}
