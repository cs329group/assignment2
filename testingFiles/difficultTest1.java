package assignment2;

public class difficultTest1 {
	public String string1 = "test";
	public static String string2;
	private Integer integer1 = 1;
	private final static String integer2;
	

	public static void main(String[] args) {
		int val;
		funct1();
		val = funct2(8);
		val = funct2(-3);
	}

	public void funct1(int param, String param2) {
		System.out.println("Inside funct1");
	}

	public int funct2(int param) {
		System.out.println("Inside funct2 with param " + param);
		return param * 2;
	}
	
	public int funct3(int param) {
		System.out.println("Inside funct3 with param " + param);
		return param * 2;
	}
}
}
