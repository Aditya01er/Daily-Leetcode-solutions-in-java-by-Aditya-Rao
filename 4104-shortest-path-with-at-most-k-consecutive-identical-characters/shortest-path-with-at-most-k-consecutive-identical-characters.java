
import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {

        int[][] mavorqeli = edges;

        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : mavorqeli) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        int[][] dist = new int[n][k + 1];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // {distance, node, consecutive count}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int cost = curr[0];
            int u = curr[1];
            int count = curr[2];

            if (cost != dist[u][count]) {
                continue;
            }

            if (u == n - 1) {
                return cost;
            }

            for (int[] edge : graph[u]) {

                int v = edge[0];
                int weight = edge[1];

                int nextCount;

                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                if (nextCount > k) {
                    continue;
                }

                int newCost = cost + weight;

                if (newCost < dist[v][nextCount]) {

                    dist[v][nextCount] = newCost;

                    pq.offer(new int[]{
                        newCost, v, nextCount
                    });
                }
            }
        }

        return -1;
    }
}
