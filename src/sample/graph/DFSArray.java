package sample.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DFSArray {
	static boolean[][] a;
	static int[] trace;
	static int n, s, f;

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File("input/path.in"));
		n = in.nextInt();
		a = new boolean[n + 1][n + 1];
		int m = in.nextInt();
		s = in.nextInt();
		f = in.nextInt();
		
		for (int i = 0; i < m; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			a[u][v] = true;
			a[v][u] = true;
		}
		in.close();

		trace = new int[n + 1];
		trace[s] = -1;
		DFS(s);
		
		System.out.println("From " + s + " you can visit:");
		for (int v = 1; v <= n; ++v)
			if (trace[v] != 0) System.out.print(v + " ");
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

	private static void DFS(int u) {
		for (int v = 1; v <= n; ++v)
			if (a[u][v] && trace[v] == 0) {
				trace[v] = u;
				DFS(v);
			}
	}
}
