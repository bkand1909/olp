package acm.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class P1119 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);

		in.nextToken();
		int n = (int) in.nval;
		in.nextToken();
		int m = (int) in.nval;
		in.nextToken();
		int k = (int) in.nval;

		boolean[][] b = new boolean[n + 1][m + 1];
		while (k-- > 0) {
			in.nextToken();
			int i = (int) in.nval;
			in.nextToken();
			int j = (int) in.nval;
			b[i][j] = true;
		}

		double[][] F = new double[n + 1][m + 1];
		for (int i = 1; i <= n; ++i)
			F[i][0] = F[i - 1][0] + 100;
		for (int j = 1; j <= m; ++j)
			F[0][j] = F[0][j - 1] + 100;
		final double L = 100 * Math.sqrt(2);
		for (int i = 1; i <= n; ++i)
			for (int j = 1; j <= m; ++j) {
				F[i][j] = Math.min(F[i - 1][j], F[i][j - 1]) + 100;
				if (b[i][j])
					F[i][j] = Math.min(F[i][j], F[i - 1][j - 1] + L);
			}
		System.out.println(Math.round(F[n][m]));
	}

}
