public class Solution {
  public String shortestPalindrome(String input) {
    // Write your solution here
    // KMP
    if (input == null || input.length() == 0) return "";
    String processed = process(input);
    int[] failure = KMPFailure(processed);
    return processed.substring(input.length() + 1, 
                                  processed.length() 
                                  - failure[processed.length() - 1])
                              + input;

  }

  private int[] KMPFailure(String str) {
    int[] failure = new int[str.length()];
    for (int i = 1; i < failure.length; i++) {
      int t = failure[i - 1];
      while (t > 0 && str.charAt(t) != str.charAt(i)) {
        t = failure[t - 1]; /////////////////////////////////////////////// !!!!!!!!!!!!!!!!!!!
      }
      if (str.charAt(t) == str.charAt(i)) t++;
      failure[i] = t;
    }
    return failure;
  }

  private String process(String str) {
    StringBuilder sb = new StringBuilder(str);
    sb.append('#');
    for (int i = str.length() - 1; i >= 0; i--) {
      sb.append(str.charAt(i));
    }
    return sb.toString();
  }
}
