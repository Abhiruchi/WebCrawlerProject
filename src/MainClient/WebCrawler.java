package MainClient;
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
		
		WebCrawlerUtil crawlerOperation = new WebCrawlerUtilImpl();
		crawlerOperation.crawlWebURLToGivenDepth(input);
	
		OutputResultsProcessor output = new OutputResultsProcessorImpl();
		output.handleOutput(crawlerOperation.getParserDataTitle(), crawlerOperation.getParserHashSet());
		
	}

}