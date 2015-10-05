package assignment2;

import japa.parser.ast.body.FieldDeclaration;

import java.util.ArrayList;
import java.util.Scanner;

public class FieldChecker {
	public final int FIELD_ADDED = 0;
	public final int FIELD_DELETED = 1;
	public final int FIELD_CHANGED_ADD_INIT = 2;
	public final int FIELD_CHANGED_DELETE_INIT = 3;
	public final int FIELD_CHANGED_INIT_VALUE = 4;
	public final int FIELD_CHANGED_MODIFIER = 5;
	
	public javaDataType d1, d2;
	
	public ArrayList<FieldReporter> reports;
	
	public FieldChecker(javaDataType d1, javaDataType d2){
		this.d1 = d1;
		this.d2 = d2;
		
		reports = new ArrayList<>();
		
		ArrayList<FieldDeclaration> fields1 = d1.getFields();
		ArrayList<FieldDeclaration> fields2 = d2.getFields();
		
		
		String varName = "";
		
		
		boolean isAdded = true;
		for(int i = 0; i < fields2.size(); i++){
			FieldDeclaration dec2 = fields2.get(i);
			varName = getVarName(dec2);
			getInitializer(dec2.getVariables().get(0).toString());
			//Now go through original file and try to find the variable
			for(int j = 0; j < fields1.size(); j++){
				FieldDeclaration dec1 = fields1.get(j);
				String origVar = getVarName(dec1);
				if(varName.equals(origVar)){
					isAdded = false; 
					//variable found, compare changes
					
					if(dec1.getModifiers() != dec2.getModifiers()){
						reports.add(new FieldReporter(dec2, FIELD_CHANGED_MODIFIER));
					}
					
					String init1 = getInitializer(dec1.getVariables().get(0).toString());
					String init2 = getInitializer(dec2.getVariables().get(0).toString());
					if(!init1.equals(init2)){
						//initialization is different
						if(init1.equals("")){
							reports.add(new FieldReporter(dec2, FIELD_CHANGED_ADD_INIT));
						}else if(init2.equals("")){
							reports.add(new FieldReporter(dec2, FIELD_CHANGED_DELETE_INIT));
						}else{
							reports.add(new FieldReporter(dec2, FIELD_CHANGED_INIT_VALUE));
						}
					}
					
				}
			}
			
			if(isAdded){
				reports.add(new FieldReporter(dec2, FIELD_ADDED));
			}
			isAdded = true;
		}
		
		
		
		//Check for deleted fields
		boolean isDeleted = true;
		for(int i = 0; i < fields1.size(); i++){
			String varName1 = getVarName(fields1.get(i));
			for(int j = 0; j < fields2.size(); j++){
				String varName2 = getVarName(fields2.get(j));
				if(varName1.equals(varName2)){
					isDeleted = false;
				}
			}
			
			if(isDeleted){
				reports.add(new FieldReporter(fields1.get(i), FIELD_DELETED));
			}
			isDeleted = true;
		}
		
		System.out.println(reports);
		
	}
	
	public String getVarName(FieldDeclaration f){
		Scanner scan = new Scanner(f.getVariables().get(0).toString());
		return scan.next();
	}
	
	public String getInitializer(String variable){
		Scanner scan = new Scanner(variable);
		scan.useDelimiter("\\s*=\\s*");
		scan.next(); //skip past variable name and = sign
		
		if(scan.hasNext()){
			return scan.next();
		}
		return "";
	}
	
	public ArrayList<FieldReporter> getReports(){
		return reports;
	}

	
	public class FieldReporter{
		
		FieldDeclaration field;
		int modification;
		
		public FieldReporter(FieldDeclaration f, int m){
			field = f;
			modification = m;
		}
		
		@Override
		public String toString(){
			return getVarName(field) + " " + getMessage();
		}
		
		public String getMessage(){
			String a = "";
			switch(modification){
				case FIELD_ADDED: a = "This field was added.";
					break;
				case FIELD_DELETED: a = "This field was deleted.";
					break;
				case FIELD_CHANGED_ADD_INIT: a = "Added initialization to this field.";
					break;
				case FIELD_CHANGED_DELETE_INIT: a = "Deleted initialization to this field.";
					break;
				case FIELD_CHANGED_INIT_VALUE: a = "Modified initialization to this field.";
					break;
				case FIELD_CHANGED_MODIFIER: a = "Field modifier for this field has changed.";
					break;
			}
			
			return a;
		}
	}
}
