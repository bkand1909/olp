package sample.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFSArrayList {
	private static int n, s, f;
	private static ArrayList<Integer>[] adj;
	private static int[] trace;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(new File("input/path.in"));
		n = in.nextInt();
		int m = in.nextInt();
		s = in.nextInt();
		f = in.nextInt();

		adj = new ArrayList[n + 1];
		for (int i = 0; i <= n; ++i)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < m; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		in.close();

		trace = new int[n + 1];
		trace[s] = -1;
		BFS();

		System.out.println("From " + s + " you can visit:");
		for (int v = 1; v <= n; ++v)
			if (trace[v] != 0)
				System.out.print(v + " ");
		System.out.println();
		System.out.println("Path from " + s + " to " + f + ":");
		if (trace[f] == 0) {
			System.out.println("not found!");
		} else {
			while (f != s) {
				System.out.print(f + " <- ");
				f = trace[f];
			}
			System.out.println(s);
		}
	}

	private static void BFS() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v : adj[u]) {
				if (trace[v] == 0) {
					trace[v] = u;
					queue.add(v);
				}
			}
		}
	}
}
