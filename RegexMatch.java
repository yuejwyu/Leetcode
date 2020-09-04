package Laicode;

public class RegexMatch {
  public boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0, new boolean[s.length() + 1][p.length()]);
    }
    
    private boolean isMatch(String s, String p, int i, int j, boolean[][] visited) {
        if (j == p.length()) {
            return i == s.length();
        }
        
        if (visited[i][j]) return false;
        visited[i][j] = true;
        
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            if (isMatch(s, p, i, j + 2, visited)) return true;
            if (i < s.length() && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))
                && isMatch(s, p, i + 1, j, visited)) return true;
        } else {
            if (i == s.length()) return false; ////////////////////////////////////////// !!!!!!!!!!!!!!!
            if (p.charAt(j) == '.') {
                if (isMatch(s, p, i + 1, j + 1, visited)) return true;
            } else {
                if (p.charAt(j) == s.charAt(i) && isMatch(s, p, i + 1, j + 1, visited)) return true;
            }
        }
                
        return false;
    }
}
