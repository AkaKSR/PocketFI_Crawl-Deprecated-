import java.util.ArrayList;
import java.util.List;

public class Test {
	private static int[] tx;
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		tx = new int[list.size()];
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			tx[i] = list.get(i);
			System.out.println(tx[i]);
			System.out.println(list.get(i));
			System.out.println();
		}
	}
}
