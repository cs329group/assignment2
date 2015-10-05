package assignment2;

import japa.parser.ast.body.FieldDeclaration;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class javaDataType {
	public HashMap<String, String> body = new HashMap<String, String>();
	public HashMap<String, String> parameters = new HashMap<String, String>();
	public ArrayList<String> methods = new	ArrayList<String>();
	public ArrayList<FieldDeclaration> fields = new	ArrayList<FieldDeclaration>();
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
	
	public void addField(FieldDeclaration field){
		fields.add(field);
	}
	
	public ArrayList<FieldDeclaration> getFields(){
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
	
	/**
	 * Checks this javaDataType against another javaDataType assuming this object is the older file and
	 * the object given in the parameters is the newer/updated file. A method that is the same name but different
	 * parameters is considered an added method.
	 * @param updatedFile The file looking for added functions
	 * @return An ArrayList of methods that were added in updatedFile.
	 */
	public ArrayList<String> checkMethodsHelper(javaDataType updatedFile){
		/*
		 * Process: Go through each function. If it exists in updatedFile but not in this, then it is an added method
		 */
		ArrayList<String> addedMethods = new ArrayList<String>();
		ArrayList<String> duplicateMethods = new ArrayList<String>();
		
		for(String method : methods){
			if(!updatedFile.methods.contains(method)){
				// It isn't in there at all, time to say it was added, easy
				addedMethods.add(method);
			}
			else{
				// Ok, both have it. If there is a greater # of the method in updatedFile, then clearly a method was added
				// We need to count each method each list to see
					
				// TODO: This method for duplicates might not work too well.
				// If hashmap is used then this might be the only way.
				if(duplicateMethods.contains(method)) continue;
				
				int updatedCount = 0;
				int originalCount = 0;
				
				// First: Checking updatedfile methods
				Iterator i = updatedFile.methods.listIterator();
				while(i.hasNext()){
					String m = (String) i.next();
					if(m.equals(method)) updatedCount++;
				}
				
				// First: Checking updatedfile methods
				i = methods.listIterator();
				while(i.hasNext()){
					String m = (String) i.next();
					if(m.equals(method)) originalCount++;
				}
				
				if(updatedCount > originalCount) addedMethods.add(method+"-duplicated "+Integer.toString(updatedCount-originalCount)+" times");
				
			}
		}
		
		return addedMethods;
		
	}
	 
	public ArrayList<String> checkForAddedMethods (javaDataType updatedFile){
		
		// Pretty sure this is valid. If a method exists in this but not in updated file,
		// Then checking for added methods where this is considered the added file is perfectly valid.
		ArrayList<String> m = updatedFile.checkMethodsHelper(this);
		System.out.print("Added methods: ");
		int i = 0;
		for (String string : m) {
			if(i > 0){ System.out.print(", "); i++;}
			System.out.print(string);
		}
		System.out.println();
		return m;
	}
	
	public ArrayList<String> checkForDeletedMethods (javaDataType updatedFile){
		
		// Pretty sure this is valid. If a method exists in this but not in updated file,
		// Then checking for added methods where this is considered the added file is perfectly valid.
		ArrayList<String> m = this.checkMethodsHelper(updatedFile);
		System.out.print("Removed methods: ");
		int i = 0;
		for (String string : m) {
			if(i > 0){ System.out.print(", "); i++;}
			System.out.print(string);
		}
		System.out.println();
		return m;
	}
	
	// DOES NOT HANDLE DUPLICATES
	public ArrayList<String> checkForChangedBodies (javaDataType updatedFile){
		
		ArrayList<String> changedMethods = new ArrayList<String>();
		
		for (java.util.Map.Entry<String, String> entry : body.entrySet()) {
			String key = entry.getKey();
			// Doesn't contain the key, no need to check for body
			if(!updatedFile.body.containsKey(key)) continue;
			
			String originalBodyString = entry.getValue();
			String updatedBodyString = updatedFile.body.get(key);
			
			if(!originalBodyString.equals(updatedBodyString)) changedMethods.add(key);
		}
		
		System.out.print("Methods where body was changed: ");
		int i = 0;
		for (String string : changedMethods) {
			if(i > 0){ System.out.print(", "); i++;}
			System.out.print(string);
		}
		System.out.println();
		
		return changedMethods;
	}
	
	
}
