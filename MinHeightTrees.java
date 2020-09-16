public class Solution {
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    // Write your solution here
    // use topology sort
    if (n <= 0) return new ArrayList<>();
    if (n == 1) return Arrays.asList(0);
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for (int i = 0; i < n; i++) {
      graph.put(i, new HashSet<>());
    }
    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }
    List<Integer> res = new ArrayList<>();
    Queue<Integer> queue = new ArrayDeque<>();
    // int[] count = new int[n];
    for (int i = 0; i < n; i++) {
      // count[i] = graph.get(i).size();
      if (graph.get(i).size() == 1) {
        queue.offer(i);
      }
    }
    while (!queue.isEmpty()) {
      int size = queue.size();
      boolean flag = size == graph.size();
      for (int i = 0; i < size; i++) {
        int cur = queue.poll();
        if (flag) {
          res.add(cur);
        } else {
          for (int nei : graph.get(cur)) {
            graph.get(nei).remove(cur);
            if (graph.get(nei).size() == 1) {
              queue.offer(nei);
            }
            // if (--count[nei] == 1) {
            //   queue.offer(nei);
            // }
          }
        }
        graph.remove(cur);
      }
    }
    return res;
  }
}
