package main;

public class Link {

	
	private String url;
	private Type type;
	
	public Link(String url,Type type)
	{
		this.url = url;
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	
}
