package Laicode;

import java.util.*;

public class CountArray {
  // method1: mergesort
  public int[] countArray(int[] array) {
    int[] index = new int[array.length];
    int[] count = new int[array.length];
    int[] help = new int[array.length];

    for (int i = 0; i < array.length; i++) {
      index[i] = i;
    }

    mergeSort(array, index, count, help, 0, array.length - 1);

    return count;
  }

  private void mergeSort(int[] array, int[] index, int[] count, int[] help, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = left + (right - left) / 2;
    mergeSort(array, index, count, help, left, mid);
    mergeSort(array, index, count, help, mid + 1, right);
    merge(array, index, count, help, left, mid, right);
  }

  private void merge(int[] array, int[] index, int[] count, int[] help, int left, int mid, int right) {
    for (int i = left; i <= right; i++) {
      help[i] = index[i];
    }

    int l = left;
    int r = mid + 1;
    int cur = left;

    while (l <= mid) {
      if (r > right || array[help[l]] <= array[help[r]]) {
        count[help[l]] += r - mid - 1; // cur - l is the same
        index[cur++] = help[l++];
      }
      else {
        index[cur++] = help[r++];
      }      
    }
  }
  
  // -------------------------------------------------------------------------
  //method2: segment tree
  public List<Integer> countSmaller(int[] nums) {
        TreeSet<Integer> s = new TreeSet<>();
        for (int i: nums) s.add(i);
        ArrayList<Integer> u = new ArrayList<>(s);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Collections.binarySearch(u, nums[i]);
        }
        SegTree tree = new SegTree();
        tree.resize(u.size());
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            res.add(tree.query(nums[i]));
            tree.addOne(nums[i]);
        }
        Collections.reverse(res);
        return res;
    }
    
    static class SegTree {
      int k, m; // k = height - 1, m = 2 ** k = leafNum
      int[] a;  // a is the heap-like tree

      void resize(int size) {
          k = Math.getExponent(size) + 1;
          m = 1 << k;
          a = new int[m + m]; // 1-base index !!!
      }

      void addOne(int position) {
          position += m; // first index is 2 ** (height + 1) = m !!!
          ++a[position];
          for (int i = position >> 1; i >= 1; i >>= 1) {
              // a[i] = a[i + i] + a[i + i + 1];
              a[i]++;
          }
      }

      int query(int position) {
          position += m;
          int answer = 0;
          for (int i = position; i != 1; i >>= 1) {
              if ((i & 1) != 0) answer += a[i - 1];
          }
          return answer;
      }
   }
}
