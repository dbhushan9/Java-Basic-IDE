package model;

public class CompiledC {
	
	public String code;
	private String output;
	private String input;
	
	public CompiledC()
	{
		code="";
		output="";
		input="";	
	}
	public String getCode()
	{
		return code;
	}
	public String getOutput()
	{
		return output;
	}
	public String getInput()
	{
		return input;
	}
	
	public void setCode(String obj)
	{
		code=obj;
	}
	public void setOutput(String obj)
	{
		output=obj;
	}
	public void setInput(String obj)
	{
		input=obj;
	}
}
