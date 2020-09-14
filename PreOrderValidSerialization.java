public class Solution {
  public boolean isValidSerialization(String preorder) {
    // Write your solution here
    Deque<String> stack = new ArrayDeque<>();
    String[] nodes = preorder.split(",");
    for (int i = nodes.length - 1; i >= 0; i--) {
      if (nodes[i].equals("#")) {
        if (i - 1 >= 0) {
          if (nodes[i - 1].equals("#")) {
            if (i - 2 >= 0) {
              if (nodes[i - 2].equals("#")) {
                return false;
              } else {
                stack.offerFirst(nodes[i - 2]);
                i -= 2;
              }
            } else {
              return false;
            }
          } else {
            if (stack.isEmpty()) {
              return false;
            } else {
              stack.pollFirst();
              stack.offerFirst(nodes[i - 1]);
              i -= 1;
            }
          }
        } else {
          return false;
        }
      } else {
        if (stack.size() < 2) {
          return false;
        } else {
          stack.pollFirst();
          stack.pollFirst();
          stack.offerFirst(nodes[i]);
        }
      }
    }
    return stack.size() == 1;
  }
}
