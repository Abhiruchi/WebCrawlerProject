import java.io.IOException;

import InputProcessor.*;
import OutputProcessor.*;
import UtilProcessor.*;

public class WebCrawler {

	
	public WebCrawler() {
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		InputQueryProcessor input = new InputQueryProcessorImpl();
		input.handleInput();
		
		System.out.println(input.GetURLString());
		
		WebCrawlerUtil crawlerOperation = new WebCrawlerUtilImpl();
		crawlerOperation.crawlWebURLToGivenDepth(input.GetURLString(), input.GetDepth());
	
		OutputResultsProcessor output = new OutputResultsProcessorImpl();
		output.handleOutput(crawlerOperation.getParserDataTitle(), crawlerOperation.getParserHashSet());
		
	}

}
