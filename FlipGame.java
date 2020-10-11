public class Solution {
  public boolean canWin(String input) {
    // Write your solution here
    Set<Integer> canFlipIndex = new HashSet<>();
    for(int i = 0; i < input.length() - 1; i++) {
      if (input.charAt(i) == '+' && input.charAt(i + 1) == '+') {
        canFlipIndex.add(i);
      }
    }
    return dfs(canFlipIndex);
  }

  private boolean dfs(Set<Integer> canFlipIndex) {
    Set<Integer> copy = new HashSet<>(canFlipIndex);
    for (int i : copy) {
      canFlipIndex.remove(i);
      boolean removeBefore = canFlipIndex.remove(i - 1);
      boolean removeAfter = canFlipIndex.remove(i + 1);
      boolean canWin = !dfs(canFlipIndex); ////////////////////////////// !!!!!!!!!!!
      canFlipIndex.add(i);
      if (removeBefore) canFlipIndex.add(i - 1);
      if (removeAfter) canFlipIndex.add(i + 1);
      if (canWin) return true; ////////////////////////////////////////// !!!!!!!!!!!
    }
    return false;
  }
}
