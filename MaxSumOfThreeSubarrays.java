public class Solution {
  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    // Write your solution here
    if (nums == null) return null;
    if (nums.length < 3 * k) return new int[0];
    int[] memo = new int[nums.length - k + 1];
    int sum = 0;
    for (int i = 0; i < nums.length; i++) { //////////////// nums.length no need + 1
      sum += nums[i];
      if (i >= k - 1) {
        if (i > k - 1) sum -= nums[i - k];
        memo[i - k + 1] = sum;
      }
    }
    int[] leftMax = new int[memo.length];
    int[] rightMax = new int[memo.length];
    for (int i = 1; i < memo.length; i++) {
      leftMax[i] = memo[i] > memo[leftMax[i - 1]] ? i : leftMax[i - 1];
    }
    rightMax[memo.length - 1] = memo.length - 1;
    for (int i = memo.length - 2; i >= 0; i--) {
      rightMax[i] = memo[i] >= memo[rightMax[i + 1]] ? i : rightMax[i + 1];
    }
    int max = 0;
    int[] res = null;
    for (int i = k; i < memo.length - k; i++) {
      int cur = memo[leftMax[i - k]] + memo[i] + memo[rightMax[i + k]];
      if (cur > max) {
        max = cur;
        res = new int[]{leftMax[i - k], i, rightMax[i + k]};
      }
    }
    return res;
  }
}
