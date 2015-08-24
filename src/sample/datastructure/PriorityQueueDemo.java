package sample.datastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PriorityQueueDemo {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/pq.in"));
		PriorityQueue<Integer> pq = new PriorityQueue<>(2,
				new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o1 - o2;
					}
				});
		while (true) {
			String s = in.next();
			if (s.equalsIgnoreCase("add")) {
				int a = in.nextInt();
				pq.add(a);
			} else if (s.equalsIgnoreCase("max")) {
				System.out.println(pq.poll());
			} else
				break;
		}
		Integer[] ar = { 1, 7, 5, 3, 5, 7, 9, 2 };
		// Arrays.sort(ar, new Comparator<Integer>() {
		// @Override
		// public int compare(Integer o1, Integer o2) {
		// return o2 - o1;
		// }
		// });
		// System.out.println(Arrays.toString(ar));

		Cell[] cells = new Cell[ar.length];
		for (int i = 0; i < ar.length; ++i)
			cells[i] = new Cell(i, i, ar[i]);
		
		Arrays.sort(cells);
		System.out.println(Arrays.toString(cells));
	}
}

class Cell implements Comparable<Cell> {
	public int x, y;
	public int step;

	public Cell(int x, int y, int step) {
		super();
		this.x = x;
		this.y = y;
		this.step = step;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + step + "]";
	}

	@Override
	public int compareTo(Cell o) {
		return this.step - o.step;
	}
}

