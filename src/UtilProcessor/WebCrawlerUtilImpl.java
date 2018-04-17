package UtilProcessor;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawlerUtilImpl implements WebCrawlerUtil {

	public WebCrawlerUtilImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crawlWebURLToGivenDepth(String webURL, int depth) throws IOException {
		
		CrawlerVisitor crawler = new CrawlerVisitor();
		Response response = crawler.crawlOnResponse(webURL);
		
		PageParser parser = new PageParser();
		parser.parseContenton(response);
		
        
	}

}
