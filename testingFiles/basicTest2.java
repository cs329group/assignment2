package testingFiles;

public class basicTest2{
	//deleted field - string1, string2, integer3
	//added field - string3
	//changed field - integer1
	public String string3;
	private Integer integer1;


	public static void main(String[] args) {
		int val;
		funct1();
		val = funct2(8);
		val = funct2(-3);
	}

	//deleted function - funt1

	//changed method - added param2
	public static int funct2(int param, int param2) {
		System.out.println("Inside funct2 with param " + param);
		return param * 2;
	}
	
	// added function - funct3
	public void funct3() {
		
	}
}
