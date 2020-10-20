/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  public List<TreeNode> generateBSTs(int n) {
    //Input your code here
    if (n < 0) return new ArrayList<>();
    List<List<List<TreeNode>>> memo = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      List<List<TreeNode>> tmp = new ArrayList<>();
      for (int j = 0; j < n; j++) {
        tmp.add(new ArrayList<>());
      }
      memo.add(tmp);
    }
    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = i + 1;
    }
    List<TreeNode> empty = new ArrayList<>(); ///////////////////////////////////////////////// !!!!!!!!!!!!!!!!
    empty.add(null);
    return generateBSTs(nums, 0, n - 1, memo, empty);
  }

  private List<TreeNode> generateBSTs(int[] nums, int left, int right, 
                                      List<List<List<TreeNode>>> memo, 
                                      List<TreeNode> empty) {
    if (left > right) { ///////////////////////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      return empty;
    }
    if (memo.get(left).get(right).size() > 0) {
      return memo.get(left).get(right);
    }
    List<TreeNode> target = memo.get(left).get(right);
    for (int i = left; i <= right; i++) {
      int cur = nums[i];
      List<TreeNode> leftBSTs = generateBSTs(nums, left, i - 1, memo, empty);
      List<TreeNode> rightBSTs = generateBSTs(nums, i + 1, right, memo, empty);
      for (TreeNode leftBST : leftBSTs) {
        for (TreeNode rightBST : rightBSTs) {
          TreeNode tmp = new TreeNode(cur);
          tmp.left = leftBST;
          tmp.right = rightBST;
          target.add(tmp);
        }
      }
    }
    return target;
  }
}
