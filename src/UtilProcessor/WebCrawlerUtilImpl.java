package UtilProcessor;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Connection.Response;

import InputProcessor.InputQueryProcessor;

public class WebCrawlerUtilImpl implements WebCrawlerUtil {

	PageParser parser;
	
	public WebCrawlerUtilImpl() {
		// TODO Auto-generated constructor stub
		parser = new PageParser();
	}

	@Override
	public void crawlWebURLToGivenDepth(InputQueryProcessor input) throws IOException {
		
		CrawlerVisitor crawler = new CrawlerVisitor();
		try {
			Response response = crawler.crawlOnResponse(input.GetInputModel().GetURLString());
			parser.parseContenton(response);
		} catch (Exception e) {
			System.out.println("Error!! Could not find page response for the given input");
		}        
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
	

}
