package UtilProcessor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import InputProcessor.InputQueryProcessor;

public interface WebCrawlerUtil {
	
	void crawlWebURLToGivenDepth(InputQueryProcessor input) throws IOException, URISyntaxException;

	HashMap<String, String> getParsedDataOutput();

}
