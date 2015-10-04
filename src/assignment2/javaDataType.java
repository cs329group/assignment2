package assignment2;

import java.util.ArrayList;
import java.util.HashMap;

public class javaDataType {
	public HashMap<String, String> body = new HashMap<String, String>();
	public HashMap<String, String> parameters = new HashMap<String, String>();
	public ArrayList<String> methods = new	ArrayList<String>();
	public ArrayList<String> fields = new	ArrayList<String>();
	public String filename = null;
	public javaDataType(String filename){
		this.filename = filename;
	}
	
	public void addMethods(String method){
		methods.add(method);
	}
	
	public ArrayList<String >getMethods(){
		return methods;
	}
	
	public void addField(String field){
		fields.add(field);
	}
	
	public ArrayList<String> getFields(){
		return fields;
	}
	
	public void addParameter(String method, String parameter){
		parameters.put(method, parameter);
	}
	
	public HashMap<String, String> getParameters(){
		return parameters;
	}
	
	public void addBody(String method, String body){
		this.body.put(method, body);
	}
	
	public HashMap<String, String> getBody(){
		return body;
	}
}
