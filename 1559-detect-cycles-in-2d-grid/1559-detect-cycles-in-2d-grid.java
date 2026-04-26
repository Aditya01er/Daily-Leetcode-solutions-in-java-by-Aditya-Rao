
    class Solution {
    int m, n;
    boolean[][] visited;

    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, int x, int y, int px, int py, char ch) {
        if (visited[x][y]) return true;

        visited[x][y] = true;

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            if (grid[nx][ny] != ch) continue;

            // parent skip
            if (nx == px && ny == py) continue;

            if (dfs(grid, nx, ny, x, y, ch)) {
                return true;
            }
        }
        return false;
    }

}