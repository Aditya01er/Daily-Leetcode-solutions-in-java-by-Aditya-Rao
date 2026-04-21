class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps){
        int n = source.length;

        UnionFind uf = new UnionFind(n);

        // Step 1: Connect indices
        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }

        // Step 2: Group indices
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            map.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> freq = map.get(root);

            freq.put(source[i], freq.getOrDefault(source[i], 0) + 1);
        }

        // Step 3: Match target
        int result = 0;

        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            Map<Integer, Integer> freq = map.get(root);

            if (freq.getOrDefault(target[i], 0) > 0) {
                freq.put(target[i], freq.get(target[i]) - 1);
            } else {
                result++; // mismatch
            }
        }

        return result;
    }
}

class UnionFind {
    int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }  
    
}