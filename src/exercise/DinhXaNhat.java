package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class DinhXaNhat {

	static int uMax, vMax;
	static int[] disFromRoot;
	static int n;
	static int[][] dis;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("input/dinh_xa_nhat.in"));
		StreamTokenizer st = new StreamTokenizer(br);
		
		st.nextToken();
		n = (int) st.nval;

		dis = new int[n][n];

		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			int u = (int) st.nval - 1;
			st.nextToken();
			int v = (int) st.nval - 1;
			st.nextToken();
			int d = (int) st.nval;
			dis[u][v] = d;
			dis[v][u] = d;
		}

		disFromRoot = new int[n];
		Arrays.fill(disFromRoot, Integer.MAX_VALUE);
		uMax = 0;
		DFS(0, 0);
		vMax = uMax;
		Arrays.fill(disFromRoot, Integer.MAX_VALUE);
		DFS(uMax, 0);
		System.out.println((uMax + 1) + " " + (vMax + 1));
	}

	private static void DFS(int u, int currentDis) {
		disFromRoot[u] = currentDis;
		if (disFromRoot[uMax] < disFromRoot[u])
			uMax = u;
		for (int v = 0; v < n; ++v) {
			if (disFromRoot[v] == Integer.MAX_VALUE && dis[u][v] > 0) {
				DFS(v, currentDis + dis[u][v]);
			}
		}
	}
}
