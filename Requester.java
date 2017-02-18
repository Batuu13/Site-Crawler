package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class Requester {
	
	private String domain;
	private boolean isVerbose;

	public Requester(String initial, boolean isVerbose) {
		this.domain = initial;
		this.isVerbose = isVerbose;
	}

	public String getContent(String link)
	{
		URL url;
		URLConnection connection = null;
		String content = null;
		
		if(!link.startsWith("http")) // if link does not start with http add base domain.
		{
			if(!link.startsWith("/")) // also add / before adding domain if it is only page name.
			{
				link = "/" + link;
			}
			link = domain + link;
		}
		
		try {
			url = new URL(link);
			
			connection = url.openConnection();
		
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        content = in.lines().collect(Collectors.joining()).replace("\n","") // removing all newlines, tabs and spaces.
												       		  .replace("\t","")
												       		  .replace(" ","");
			in.close();
		}
		catch (IOException e) 
		{
			if(isVerbose)
				System.out.println("Error: " + e.getClass().getName() + " URL: " + link);
		}
		return content;
	}
	
}
