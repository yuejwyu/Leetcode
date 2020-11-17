public class Solution {
  public int[] interleave(int[] array) {
    // Write your solution here
    // The given array is not null.
    // There is no 0 in the array.
    // The ordering of positive/negative numbers do not matter.
    int j = rainbowSort(array);
    for (int i = 1; i < j && j < array.length; i += 2) { // [i, j) need to fix
      swap(array, i, j++);
    }
    return array;
  }

  private int rainbowSort(int[] array) {
    int i = 0; // [0, i) is positive
    int j = array.length - 1; // (j, end] is negative
    while (i <= j) {
      if (array[i] > 0) {
        i++;
      } else {
        swap(array, i, j--);
      }
    }
    return i;
  }

  private void swap(int[] array, int i, int j) {
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
