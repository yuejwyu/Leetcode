public class Solution {
  public boolean isScramble(String s1, String s2) {
    // Write your solution here
    if (s1 == null || s2 == null || s1.length() != s2.length()) {
      return false;
    }
    //////////////////////////////////////////////////// could count the character first here
    int n = s1.length();
    if (n == 0) return true; /////////////////////////// !!!!!!!!!!!!!
    char[] input1 = s1.toCharArray();
    char[] input2 = s2.toCharArray();
    boolean[][][] memo = new boolean[n][n][];
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        memo[i][j] = new boolean[n - (j - i)];
      }
    }
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        for (int k = 0; k < memo[i][j].length; k++) {
          if (i == j) {
            memo[i][j][k] = input1[i] == input2[k];
          } else {
            for (int m = i; m < j; m++) {
              if ((memo[i][m][k] && memo[m + 1][j][k + (m + 1 - i)]) 
                  || (memo[m + 1][j][k] && memo[i][m][k + (j - (m + 1)) + 1])) {
                memo[i][j][k] = true;
                break;
              }
            }
          }
        }
      }
    }
    return memo[0][n - 1][0];
  }
}
