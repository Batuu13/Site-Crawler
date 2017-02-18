package main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Page {
	
	private String url;
	private HashSet<String> connections; 
	private List<Link> assests; // javascript, css files.
	
	public Page(String url)
	{
		assests = new ArrayList<>();
		connections = new HashSet<>();
		this.url = url;
	}

	public HashSet<String> getConnections() {
		return connections;
	}

	public void setConnections(HashSet<String> connections) {
		this.connections = connections;
	}

	public List<Link> getAssests() {
		return assests;
	}

	public void setAssests(List<Link> assests) {
		this.assests = assests;
	}

	public String getUrl() {
		return url;
	}

	
	
	
}
