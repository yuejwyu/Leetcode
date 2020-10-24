public class Solution {
  public int minCostII(int[][] costs) {
    // Write your solution here
    if (costs == null || costs.length == 0) return 0;
    int n = costs.length;
    int k = costs[0].length;
    if (k == 1) return costs[0][0]; ////////////////////////// !!!!!!!!!!!!!!!!!!!!!!
    int[] memo = Arrays.copyOf(costs[0], k);
    for (int i = 1; i < n; i++) {
      int[] topTwoMin = findTopTwoMin(memo);
      int[] tmp = new int[k];
      for (int j = 0; j < k; j++) {
        if (memo[j] == topTwoMin[0]) {
          tmp[j] = topTwoMin[1] + costs[i][j];
        } else {
          tmp[j] = topTwoMin[0] + costs[i][j];
        }
      }
      memo = tmp;
    }
    return findTopTwoMin(memo)[0];
  }

  private int[] findTopTwoMin(int[] array) {
    int tmp1 = Math.min(array[0], array[1]);
    int tmp2 = Math.max(array[0], array[1]);
    for (int i = 2; i < array.length; i++) {
      if (array[i] < tmp1) {
        tmp2 = tmp1;
        tmp1 = array[i];
      } else if (array[i] >= tmp1 && array[i] < tmp2) {
        tmp2 = array[i];
      }
    }
    return new int[]{tmp1, tmp2};
  }
}
