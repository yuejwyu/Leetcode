public class Solution {
  public List<List<Integer>> palindromePairs(String[] words) {
    // Write your solution here
    if (words == null || words.length < 2) return new ArrayList<>();

    Set<List<Integer>> res = new HashSet<>();

    Map<String, List<Integer>> left = new HashMap<>();
    Map<String, List<Integer>> right = new HashMap<>();

    for (int i = 0; i < words.length; i++) {
      String cur = words[i];
      String reverse = new StringBuilder(cur).reverse().toString();
      List<Integer> match = left.get(reverse);
      if (match != null) {
        for (int m : match) {
          res.add(Arrays.asList(m, i));
        }
      }
      match = right.get(reverse);
      if (match != null) {
        for (int m : match) {
          res.add(Arrays.asList(i, m));
        }
      }
      if (!left.containsKey(cur)) {
        left.put(cur, new ArrayList<>());
      }
      left.get(cur).add(i);
      if (!right.containsKey(cur)) {
        right.put(cur, new ArrayList<>());
      }
      right.get(cur).add(i);
      //left
      for (int j = cur.length() - 1; j >= 0; j--) {
        if (isPalindrome(cur, j, cur.length() - 1)) {
          String tmp = cur.substring(0, j);
          reverse = new StringBuilder(tmp).reverse().toString();
          match = right.get(reverse);
          if (match != null) {
            for (int m : match) {
              if (m != i && words[m].equals(reverse)) res.add(Arrays.asList(i, m));
            }
          }
          if (!left.containsKey(tmp)) {
            left.put(tmp, new ArrayList<>());
          }
          left.get(tmp).add(i);
        }
      }
      // right
      for (int j = 0; j < cur.length(); j++) {
        if (isPalindrome(cur, 0, j)) {
          String tmp = cur.substring(j + 1, cur.length());
          reverse = new StringBuilder(tmp).reverse().toString();
          match = left.get(reverse);
          if (match != null) {
            for (int m : match) {
              if (m != i && words[m].equals(reverse)) res.add(Arrays.asList(m, i));
            }
          }
          if (!right.containsKey(tmp)) {
            right.put(tmp, new ArrayList<>());
          }
          right.get(tmp).add(i);
        }
      }
    }
    return new ArrayList<>(res);
  }

  private boolean isPalindrome(String word, int i, int j) {
    while (i < j) {
      if (word.charAt(i++) != word.charAt(j--)) { /////////////////// ++ -- !!!!!!!!!!
        return false;
      } 
    }
    return true;
  }
}
