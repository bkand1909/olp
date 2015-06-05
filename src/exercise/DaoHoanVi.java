package exercise;

import java.util.Arrays;
import java.util.Scanner;

public class DaoHoanVi {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n + 1];
		for (int i = 1; i <= n; ++i)
			a[i] = in.nextInt();
		in.close();
		
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j <= n; ++j)
				if (i == a[j] && i != j) {
					if (j != n)
						dao(j, a);
					dao(i, a);
				}
		}
	}
	
	public static void dao(int k, int[] a) {
		for (int i = k, j = a.length - 1; i < j; i++, j--) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		System.out.println(Arrays.toString(Arrays.copyOfRange(a, 1, a.length)));
	}
}
