package sample.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapDemo {

	public static void main(String[] args) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("first", 1);
		map.put("second", 2);
		map.put("first", 3);
		for (Entry<String, Integer> pair : map.entrySet()) {
			System.out.println(pair.getKey() + " " + pair.getValue());
			System.out.println(map.get(pair.getKey()));
		}
	}
}
