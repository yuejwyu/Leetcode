public class Solution {
  public int countRangeSum(int[] nums, int lower, int upper) {
    // Write your solution here
    int[] prefixSum = new int[nums.length + 1];
    for (int i = 1; i <= nums.length; i++) {
      prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
    }
    return countPrefixSum(prefixSum, new int[prefixSum.length], 
                          0, prefixSum.length - 1, lower, upper);
  }

  private int countPrefixSum(int[] prefixSum, int[] helper, 
                            int left, int right, int lower, int upper) {
    if (left >= right) return 0;
    int mid = left + (right - left) / 2;
    int count = countPrefixSum(prefixSum, helper, left, mid, lower, upper)
              + countPrefixSum(prefixSum, helper, mid + 1, right, lower, upper);
    int rangeLeft = left;
    int rangeRight = left;
    for (int i = mid + 1; i <= right; i++) {
      while (rangeLeft <= mid && prefixSum[rangeLeft] < prefixSum[i] - upper) {
        rangeLeft++;
      }
      while (rangeRight <= mid && prefixSum[rangeRight] <= prefixSum[i] - lower) {
        rangeRight++;
      }
      count += rangeRight - rangeLeft;
    }
    merge(prefixSum, helper, left, mid, right);
    return count;
  }

  private void merge(int[] prefixSum, int[] helper, 
                      int left, int mid, int right) {
    for (int i = left; i <= right; i++) {
      helper[i] = prefixSum[i];
    }
    int k = left;
    int i = left;
    int j = mid + 1;
    while (i <= mid) {
      if (j > right || helper[i] <= helper[j]) {
        prefixSum[k++] = helper[i++];
      } else {
        prefixSum[k++] = helper[j++];
      }
    }
  }
}
