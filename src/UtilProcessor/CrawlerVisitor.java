package UtilProcessor;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class CrawlerVisitor {

	public CrawlerVisitor() {
		// TODO Auto-generated constructor stub
	}
	
	public Response crawlOnResponse(String webURL) throws IOException {

		// Connect to web URL, following Robot Protocol 
		
		Response response= Jsoup.connect(webURL)
		           .ignoreContentType(true)
		           .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")  
		           .referrer("http://www.google.com")   
		           .timeout(12000) 
		           .followRedirects(true)
		           .execute();
		
		return response;		
	}

}
