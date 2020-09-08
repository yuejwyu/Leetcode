public class ChessHorseSolution {
    private static final int[][] DIRECTIONS = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                                                {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    public int hourseMinStep(int m, int n, int[] target) { // follow up: m = n = 6 * (target[0] + target[1]);
        // TC: O(mn); SC: O(mn)
        // corner case
        if (m <= 0 || n <= 0) return -1;
        // bfs data structure
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        // initiate
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int res = 0;
        // bfs
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0] == target[0] && cur[1] == target[1]) return res;
                for (int[] direction: DIRECTIONS) {
                    int newX = cur[0] + direction[0];
                    int newY = cur[1] + direction[1];
                    if (isInBound(newX, newY, m, n)
                            && !visited[newX][newY]) {
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            res++;
        }
        return -1;
    }

    private boolean isInBound(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
