public class Solution {
  public int minTotalDistance(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
    int m = grid.length;
    int n = grid[0].length;
    List<Integer> rows = new ArrayList<>();
    List<Integer> cols = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          rows.add(i);
        }
      }
    }
    if (rows.size() == 0) return 0; ///////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[j][i] == 1) {
          cols.add(i);
        }
      }
    }
    int midRow = rows.get(rows.size() / 2);
    int midCol = cols.get(cols.size() / 2);
    int res = 0;
    for (int row : rows) {
      res += Math.abs(row - midRow);
    }
    for (int col : cols) {
      res += Math.abs(col - midCol);
    }
    return res;
  }

  // public int minTotalDistance(int[][] grid) {
  //   // Write your solution here
  //   if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
  //   int m = grid.length;
  //   int n = grid[0].length;
  //   int[][] distances = new int[grid.length][grid[0].length];
  //   for (int i = 0; i < m; i++) {
  //     for (int j = 0; j < n; j++) {
  //       if (grid[i][j] == 1) bfs(distances, i, j);
  //     }
  //   }
  //   int res = Integer.MAX_VALUE;
  //   for (int i = 0; i < m; i++) {
  //     for (int j = 0; j < n; j++) {
  //       res = Math.min(res, distances[i][j]);
  //     }
  //   }
  //   return res;
  // }

  // private void bfs(int[][] distances, int x, int y) {
  //   for (int i = 0; i < distances.length; i++) {
  //     for (int j = 0; j < distances[0].length; j++) {
  //       distances[i][j] += Math.abs(i - x) + Math.abs(j - y);
  //     }
  //   }
  // }
}
