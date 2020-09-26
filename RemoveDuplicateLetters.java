public class Solution {
  public String removeDuplicateLetters(String input) {
    // Write your solution here
    if (input == null || input.length() == 0) return "";
    Deque<Character> stack = new ArrayDeque<>();
    boolean[] seen = new boolean[26];
    int[] lastOccurence = new int[26];
    for (int i = 0; i < input.length(); i++) {
      lastOccurence[input.charAt(i) - 'a'] = i;
    }
    for (int i = 0; i < input.length(); i++) {
      char cur = input.charAt(i);
      if (!seen[cur - 'a']) {
        while (!stack.isEmpty() && stack.peekFirst() > cur && lastOccurence[stack.peekFirst() - 'a'] > i) {
          seen[stack.pollFirst() - 'a'] = false;
        }
        stack.offerFirst(cur);
        seen[cur - 'a'] = true;
      }
    }
    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pollFirst());
    }
    return sb.reverse().toString();
  }
}
