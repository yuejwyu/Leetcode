public class Solution {
  public List<Integer> primes(int target) {
    // Write your solution here
    // The given target is >= 2
    List<Integer> res = new ArrayList<>();
    boolean[] isComposite = new boolean[target + 1];
    for (int i = 2; i < isComposite.length; i++) {
      if (!isComposite[i]) {
        res.add(i);
        for (int j = i; j * i < isComposite.length; j++) {
          isComposite[j * i] = true;
        }
      }
    }
    return res;
  }
}
