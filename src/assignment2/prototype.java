package assignment2;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.util.ArrayList;

public class prototype {
	public static javaDataType tempFile;
	public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
		String file1name = System.getProperty("user.dir")+"\\testingFiles\\basicTest1.java";
		String file2name = System.getProperty("user.dir")+"\\testingFiles\\basicTest2.java";

        FileInputStream file1 = new FileInputStream(file1name);
        FileInputStream file2 = new FileInputStream(file2name); 
        
        javaDataType file1Data = new javaDataType(file1name);
        javaDataType file2Data = new javaDataType(file2name);
       
        CompilationUnit cu1;
        CompilationUnit cu2;
        try {
            // parse the file
            cu1 = JavaParser.parse(file1);
            cu2 = JavaParser.parse(file2);
        } finally {
        	file1.close();
        	file2.close();

        }
        
        tempFile = file1Data;
        // visit and print the methods names
        new variableVisitor().visit(cu1, null);
        new MethodVisitor().visit(cu1, null);
        
        tempFile = file2Data;
        new variableVisitor().visit(cu2, null);
        new MethodVisitor().visit(cu2, null);
        
        System.out.println("File 1 Fields: " + file1Data.getFields().toString());
        System.out.println("File 2 Fields: " + file2Data.getFields().toString());
        System.out.println("");
        System.out.println("File 1 Methods: " + file1Data.getMethods().toString());
        System.out.println("File 2 Methods: " + file2Data.getMethods().toString());
        System.out.println("");
        System.out.println("File 1 Body: " + file1Data.getMethods().toString());
        System.out.println("File 2 Body: " + file2Data.getMethods().toString());
        System.out.println("");
        System.out.println("File 1 Parameters: " + file1Data.getParameters().toString());
        System.out.println("File 2 Parameters: " + file2Data.getParameters().toString());

    }

    private static class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
        	String method = n.getName();
        	String body = n.getBody().toString();
        	String params;
        	try{
        		params =n.getParameters().toString();
        	}
        	catch (NullPointerException e){
        		params = null;
        	}
        	
            tempFile.addMethods(method);
            tempFile.addBody(method,  n.getBody().toString());
            tempFile.addParameter(method, params);
        }
    }
    
    private static class variableVisitor extends VoidVisitorAdapter {

    	@Override
    	public void visit(FieldDeclaration n, Object arg) {
    	    tempFile.addField(n.toString());
    	    super.visit(n, arg);
    	}
    }
}