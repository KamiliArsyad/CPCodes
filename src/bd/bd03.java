import java.util.*;

public class bd03 {
    // For all subarrays between questions[0] and questions[1], find the number of
    // subarrays which sum can be divided by questions[2]. questions[2] is either 12 or 23
    public static List<Integer> junieBirthday(List<Integer> nums, List<List<Integer>> questions) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> subarraySum = new HashMap<>();
        int[][][] memo = new int[nums.size()][nums.size()][2];

        // For every question, find the number of subarrays which sum can be divided by questions[2]
        for (List<Integer> question : questions) {
            // Put the result from helper
            res.add(helper(nums, question.get(0), question.get(1), question.get(2), subarraySum, memo));
        }

        return res;
    }

    public static int helper(List<Integer> nums, int start, int end, int divisor, HashMap<Integer, Integer> subarraySum, int[][][] memo) {
        int mapping = start * 1000000 + end * 100 + divisor;
        if (start == end) {
            boolean res = nums.get(start) % 12 == 0;
            boolean res2 = nums.get(start) % 23 == 0;
            memo[start][end][0] = res ? memo[start][end][0] + 1 : memo[start][end][0];
            memo[start][end][1] = res2 ? memo[start][end][1] + 1 : memo[start][end][1];

            return nums.get(start) % divisor == 0 ? 1 : 0;
        } else if (subarraySum.containsKey(mapping)) {
            return memo[start][end][divisor == 12 ? 0 : 1];
        } else {
            int res1 = helper(nums, start + 1, end, divisor, subarraySum, memo);
            int res2 = helper(nums, start, end - 1, divisor, subarraySum, memo);
            int count = res1 + res2;
            int sum = nums.get(start);

            // From left
            for (int i = start + 1; i <= end; i++) {
                sum += nums.get(i);
                if (sum % divisor == 0) {
                    count++;
                    int mmp = start * 1000000 + i * 100 + divisor;
                    subarraySum.put(mmp, sum);
                } else {
                    break;
                }
            }

            // From right
            sum = nums.get(end);
            for (int i = end - 1; i > start; i--) {
                sum += nums.get(i);
                if (sum % divisor == 0) {
                    count++;
                    int mmp = start * 1000000 + i * 100 + divisor;
                    subarraySum.put(mmp, sum);
                } else {
                    break;
                }
            }

            // memoize
            memo[start][end][divisor == 12 ? 0 : 1] = count;
            return count;
        }
    }

    public static void main(String[] args) {

    }
}
