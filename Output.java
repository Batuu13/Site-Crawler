package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

public class Output {

	FileOutputStream out = null;
	
	public void createSiteMap(Collection<Page> list)
	{
		try {
			out = new FileOutputStream("site-map.html");
			
			for(Page p: list)
			{
				write("<h3><b><a href=\""+p.getUrl()+"\">" + p.getUrl()+ "</a></b></h3><br>");
				
				write("\t <b> Assets </b><br>");
				write("<ul>");
				for(Link item: p.getAssests()){
					write("\t\t<li><a href=\""+item.getUrl()+"\">" + item.getUrl() + "</a> (" + item.getType() + ")</li>");
				}
				write("</ul>");
				
				write("\t<b> Connections </b><br>");
				write("<ul>");
				for(String item: p.getConnections()){
					write("\t\t<li><a href=\""+item+"\">" + item + "</a></li>");
				}
				write("</ul>");
			}
			out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	private void write(String s)
	{
	    try {
			out.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}	 
	}
	
}
