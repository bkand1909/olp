package sample.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstraHeap {

	static int n, s, f;
	static ArrayList<Edge>[] adj;
	static int[] trace;
	static int[] d;
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

		adj = new ArrayList[n];
		for (int i = 0; i < n; ++i)
			adj[i] = new ArrayList<Edge>();

		while (m-- > 0) {
			st.nextToken();
			int u = (int) (st.nval - 1);
			st.nextToken();
			int v = (int) (st.nval - 1);
			st.nextToken();
			int length = (int) st.nval;
			adj[u].add(new Edge(v, length));
		}

		int min = dijkstra();
		printResult(min);
	}

	private static void printResult(int min) {
		if (min == Integer.MAX_VALUE) {
			System.out.printf("There is no path from %d to %d\n", s + 1, f + 1);
			return;
		}
		System.out.printf("Distance from %d to %d: %d\n", s + 1, f + 1, min);
		while (f != s) {
			System.out.printf("%d <- ", f + 1);
			f = trace[f];
		}
		System.out.println(s + 1);
	}

	private static int dijkstra() {
		// d[i] là độ dài đường đi ngắn nhất từ s tới i
		d = new int[n];
		// đầu tiên d[i] = dương vô cùng, d[s] = 0
		Arrays.fill(d, Integer.MAX_VALUE);
		d[s] = 0;
		// lưu đường đi: trace[i] sẽ là đỉnh kề trước i trong đường đi
		// ngắn nhất từ s tới f
		trace = new int[n];
		// đánh dấu xem i đã cố định hay chưa
		settled = new boolean[n];

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.add(new Vertex(s));

		while (!pq.isEmpty()) {
			// Tìm trong các đỉnh có nhãn tự do ra đỉnh u có d[u] nhỏ nhất
			Vertex u = pq.poll();
			// Thuật toán sẽ kết thúc khi các đỉnh tự do đều có nhãn +∞ hoặc đã
			// chọn đến đỉnh f
			if (u.id == f)
				return d[u.id];
			// Cố định nhãn đỉnh u
			settled[u.id] = true;
			// Dùng đỉnh u tối ưu nhãn những đỉnh tự do kề với u
			for (Edge e : adj[u.id]) {
				if (!settled[e.vertex] && d[e.vertex] > d[u.id] + e.length) {
					d[e.vertex] = d[u.id] + e.length;
					pq.add(new Vertex(e.vertex));
					trace[e.vertex] = u.id;
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	static class Vertex implements Comparable<Vertex> {
		public int id;

		@Override
		public int compareTo(Vertex other) {
			return d[id] - d[other.id];
		}

		public Vertex(int v) {
			this.id = v;
		}
	}

	static class Edge {
		public int vertex;
		public int length;

		// constructor
		public Edge(int vertex, int length) {
			this.vertex = vertex;
			this.length = length;
		}
	}
}
