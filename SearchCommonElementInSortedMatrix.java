public class Solution {
  static class Info implements Comparable<Info> {
    int row;
    int val;
    Info(int row, int val) {
      this.row = row;
      this.val = val;
    }

    @Override
    public int compareTo(Info info) {
      return -Integer.compare(val, info.val);
    }
  }

  public int search(int[][] matrix) {
    // Write your solution here
    if (matrix == null || matrix.length < 2) return -1; ////////////////////////////// < 2 !!!!!!!!!!!!!!!!
    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i] == null || matrix[i].length == 0) return -1;
    }
    int[] indexes = new int[matrix.length];
    while (true) {
      int currentMaxRow = getMaxRow(matrix, indexes);
      int currentMax = matrix[currentMaxRow][indexes[currentMaxRow]];
      int count = 0;
      for (int i = 0; i < matrix.length; i++) {
        while (indexes[i] < matrix[i].length && matrix[i][indexes[i]] < currentMax) {
          indexes[i]++;
        }
      }
      for (int i = 0; i < matrix.length; i++) {
        if (indexes[i] == matrix[i].length) return -1;
        if (matrix[i][indexes[i]] == currentMax) count++;
      }
      if (count == matrix.length) return matrix[0][indexes[0]];
    }
  }

  private int getMaxRow(int[][] matrix, int[] indexes) {
    List<Info> infoList = new ArrayList<>();
    for (int i = 0; i < indexes.length; i++) {
      infoList.add(new Info(i, matrix[i][indexes[i]]));
    }
    PriorityQueue<Info> maxHeap = new PriorityQueue<>(infoList);
    return maxHeap.peek().row;
  }
}
