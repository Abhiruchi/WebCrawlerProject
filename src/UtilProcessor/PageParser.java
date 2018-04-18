package UtilProcessor;


import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import UtilProcessor.model.PageParserInfoModel;

public class PageParser {

	private PageParserInfoModel parserInfoModel;
	 
	public PageParser() {
		// TODO Auto-generated constructor stub
		parserInfoModel = new PageParserInfoModel();
	}

	void parseRequiredContenton(Response response) throws IOException {
		Document webDoc = response.parse();
		
		parserInfoModel.SetPageURL(webDoc.baseUri());
		
		// set the page title
		parserInfoModel.SetPageTitle(webDoc.title());
	      	      
	    // get all links in page
	    Elements links = webDoc.select("a[href]");
	    
	    HashSet<String> directedLinksHashSet = new HashSet<String>();
	    
        for (Element link : links) {
        	directedLinksHashSet.add(link.attr("abs:href"));
        }
        
        parserInfoModel.SetLinkedPages(directedLinksHashSet);
        
	}
	
	PageParserInfoModel GetParserInfoModel() {
		return parserInfoModel;
	}
	
	
}
