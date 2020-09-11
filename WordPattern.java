public class WordPattern {
  public boolean wordPatternMatch(String pattern, String input) {
    // Write your solution here
    if (pattern == null || input == null) return false;
    if (pattern.length() == 0) return input.length() == 0;
    if (pattern.length() > input.length()) return false;
    return dfs(pattern, input, 0, 0, new HashMap<>(), new HashSet<>());
  }

  private boolean dfs(String pattern, String input, int i, int j, 
                        Map<Character, String> map, Set<String> set) {
    if (i == pattern.length() || j == input.length()) {
      return i == pattern.length() && j == input.length();
    }
    char cur = pattern.charAt(i);
    if (map.containsKey(cur)) {
      String match = map.get(cur);
      if (isMatch(input, j, match)) {
        return dfs(pattern, input, i + 1, j + match.length(), map, set);
      }
    } else {
      for (int k = j + 1; k <= input.length(); k++) {
        String match = input.substring(j, k);
        if (set.contains(match)) continue;
        map.put(cur, match);
        set.add(match);
        if (dfs(pattern, input, i + 1, k, map, set)) return true;
        map.remove(cur);
        set.remove(match);
      }
    }
    return false;
  }

  private boolean isMatch(String input, int j, String match) {
    if (input.length() - j < match.length()) return false;
    for (int i = 0; i < match.length(); i++) {
      if (input.charAt(j + i) != match.charAt(i)) return false;
    }
    return true;
  }
}
