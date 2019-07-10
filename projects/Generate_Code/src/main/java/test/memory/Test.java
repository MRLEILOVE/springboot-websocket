package test.memory;

public class Test {

	public static void main(String[] args) {
		Integer a = 100;
		Integer b = 100;
		System.out.println(a == b); // prints true

		Integer c = 200;
		Integer d = 200;
		System.out.println(c == d); // prints false
	}

}
