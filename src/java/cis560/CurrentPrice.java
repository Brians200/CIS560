package cis560;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CurrentPrice {
	
	static String getCurrentPrice(String symbol)
	{
		String nextLine;
		URL url = null;
		URLConnection urlConnection = null;
		InputStreamReader inputStream = null;
		BufferedReader bufferedReader = null;
		
		try{
			url = new URL("http://finance.yahoo.com/d/quotes.csv?s="+symbol+"&f=l1");
			urlConnection = url.openConnection();
			inputStream = new InputStreamReader(urlConnection.getInputStream());
			bufferedReader = new BufferedReader(inputStream);
			
			
			nextLine = bufferedReader.readLine();
			if(nextLine !=null)
			{
				return nextLine;
			}
			else
			{
				return "";
			}
		}
		catch(MalformedURLException e)
		{
			System.out.println("BAD URL");
			System.out.println(e);
		}
		catch(IOException e)
		{
			System.out.println("THERE IS NO DATA");
			System.out.println(e);
		}
		return "";
	}
}
