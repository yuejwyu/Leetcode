public class KthMissingNumber {
    public int kthMissingNumbers(int[] nums, int k) {
        if (nums == null || k < 1) return -1;
        if (nums.length == 0) return k - 1;
        if (nums[0] - 0 >= k) {
            return k - 1;
        }
        if (nums[nums.length - 1] - (nums.length - 1) < k) {
            return nums[nums.length - 1] +
                    (k - (nums[nums.length - 1] - (nums.length - 1)));
        }
        return kthMissingNumbers(nums, k - (nums[0] - 0), 0, nums.length - 1);
    }

    private int kthMissingNumbers(int[] nums, int k, int left, int right) {
        // return the kth missing number in nums[left : right]
        if (left == right - 1) {
            return nums[left] + k;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] - mid - (nums[left] - left) >= k) {
            return kthMissingNumbers(nums, k, left, mid);
        } else {
            return kthMissingNumbers(nums,
                    k - (nums[mid] - mid - (nums[left] - left)), mid, right);
        }
    }
}
