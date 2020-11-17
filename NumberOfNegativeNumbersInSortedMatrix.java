public class Solution {
  public int negNumber(int[][] matrix) {
    // Write your solution here
    int count = 0;
    for (int[] array : matrix) {
      count += negNumber(array);
    }
    return count;
  }

  private int negNumber(int[] array) {
    if (array == null || array.length == 0) return 0;
    int left = 0; // array[0, left) is negative
    int right = array.length - 1; // array(right, last index] is not negative
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (array[mid] < 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    } 
    return left;
  }
}
