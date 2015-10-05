package testingFiles;

public class mediumTest1 {
	public String string1;
	public String string2;
	public Integer integer1;
	public String integer2;
	

	public static void main(String[] args) {
		int val;
		funct1();
		val = funct2(8);
		val = funct2(-3);
	}

	public static void funct1() {
		System.out.println("Inside funct1");
	}

	public static int funct2(int param) {
		System.out.println("Inside funct2 with param " + param);
		return param * 2;
	}
}
