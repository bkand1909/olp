package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class SoTamSau {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);

		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			int x = (int) st.nval;
			if (x < 0)
				break;
			solve(x);
		}
	}

	private static void solve(int x) {
		int[] powModX = new int[201]; // save 10^i mod x
		powModX[1] = 10 % x;
		for (int i = 2; i <= 200; ++i)
			powModX[i] = (powModX[i - 1] * 10) % x;

		boolean[][] added = new boolean[201][201]; // check if [x][y] was added to queue
		Queue<Number> queue = new LinkedList<>();
		queue.add(new Number(0, 1, 6 % x));
		added[0][1] = true;
		queue.add(new Number(1, 0, 8 % x));
		added[1][0] = true;

		while (!queue.isEmpty()) {
			Number n = queue.poll();
			if (n.modX == 0) {
				for (int i = 0; i < n.nEight; ++i)
					System.out.print(8);
				for (int i = 0; i < n.nSix; ++i)
					System.out.print(6);
				System.out.println();
				return;
			}
			if (n.nEight + n.nSix < 200) {
				if (!added[n.nEight][n.nSix + 1]) {
					queue.add(new Number(n.nEight, n.nSix + 1, (n.modX * 10 + 6) % x));
					added[n.nEight][n.nSix + 1] = true;
				}
				if (!added[n.nEight + 1][n.nSix]) {
					queue.add(new Number(n.nEight + 1, n.nSix, (8 * powModX[n.nEight + n.nSix] + n.modX) % x));
					added[n.nEight + 1][n.nSix] = true;
				}
			}
		}
		System.out.println(-1);
	}

	static class Number {
		public int nEight;
		public int nSix;
		public int modX;

		public Number(int nEights, int nSixs, int modX) {
			super();
			this.nEight = nEights;
			this.nSix = nSixs;
			this.modX = modX;
		}
	}
}
