package vn.spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class MTWALK {

	static int m, n;
	static int[][] a;
	static final int[] di = { 0, 1, 0, -1 };
	static final int[] dj = { 1, 0, -1, 0 };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);

		st.nextToken();
		m = (int) st.nval;
		st.nextToken();
		n = (int) st.nval;

		a = new int[m + 2][n + 2];
		for (int i = 1; i <= m; ++i)
			for (int j = 1; j <= n; ++j) {
				st.nextToken();
				a[i][j] = (int) st.nval;
			}

		int minDiff = 200, left = 0, right = 110;
		while (left <= right) {
			int diff = (left + right) / 2;
			boolean ok = false;
			for (int low = 0; low <= 110 - diff; ++low) {
				int high = low + diff;
				visited = new boolean[m + 2][n + 2];
				if (DFS(1, 1, low, high)) {
					ok = true;
					minDiff = Math.min(diff, minDiff);
					break;
				}
			}
			if (ok)
				right = diff - 1;
			else
				left = diff + 1;
		}
		
		System.out.println(minDiff);
	}

	static boolean DFS(int i, int j, int low, int high) {
		if (a[i][j] < low || a[i][j] > high)
			return false;
		if (i == m && j == n)
			return true;
		for (int k = 0; k < 4; ++k) {
			int ii = i + di[k];
			int jj = j + dj[k];
			if (ii > 0 && ii <= m && jj > 0 && jj <= n && !visited[ii][jj]) {
				visited[ii][jj] = true;
				if (DFS(ii, jj, low, high))
					return true;
				visited[ii][jj] = false;
			}
		}
		return false;
	}
}
