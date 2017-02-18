package main;
public class Main {

	public static void main(String[] args) {
		//send true as parameter Crawler(true) for verbose version.
		Crawler crawler = new Crawler();
	
		crawler.crawl("http://batuhanyaman.com");
	}

}
