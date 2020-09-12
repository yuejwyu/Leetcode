public class Celebrity {
  public int celebrity(int[][] matrix) {
    // Write your solution here
    // given matrix is not null and N >= 2
    int n = matrix.length;
    int celebrity = 0;
    for (int test = 1; test < n; test++) {
      if (matrix[celebrity][test] == 1) {
        celebrity = test;
      }
    }
    return isCelebrity(matrix, celebrity) ? celebrity : -1;
  }

  private boolean isCelebrity(int[][] matrix, int i) {
    for (int k = 0; k < matrix.length; k++) {
      if (k == i) continue;
      if (matrix[k][i] == 0 || matrix[i][k] == 1) {
        return false;
      }
    }
    return true;
  }
}
