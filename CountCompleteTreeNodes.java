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
  public int countNodes(TreeNode root) {
    // Write your solution here
    // time complexity: O(height + height^2) = O(logn + (logn)^2)
    // space complexity: O(height) = O(logn)
    int layer = getLayer(root);
    int[] powers = powersOfTwo(layer);
    if (layer <= 1) return powers[layer] - 1;
    int res = powers[layer - 1] - 1;
    int depth = 1;
    while (depth < layer) {
      if (canReachEnd(root.right, depth + 1, layer)) {
        res += powers[layer - depth - 1];
        root = root.right;
      } else {
        root = root.left;
      }
      depth++;
    }
    if (root != null) res++;
    return res;
  }

  private int getLayer(TreeNode root) {
    int layer = 0;
    TreeNode tmp = root;
    while (tmp != null) {
      tmp = tmp.left;
      layer++;
    }
    return layer;
  }

  private int[] powersOfTwo(int num) {
    int[] powers = new int[num + 1];
    powers[0] = 1;
    for (int i = 1; i <= num; i++) {
      powers[i] = powers[i - 1] * 2;
    }
    return powers;
  }

  private boolean canReachEnd(TreeNode root, int depth, int target) {
    while (root != null) {
      root = root.left;
      depth++;
    }
    return depth == target + 1;
  }
}
