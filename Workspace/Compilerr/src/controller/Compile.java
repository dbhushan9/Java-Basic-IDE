	package controller;

import java.io.IOException;
import java.net.URI;
import java.util.*;

import javax.tools.*;

import model.RawC;


public class Compile
{   
    /** where shall the compiled class be saved to (should exist already) */
    
	private static String classOutputFolder = "F:\\";

    public static RawC obj;
    public static String err;
    public static int flag;
 
	/** compile your files by JavaCompiler */
    public static void compile(RawC robj)
    {
    	
    	obj=robj;
    	flag=0;
        //get system compiler:
    	JavaFileObject file = getJavaFileObject(robj);
        
        Iterable<? extends JavaFileObject> files = Arrays.asList(file);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
 
        // for compilation diagnostic message processing on compilation WARNING/ERROR
        MyDiagnosticListener c = new MyDiagnosticListener();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(c, Locale.ENGLISH, null);
        
        //specify classes output folder
        Iterable options = Arrays.asList("-d", classOutputFolder);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, c, options, null, files);
        Boolean result = task.call();
        if (result == true)
        {
            obj.setOutput("Compilation Sucessful");
            flag=1;
        }
    }

    public static class MyDiagnosticListener implements DiagnosticListener<JavaFileObject>
    {
        public void report(Diagnostic<? extends JavaFileObject> diagnostic)
        {
       
        	err+=	"\n" + diagnostic.getLineNumber()+
        			":"+diagnostic.getKind()+" : "+
        			": " + diagnostic.getMessage(Locale.ENGLISH);
        }
    }
 
    /** java File Object represents an in-memory java source file <br>
     * so there is no need to put the source file on hard disk  **/

    public static class InMemoryJavaFileObject extends SimpleJavaFileObject
    {
        private String contents = null;
 
        public InMemoryJavaFileObject(String className, String contents) throws Exception
        {
            super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.contents = contents;
        }
 
        public CharSequence getCharContent(boolean ignoreEncodingErrors)
                throws IOException
        {
            return contents;
        }
    }
 
    
    /** Get a simple Java File Object ,<br>
     * It is just for demo, content of the source code is dynamic in real use case */
    private static JavaFileObject getJavaFileObject(RawC obj)
    {
        StringBuilder contents = new StringBuilder(obj.getCode());
        JavaFileObject so = null;
        try
        {
            so = new InMemoryJavaFileObject("Test", contents.toString());
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return so;
    }
}