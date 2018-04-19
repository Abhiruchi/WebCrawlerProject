package MainClient;
import java.io.IOException;
import java.net.URISyntaxException;

import InputProcessor.*;
import OutputProcessor.*;
import UtilProcessor.*;

public class WebCrawler {

	
	public WebCrawler() {
		// TODO Auto-generated constructor stub	
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		
		InputQueryProcessor input = new InputQueryProcessorImpl();
		input.handleInput();
		
		WebCrawlerUtil crawlerOperationImpl = new WebCrawlerUtilImpl();
		crawlerOperationImpl.crawlWebURLToGivenDepth(input);
	
		OutputResultsProcessor output = new OutputResultsProcessorImpl();
		output.handleOutput(crawlerOperationImpl);
		
	}

}
