package assignment2;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

// TODO: Change this to false when done debugging and creating application.


public class prototype {
	public static final boolean DEBUG = true;
	public static javaDataType tempFile;
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	public static void main(String[] args) throws Exception {
		/*
		 * arg[0] is file 1 (original file) and arg[1] is file 2 (new file).
		 * Checking args and if files exist. 
		 */
		if(args.length != 2 && !DEBUG){
			// TODO: Once program name is figured out, add it here.
		    System.out.println("Arguments are invalid. Use java <program TODO CHANGE THIS> <file1> <file2>");
		}
		
		String file1name;
		if(DEBUG && isWindows()) file1name = System.getProperty("user.dir")+"\\testingFiles\\basicTest1.java";
		else if(DEBUG && isMac()) file1name = System.getProperty("user.dir")+"/testingFiles/basicTest1.java";
		else file1name = args[0];
		
		String file2name;
		if(DEBUG && isWindows()) file2name = System.getProperty("user.dir")+"\\testingFiles\\basicTest2.java";
		else if(DEBUG && isMac()) file2name = System.getProperty("user.dir")+"/testingFiles/basicTest2.java";
		else file2name = args[1];
		
		File file1 = new File(file1name);
		
		if(!file1.exists() || file1.isDirectory()) { 
		    System.out.println(file1name);
		    System.out.println("File 1 is not valid. Exitting...");
		    System.exit(0);
		}
		file1 = null;
		
		
		File file2 = new File(file2name);
		
		if(!file2.exists() || file2.isDirectory()) { 
		    System.out.println(file2name);
		    System.out.println("File 2 is not valid. Exitting...");
		    System.exit(0);
		}
		file2 = null;
		
		/*
		 * Ok, we've checked the files and it seems that they're at least present. 
		 * Time to start working on the input stream
		 */

        FileInputStream file1InputStream = new FileInputStream(file1name);
        FileInputStream file2InputStream = new FileInputStream(file2name); 
        
        javaDataType file1Data = new javaDataType(file1name);
        javaDataType file2Data = new javaDataType(file2name);
       
        CompilationUnit cu1;
        CompilationUnit cu2;
        try {
            // parse the file
            cu1 = JavaParser.parse(file1InputStream);
            cu2 = JavaParser.parse(file2InputStream);
        } finally {
        	file1InputStream.close();
        	file2InputStream.close();
        }
        
        /*
         * Hooray, it parsed! Now we have a variety of things to check:
         * 1) A method is added in file2
         * 2) A method is deleted in file2
         * 3) Changed the body of a method
         * 4) A method is relocated
         * 5) Add a field
         * 6) Delete a field
         * 7) Change the definition of an instance field initializer
         */
        
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
    
    
    
    
    /*
     * Helper functions to determine files
     */
    public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
		
	}

	public static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}

}