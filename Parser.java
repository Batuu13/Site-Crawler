package main;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Parser {
	/*
	 * Parses a page content and collects all links and its assets and returns Page object;
	 */
	
	final String URL_PATTERN = "(?:src|href)=[\"']+[/]{0,1}([^#][^\"'\\s]*)[\"']+";
	
	public Page parse(String url,String content)
	{
		
		Page page = new Page(url);
		if(content != null)
		{	 
			
			Pattern r = Pattern.compile(URL_PATTERN,Pattern.CASE_INSENSITIVE);
		     Matcher m = r.matcher(content);
		     while(m.find())
		     { 
		    	String str = m.group(1);
		    	
		    	// if there is a question mark, we are cutting it out.
		    	int i = str.lastIndexOf("?"); 
				if(i != -1)
					str = str.substring(0,i);
				// if there is a #, we are cutting it out.
				i = str.lastIndexOf("#"); 
				if(i != -1)
					str = str.substring(0,i);
				// if there is a / at the end, we are cutting it out.
				if(str.charAt(str.length()-1) == '/')
					str = str.substring(0,str.length()-1);
				
				
				
		    	String typeStr = str.substring(str.lastIndexOf(".") + 1,str.length()); // we are getting the extension.
		    	
		    	
		    	Type t = getType(typeStr);
		    	if(t == Type.URL)
		    	{	
		    		page.getConnections().add(str);
		    	}
		    	else
		    	{
		    		page.getAssests().add(new Link(str, t));
		    	}
		     }
		}
	     return page;
	}

	private Type getType(String type) {
		
		switch(type)
		{
		case "js":
			return Type.JS;
		case "css":
			return Type.CSS;
		case "jpeg":
			return Type.JPEG;
		case "jpg":
			return Type.JPG;
		case "png":
			return Type.PNG;
		case "gif":
			return Type.GIF;
		default:
			return Type.URL;
		}
		
	}
	
	
}
