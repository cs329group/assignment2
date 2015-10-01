package assignment2;

public class test1 {
	public String str;
	public static void funct1() {
		System.out.println("Inside funct1");
	}

	public static void main(String[] args) {
		int val;
		funct1();
		val = funct2(8);
		val = funct2(-3);
	}

	public static int funct2(int param) {
		System.out.println("Inside funct2 with param " + param);
		return param * 2;
	}
}
