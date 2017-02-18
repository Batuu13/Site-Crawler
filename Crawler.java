package main;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class Crawler {

	HashMap<String,Page> visited;
	Queue<String> linkQueue;
	HashSet<String> ignored; // for keeping passed links. To see external links.
	String initialURL;
	private Requester requester;
	private Parser parser;
	private boolean isVerbose;
	public Crawler()
	{
		this(false);
	}
	public Crawler(boolean verbose)
	{
		isVerbose = verbose;
		
		visited = new HashMap<>();
		linkQueue = new LinkedList<>();
		ignored = new HashSet<>();

		parser = new Parser();
		
	}
	public void crawl(String initial)
	{
		requester = new Requester(initial,isVerbose);
		
		initialURL = initial;
		linkQueue.add(initial);
		while(!linkQueue.isEmpty())
		{
			String url = linkQueue.poll();
			if(!visited.containsKey(url))
			{
				if(filter(url))
				{
					if(isVerbose)
						System.out.println("Added: " + url);
					else
						System.out.println("Found Count:" + visited.size());
					
					String content = requester.getContent(url);
					Page page = parser.parse(url, content);
					linkQueue.addAll(page.getConnections());
					visited.put(url, page);
				}
				else
					ignored.add(url);
			}
			
		}
		listPages();
	}
	private void listPages()
	{
		Collection<Page> list =  visited.values();
		Output o = new Output();
		o.createSiteMap(list);
		
		if(isVerbose)
			for(String item: ignored){
				System.out.println(item);
			}
		
	}
	private boolean filter(String url) {
		// /about	http://www.external.com www.tomblomfield.com/about
		//tomblomfield.com	 www.tomblomfield.com	http://tomblomfield.com
		String domain = initialURL.substring(initialURL.indexOf("w")+4);
		if(url.contains("http") || url.contains("https"))
		{
			if(url.contains(domain))
			{
				return true;
			}
			return false;
		}
		if(url.length() > 2)
			return true;
		return false;
	}
	
}
