package assignment2;

public class difficultTest2 {
	//change public modifier
	public Integer integer1 = 1;
	//change final modifier
	private final static String integer2;
	// change string1 field value
	public String string1 = "changed";
	//change static modifier on string2
	public static String string2;
	
	//changed method body
	public static void main(String[] args) {
		int val;
		val = funct2(8);
		val = funct2(-3);
	}

	//changed the parameter type
	public int funct2(String param) {
		System.out.println("Inside funct2 with param " + param);
		return param * 2;
	}
	
	// changed order of method inside the class
	public void funct1(int param, String param2) {
		System.out.println("Inside funct1");
	}

	//chnaged the public modifier
	private int funct3(int param) {
		System.out.println("Inside funct3 with param " + param);
		return param * 2;
	}
}
