public class Solution {
  public int threeSumSmaller(int[] num, int target) {
    // Write your solution here
    if (num == null || num.length < 3) return 0;
    Arrays.sort(num);
    int res = 0;
    for (int i = 0; i < num.length; i++) {
      res += twoSumSmaller(num, i + 1, num.length - 1, target - num[i]);
    }
    return res;
  }

  private int twoSumSmaller(int[] num, int left, int right, int target) {
    int res = 0;
    while (left < right) {
      if (num[left] + num[right] < target) {
        res += right - left;
        left++;
      } else {
        right--;
      }
    }
    return res;
  }
}
