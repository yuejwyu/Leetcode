public class Solution {
  public int leastInsertion(String input) {
    // Write your solution here
    if (input == null || input.length() == 0) return 0; /////////////////// !!!!!!!!!!!!!!!!!!!!
    int n = input.length();
    char[] array = input.toCharArray();
    int[] memo = new int[n];
    for (int i = n - 2; i >= 0; i--) {
      int[] tmp = new int[n];
      for (int j = i + 1; j < n; j++) {
        tmp[j] = array[i] == array[j] ? memo[j - 1] : memo[j - 1] + 2;
        tmp[j] = Math.min(tmp[j], tmp[j - 1] + 1);
        tmp[j] = Math.min(tmp[j], memo[j] + 1);
      }
      memo = tmp;
    }
    return memo[n - 1];
  }
}
