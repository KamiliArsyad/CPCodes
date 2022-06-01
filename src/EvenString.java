public class EvenString {
    public static int solution(StringBuilder str, int i) {
        if (i >= str.length() - 1) {
            return (i + 1) % 2 == 0 ? 0 : 1;
        }

        return str.charAt(i) == str.charAt(i + 1)
                ? solution(str, i + 2)
                : min(
                        1 + solution(str.deleteCharAt(i), i == 0 ? 0 : i - 1),
                        solution(str, i + 1));
    }

    public static int min(int... arr) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }

        return min;
    }


    public static void main(String[] args) {
        String input = "aabbdabdccc";
        int output = solution(new StringBuilder(input), 0);


        System.out.println(output);
    }
}
