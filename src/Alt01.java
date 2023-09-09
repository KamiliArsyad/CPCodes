import java.util.HashSet;

public class Alt01 {
    public static boolean Soln(int[] arr) {
        HashSet<Integer> traversed = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            if (traversed.contains(arr[i])) {
                return true;
            }

            traversed.add(arr[i]);
        }

        return false;
    }

    public static void installmentFinder(int order) {
        // Array of 3 numbers: installement
        int installment = order / 3;
        int firstInstallment = installment * 3 < order ? order - 2 * installment : installment;

        System.out.println(firstInstallment + " " + installment + " " + installment);
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4};

        installmentFinder(101);
    }
}
