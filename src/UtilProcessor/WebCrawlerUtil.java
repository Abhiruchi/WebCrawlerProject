package UtilProcessor;

import java.io.IOException;

public interface WebCrawlerUtil {
	
	void crawlWebURLToGivenDepth(String webURL, int depth) throws IOException;

}
