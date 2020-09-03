package Laicode;

public class BirdNest {
    public int birdNest(int[] array) {
        if (array == null || array.length == 0) return 0;
        int[] res = {0};
        mergeCount(array, res, new int[array.length], 0, array.length - 1);
        return res[0];
    }

    private void mergeCount(int[] array, int[] res, int[] helper, int left, int right) {
        if (left == right) return;
        int mid = left + (right - left) / 2;
        mergeCount(array, res, helper, left, mid);
        mergeCount(array, res, helper, mid + 1, right);
        merge(array, res, helper, left, mid, right);
    }

    private void merge(int[] array, int[] res, int[] helper, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            helper[i] = array[i];
        }
        int l = left;
        int r = mid + 1;
        int cur = left;
        while (l <= mid) {
            if (r > right || helper[l] < helper[r]) {
                res[0] += cur - l;
                array[cur++] = helper[l++];
            } else {
                array[cur++] = helper[r++];
            }
        }
    }

    public static void main(String[] args) {
        BirdNest bn = new BirdNest();
        int res = bn.birdNest(new int[]{3, 0, 2, 1});
        System.out.println(res);
    }
}
