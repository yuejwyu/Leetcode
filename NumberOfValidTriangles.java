public class Solution {
  public int numOfTriangles(int[] array) {
    // Write your solution here
    // The given array is not null and has length of at least 3
    Arrays.sort(array);
    int res = 0;
    for (int i = 0; i < array.length - 2; i++) {
      int cur = array[i];
      int k = i + 2;
      for (int j = i + 1; j < array.length - 1; j++) {
        while (k < array.length && array[k] < array[j] + cur) {
          k++;
        }
        res += k - j - 1;
      }
    }
    return res;
  }
}
