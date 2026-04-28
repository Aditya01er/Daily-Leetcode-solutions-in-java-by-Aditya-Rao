   class Solution {
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    
   
    int[][][] typeDirs = {
        {},
        {{0,1},{0,-1}},    
        {{1,0},{-1,0}},     
        {{0,-1},{1,0}},      
        {{0,1},{1,0}},       
        {{0,-1},{-1,0}},     
        {{0,1},{-1,0}}       
    };

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfs(grid, 0, 0, visited);
    }

    private boolean dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        
        if (i == m - 1 && j == n - 1) return true;
        visited[i][j] = true;

        for (int[] d : typeDirs[grid[i][j]]) {
            int ni = i + d[0];
            int nj = j + d[1];

            if (ni < 0 || nj < 0 || ni >= m || nj >= n || visited[ni][nj])
                continue;
            for (int[] back : typeDirs[grid[ni][nj]]) {
                if (ni + back[0] == i && nj + back[1] == j) {
                    if (dfs(grid, ni, nj, visited))
                        return true;
                }
            }
        }
        return false;
    }

}