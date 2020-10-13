public class Solution {
  static class Node {
    Node[] children = new Node[26];
    boolean isWord;
  }

  static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public List<String> findWords(char[][] board, String[] words) {
    // Write your solution here
    if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
      return new ArrayList<>();
    }
    Set<String> res = new HashSet<>();
    Node root = buildDict(words);
    final int rows = board.length;
    final int cols = board[0].length;
    boolean[][] visited = new boolean[rows][cols];
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        helper(board, i, j, root, sb, res, visited);
      }
    }
    return new ArrayList<>(res);
  }

  private Node buildDict(String[] words) {
    Node root = new Node();
    for (String word: words) {
      Node cur = root;
      for (int i = 0; i < word.length(); i++) {
        Node next = cur.children[word.charAt(i) - 'a'];
        if (next == null) {
          next = new Node();
          cur.children[word.charAt(i) - 'a'] = next;
        }
        cur = next;
      }
      cur.isWord = true;
    }
    return root;
  }

  private void helper(char[][] board, int x, int y, Node root, StringBuilder sb, Set<String> res, boolean[][] visited) {
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) {
      return;
    }

    char ch = board[x][y];

    if (root.children[ch - 'a'] == null) {
      return;
    }

    sb.append(ch);
    visited[x][y] = true;

    root = root.children[ch - 'a'];
    if (root.isWord) {
      res.add(sb.toString());
    }
    
    for (int[] dir: DIRS) {
      helper(board, dir[0] + x, dir[1] + y, root, sb, res, visited);
    }

    visited[x][y] = false;
    sb.deleteCharAt(sb.length() - 1);
  }
}
