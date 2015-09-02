package sample.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstra {

	static int n, s, f;
	static List<List<Edge>> adj;
	static int[] trace, d;
	static boolean[] settled;

	public static void main(String[] args) throws IOException {

		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader(
				"input/minpath.in"));
		StreamTokenizer st = new StreamTokenizer(br);

		st.nextToken();
		n = (int) st.nval;
		st.nextToken();
		int m = (int) st.nval;
		st.nextToken();
		s = (int) (st.nval - 1);
		st.nextToken();
		f = (int) (st.nval - 1);

		adj = new ArrayList<List<Edge>>(n);
		for (int i = 0; i < n; ++i)
			adj.add(new ArrayList<Edge>());
		while (m-- > 0) {
			st.nextToken();
			int u = (int) (st.nval - 1);
			st.nextToken();
			int v = (int) (st.nval - 1);
			st.nextToken();
			int length = (int) st.nval;
			adj.get(u).add(new Edge(v, length));
		}

		dijkstra();
		printResult();
	}

	private static void printResult() {
		if (d[f] == Integer.MAX_VALUE) {
			System.out.printf("There is no path from %d to %d\n", s + 1, f + 1);
			return;
		}
		System.out.printf("Distance from %d to %d: %d\n", s + 1, f + 1, d[f]);
		while (f != s) {
			System.out.printf("%d <- ", f + 1);
			f = trace[f];
		}
		System.out.println(s + 1);
	}

	private static void dijkstra() {
		d = new int[n];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[s] = 0;
		trace = new int[n];
		settled = new boolean[n];

		while (true) {
			// Tìm trong các đỉnh có nhãn tự do ra đỉnh u có d[u] nhỏ nhất
			int u = -1, min = Integer.MAX_VALUE;
			for (int i = 0; i < n; ++i)
				if (!settled[i] && d[i] < min) {
					min = d[i];
					u = i;
				}
			// Thuật toán sẽ kết thúc khi các đỉnh tự do đều có nhãn +∞ hoặc đã
			// chọn đến đỉnh f
			if (u == -1 || u == f)
				break;
			// Cố định nhãn đỉnh u
			settled[u] = true;
			// Dùng đỉnh u tối ưu nhãn những đỉnh tự do kề với u
			for (Edge e : adj.get(u)) {
				if (!settled[e.vertex] && d[e.vertex] > d[u] + e.length) {
					d[e.vertex] = d[u] + e.length;
					trace[e.vertex] = u;
				}
			}
		}
	}
}

class Edge {
	public int vertex;
	public int length;

	public Edge(int vertex, int length) {
		this.vertex = vertex;
		this.length = length;
	}
}
