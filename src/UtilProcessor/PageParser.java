package UtilProcessor;


import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageParser {

	HashSet<String> hashSet;
	String mainPageTitle;
	
	public PageParser() {
		// TODO Auto-generated constructor stub
		hashSet = new HashSet<String>();
		mainPageTitle = "";
	}

	void parseContenton(Response response) throws IOException {
		Document webDoc = response.parse();
		
		// set the page title
		mainPageTitle = webDoc.title();
	      	      
	    // get all links in page
	    Elements links = webDoc.select("a[href]");
	   
        for (Element link : links) {
        	hashSet.add(link.attr("abs:href"));

        }
        
	}
	
	HashSet<String> getHashSet() {
		return hashSet;
	}
	
	String getPageTitle() {
		return mainPageTitle;
	}
	
}
