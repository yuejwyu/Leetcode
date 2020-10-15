public class Solution {
  public List<Integer> diffWaysToCompute(String input) {
    // Write your solution here
    if (input == null || input.length() == 0) return new ArrayList<>();
    List<Integer> numbers = new ArrayList<>();
    List<Character> operators = new ArrayList<>();
    List<List<List<Integer>>> memo = new ArrayList<>();
    preprocess(numbers, operators, input, memo);
    List<Integer> res = dp(numbers, operators, memo, 0, numbers.size() - 1);
    Collections.sort(res);
    return res;
  }

  private List<Integer> dp(List<Integer> numbers, List<Character> operators, 
                          List<List<List<Integer>>> memo, int left, int right) {
    if (memo.get(left).get(right).size() > 0 || left > right) {
      return memo.get(left).get(right);
    }
    if (left == right) {
      memo.get(left).get(right).add(numbers.get(left));
      return memo.get(left).get(right);
    }
    List<Integer> cur = memo.get(left).get(right);
    for (int i = left; i < right; i++) {
      char operator = operators.get(i);
      List<Integer> leftPart = dp(numbers, operators, memo, left, i);
      List<Integer> rightPart = dp(numbers, operators, memo, i + 1, right);
      for (int tmp1 : leftPart) {
        for (int tmp2 : rightPart) {
          if (operator == '+') {
            cur.add(tmp1 + tmp2);
          } else if (operator == '-') {
            cur.add(tmp1 - tmp2);
          } else {
            cur.add(tmp1 * tmp2);
          }
        }
      }
    }
    return cur;
  }

  private void preprocess(List<Integer> numbers, List<Character> operators, 
                          String input, List<List<List<Integer>>> memo) {
    int sum = 0;
    for (char ch : input.toCharArray()) {
      if (Character.isDigit(ch)) {
        sum = sum * 10 + (ch - '0');
      } else {
        numbers.add(sum);
        sum = 0;
        operators.add(ch);
      }
    }
    numbers.add(sum);
    for (int i = 0; i < numbers.size(); i++) {
      List<List<Integer>> tmp = new ArrayList<>();
      for (int j = 0; j < numbers.size(); j++) {
        tmp.add(new ArrayList<>());
      }
      memo.add(tmp);
    }
  }
}
