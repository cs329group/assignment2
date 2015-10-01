package assignment2;

import java.util.ArrayList;

public class javaDataType {
	public ArrayList<String> methods = new	ArrayList<String>();
	public String filename = null;
	public javaDataType(String filename){
		this.filename = filename;
	}
	
	public ArrayList<String >getMethods(){
		return methods;
	}
}
