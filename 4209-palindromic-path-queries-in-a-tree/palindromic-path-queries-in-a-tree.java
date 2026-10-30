
import java.util.*;

class Solution {

    List<Integer>[] graph;
    int[] parent, depth, size, heavy;
    int[] head, pos;
    int[] seg;
    int segSize;
    int timer;

    int getMask(char c) {
        return 1 << (c - 'a');
    }

    void buildSegmentTree(int[] values) {
        segSize = 1;
        while (segSize < values.length) {
            segSize <<= 1;
        }

        seg = new int[2 * segSize];

        for (int i = 0; i < values.length; i++) {
            seg[segSize + i] = values[i];
        }

        for (int i = segSize - 1; i > 0; i--) {
            seg[i] = seg[i << 1] ^ seg[i << 1 | 1];
        }
    }

    void update(int index, int value) {
        int p = segSize + index;
        seg[p] = value;

        while (p > 1) {
            p >>= 1;
            seg[p] = seg[p << 1] ^ seg[p << 1 | 1];
        }
    }

    int rangeQuery(int l, int r) {
        int result = 0;
        l += segSize;
        r += segSize;

        while (l < r) {
            if ((l & 1) == 1) {
                result ^= seg[l++];
            }

            if ((r & 1) == 1) {
                result ^= seg[--r];
            }

            l >>= 1;
            r >>= 1;
        }

        return result;
    }

    int queryPath(int u, int v) {
        int result = 0;

        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            result ^= rangeQuery(pos[head[u]], pos[u] + 1);
            u = parent[head[u]];
        }

        int l = Math.min(pos[u], pos[v]);
        int r = Math.max(pos[u], pos[v]);

        result ^= rangeQuery(l, r + 1);

        return result;
    }

    public List<Boolean> palindromePath(
        int n,
        int[][] edges,
        String s,
        String[] queries
    ) {

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];

        Arrays.fill(parent, -1);
        Arrays.fill(heavy, -1);

        // Iterative DFS
        int[] order = new int[n];
        int[] stack = new int[n];

        int top = 0;
        int count = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : graph[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // Subtree sizes and heavy child
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];

            size[u] = 1;
            int maxSize = 0;

            for (int v : graph[u]) {
                if (parent[v] == u) {
                    size[u] += size[v];

                    if (size[v] > maxSize) {
                        maxSize = size[v];
                        heavy[u] = v;
                    }
                }
            }
        }

        // Heavy-Light Decomposition
        int[] nodeStack = new int[n];
        int[] headStack = new int[n];

        top = 0;
        timer = 0;

        nodeStack[top] = 0;
        headStack[top++] = 0;

        while (top > 0) {
            int start = nodeStack[--top];
            int chainHead = headStack[top];

            for (int u = start; u != -1; u = heavy[u]) {
                head[u] = chainHead;
                pos[u] = timer++;

                for (int v : graph[u]) {
                    if (parent[v] == u && v != heavy[u]) {
                        nodeStack[top] = v;
                        headStack[top++] = v;
                    }
                }
            }
        }

        // Segment tree initialization
        int[] values = new int[n];

        for (int u = 0; u < n; u++) {
            values[pos[u]] = getMask(s.charAt(u));
        }

        buildSegmentTree(values);

        // Process queries
        List<Boolean> answer = new ArrayList<>();
        char[] chars = s.toCharArray();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                chars[u] = c;
                update(pos[u], getMask(c));

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = queryPath(u, v);

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }
}
