package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import model.CompiledC;

public class Executor{
	
	public static CompiledC cobj;
	public static void execute(CompiledC obj) throws Exception
	{
		cobj=obj;
		 Timer t=new Timer();

	        Process process = Runtime.getRuntime().exec("java -cp F:\\ Test ");

	        TimerTask killer = new TimeOutProcessKiller(process);

	        t.schedule(killer,5000);                                            //TimeOUT

	        InputStream errin = process.getErrorStream();                   
	        OutputStream stdin = process.getOutputStream();
	        InputStream stdout = process.getInputStream();

	        BufferedReader errorOutput = new BufferedReader(new InputStreamReader(errin));
	        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
	        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

	        writer.write(cobj.getInput());
	        writer.flush();
	        writer.close(); //if Not CLosed then the driven program waits for the input 

	        Scanner scanout = new Scanner(stdout);
	        Scanner scanerror = new Scanner(errin);
	        cobj.setOutput("");
	        while (scanout.hasNextLine()||scanerror.hasNextLine()) {
	            if(scanerror.hasNextLine()) 
	            	 cobj.setOutput(cobj.getOutput() + scanerror.nextLine()+"\n");
	            else
	            	cobj.setOutput(cobj.getOutput() + scanout.nextLine()+"\n");
	        }

	        killer.cancel();
	      //  process.waitFor();
	}
    
}

class TimeOutProcessKiller extends TimerTask{
    private Process p;
    public TimeOutProcessKiller(Process p){
        this.p=p;
    }
    public void run() {
        p.destroy();
    }
}   