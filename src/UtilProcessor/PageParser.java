package UtilProcessor;


import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageParser {

	public PageParser() {
		// TODO Auto-generated constructor stub
	}

	void parseContenton(Response response) throws IOException {
		Document webDoc = response.parse();
		
		// get the page title
	    String title = webDoc.title();
	      
		System.out.println("Title: " + title);
	      
	    // get all links in page
	    Elements links = webDoc.select("a[href]");
	   
        for (Element link : links) {
        	print(" %s", link.attr("abs:href"));
        }
        
		System.out.println("Done");

        
	}
	
	private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
	
	private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
	
}
