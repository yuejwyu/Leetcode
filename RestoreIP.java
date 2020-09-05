package Laicode;

public class RestoreIP {
  public List<String> Restore(String ip) {
    List<String> res = new ArrayList<>();
    if (ip == null) return res;
    helper(res, ip, new int[4], 0, 0);
    return res;
  }

  private void helper(List<String> res, String ip, int[] cur, int pos, int index) {
    if (index == ip.length() || pos == 4) {
      if (index == ip.length() && pos == 4) res.add(parse(cur));
      return;
    }
    if (cur[pos] == 0 && ip.charAt(index) == '0') {
      helper(res, ip, cur, pos + 1, index + 1);
    } else {
      cur[pos] = cur[pos] * 10 + (ip.charAt(index) - '0');
      if (cur[pos] > 0 && cur[pos] <= 255) {
        helper(res, ip, cur, pos, index + 1);
        helper(res, ip, cur, pos + 1, index + 1);
      }
      cur[pos] /= 10;
    }
  }

  private String parse(int[] cur) {
    return Integer.toString(cur[0]) + '.' 
          + Integer.toString(cur[1]) + '.'
          + Integer.toString(cur[2]) + '.'
          + Integer.toString(cur[3]);
  }
}
