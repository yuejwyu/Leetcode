class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int left = 0;
        int right = 0; // every number in A[left, right) is in range [L, R]
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > R) {
                left = right = i + 1;
            } else {
                if (A[i] >= L) {
                    right = i + 1;
                }
                res += right - left;
            }
        }
        return res;
    }
}
