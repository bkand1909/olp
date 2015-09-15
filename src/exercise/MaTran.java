package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class MaTran {

	private static int m, n;
	private static int x1, y1, x2, y2;
	private static int[][] val;
	private static int[][] d;
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {1, 0, -1, 0};
	private static Cell[][] trace;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"input/ma_tran.in"));
		StreamTokenizer st = new StreamTokenizer(br);

		st.nextToken();
		m = (int) st.nval;
		st.nextToken();
		n = (int) st.nval;
		st.nextToken();
		x1 = (int) st.nval - 1;
		st.nextToken();
		y1 = (int) st.nval - 1;
		st.nextToken();
		x2 = (int) st.nval - 1;
		st.nextToken();
		y2 = (int) st.nval - 1;

		val = new int[m][n];
		d = new int[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				st.nextToken();
				val[i][j] = (int) st.nval;
				d[i][j] = Integer.MAX_VALUE;
			}
		}

		dijkstraHeap();
		System.out.println(d[x2][y2]);
		for (Cell c = new Cell(x2, y2); c.x != x1 || c.y != y1; c = trace[c.x][c.y]) {
			System.out.printf("[%d, %d] = %d\n", c.x + 1, c.y + 1, val[c.x][c.y]);
		}
		System.out.printf("[%d, %d] = %d\n", x1 + 1, y1 + 1, val[x1][y1]);
	}

	private static void dijkstraHeap() {
		d[x1][y1] = val[x1][y1];
		PriorityQueue<Cell> queue = new PriorityQueue<>();
		trace = new Cell[m][n];
		trace[x1][y1] = new Cell(x1, y1);
		queue.add(new Cell(x1, y1));
		
		while (!queue.isEmpty()) {
			Cell cell = queue.poll();
			if (cell.x == x2 && cell.y == y2)
				return;
			for (int i = 0; i < 4; ++i) {
				int xx = cell.x + dx[i];
				int yy = cell.y + dy[i];
				if (xx < 0 || xx >= m || yy < 0 || yy >= n)
					continue;
				if (trace[xx][yy] == null && d[xx][yy] > d[cell.x][cell.y] + val[xx][yy]) {
					d[xx][yy] = d[cell.x][cell.y] + val[xx][yy];
					trace[xx][yy] = new Cell(cell.x, cell.y);
					queue.add(new Cell(xx, yy));
				}
			}
		}
	}

	static class Cell implements Comparable<Cell> {
		public int x, y;

		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Cell o) {
			return d[x][y] - d[o.x][o.y];
		}
	}
}
