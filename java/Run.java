package gui;

import java.io.*;


public class Run
{	
	public static void main(String args[])
	{
		try
		{
			ProcessBuilder pb = new ProcessBuilder("java","C:\\code\\java\\Gui");
			Process p = pb.start();

			System.out.println("Process:"+p);
		/*	BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String ret = in.readLine();
			System.out.println("value is : "+ret);
		*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}