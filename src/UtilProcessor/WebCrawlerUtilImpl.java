package UtilProcessor;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Connection.Response;

public class WebCrawlerUtilImpl implements WebCrawlerUtil {

	PageParser parser;
	
	public WebCrawlerUtilImpl() {
		// TODO Auto-generated constructor stub
		parser = new PageParser();
	}

	@Override
	public void crawlWebURLToGivenDepth(String webURL, int depth) throws IOException {
		
		CrawlerVisitor crawler = new CrawlerVisitor();
		Response response = crawler.crawlOnResponse(webURL);
		
		parser.parseContenton(response);
		        
	}

	@Override
	public String getParserDataTitle() {
		// TODO Auto-generated method stub
		return parser.getPageTitle();
	}
	
	@Override
	public HashSet<String> getParserHashSet() {
		// TODO Auto-generated method stub
		return parser.getHashSet();
	}

	@Override
	public HashSet<String> getData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
